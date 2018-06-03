package cn.bdc.wcs.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.TBdcPonding;
import cn.bdc.wcs.common.service.BaseService;
import cn.bdc.wcs.repository.TBdcPondingRepository;

@Service
public class TBdcPondingService extends BaseService {
	
	@Resource
	private TBdcPondingRepository tBdcPondingRepository;
	
	@Transactional
	public void save(TBdcPonding tBdcPonding) {
		tBdcPondingRepository.save(tBdcPonding);
	}

	@Transactional
	public void delete(int id) {
		tBdcPondingRepository.deleteById(id);
	}
	
	public Iterable<TBdcPonding> getAll() {
		return tBdcPondingRepository.findAllByOrderByCrateDateDesc();
	}

	public Iterable<TBdcPonding> getAllById(Iterable<Integer> ids){
		return tBdcPondingRepository.findAllById(ids);
	}

	public Optional<TBdcPonding> getById(int id){
		return tBdcPondingRepository.findById(id);
	}
}
