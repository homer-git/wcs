package cn.bdc.wcs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcPreparationNotice;
import cn.bdc.wcs.bean.TBdcPreparationOrder;

public interface TBdcPreparationNoticeRepository extends CrudRepository<TBdcPreparationNotice, Integer> {
	
	public Iterable<TBdcPreparationNotice> findAllByOrderByCrateDateDesc();
	
	public Optional<TBdcPreparationNotice> findByPreparationTitle(String preparationTitle);
	
	public Iterable<TBdcPreparationNotice> findByPreparationTitleLike(String preparationTitle);

}
