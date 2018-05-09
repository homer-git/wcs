package cn.bdc.wcs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.bdc.wcs.bean.TBdcPreparationOrder;
import cn.bdc.wcs.bean.TBdcStatusFeedback;
import cn.bdc.wcs.service.TBdcStatusFeedbackService;


@RestController
@RequestMapping("/status")
public class TBdcStatusFeedbackController {

	@Resource
	private TBdcStatusFeedbackService tBdcStatusFeedbackService;
	
    /** 
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解 
     *  
     */  
    @RequestMapping(value = "/feedback/save", method = { RequestMethod.POST, RequestMethod.GET })  
    public Map<String, String> saveFeedback(@RequestBody TBdcStatusFeedback tBdcStatusFeedback) {  
    	Map<String, String> returnMap = new HashMap<String, String>();
    	
    	tBdcStatusFeedbackService.save(tBdcStatusFeedback);
    	returnMap.put("statusFeedbackId", String.valueOf(tBdcStatusFeedback.getStatusFeedbackId()));
    	
    	return returnMap;
    } 
	
	@RequestMapping("/feedback/delete")
	public String deleteFeedback(int id) {
		tBdcStatusFeedbackService.delete(id);
		return "delete success.";
	}

	@RequestMapping("/feedback/getAll")
	public Iterable<TBdcStatusFeedback> getAllFeedback(){
		return tBdcStatusFeedbackService.getAll();
	}

	@RequestMapping("/feedback/getAllById")
	public Iterable<TBdcStatusFeedback> getAllByIdFeedback(Iterable<Integer> ids){
		return tBdcStatusFeedbackService.getAllById(ids);
	}

	@RequestMapping("/feedback/getById")
	public Optional<TBdcStatusFeedback> getByIdFeedback(Integer id){
		return tBdcStatusFeedbackService.getById(id);
	}
	

	//TEST/////////
	
	@RequestMapping("/feedback/saveTest")
	public Map<String, String> saveFeedbackTest() {
    	Map<String, String> returnMap = new HashMap<String, String>();
    	
		TBdcPreparationOrder tBdcPreparationOrder = new TBdcPreparationOrder();
		tBdcPreparationOrder.setPreparationOrderId(26);
		
		TBdcStatusFeedback tBdcStatusFeedback = new TBdcStatusFeedback();
		tBdcStatusFeedback.setLargeEmergencyUnits("TestUnits");
		tBdcStatusFeedback.setPreparationOrderId(tBdcPreparationOrder);
		tBdcStatusFeedback.setVehiclePatrolsHumans(10);
		tBdcStatusFeedback.setVehiclePatrolsUnits(2);
		tBdcStatusFeedback.setStatus(1);
		tBdcStatusFeedback.setCrateDate(new Date());
		tBdcStatusFeedback.setCreatedBy("-1");
		tBdcStatusFeedback.setCreatedUnit("-1");
		
		tBdcStatusFeedbackService.save(tBdcStatusFeedback);
    	returnMap.put("statusFeedbackId", String.valueOf(tBdcStatusFeedback.getStatusFeedbackId()));
		
		return returnMap;
	}
}
