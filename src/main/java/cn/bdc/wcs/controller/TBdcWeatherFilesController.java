package cn.bdc.wcs.controller;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.bdc.wcs.bean.TBdcWeatherFiles;
import cn.bdc.wcs.service.TBdcWeatherFilesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/weatherfile")
public class TBdcWeatherFilesController {

	@Resource
	private TBdcWeatherFilesService tBdcWeatherFilesService;

	@RequestMapping(value = "/getAll", method = { RequestMethod.GET })
	public Iterable<TBdcWeatherFiles> getAllWeatherFiles(){
		return tBdcWeatherFilesService.getAll();
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE })
	public String deleteWeatherFiles(Integer id){
		tBdcWeatherFilesService.delete(id);
		return "delete success.";
	}

	@RequestMapping(value = "/getById", method = { RequestMethod.POST, RequestMethod.GET })
	public Optional<TBdcWeatherFiles> getWeatherFilesById(Integer id){
		return tBdcWeatherFilesService.getById(id);
	}

	@RequestMapping(value = "/getByFileType", method = { RequestMethod.POST, RequestMethod.GET })
	public Iterable<TBdcWeatherFiles> getWeatherFilesByFileTypeOrderByCrateDateDesc(String fileType){
		return tBdcWeatherFilesService.getByFileTypeOrderByCrateDateDesc(fileType);
	}

	@RequestMapping(value = "/getByFileName", method = { RequestMethod.POST, RequestMethod.GET })
	public Iterable<TBdcWeatherFiles> getWeatherFilesByFileName(String fileName){
		return tBdcWeatherFilesService.getByFileName(fileName);
	}
}
