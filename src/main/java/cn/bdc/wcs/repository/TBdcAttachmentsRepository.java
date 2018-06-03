package cn.bdc.wcs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cn.bdc.wcs.bean.TBdcAttachments;


public interface TBdcAttachmentsRepository extends CrudRepository<TBdcAttachments, Integer> {
	
	public Iterable<TBdcAttachments> findBySourceTableAndSourceIdOrderBySourceSeq(String sourceTable, String sourceId);
	
	public Iterable<TBdcAttachments> findBySourceTableAndSourceIdAndOriginalFileNameOrderBySourceSeq(String sourceTable, String sourceId, String originalFileName);

	public Iterable<TBdcAttachments> findByOriginalFileName(String originalFileName);
	
	public Optional<TBdcAttachments> findByUuidFileName(String uuidFileName);
}
