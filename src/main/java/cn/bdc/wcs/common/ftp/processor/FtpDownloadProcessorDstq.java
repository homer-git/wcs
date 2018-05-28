package cn.bdc.wcs.common.ftp.processor;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.bdc.wcs.bean.TBdcWeatherFiles;
import cn.bdc.wcs.service.TBdcWeatherFilesService;

@Component
public class FtpDownloadProcessorDstq implements Processor {

    private static Logger logger = LoggerFactory.getLogger( FtpDownloadProcessorDstq.class );
    
    public static final String WEATHER_FILE_TYPE = "WEATHER_DSTQ";
  
    @Value("${ftp.http.url}")
    private String ftpHttpUrl;

    @Value("${ftp.url.prefix}")
    private String ftpUrlPrefix;

    @Value("${ftp.url}")
    private String ftpUrl;
    
    @Value("${ftp.local.data.dir.dstq}")
    private String localDir;

    @Value("${ftp.url.dstq}")
    private String ftpUrlDstq;

    @Autowired
    TBdcWeatherFilesService tBdcWeatherFilesService;//业务逻辑处理组件
    
    @Override
    public void process(Exchange exchange) throws Exception {
        GenericFileMessage<RandomAccessFile> inFileMessage = (GenericFileMessage<RandomAccessFile>) exchange.getIn();
        GenericFile<RandomAccessFile> file = inFileMessage.getGenericFile();
        String splitTag = File.separator;//系统文件分隔符
        String fileName = file.getFileName();//文件名
        String filePath = localDir + fileName;
        String fileUri = ftpHttpUrl + ftpUrlDstq + fileName;
        String fileFTPUri = ftpUrlPrefix + ftpUrl + ftpUrlDstq + fileName;
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        Date fileLastModifiedTime = new Date(file.getLastModified());
        long fileSize = file.getFileLength();
        
//还未写入文件 无法获取文件信息
//        Path filePathObj = Paths.get(filePath);
//        BasicFileAttributeView basicView = Files.getFileAttributeView(filePathObj, BasicFileAttributeView.class);
//        BasicFileAttributes basicFileAttributes = basicView.readAttributes();
        
//        Date fileCreationTime = new Date(basicFileAttributes.creationTime().toMillis());
//        Date fileLastAccessTime = new Date(basicFileAttributes.lastAccessTime().toMillis());
//        FileOwnerAttributeView ownerView = Files.getFileAttributeView(filePathObj, FileOwnerAttributeView.class);
//        String fileOwner = ownerView.getOwner().toString();
        
        TBdcWeatherFiles tBdcWeatherFiles = new TBdcWeatherFiles();
        tBdcWeatherFiles.setFileType(FtpDownloadProcessorDstq.WEATHER_FILE_TYPE);
        tBdcWeatherFiles.setFileName(fileName);
        tBdcWeatherFiles.setFilePath(filePath);
        tBdcWeatherFiles.setFileUri(fileUri);
        tBdcWeatherFiles.setFileFTPUri(fileFTPUri);
        tBdcWeatherFiles.setFileSuffix(fileSuffix);
//        tBdcWeatherFiles.setFileCreationTime(fileCreationTime);
//        tBdcWeatherFiles.setFileLastAccessTime(fileLastAccessTime);
        tBdcWeatherFiles.setFileLastModifiedTime(fileLastModifiedTime);
        tBdcWeatherFiles.setFileSize(fileSize);
//        tBdcWeatherFiles.setFileOwner(fileOwner);
        
        tBdcWeatherFiles.setCrateDate(new Date());
        tBdcWeatherFiles.setCreatedBy("-1");
        tBdcWeatherFiles.setCreatedUnit("-1");
        
        tBdcWeatherFilesService.save(tBdcWeatherFiles);
        
        logger.info(localDir + fileName);//文件的绝对路径
        
    }

}
