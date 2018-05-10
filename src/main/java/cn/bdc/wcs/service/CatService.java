package cn.bdc.wcs.service;


import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.Cat;
import cn.bdc.wcs.repository.CatRepository;


@Service
public class CatService {
	
	@Resource
	private CatRepository catRepository;
	
	@Transactional
	public void save(Cat cat) {
		catRepository.save(cat);
	}

	@Transactional
	public void delete(int id) {
		catRepository.deleteById(id);
	}
	
	public Iterable<Cat> getAll() {
		return catRepository.findAll();
	}

}
