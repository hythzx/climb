package com.yjy.climb.config;

import com.yjy.climb.security.jwt.JWTConfigurer;
import com.yjy.climb.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import tech.jhipster.config.JHipsterProperties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 该类参考 <a href="https://github.com/jhipster/generator-jhipster">JHipster</a> 配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration {

	private final Logger log = getLogger(SecurityConfiguration.class);

	private final JHipsterProperties jHipsterProperties;

	private final CorsFilter corsFilter;

	private final TokenProvider tokenProvider;

	private final SecurityProblemSupport problemSupport;

	public SecurityConfiguration(JHipsterProperties jHipsterProperties, CorsFilter corsFilter, TokenProvider tokenProvider, SecurityProblemSupport problemSupport) {
		this.jHipsterProperties = jHipsterProperties;
		this.corsFilter = corsFilter;
		this.tokenProvider = tokenProvider;
		this.problemSupport = problemSupport;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable()
				.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling()
					.authenticationEntryPoint(problemSupport)
					.accessDeniedHandler(problemSupport)
				.and()
					.headers()
					.contentSecurityPolicy(jHipsterProperties.getSecurity().getContentSecurityPolicy())
				.and()
					.referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
				.and()
					.permissionsPolicy().policy("camera=(), fullscreen=(self), geolocation=(), gyroscope=(), magnetometer=(), microphone=(), midi=(), payment=(), sync-xhr=()")
				.and()
					.frameOptions()
					.deny()
				.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.antMatchers("/swagger-ui/**").permitAll()
					.antMatchers("/druid/**").permitAll()
					.antMatchers("/public/**").permitAll()
				    .antMatchers("/api/captcha/**").permitAll()
					.antMatchers("/api/authenticate").permitAll()
					.antMatchers("/api/register").permitAll()
					.antMatchers("/api/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
					.antMatchers("/api/**").authenticated()
					.antMatchers("/management/health").permitAll()
					.antMatchers("/management/health/**").permitAll()
					.antMatchers("/management/info").permitAll()
					.antMatchers("/management/prometheus").permitAll()
					.antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.and()
					.httpBasic()
				.and()
					.apply(securityConfigurerAdapter());
		return http.build();
	}

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}
}
