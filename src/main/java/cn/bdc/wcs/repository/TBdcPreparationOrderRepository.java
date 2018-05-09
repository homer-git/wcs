package cn.bdc.wcs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcPreparationOrder;


public interface TBdcPreparationOrderRepository extends CrudRepository<TBdcPreparationOrder, Integer>{
	
	public Optional<TBdcPreparationOrder> findByPreparationTitle(String preparationTitle);
	
	public Iterable<TBdcPreparationOrder> findByPreparationTitleLike(String preparationTitle);

}
