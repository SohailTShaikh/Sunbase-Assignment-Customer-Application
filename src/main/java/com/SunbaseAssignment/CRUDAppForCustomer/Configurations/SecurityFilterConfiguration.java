package com.SunbaseAssignment.CRUDAppForCustomer.Configurations;


import com.SunbaseAssignment.CRUDAppForCustomer.JWTClass.JWTAuthenticationEntryPoint;
import com.SunbaseAssignment.CRUDAppForCustomer.JWTClass.JWTAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@AllArgsConstructor
public class SecurityFilterConfiguration {

    private JWTAuthenticationEntryPoint point;
    private JWTAuthenticationFilter filter;


    @Bean

    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        return security.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/welcome")
                        .permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
                .build();
    }
}
