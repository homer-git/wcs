package cn.bdc.wcs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.bdc.wcs.bean.TBdcPreparationNotice;
import cn.bdc.wcs.bean.TBdcPreparationOrder;
import cn.bdc.wcs.common.service.WeChatQyAPIService;
import cn.bdc.wcs.service.TBdcPreparationNoticeService;
import cn.bdc.wcs.service.TBdcPreparationOrderService;

@CrossOrigin(origins = "*", maxAge = 3600) 
@RestController
@RequestMapping("/preparation")
public class TBdcPreparationController {
	
	@Value("${wcs.wxqy.corpid}")
	private String corpID;
	
	@Value("${wcs.wxqy.secret}")
	private String secret;

	@Value("${wcs.wxqy.agentid}")
    private String agentId;
	
	@Value("${wcs.wxqy.sendmsg2deptid}")
	private String sendMsg2DeptId;
	
	//防汛备勤令
	@Resource
	private TBdcPreparationOrderService tBdcPreparationOrderService;
	
    /** 
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解 
     *  
     */  
    @RequestMapping(value = "/order/save", method = { RequestMethod.POST, RequestMethod.GET })  
    public Map<String, String> saveOrder(@RequestBody TBdcPreparationOrder tBdcPreparationOrder) { 
    	Map<String, String> returnMap = new HashMap<String, String>();
    	if(null == tBdcPreparationOrder.getPreparationVersion()) {
    		tBdcPreparationOrder.setPreparationVersion(tBdcPreparationOrderService.getNextPreparationVersion());
    	}
		tBdcPreparationOrder.setPublishDate(new Date());
		tBdcPreparationOrder.setCrateDate(new Date());
		tBdcPreparationOrder.setCreatedBy("-1");
		tBdcPreparationOrder.setCreatedUnit("-1");
    	tBdcPreparationOrderService.save(tBdcPreparationOrder);
    	
    	StringBuffer msgSb = new StringBuffer();
    	msgSb.append("备勤令：防汛备勤通知【");
    	msgSb.append(tBdcPreparationOrder.getPreparationTitle());
    	msgSb.append("】，请及时响应。");
    	
    	WeChatQyAPIService weChatQyAPIService = new WeChatQyAPIService();
    	weChatQyAPIService.setCorpID(corpID);
    	weChatQyAPIService.setSecret(secret);
    	weChatQyAPIService.setAgentId(agentId);
    	weChatQyAPIService.sendWeChatMsg("text", "", sendMsg2DeptId, "", msgSb.toString(), "", "","", "","", "0");  
    	
    	returnMap.put("preparationOrderId", String.valueOf(tBdcPreparationOrder.getPreparationOrderId()));
    	
    	return returnMap;
    } 
	
	@RequestMapping(value = "/order/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteOrder(int id) {
		tBdcPreparationOrderService.delete(id);
		return "delete success.";
	}

	@RequestMapping(value = "/order/getAll", method = { RequestMethod.GET })
	public Iterable<TBdcPreparationOrder> getAllOrder(){
		return tBdcPreparationOrderService.getAll();
	}

	@RequestMapping(value = "/order/getAllById", method = { RequestMethod.GET })
	public Iterable<TBdcPreparationOrder> getAllByIdOrder(Iterable<Integer> ids){
		return tBdcPreparationOrderService.getAllById(ids);
	}

	@RequestMapping(value = "/order/getById", method = { RequestMethod.GET })
	public Optional<TBdcPreparationOrder> getByIdOrder(Integer id){
		return tBdcPreparationOrderService.getById(id);
	}

	@RequestMapping(value = "/order/getByPreparationTitle", method = { RequestMethod.GET })
	public Optional<TBdcPreparationOrder> getByPreparationTitleOrder(String preparationTitle){
		return tBdcPreparationOrderService.getByPreparationTitle(preparationTitle);
	}

	@RequestMapping(value = "/order/getByPreparationTitleLike", method = { RequestMethod.GET })
	public Iterable<TBdcPreparationOrder> getByPreparationTitleOrderLike(String preparationTitle){
		String lPreparationTitle = preparationTitle + "%";
		return tBdcPreparationOrderService.getByPreparationTitleLike(lPreparationTitle);
	} 

	@RequestMapping(value = "/order/getNextPreparationVersion", method = { RequestMethod.GET })
	public String getNextPreparationVersion(){
		return tBdcPreparationOrderService.getNextPreparationVersion();
	} 
	
	
	//通知
	@Resource
	private TBdcPreparationNoticeService tBdcPreparationNoticeService;

	
    /** 
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解 
     *  
     */  
    @RequestMapping(value = "/notice/save", method = { RequestMethod.POST, RequestMethod.GET })  
    public Map<String, String> saveNotice(@RequestBody TBdcPreparationNotice tBdcPreparationNotice) { 
    	Map<String, String> returnMap = new HashMap<String, String>();

		tBdcPreparationNotice.setPublishDate(new Date());
		tBdcPreparationNotice.setCrateDate(new Date());
		tBdcPreparationNotice.setCreatedBy("-1");
		tBdcPreparationNotice.setCreatedUnit("-1");
    	tBdcPreparationNoticeService.save(tBdcPreparationNotice);    	
    	returnMap.put("preparationNoticeId", String.valueOf(tBdcPreparationNotice.getPreparationNoticeId()));
    	
    	return returnMap;
    }	
	
	@RequestMapping(value = "/notice/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteNotice(int id) {
		tBdcPreparationNoticeService.delete(id);
		return "delete success.";
	}

	@RequestMapping(value = "/notice/getAll", method = { RequestMethod.GET })
	public Iterable<TBdcPreparationNotice> getAllNotice(){
		return tBdcPreparationNoticeService.getAll();
	}

	@RequestMapping(value = "/notice/getAllById", method = { RequestMethod.GET })
	public Iterable<TBdcPreparationNotice> getAllByIdNotice(Iterable<Integer> ids){
		return tBdcPreparationNoticeService.getAllById(ids);
	}

	@RequestMapping(value = "/notice/getById", method = { RequestMethod.GET })
	public Optional<TBdcPreparationNotice> getByIdNotice(Integer id){
		return tBdcPreparationNoticeService.getById(id);
	}

	@RequestMapping(value = "/notice/getByPreparationTitle", method = { RequestMethod.GET })
	public Optional<TBdcPreparationNotice> getByPreparationTitleNotice(String preparationTitle){
		return tBdcPreparationNoticeService.getByPreparationTitle(preparationTitle);
	}

	@RequestMapping(value = "/notice/getByPreparationTitleLike", method = { RequestMethod.GET })
	public Iterable<TBdcPreparationNotice> getByPreparationTitleNoticeLike(String preparationTitle){
		String lPreparationTitle = preparationTitle + "%";
		return tBdcPreparationNoticeService.getByPreparationTitleLike(lPreparationTitle);
	} 
	
	
	
	
	//TEST/////////

	@RequestMapping(value = "/order/saveTest", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, String> saveOrderTest() {
    	Map<String, String> returnMap = new HashMap<String, String>();
    	
		TBdcPreparationOrder tBdcPreparationOrder = new TBdcPreparationOrder();
		tBdcPreparationOrder.setPreparationTitle("Tile Preparation Order 中文测试");
		tBdcPreparationOrder.setPreparationVersion(tBdcPreparationOrderService.getNextPreparationVersion());
		tBdcPreparationOrder.setPreparationContent("Content Preparation Order 中文测试");
		tBdcPreparationOrder.setPublishDate(new Date());
		tBdcPreparationOrder.setCrateDate(new Date());
		tBdcPreparationOrder.setCreatedBy("-1");
		tBdcPreparationOrder.setCreatedUnit("-1");
		
		tBdcPreparationOrderService.save(tBdcPreparationOrder);
		returnMap.put("preparationOrderId", String.valueOf(tBdcPreparationOrder.getPreparationOrderId()));
		
		return returnMap;
	}
	
	@RequestMapping(value = "/notice/saveTest", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, String> saveNoticeTest() {
    	Map<String, String> returnMap = new HashMap<String, String>();
    	
		TBdcPreparationNotice tBdcPreparationNotice = new TBdcPreparationNotice();
		tBdcPreparationNotice.setPreparationTitle("Tile Preparation Notice 中文测试");
		tBdcPreparationNotice.setPreparationContent("Content Preparation Notice 中文测试");
		tBdcPreparationNotice.setPublishDate(new Date());
		tBdcPreparationNotice.setCrateDate(new Date());
		tBdcPreparationNotice.setCreatedBy("-1");
		tBdcPreparationNotice.setCreatedUnit("-1");
		
		tBdcPreparationNoticeService.save(tBdcPreparationNotice);
		returnMap.put("preparationNoticeId", String.valueOf(tBdcPreparationNotice.getPreparationNoticeId()));
		
		return returnMap;
	}

}
