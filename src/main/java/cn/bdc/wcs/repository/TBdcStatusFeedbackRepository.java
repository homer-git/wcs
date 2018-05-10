package cn.bdc.wcs.repository;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcStatusFeedback;

public interface TBdcStatusFeedbackRepository extends CrudRepository<TBdcStatusFeedback, Integer> {
	
	public Iterable<TBdcStatusFeedback> findAllByPreparationOrderId(Iterable<Integer> preparationOrderIds);
	
}
