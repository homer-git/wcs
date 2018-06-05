package cn.bdc.wcs.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_BDC_Attachments")  
public class TBdcAttachments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false, precision=20) 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int attachmentId;

	@Column(nullable = true, length=150)
	private String attachmentTitle;

	@Column(nullable = true, length=500)
	private String attachmentDesc;

	@Column(nullable = false, length=150)
	private String sourceTable;
	
	@Column(nullable = false, length=20)
	private String sourceId;

	@Column(nullable = false, precision=20) 
	private int sourceSeq;

	@Column(nullable = false, length=150)
	private String originalFileName;
	
	@Column(nullable = false, length=150)
	private String uuidFileName;
	
	@Column(nullable = false, length=150)
	private String fileMimeType;	

	@Column(nullable = false, length=500)
	private String filePath;

	@Column(nullable = false, length=500)
	private String fileUri;

	@Column(nullable = true, length=50)
	private String fileSuffix;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)//设置为时间类型
	private Date fileCreationTime;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)//设置为时间类型
	private Date fileLastAccessTime;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)//设置为时间类型
	private Date fileLastModifiedTime;

	@Column(nullable = true, length=20)
	private long fileSize;

	@Column(nullable = true, length=50)
	private String fileOwner;
	
//	@Lob
//	@Basic(fetch = FetchType.LAZY)
//	@Column(name=" file_content", columnDefinition="longtext", nullable=true) 
//	private String fileContent;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)//设置为时间类型 
	private Date crateDate;

	@Column(nullable = false, length=20)
	private String createdBy;

	@Column(nullable = false, length=100)
	private String createdUnit;

	@Column(nullable = true, length=100)
	private String createdSubUnit;

	@Column(nullable = true, length=100)
	private String attribute1;

	@Column(nullable = true, length=100)
	private String attribute2;

	@Column(nullable = true, length=100)
	private String attribute3;

	@Column(nullable = true, length=100)
	private String attribute4;

	@Column(nullable = true, length=100)
	private String attribute5;

	public int getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentTitle() {
		return attachmentTitle;
	}

	public void setAttachmentTitle(String attachmentTitle) {
		this.attachmentTitle = attachmentTitle;
	}

	public String getAttachmentDesc() {
		return attachmentDesc;
	}

	public void setAttachmentDesc(String attachmentDesc) {
		this.attachmentDesc = attachmentDesc;
	}

	public String getSourceTable() {
		return sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public int getSourceSeq() {
		return sourceSeq;
	}

	public void setSourceSeq(int sourceSeq) {
		this.sourceSeq = sourceSeq;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getUuidFileName() {
		return uuidFileName;
	}

	public void setUuidFileName(String uuidFileName) {
		this.uuidFileName = uuidFileName;
	}

	public String getFileMimeType() {
		return fileMimeType;
	}

	public void setFileMimeType(String fileMimeType) {
		this.fileMimeType = fileMimeType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public Date getFileCreationTime() {
		return fileCreationTime;
	}

	public void setFileCreationTime(Date fileCreationTime) {
		this.fileCreationTime = fileCreationTime;
	}

	public Date getFileLastAccessTime() {
		return fileLastAccessTime;
	}

	public void setFileLastAccessTime(Date fileLastAccessTime) {
		this.fileLastAccessTime = fileLastAccessTime;
	}

	public Date getFileLastModifiedTime() {
		return fileLastModifiedTime;
	}

	public void setFileLastModifiedTime(Date fileLastModifiedTime) {
		this.fileLastModifiedTime = fileLastModifiedTime;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileOwner() {
		return fileOwner;
	}

	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}

//	public String getFileContent() {
//		return fileContent;
//	}
//
//	public void setFileContent(String fileContent) {
//		this.fileContent = fileContent;
//	}

	public Date getCrateDate() {
		return crateDate;
	}

	public void setCrateDate(Date crateDate) {
		this.crateDate = crateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedUnit() {
		return createdUnit;
	}

	public void setCreatedUnit(String createdUnit) {
		this.createdUnit = createdUnit;
	}

	public String getCreatedSubUnit() {
		return createdSubUnit;
	}

	public void setCreatedSubUnit(String createdSubUnit) {
		this.createdSubUnit = createdSubUnit;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

}
