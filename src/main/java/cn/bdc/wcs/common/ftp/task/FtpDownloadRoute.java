package cn.bdc.wcs.common.ftp.task;

import java.net.InetAddress;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.bdc.wcs.common.ftp.processor.FtpDownloadProcessorCgtq;
import cn.bdc.wcs.common.ftp.processor.FtpDownloadProcessorDstq;
import cn.bdc.wcs.common.ftp.processor.FtpDownloadProcessorLdhb;
import cn.bdc.wcs.common.ftp.processor.FtpDownloadProcessorYjyb;

@Component
public class FtpDownloadRoute extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(FtpDownloadRoute.class);

	//短时天气
    @Value("${ftp.server.uri.dstq}")
    private String ftpUriDstq;

    @Value("${ftp.local.data.dir.dstq}")
    private String localDirDstq;

    //预警预报
    @Value("${ftp.server.uri.yjyb}")
    private String ftpUriYjyb;
    
    @Value("${ftp.local.data.dir.yjyb}")
    private String localDirYjyb;

    //雷达回波
    @Value("${ftp.server.uri.ldhb}")
    private String ftpUriLdhb;

    @Value("${ftp.local.data.dir.ldhb}")
    private String localDirLdhb;

    //常规天气
    @Value("${ftp.server.uri.cgtq}")
    private String ftpUriCgtq;

    @Value("${ftp.local.data.dir.cgtq}")
    private String localDirCgtq;


    @Value("${host.nodes}")
    private String hostNodes;

    @Value("${ftp.task.start}")
    private boolean isStart;
    
    @Autowired
    FtpDownloadProcessorDstq ftpDownloadProcessorDstq;
    
    @Autowired
    FtpDownloadProcessorYjyb ftpDownloadProcessorYjyb;
    
    @Autowired
    FtpDownloadProcessorLdhb ftpDownloadProcessorLdhb;
    
    @Autowired
    FtpDownloadProcessorCgtq ftpDownloadProcessorCgtq;

    @Override
    public void configure() throws Exception {

        if (isStart && isExecHost()) {
        	//短时天气
            from(ftpUriDstq).process(ftpDownloadProcessorDstq).to("file:" + localDirDstq)
                .log(LoggingLevel.INFO, logger, "[DSTQ] download file ${file:name} complete.");
            
            //预警预报
            from(ftpUriYjyb).process(ftpDownloadProcessorYjyb).to("file:" + localDirYjyb)
            	.log(LoggingLevel.INFO, logger, "[YJYB] download file ${file:name} complete.");
            
            //雷达回波
            from(ftpUriLdhb).process(ftpDownloadProcessorLdhb).to("file:" + localDirLdhb)
            	.log(LoggingLevel.INFO, logger, "[LDHB] download file ${file:name} complete.");
            
            //常规天气
            from(ftpUriCgtq).process(ftpDownloadProcessorCgtq).to("file:" + localDirCgtq)
            	.log(LoggingLevel.INFO, logger, "[CGTQ] download file ${file:name} complete.");
        }
    }

    /**
     * 判断是否为执行任务的主机
     * @author sunk
     */
    private boolean isExecHost() {
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            logger.error("get hostname fail !", e);
            return false;
        }
        return hostNodes.indexOf(hostName.trim()) != -1;
    }
}