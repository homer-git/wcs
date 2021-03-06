package cn.bdc.wcs.repository;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcWeatherFiles;

public interface TBdcWeatherFilesRepository extends CrudRepository<TBdcWeatherFiles, Integer> {
	
	public Iterable<TBdcWeatherFiles> findByFileTypeOrderByCrateDateDesc(String fileType);

	public Iterable<TBdcWeatherFiles> findByFileName(String fileName);
}
