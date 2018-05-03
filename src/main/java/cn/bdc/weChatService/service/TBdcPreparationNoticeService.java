package cn.bdc.weChatService.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.weChatService.bean.TBdcPreparationNotice;
import cn.bdc.weChatService.repository.TBdcPreparationNoticeRepository;

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
		return tBdcPreparationNoticeRepository.findAll();
	}
}
