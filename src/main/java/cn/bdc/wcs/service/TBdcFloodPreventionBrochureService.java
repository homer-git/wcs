package cn.bdc.wcs.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.TBdcFloodPreventionBrochure;
import cn.bdc.wcs.common.service.BaseService;
import cn.bdc.wcs.repository.TBdcFloodPreventionBrochureRepository;

@Service
public class TBdcFloodPreventionBrochureService extends BaseService {
	
	@Resource
	private TBdcFloodPreventionBrochureRepository tBdcFloodPreventionBrochureRepository;
	
	@Transactional
	public void save(TBdcFloodPreventionBrochure tBdcFloodPreventionBrochure) {
		tBdcFloodPreventionBrochureRepository.save(tBdcFloodPreventionBrochure);
	}

	@Transactional
	public void delete(int id) {
		tBdcFloodPreventionBrochureRepository.deleteById(id);
	}
	
	public Iterable<TBdcFloodPreventionBrochure> getAll() {
		return tBdcFloodPreventionBrochureRepository.findAllByOrderByCrateDateDesc();
	}

	public Iterable<TBdcFloodPreventionBrochure> getAllById(Iterable<Integer> ids){
		return tBdcFloodPreventionBrochureRepository.findAllById(ids);
	}

	public Optional<TBdcFloodPreventionBrochure> getById(int id){
		return tBdcFloodPreventionBrochureRepository.findById(id);
	}
}
