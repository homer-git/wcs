package cn.bdc.wcs.repository;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcPonding;


public interface TBdcPondingRepository extends CrudRepository<TBdcPonding, Integer> {
	
	public Iterable<TBdcPonding> findAllByOrderByCrateDateDesc();
	
}
