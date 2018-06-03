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

import cn.bdc.wcs.bean.TBdcPonding;
import cn.bdc.wcs.service.TBdcPondingService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ponding")
public class TBdcPondingController {

	@Resource
	private TBdcPondingService tBdcPondingService;
	
    /** 
     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解 
     *  
     */  
    @RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })  
    public Map<String, String> savePonding(@RequestBody TBdcPonding tBdcPonding) {  
    	Map<String, String> returnMap = new HashMap<String, String>();

    	tBdcPonding.setCrateDate(new Date());
    	tBdcPonding.setCreatedBy("-1");
    	tBdcPonding.setCreatedUnit("-1");
    	tBdcPondingService.save(tBdcPonding);
    	returnMap.put("pondingId", String.valueOf(tBdcPonding.getPondingId()));
    	returnMap.put("sourceTable", "T_BDC_Ponding");
    	
    	return returnMap;
    } 
	
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE })
	public String deletePonding(int id) {
		tBdcPondingService.delete(id);
		return "delete success.";
	}

	@RequestMapping(value = "/getAll", method = { RequestMethod.GET })
	public Iterable<TBdcPonding> getAllPonding(){
		return tBdcPondingService.getAll();
	}

	@RequestMapping(value = "/getAllById", method = { RequestMethod.POST, RequestMethod.GET })
	public Iterable<TBdcPonding> getAllByIdPonding(Iterable<Integer> ids){
		return tBdcPondingService.getAllById(ids);
	}

	@RequestMapping(value = "/getById", method = { RequestMethod.POST, RequestMethod.GET })
	public Optional<TBdcPonding> getByIdPonding(Integer id){
		return tBdcPondingService.getById(id);
	}
	

	//TEST/////////
	
	@RequestMapping(value = "/saveTest", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, String> savePondingTest() {
    	Map<String, String> returnMap = new HashMap<String, String>();
    	
    	TBdcPonding tBdcPonding = new TBdcPonding();
		
    	tBdcPonding.setPondingPlace("积水地点测试");
    	tBdcPonding.setPondingArea(150);
    	tBdcPonding.setPondingDepth(1002);
    	tBdcPonding.setCrateDate(new Date());
    	tBdcPonding.setCreatedBy("-1");
    	tBdcPonding.setCreatedUnit("-1");
		
    	tBdcPondingService.save(tBdcPonding);
    	returnMap.put("pondingId", String.valueOf(tBdcPonding.getPondingId()));
    	returnMap.put("sourceTable", "T_BDC_Ponding");
		
		return returnMap;
	}
}
