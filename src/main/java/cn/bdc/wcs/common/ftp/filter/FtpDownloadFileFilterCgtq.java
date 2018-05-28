package cn.bdc.wcs.common.ftp.filter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FtpDownloadFileFilterCgtq implements GenericFileFilter<Object> {

    private static Logger logger = LoggerFactory.getLogger(FtpDownloadFileFilterCgtq.class);

    @Value("${ftp.local.data.dir.cgtq}")
    private String localDir;

    /**
     * 过滤下载文件
     * @author sunk
     */
    @Override
    public boolean accept(GenericFile<Object> file) {

        try {
            String fileName = file.getFileName();
            long lastModified = file.getLastModified();

            return isLatestFile(lastModified, 3) && !isInLocalDir(fileName) ? true : false;
        } catch (Exception e) {
            logger.error("ftp download file filter error !", e);
            return false;
        }
    }

    /**
     * 文件是否为最近几天内的
     * <p> 用于过滤一部分历史数据
     * @author sunk
     */
    private boolean isLatestFile(long lastModified, int dateNum) {

        Calendar calLastMod = Calendar.getInstance();
        calLastMod.setTimeInMillis(lastModified);

        Calendar calThreshold = Calendar.getInstance();
        calThreshold.add(Calendar.DATE, - dateNum);

        return calLastMod.compareTo(calThreshold) > 0 ? true : false;
    }

    /**
     * 文件是否已在本地目录中
     * @author sunk
     */
    private boolean isInLocalDir(String fileName) {
        try {
            //获取本地文件夹中已下载的文件名
            List<String> localFileNames = Files.walk(Paths.get(localDir))
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
            //logger.info("local downloaded files : " + Arrays.toString(localFileNames.toArray()));

            return localFileNames.contains(fileName) ? true : false;
        } catch (Exception e) {
            logger.error("get local downloaded files fail !", e);
            return true;
        }
    }
}