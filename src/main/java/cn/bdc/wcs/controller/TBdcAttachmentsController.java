package cn.bdc.wcs.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.bdc.wcs.bean.TBdcAttachments;
import cn.bdc.wcs.service.TBdcAttachmentsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/attachments")
public class TBdcAttachmentsController {

	@Value("${wcs.attachments.path}")
	private String attachmentsPath;

	@Value("${wcs.attachments.http.url}")
	private String httpUri;
	
	@Value("${wcs.attachments.file.separator}")
	private String fileSeparator;

	@Resource
	private TBdcAttachmentsService tBdcAttachmentsService;

	@RequestMapping(value = "/getAll", method = { RequestMethod.GET })
	public Iterable<TBdcAttachments> getAllAttachments() {
		return tBdcAttachmentsService.getAll();
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE })
	public String deleteAttachment(Integer id) {
		tBdcAttachmentsService.delete(id);
		return "delete success.";
	}

	@RequestMapping(value = "/getById", method = { RequestMethod.POST, RequestMethod.GET })
	public Optional<TBdcAttachments> getAttachmentById(Integer id) {
		return tBdcAttachmentsService.getById(id);
	}

	@RequestMapping(value = "/getBySourceTableAndSourceId", method = { RequestMethod.POST, RequestMethod.GET })
	public Iterable<TBdcAttachments> getBySourceTableAndSourceIdOrderBySourceSeq(String sourceTable, String sourceId) {
		return tBdcAttachmentsService.getBySourceTableAndSourceIdOrderBySourceSeq(sourceTable, sourceId);
	}

	@RequestMapping(value = "/getBySourceTableAndSourceIdAndOriginalFileName", method = { RequestMethod.POST,
			RequestMethod.GET })
	public Iterable<TBdcAttachments> getBySourceTableAndSourceIdAndOriginalFileNameOrderBySourceSeq(String sourceTable,
			String sourceId, String originalFileName) {
		return tBdcAttachmentsService.getBySourceTableAndSourceIdAndOriginalFileNameOrderBySourceSeq(sourceTable,
				sourceId, originalFileName);
	}

	@RequestMapping(value = "/getByOriginalFileName", method = { RequestMethod.POST, RequestMethod.GET })
	public Iterable<TBdcAttachments> getByOriginalFileName(String originalFileName) {
		return tBdcAttachmentsService.getByOriginalFileName(originalFileName);
	}

	@RequestMapping(value = "/getByUuidFileName", method = { RequestMethod.POST, RequestMethod.GET })
	public Optional<TBdcAttachments> getByUuidFileName(String uuidFileName) {
		return tBdcAttachmentsService.getByUuidFileName(uuidFileName);
	}

	/**
	 * 文件上传具体实现方法;
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file, String sourceTable, String sourceId, String attachmentTitle, String attachmentDesc) {
		if (!file.isEmpty()) {
			try {
				
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
				String todayStr = format.format(calendar.getTime());

				TBdcAttachments tBdcAttachments = new TBdcAttachments();
				tBdcAttachments.setCrateDate(new Date());
				tBdcAttachments.setCreatedBy("-1");
				tBdcAttachments.setCreatedUnit("-1");
				
				tBdcAttachments.setSourceTable(sourceTable);
				tBdcAttachments.setSourceId(sourceId);
				tBdcAttachments.setSourceSeq(1);
				tBdcAttachments.setAttachmentTitle(attachmentTitle);
				tBdcAttachments.setAttachmentDesc(attachmentDesc);
				tBdcAttachments.setFileMimeType(file.getContentType());
				tBdcAttachments.setFileSize(file.getSize());
				
				String originalFileName = file.getOriginalFilename();
				tBdcAttachments.setOriginalFileName(originalFileName);
				
				String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
				tBdcAttachments.setFileSuffix(fileSuffix);
				
				String uuidFileName = UUID.randomUUID().toString().replace("-", "") + fileSuffix;
				tBdcAttachments.setUuidFileName(uuidFileName);
				
				String filePath = attachmentsPath + fileSeparator + todayStr + fileSeparator + uuidFileName;
				tBdcAttachments.setFilePath(filePath);
				
				String fileUri = httpUri + "/" + todayStr + "/" + uuidFileName;
				tBdcAttachments.setFileUri(fileUri);
				
				File filePathExists =new File(attachmentsPath + fileSeparator + todayStr + fileSeparator); 
				if (!filePathExists.exists()  && !filePathExists.isDirectory())
					filePathExists.mkdirs();
				
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				out.write(file.getBytes());
				out.flush();
				out.close();

		        Path filePathObj = Paths.get(filePath);
		        BasicFileAttributeView basicView = Files.getFileAttributeView(filePathObj, BasicFileAttributeView.class);
		        BasicFileAttributes basicFileAttributes = basicView.readAttributes();
		        
		        Date fileCreationTime = new Date(basicFileAttributes.creationTime().toMillis());
		        Date fileLastAccessTime = new Date(basicFileAttributes.lastAccessTime().toMillis());
		        Date fileLastModifiedTime = new Date(basicFileAttributes.lastModifiedTime().toMillis());
		        FileOwnerAttributeView ownerView = Files.getFileAttributeView(filePathObj, FileOwnerAttributeView.class);
		        String fileOwner = ownerView.getOwner().toString();

		        tBdcAttachments.setFileCreationTime(fileCreationTime);
		        tBdcAttachments.setFileLastAccessTime(fileLastAccessTime);
		        tBdcAttachments.setFileLastModifiedTime(fileLastModifiedTime);
		        tBdcAttachments.setFileOwner(fileOwner);
				
				tBdcAttachmentsService.save(tBdcAttachments);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				return "上传失败," + e.getMessage();
			}
			return "上传成功";
		} else {
			return "上传失败，因为文件是空的.";
		}

	}

	/**
	 * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		String sourceTable = request.getParameter("sourceTable");
		String sourceId = request.getParameter("sourceId");
		String attachmentTitle = request.getParameter("attachmentTitle");
		String attachmentDesc = request.getParameter("attachmentDesc");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
		String todayStr = format.format(calendar.getTime());
		
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {

					TBdcAttachments tBdcAttachments = new TBdcAttachments();
					tBdcAttachments.setCrateDate(new Date());
					tBdcAttachments.setCreatedBy("-1");
					tBdcAttachments.setCreatedUnit("-1");
					
					tBdcAttachments.setSourceTable(sourceTable);
					tBdcAttachments.setSourceId(sourceId);
					tBdcAttachments.setAttachmentTitle(attachmentTitle);
					tBdcAttachments.setAttachmentDesc(attachmentDesc);
					tBdcAttachments.setSourceSeq(i + 1);
					tBdcAttachments.setFileMimeType(file.getContentType());
					tBdcAttachments.setFileSize(file.getSize());
					
					String originalFileName = file.getOriginalFilename();
					tBdcAttachments.setOriginalFileName(originalFileName);
					
					String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
					tBdcAttachments.setFileSuffix(fileSuffix);
					
					String uuidFileName = UUID.randomUUID().toString().replace("-", "") + fileSuffix;
					tBdcAttachments.setUuidFileName(uuidFileName);
					
					String filePath = attachmentsPath + fileSeparator + todayStr + fileSeparator + uuidFileName;
					tBdcAttachments.setFilePath(filePath);
					
					String fileUri = httpUri + "/" + todayStr + "/" + uuidFileName;
					tBdcAttachments.setFileUri(fileUri);
					
					File filePathExists =new File(attachmentsPath + fileSeparator + todayStr + fileSeparator); 
					if (!filePathExists.exists() && !filePathExists.isDirectory())
						filePathExists.mkdirs();

					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
					stream.write(bytes);
					stream.close();

			        Path filePathObj = Paths.get(filePath);
			        BasicFileAttributeView basicView = Files.getFileAttributeView(filePathObj, BasicFileAttributeView.class);
			        BasicFileAttributes basicFileAttributes = basicView.readAttributes();
			        
			        Date fileCreationTime = new Date(basicFileAttributes.creationTime().toMillis());
			        Date fileLastAccessTime = new Date(basicFileAttributes.lastAccessTime().toMillis());
			        Date fileLastModifiedTime = new Date(basicFileAttributes.lastModifiedTime().toMillis());
			        FileOwnerAttributeView ownerView = Files.getFileAttributeView(filePathObj, FileOwnerAttributeView.class);
			        String fileOwner = ownerView.getOwner().toString();

			        tBdcAttachments.setFileCreationTime(fileCreationTime);
			        tBdcAttachments.setFileLastAccessTime(fileLastAccessTime);
			        tBdcAttachments.setFileLastModifiedTime(fileLastModifiedTime);
			        tBdcAttachments.setFileOwner(fileOwner);
					
					tBdcAttachmentsService.save(tBdcAttachments);
					
				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " =>" + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " becausethe file was empty.";
			}
		}
		return "upload successful";

	}
}
