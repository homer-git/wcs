package cn.bdc.wcs;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author guhm
 * @version 1.0
 * 
 * wcs启动程序
 * 
 *
 */
@SpringBootApplication
public class WcsApplication {
	

	@Value("${wcs.attachments.file.maxfilesize}")
	private String maxFileSize;
	
	@Value("${wcs.attachments.file.maxrequestsize}")
	private String maxRequestSize;

	public static void main(String[] args) {
		SpringApplication.run(WcsApplication.class, args);
	}
	
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(maxFileSize); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }
}
