package cn.bdc.wcs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcPreparationNotice;

public interface TBdcPreparationNoticeRepository extends CrudRepository<TBdcPreparationNotice, Integer> {
	
	public Optional<TBdcPreparationNotice> findByPreparationTitle(String preparationTitle);
	
	public Iterable<TBdcPreparationNotice> findByPreparationTitleLike(String preparationTitle);

}
