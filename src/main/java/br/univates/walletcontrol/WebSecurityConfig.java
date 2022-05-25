package br.univates.walletcontrol;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Arthur
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/home/**").permitAll()
		.antMatchers("/register/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
		.logout(form -> form.logoutUrl("/logout").logoutSuccessUrl("/home")).csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.passwordEncoder(new BCryptPasswordEncoder())
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
			.authoritiesByUsernameQuery("SELECT username, role FROM users WHERE username = ?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
