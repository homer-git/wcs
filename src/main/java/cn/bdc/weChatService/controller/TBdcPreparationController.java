package cn.bdc.weChatService.controller;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.bdc.weChatService.bean.TBdcPreparationNotice;
import cn.bdc.weChatService.bean.TBdcPreparationOrder;
import cn.bdc.weChatService.service.TBdcPreparationNoticeService;
import cn.bdc.weChatService.service.TBdcPreparationOrderService;


@RestController
@RequestMapping("/preparation")
public class TBdcPreparationController {
	
	//防汛备勤令
	@Resource
	private TBdcPreparationOrderService tBdcPreparationOrderService;
	
    /** 
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解 
     *  
     */  
    @RequestMapping(value = "/order/save", method = { RequestMethod.POST, RequestMethod.GET })  
    public String saveOrder(@RequestBody TBdcPreparationOrder tBdcPreparationOrder) {  
    	tBdcPreparationOrderService.save(tBdcPreparationOrder);
    	
    	return "save ok.";
    } 
	
	@RequestMapping("/order/delete")
	public String deleteOrder(int id) {
		tBdcPreparationOrderService.delete(id);
		return "delete ok.";
	}

	@RequestMapping("/order/getAll")
	public Iterable<TBdcPreparationOrder> getAllOrder(){
		return tBdcPreparationOrderService.getAll();
	}

	@RequestMapping("/order/getAllById")
	public Iterable<TBdcPreparationOrder> getAllByIdOrder(Iterable<Integer> id){
		return tBdcPreparationOrderService.getAllById(id);
	}

	@RequestMapping("/order/getById")
	public Optional<TBdcPreparationOrder> getByIdOrder(Integer id){
		return tBdcPreparationOrderService.getById(id);
	}

	@RequestMapping("/order/getByPreparationTitle")
	public Optional<TBdcPreparationOrder> getByPreparationTitleOrder(String preparationTitle){
		return tBdcPreparationOrderService.getByPreparationTitle(preparationTitle);
	}

	@RequestMapping("/order/getByPreparationTitleLike")
	public Iterable<TBdcPreparationOrder> getByPreparationTitleOrderLike(String preparationTitle){
		String lPreparationTitle = preparationTitle + "%";
		return tBdcPreparationOrderService.getByPreparationTitleLike(lPreparationTitle);
	} 
	
	
	//通知
	@Resource
	private TBdcPreparationNoticeService tBdcPreparationNoticeService;

	
    /** 
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解 
     *  
     */  
    @RequestMapping(value = "/notice/save", method = { RequestMethod.POST, RequestMethod.GET })  
    public String saveNotice(@RequestBody TBdcPreparationNotice tBdcPreparationNotice) {  
    	tBdcPreparationNoticeService.save(tBdcPreparationNotice);
    	
    	return "save ok.";
    }	
	
	@RequestMapping("/notice/delete")
	public String deleteNotice(int id) {
		tBdcPreparationNoticeService.delete(id);
		return "delete ok.";
	}

	@RequestMapping("/notice/getAll")
	public Iterable<TBdcPreparationNotice> getAllNotice(){
		return tBdcPreparationNoticeService.getAll();
	}

	@RequestMapping("/notice/getAllById")
	public Iterable<TBdcPreparationNotice> getAllByIdNotice(Iterable<Integer> id){
		return tBdcPreparationNoticeService.getAllById(id);
	}

	@RequestMapping("/notice/getById")
	public Optional<TBdcPreparationNotice> getByIdNotice(Integer id){
		return tBdcPreparationNoticeService.getById(id);
	}

	@RequestMapping("/notice/getByPreparationTitle")
	public Optional<TBdcPreparationNotice> getByPreparationTitleNotice(String preparationTitle){
		return tBdcPreparationNoticeService.getByPreparationTitle(preparationTitle);
	}

	@RequestMapping("/notice/getByPreparationTitleLike")
	public Iterable<TBdcPreparationNotice> getByPreparationTitleNoticeLike(String preparationTitle){
		String lPreparationTitle = preparationTitle + "%";
		return tBdcPreparationNoticeService.getByPreparationTitleLike(lPreparationTitle);
	} 
	
	
	
	
	//TEST/////////

	@RequestMapping("/order/saveTest")
	public String saveOrderTest() {
		TBdcPreparationOrder tBdcPreparationOrder = new TBdcPreparationOrder();
		tBdcPreparationOrder.setPreparationTitle("Tile Preparation Order");
		tBdcPreparationOrder.setPreparationVersion("1.0");
		tBdcPreparationOrder.setPreparationContent("Content Preparation Order");
		tBdcPreparationOrder.setPublishDate(new Date());
		tBdcPreparationOrder.setCrateDate(new Date());
		tBdcPreparationOrder.setCreatedBy("-1");
		tBdcPreparationOrder.setCreatedUnit("-1");
		
		tBdcPreparationOrderService.save(tBdcPreparationOrder);
		
		return "save ok.";
	}
	
	@RequestMapping("/notice/saveTest")
	public String saveNoticeTest() {
		TBdcPreparationNotice tBdcPreparationNotice = new TBdcPreparationNotice();
		tBdcPreparationNotice.setPreparationTitle("Tile Preparation Notice");
		tBdcPreparationNotice.setPreparationContent("Content Preparation Notice");
		tBdcPreparationNotice.setPublishDate(new Date());
		tBdcPreparationNotice.setCrateDate(new Date());
		tBdcPreparationNotice.setCreatedBy("-1");
		tBdcPreparationNotice.setCreatedUnit("-1");
		
		tBdcPreparationNoticeService.save(tBdcPreparationNotice);
		
		return "save ok.";
	}

}
