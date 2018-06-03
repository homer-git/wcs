package cn.bdc.wcs.service;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.bdc.wcs.bean.TBdcAttachments;
import cn.bdc.wcs.common.service.BaseService;
import cn.bdc.wcs.repository.TBdcAttachmentsRepository;

@Service
public class TBdcAttachmentsService extends BaseService {
	
	@Resource
	private TBdcAttachmentsRepository tBdcAttachmentsRepository;
	
	@Transactional
	public void save(TBdcAttachments tBdcAttachments) {
		tBdcAttachmentsRepository.save(tBdcAttachments);
	}

	@Transactional
	public void delete(int id) {
		tBdcAttachmentsRepository.deleteById(id);
	}
	
	public Iterable<TBdcAttachments> getAll() {
		return tBdcAttachmentsRepository.findAll();
	}
	
	public Optional<TBdcAttachments> getById(int id) {
		return tBdcAttachmentsRepository.findById(id);
	}
	
	public Iterable<TBdcAttachments> getBySourceTableAndSourceIdOrderBySourceSeq(String sourceTable, String sourceId) {
		return tBdcAttachmentsRepository.findBySourceTableAndSourceIdOrderBySourceSeq(sourceTable, sourceId);
	}
	
	public Iterable<TBdcAttachments> getBySourceTableAndSourceIdAndOriginalFileNameOrderBySourceSeq(String sourceTable, String sourceId, String originalFileName) {
		return tBdcAttachmentsRepository.findBySourceTableAndSourceIdAndOriginalFileNameOrderBySourceSeq(sourceTable, sourceId, originalFileName);
	}
	
	public Iterable<TBdcAttachments> getByOriginalFileName(String originalFileName) {
		return tBdcAttachmentsRepository.findByOriginalFileName(originalFileName);
	}
	
	public Optional<TBdcAttachments> getByUuidFileName(String uuidFileName) {
		return tBdcAttachmentsRepository.findByUuidFileName(uuidFileName);
	}


}
