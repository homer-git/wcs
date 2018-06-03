package cn.bdc.wcs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.bdc.wcs.bean.TBdcFloodPreventionBrochure;
import cn.bdc.wcs.service.TBdcFloodPreventionBrochureService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/floodprevention")
public class TBdcFloodPreventionBrochureController {

	@Resource
	private TBdcFloodPreventionBrochureService tBdcFloodPreventionBrochureService;
	
    /** 
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解 
     *  
     */  
    @RequestMapping(value = "/brochure/save", method = { RequestMethod.POST, RequestMethod.GET })  
    public Map<String, String> saveBrochure(@RequestBody TBdcFloodPreventionBrochure tBdcFloodPreventionBrochure) {  
    	Map<String, String> returnMap = new HashMap<String, String>();

    	tBdcFloodPreventionBrochure.setCrateDate(new Date());
    	tBdcFloodPreventionBrochure.setCreatedBy("-1");
    	tBdcFloodPreventionBrochure.setCreatedUnit("-1");
    	tBdcFloodPreventionBrochureService.save(tBdcFloodPreventionBrochure);
    	returnMap.put("floodPreventionId", String.valueOf(tBdcFloodPreventionBrochure.getFloodPreventionId()));
    	
    	return returnMap;
    } 
	
	@RequestMapping(value = "/brochure/delete", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE })
	public String deleteBrochure(int id) {
		tBdcFloodPreventionBrochureService.delete(id);
		return "delete success.";
	}

	@RequestMapping(value = "/brochure/getAll", method = { RequestMethod.GET })
	public Iterable<TBdcFloodPreventionBrochure> getAllBrochure(){
		return tBdcFloodPreventionBrochureService.getAll();
	}

	@RequestMapping(value = "/brochure/getAllById", method = { RequestMethod.POST, RequestMethod.GET })
	public Iterable<TBdcFloodPreventionBrochure> getAllByIdBrochure(Iterable<Integer> ids){
		return tBdcFloodPreventionBrochureService.getAllById(ids);
	}

	@RequestMapping(value = "/brochure/getById", method = { RequestMethod.POST, RequestMethod.GET })
	public Optional<TBdcFloodPreventionBrochure> getByIdBrochure(Integer id){
		return tBdcFloodPreventionBrochureService.getById(id);
	}
	

	//TEST/////////
	
	@RequestMapping(value = "/brochure/saveTest", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, String> saveBrochureTest() {
    	Map<String, String> returnMap = new HashMap<String, String>();
    	
    	TBdcFloodPreventionBrochure tBdcFloodPreventionBrochure = new TBdcFloodPreventionBrochure();
		
    	tBdcFloodPreventionBrochure.setFloodPreventionTitle("测试链接");
    	tBdcFloodPreventionBrochure.setFloodPreventionLink("https://www.ufangor.com:8443");
    	tBdcFloodPreventionBrochure.setCrateDate(new Date());
    	tBdcFloodPreventionBrochure.setCreatedBy("-1");
    	tBdcFloodPreventionBrochure.setCreatedUnit("-1");
		
    	tBdcFloodPreventionBrochureService.save(tBdcFloodPreventionBrochure);
    	returnMap.put("floodPreventionId", String.valueOf(tBdcFloodPreventionBrochure.getFloodPreventionId()));
		
		return returnMap;
	}
}
