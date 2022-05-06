package com.example.demo.security;

import com.example.demo.security.JWT.JWTSecurityConfigurer;
import com.example.demo.security.JWT.JWTTokenProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity

@NoArgsConstructor
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .formLogin().permitAll()
                .defaultSuccessUrl("/article.html", true)
                .and()

                .logout().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/article/add").hasRole("USER")
                .antMatchers("/article/rename").hasRole("USER")
                .antMatchers("/article/deleteByName").hasRole("USER")
                .antMatchers("/balance/add").hasRole("USER")
                .antMatchers("/balance/deleteByID").hasRole("USER")
                .antMatchers("/operation/add").hasRole("USER")
                .antMatchers("/signin").permitAll()
                .antMatchers("/newUser").permitAll()
                .antMatchers("/article").permitAll()
                .antMatchers("/article/getByName").permitAll()
                .antMatchers("/balance/getAll").permitAll()
                .antMatchers("/balance/getMostProfitable").permitAll()
                .antMatchers("/balance/getMostSpending").permitAll()
                .antMatchers("/balance/getAmountByID").permitAll()
                .antMatchers("/balance/getSummaryAmount").permitAll()
                .antMatchers("/balance/rankByCreateDate").permitAll()
                .antMatchers("/balance/getAvailable").permitAll()
                .antMatchers("/operation/rankArticlesOfDebitForThePeriod").permitAll()
                .antMatchers("/operation/rankArticlesOfCreditForThePeriod").permitAll()
                .antMatchers("/operation/getAllByCurrentArticle").permitAll()
                .antMatchers("/operation/getAllByCurrentBalanceForThePeriod").permitAll()
                .antMatchers("/operation/getAllForThePeriod").permitAll()
                .antMatchers("/operation/getMostPopularBalanceOfThePeriod").permitAll()
                .antMatchers("/operation/getMostPopularArticleOfThePeriod").permitAll()
                .antMatchers("/article.html/**").permitAll()
                .antMatchers("/js/article.js/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .apply(new JWTSecurityConfigurer(jwtTokenProvider));
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(clientService).passwordEncoder(passwordEncoder);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication()
//               .withUser("user").password("{noop}pwd").roles("USER")
//               .and()
//               .withUser("admin").password("{noop}apwd").roles("ADMIN");
//    }
}