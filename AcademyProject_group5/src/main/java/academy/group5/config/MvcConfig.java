package academy.group5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import academy.group5.interceptor.LectureManagerSessionCheckInterceptor;
import academy.group5.interceptor.MileageManagerSessionCheckInterceptor;
import academy.group5.interceptor.SessionCheckInterceptor;

// Spring@MVC에 대한 빈들을 설정
@Configuration
@ComponentScan(basePackages={"academy.group5.controller"})
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionCheckInterceptor())
			.addPathPatterns("/campus/**")
			.addPathPatterns("/lecture/**")
			.addPathPatterns("/write/**")
			.addPathPatterns("/mileage/**")
			.addPathPatterns("/noti/**")
			.addPathPatterns("/info/**");
		
		registry.addInterceptor(new LectureManagerSessionCheckInterceptor())
		.addPathPatterns("/lectureManage/**");
		
		registry.addInterceptor(new MileageManagerSessionCheckInterceptor())
		.addPathPatterns("/mileageManage/**");
		
		super.addInterceptors(registry);
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/WEB-INF/view/error/*").setViewName("ERROR");
		super.addViewControllers(registry);
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	
		registry.addResourceHandler("/upload/**")
			.addResourceLocations("/WEB-INF/upload/");
		
		/*registry.addResourceHandler("/php/**")
			.addResourceLocations("/WEB-INF/php/");*/
	      
	    registry.addResourceHandler("/html/**")
	    	.addResourceLocations("WEB-INF/html/");
	      
	    registry.addResourceHandler("/css/**")
			.addResourceLocations("WEB-INF/assets/css/");
	      
	    registry.addResourceHandler("/images/**")
	    	.addResourceLocations("WEB-INF/assets/images/");
	      
	    registry.addResourceHandler("/fonts/**")
	    	.addResourceLocations("WEB-INF/assets/fonts/");
	      
	    registry.addResourceHandler("/fonts/**")
	    	.addResourceLocations("WEB-INF/assets/fonts/");
	      
	    registry.addResourceHandler("/js/**")
	    	.addResourceLocations("WEB-INF/assets/js/");
	   }
}
