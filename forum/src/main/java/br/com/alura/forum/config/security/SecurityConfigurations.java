package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{

	//CONFIGURACAO DE AUTENTICACAO
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	}
	
	//CONFIGURACAO DE AUTORIZACAO
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/topicos").permitAll()
		.antMatchers(HttpMethod.GET,"/topicos/*").permitAll();
	}
	
	//CONFIGURACAO DE RECURSOS ESTATICOS (JS, CSS, IMAGENS, ETC.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
}
