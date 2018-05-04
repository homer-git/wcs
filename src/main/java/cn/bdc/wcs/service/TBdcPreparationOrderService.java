package cn.bdc.wcs.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.TBdcPreparationOrder;
import cn.bdc.wcs.repository.TBdcPreparationOrderRepository;

@Service
public class TBdcPreparationOrderService {
	
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


}