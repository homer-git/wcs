package cn.bdc.wcs.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.TBdcPreparationNotice;
import cn.bdc.wcs.repository.TBdcPreparationNoticeRepository;

@Service
public class TBdcPreparationNoticeService {
	
	@Resource
	private TBdcPreparationNoticeRepository tBdcPreparationNoticeRepository;
	
	@Transactional
	public void save(TBdcPreparationNotice tBdcPreparationNotice) {
		tBdcPreparationNoticeRepository.save(tBdcPreparationNotice);
	}

	@Transactional
	public void delete(int id) {
		tBdcPreparationNoticeRepository.deleteById(id);
	}
	
	public Iterable<TBdcPreparationNotice> getAll() {
		return tBdcPreparationNoticeRepository.findAllByOrderByCrateDateDesc();
	}

	public Iterable<TBdcPreparationNotice> getAllById(Iterable<Integer> id){
		return tBdcPreparationNoticeRepository.findAllById(id);
	}

	public Optional<TBdcPreparationNotice> getById(int id){
		return tBdcPreparationNoticeRepository.findById(id);
	}

	public Optional<TBdcPreparationNotice> getByPreparationTitle(String preparationTitle){
		return tBdcPreparationNoticeRepository.findByPreparationTitle(preparationTitle);
	}

	public Iterable<TBdcPreparationNotice> getByPreparationTitleLike(String preparationTitle){
		return tBdcPreparationNoticeRepository.findByPreparationTitleLike(preparationTitle);
	}
}
