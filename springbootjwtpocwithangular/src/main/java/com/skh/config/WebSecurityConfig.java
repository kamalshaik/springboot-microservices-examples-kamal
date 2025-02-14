package com.skh.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		System.out.println("WebSecurityConfig--->configureGlobal()");
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		/* Read below site
		https://www.yawintutor.com/encode-decode-using-bcryptpasswordencoder-in-spring-boot-security/
		 */
		System.out.println("WebSecurityConfig--->passwordEncoder()");
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		System.out.println("WebSecurityConfig--->authenticationManagerBean()");
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("WebSecurityConfig--->configure()");
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests()
				.antMatchers(new String[] { "/authenticate", "/h2-console","/saveNewUser" }).permitAll().
				// all other requests need to be authenticated
				anyRequest().authenticated().and()
				// below code is for "preflight" CORS issue.
				.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class).
//				and(). // comment above code and allow this code it will work perfectly..
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	/*
	 * @Bean CorsConfigurationSource corsConfigurationSource() {
	 * System.out.println("WebSecurityConfig ---> corsConfigurationSource()");
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(Arrays.asList("/**"));
	 * configuration.setAllowCredentials(true);
	 * configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers",
	 * "Access-Control-Allow-Origin","Access-Control-Request-Method",
	 * "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type",
	 * "Authorization")); configuration.setAllowedMethods(Arrays.asList("DELETE",
	 * "GET", "POST", "PATCH", "PUT")); UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration); return source; }
	 */

}
