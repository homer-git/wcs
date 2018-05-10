package cn.bdc.wcs.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.bdc.wcs.bean.Cat;
import cn.bdc.wcs.service.CatService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cat")
public class CatController {

	@Resource
	private CatService catService;
	
	@RequestMapping("/save")
	public String save() {
		Cat cat = new Cat();
		cat.setCatName("Tom");
		cat.setCatAge(3);
		catService.save(cat);
		
		return "save ok.";
	}
	
	@RequestMapping("/delete")
	public String delete(int id) {
		catService.delete(id);
		return "delete ok.";
	}

	@RequestMapping("/getAll")
	public Iterable<Cat> getAll(){
		return catService.getAll();
	}

	@RequestMapping("/save2")
	public String save2() {
		Cat cat = new Cat();
		cat.setId(7);
		cat.setCatName("Tom_CAT");
		cat.setCatAge(30);
		catService.save(cat);
		
		return "save ok.";
	}
	
	

}
