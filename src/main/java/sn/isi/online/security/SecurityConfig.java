package sn.isi.online.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Boot auto-configuration attempts to automatically configure your Spring
 * application based on the jar dependencies that you have added. For example, if
 * HSQLDB is on your classpath, and you have not manually configured any database
 * connection beans, then Spring Boot auto-configures an in-memory database.
 */

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT email as principal, password as credentials, etat FROM professeur WHERE email =  ?")
			.authoritiesByUsernameQuery("SELECT email as principal, nom as role FROM professeurs_roles WHERE email = ?")
			.passwordEncoder(new BCryptPasswordEncoder());
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin();//pour afficher un formulaire de connexion par defaut
		http.formLogin().loginPage("/online/login");//personnaliser le form de login
		//les droits dun USER
		http.authorizeRequests().antMatchers("/online/cours").hasAnyAuthority("ROLE_USER", "ROLE_ETUDIANT", "ROLE_PROFESSEUR");
		//les droits du role ETUDIANT
		http.authorizeRequests().antMatchers("/online/etudiants").hasAnyAuthority("ROLE_ETUDIANT", "ROLE_PROFESSEUR");
		//les droits du role PROFESSEUR
		http.authorizeRequests().antMatchers("/online/professeurs").hasAuthority("ROLE_PROFESSEUR");
		//gestion des droits
		http.exceptionHandling().accessDeniedPage("/online/403");
		http.csrf().disable();
	}

}
