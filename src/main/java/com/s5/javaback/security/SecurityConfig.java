package com.s5.javaback.security;

import com.s5.javaback.security.token.FirebaseEntryPoint;
import com.s5.javaback.security.token.FirebaseFilter;
import com.s5.javaback.security.token.FirebaseProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final FirebaseEntryPoint entryPoint;
    private final FirebaseFilter filter;
    private final FirebaseProvider provider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers(publicEndpoint).permitAll()
                .antMatchers(HttpMethod.GET, "/public/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/update").permitAll()
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                //Home  .hasAnyRole(RoleType.ADMIN.getName()
                .antMatchers(HttpMethod.POST,"/home_event/create").permitAll()
                .antMatchers(HttpMethod.GET,"/home_event/find/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/home_event/name").permitAll()
                //Turn
                .antMatchers(HttpMethod.POST,"/turn/create/{idHome}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/turn/{idTurn}").permitAll()
                .antMatchers(HttpMethod.POST,"/payment/{turnId}").permitAll()
                .antMatchers(HttpMethod.GET,"/payment/success").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(filter, BasicAuthenticationFilter.class);
        http.authenticationProvider(provider);
        http.exceptionHandling().authenticationEntryPoint(entryPoint);

        return http.build();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
            }
        };
    }

    private static final String[] publicEndpoint = {
            "/swagger-resources/**",
            "/swagger-ui/**", "/v2/api-docs",
            "/v3/api-docs",
            "/api/docs",
            "/api/docs/**",
            "/api/docs/swagger-ui",
            "/swagger-ui.html",
            "/**/swagger-ui/**",
            "/swagger-ui"
    };


}
