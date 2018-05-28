package cn.bdc.wcs.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.TBdcWeatherFiles;
import cn.bdc.wcs.common.service.BaseService;
import cn.bdc.wcs.repository.TBdcWeatherFilesRepository;

@Service
public class TBdcWeatherFilesService extends BaseService {
	
	@Resource
	private TBdcWeatherFilesRepository tBdcWeatherFilesRepository;
	
	@Transactional
	public void save(TBdcWeatherFiles tBdcWeatherFiles) {
		tBdcWeatherFilesRepository.save(tBdcWeatherFiles);
	}

	@Transactional
	public void delete(int id) {
		tBdcWeatherFilesRepository.deleteById(id);
	}
	
	public Iterable<TBdcWeatherFiles> getAll() {
		return tBdcWeatherFilesRepository.findAll();
	}


}
