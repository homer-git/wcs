package cn.bdc.wcs.service;

import java.util.Calendar;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.bdc.wcs.bean.TBdcPreparationOrder;
import cn.bdc.wcs.common.service.BaseService;
import cn.bdc.wcs.repository.TBdcPreparationOrderRepository;

@Service
public class TBdcPreparationOrderService extends BaseService {
	
	@Resource
	private TBdcPreparationOrderRepository tBdcPreparationOrderRepository;
	
	@Transactional
	public void save(TBdcPreparationOrder tBdcPreparationOrder) {
		tBdcPreparationOrderRepository.save(tBdcPreparationOrder);
	}

	@Transactional
	public void delete(int id) {
		tBdcPreparationOrderRepository.deleteById(id);
	}
	
	public Iterable<TBdcPreparationOrder> getAll() {
		return tBdcPreparationOrderRepository.findAllByOrderByCrateDateDesc();
	}

	public Iterable<TBdcPreparationOrder> getAllById(Iterable<Integer> ids){
		return tBdcPreparationOrderRepository.findAllById(ids);
	}

	public Optional<TBdcPreparationOrder> getById(int id){
		return tBdcPreparationOrderRepository.findById(id);
	}

	public Optional<TBdcPreparationOrder> getByPreparationTitle(String preparationTitle){
		return tBdcPreparationOrderRepository.findByPreparationTitle(preparationTitle);
	}

	public Iterable<TBdcPreparationOrder> getByPreparationTitleLike(String preparationTitle){
		return tBdcPreparationOrderRepository.findByPreparationTitleLike(preparationTitle);
	}	
	
	@Transactional
	public int getNextPreparationVersionSeqByYear(int year) {

		int sequence = 1;
		
//		Query query = em.createQuery("select p from Person p where p.personid=?1 ");
//		query.setParameter(1,new Integer(1));
//		Query query = em.createQuery("SELECT max(SUBSTRING_INDEX(substr(preparationVersion,INSTR(preparationVersion,'第') + 1),'期',1)) FROM TBdcPreparationOrder WHERE substr(preparationVersion,2,4) = :Year");
		Query query = em.createQuery("SELECT max(preparationVersionSeq) FROM TBdcPreparationOrder WHERE preparationVersionYear = :Year");
		query.setParameter("Year",year); 
		Object result = query.getSingleResult();
		if(!StringUtils.isEmpty(result)) {
			String maxPreparationVersionSeq = result.toString();
			//sequence = Integer.parseInt(maxPreparationVersion.substring(maxPreparationVersion.indexOf("第") + 1, maxPreparationVersion.indexOf("期")));
			sequence = Integer.parseInt(maxPreparationVersionSeq);
			sequence++;			
		}
		return sequence;
	}	
	
	@Transactional
	public String getNextPreparationVersion() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int nextPreparationVersionSeq = getNextPreparationVersionSeqByYear(year);

		String nextPreparationVersion = "[" + year + "年]第" + nextPreparationVersionSeq + "期";
		return nextPreparationVersion;
	}
	
	@Transactional
	public int getNextPreparationVersionSeqByCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int nextPreparationVersionSeq = getNextPreparationVersionSeqByYear(year);
		return nextPreparationVersionSeq;
	}


}
