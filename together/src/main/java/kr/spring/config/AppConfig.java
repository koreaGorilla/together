package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

//자바코드 기반 설정 클래스
@Configuration
public class AppConfig implements WebMvcConfigurer{

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



