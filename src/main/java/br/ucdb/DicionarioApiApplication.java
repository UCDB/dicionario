package br.ucdb;

import br.ucdb.Controller.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DicionarioApiApplication {

	@Bean
	public FilterRegistrationBean filtroJwt(){
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new TokenFilter());
		frb.addUrlPatterns("/admin/*");

		return frb;
	}



	public static void main(String[] args) {
		SpringApplication.run(DicionarioApiApplication.class, args);
	}


}
