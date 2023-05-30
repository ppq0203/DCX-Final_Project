package project.momento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/", "/login.com").permitAll()
			.requestMatchers("/css/**").permitAll() // 이부분
			.requestMatchers("/img/**").permitAll() // 이부분
			.requestMatchers("/js/**").permitAll() // 이부분
//			.anyRequest().authenticated()
			.anyRequest().permitAll()
			).cors().disable()
	        .csrf().disable()
	        .formLogin().disable() //기본 로그인 페이지 없애기
			.formLogin((form) -> form
				.loginPage("/login.com")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}