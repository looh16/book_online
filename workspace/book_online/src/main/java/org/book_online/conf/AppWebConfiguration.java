package org.book_online.conf;

import org.book_online.controllers.HomeController;
import org.book_online.daos.ProductDAO;
import org.book_online.models.FileSaver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class,ProductDAO.class,FileSaver.class}) 
public class AppWebConfiguration {
	
@Bean 
public InternalResourceViewResolver internalResourceViewResolver() { 
	InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
	resolver.setPrefix("/WEB-INF/views/"); 
	resolver.setSuffix(".jsp"); 
	return resolver; 
  }
@Bean(name="messageSource")  
public MessageSource loadBundle(){ ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
    bundle.setBasename("/WEB-INF/messages"); 
    bundle.setDefaultEncoding("UTF-8");
    bundle.setCacheSeconds(1); 
    return bundle;
  }
@Bean 
public FormattingConversionService mvcConversionService() { 
	DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
    DateFormatterRegistrar registrar = new DateFormatterRegistrar(); 
       registrar.setFormatter(new DateFormatter("dd-MM-yyyy")); 
       registrar.registerFormatters(conversionService); 
       return conversionService;
  }
@Bean 
public MultipartResolver multipartResolver(){ 
	return new StandardServletMultipartResolver(); 
  }

}
