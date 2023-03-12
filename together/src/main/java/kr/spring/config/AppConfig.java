package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.interceptor.LoginCheckInterceptor;
import kr.spring.interceptor.WriterCheckInterceptor;

//자바코드 기반 설정 클래스
@Configuration
public class AppConfig implements WebMvcConfigurer{

	//변수지정
	private LoginCheckInterceptor loginCheck;
	private WriterCheckInterceptor writerCheck;
	
	@Bean
	public LoginCheckInterceptor interceptor2() {
		loginCheck = new LoginCheckInterceptor();
		return loginCheck;//반환
	}
	
	@Bean
	public WriterCheckInterceptor interceptor4() {
		writerCheck = new WriterCheckInterceptor();
		return writerCheck;
	}
	
	//인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) { //인터셉터 등록은 ajax파일 제외하고 등록하나?
		//인터셉터를 등록해주고 어떤 요청일 때 로그인 처리를 해야할지(회원제 서비스에 해당하는 페이지만) addPathPatterns에 등록을 계속 해줌
		//LoginCheckInterceptor 설정
		registry.addInterceptor(loginCheck)
				.addPathPatterns("/review/write.do")
				.addPathPatterns("/review/update.do")
				.addPathPatterns("/review/delete.do");
	
		
		//WriterCheckInterceptor 설정 (로그인한 아이디와 작성자 아이디 일치하는지)
		registry.addInterceptor(writerCheck)
				.addPathPatterns("/review/update.do")
				.addPathPatterns("/review/delete.do");
	}
	
	//타일스 설정
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer =
				new TilesConfigurer();
		//해당 경로에 xml 설정 파일을 넣음
		configurer.setDefinitions(new String[] {
				"/WEB-INF/tiles-def/main.xml",
				"/WEB-INF/tiles-def/byb.xml",
				"/WEB-INF/tiles-def/hapalpal.xml",
				"/WEB-INF/tiles-def/hyem.xml",
				"/WEB-INF/tiles-def/kjw.xml",
				"/WEB-INF/tiles-def/ksh.xml",
				"/WEB-INF/tiles-def/kys.xml",
				"/WEB-INF/tiles-def/seul.xml",
				"/WEB-INF/tiles-def/shw.xml"
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = 
				new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
}



