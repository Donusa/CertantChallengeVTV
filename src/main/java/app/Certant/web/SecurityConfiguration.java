package app.Certant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

   @Autowired
   private UserDetailsService userDatailsService;
   
   public BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }
   
   @Autowired
   public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
       build.userDetailsService(userDatailsService).passwordEncoder(passwordEncoder());
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception{
       http.authorizeHttpRequests().anyRequest().authenticated()
               .and().formLogin().loginPage("/login")
               .permitAll()
                .and()
            .logout()                                    
                .permitAll();;
   }

}
