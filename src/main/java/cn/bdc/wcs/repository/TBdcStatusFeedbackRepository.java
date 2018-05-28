package cn.bdc.wcs.repository;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcPreparationOrder;
import cn.bdc.wcs.bean.TBdcStatusFeedback;

public interface TBdcStatusFeedbackRepository extends CrudRepository<TBdcStatusFeedback, Integer> {
	
	public Iterable<TBdcStatusFeedback> findAllByOrderByCrateDateDesc();
	
	public Iterable<TBdcStatusFeedback> findAllByPreparationOrderId(TBdcPreparationOrder preparationOrderId);
	
	public Iterable<TBdcStatusFeedback> findAllByPreparationOrderIdAndCreatedSubUnit(TBdcPreparationOrder preparationOrderId, String createdSubUnit);
}
