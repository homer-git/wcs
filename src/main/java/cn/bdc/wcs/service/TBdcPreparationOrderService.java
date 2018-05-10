package cn.bdc.wcs.service;

import java.util.Calendar;
import java.util.Optional;

import javax.annotation.Resource;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.TBdcPreparationOrder;
import cn.bdc.wcs.common.service.BaseService;
import cn.bdc.wcs.repository.TBdcPreparationOrderRepository;

import org.springframework.util.StringUtils;

@Service
public class TBdcPreparationOrderService extends BaseService {
	
//	@PersistenceContext
//	private EntityManager em;	
	
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
		return tBdcPreparationOrderRepository.findAll();
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
	public String getNextPreparationVersion() {
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		String nextPreparationVersion = "";
		int sequence = 1;
		
//		Query query = em.createQuery("select p from Person p where p.personid=?1 ");
//		query.setParameter(1,new Integer(1)); 		
		Query query = em.createQuery("SELECT max(preparationVersion) FROM TBdcPreparationOrder WHERE substr(preparationVersion,2,4) = :Year");
		query.setParameter("Year",year); 
		Object result = query.getSingleResult();
		if(!StringUtils.isEmpty(result)) {
			String maxPreparationVersion = result.toString();
			sequence = Integer.parseInt(maxPreparationVersion.substring(maxPreparationVersion.indexOf("第") + 1, maxPreparationVersion.indexOf("期")));
			sequence++;			
		}
		nextPreparationVersion = "[" + year + "年]第" + sequence + "期";
		return nextPreparationVersion;
	}


}
