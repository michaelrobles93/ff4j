package com.construo.ff4j.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/*@Configuration
@EnableWebSecurity
@ConditionalOnProperty(value = "ff4j.webconsole.secure", havingValue = "true")*/
//public class FF4jWebConsoleSecure extends WebSecurityConfigurerAdapter {
public class FF4jWebConsoleSecure {

    private static final Logger LOGGER = LoggerFactory.getLogger(FF4jWebConsoleSecure.class);

    @Value("${ff4j.webconsole.username}")
    private String username;

    @Value("${ff4j.webconsole.password}")
    private String password;

    @Value("${ff4j.webconsole.url}")
    private String url;

    @Value("${ff4j.webconsole.roles}")
    private String[] roles;


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(username).password(encoder.encode(password)).roles(Arrays.stream(roles).toArray(String[]::new));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Securing the webconsole access as property 'ff4j.webconsole.secure' is true");
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(url + "/**").hasRole("ADMIN")
                .and()
                .httpBasic();
    }*/

}
