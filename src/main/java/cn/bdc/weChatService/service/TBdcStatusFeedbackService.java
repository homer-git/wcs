package cn.bdc.weChatService.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.weChatService.bean.TBdcStatusFeedback;
import cn.bdc.weChatService.repository.TBdcStatusFeedbackRepository;

@Service
public class TBdcStatusFeedbackService {

	@Resource
	private TBdcStatusFeedbackRepository tBdcStatusFeedbackRepository;
	
	@Transactional
	public void save(TBdcStatusFeedback tBdcStatusFeedback) {
		tBdcStatusFeedbackRepository.save(tBdcStatusFeedback);
	}

	@Transactional
	public void delete(int id) {
		tBdcStatusFeedbackRepository.deleteById(id);
	}
	
	public Iterable<TBdcStatusFeedback> getAll() {
		return tBdcStatusFeedbackRepository.findAll();
	}

	public Iterable<TBdcStatusFeedback> getAllById(Iterable<Integer> ids){
		return tBdcStatusFeedbackRepository.findAllById(ids);
	}

	public Optional<TBdcStatusFeedback> getById(int id){
		return tBdcStatusFeedbackRepository.findById(id);
	}

}
