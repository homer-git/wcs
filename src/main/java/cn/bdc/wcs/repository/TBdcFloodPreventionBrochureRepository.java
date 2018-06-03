package cn.bdc.wcs.repository;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcFloodPreventionBrochure;


public interface TBdcFloodPreventionBrochureRepository extends CrudRepository<TBdcFloodPreventionBrochure, Integer> {
	
	public Iterable<TBdcFloodPreventionBrochure> findAllByOrderByCrateDateDesc();
	
}
