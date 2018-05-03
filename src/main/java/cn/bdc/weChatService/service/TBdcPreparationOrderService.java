package cn.bdc.weChatService.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.weChatService.bean.TBdcPreparationOrder;
import cn.bdc.weChatService.repository.TBdcPreparationOrderRepository;

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


}
