package kr.co.clozet;

import kr.co.clozet.files.properties.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;
@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(FileProperties.class)
public class ApiApplication {

	public static void main(String... args) {

		SpringApplication.run(ApiApplication.class, args);
	}
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
		return new HiddenHttpMethodFilter();
	}
}
