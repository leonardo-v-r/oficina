package web.oficina.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/usuario/abrircadastrar").permitAll()
			.antMatchers("/usuario/cadastroNovo").permitAll()
			.anyRequest().authenticated();
        
        http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll();
        
        http.logout()
        .logoutRequestMatcher(
        		new AntPathRequestMatcher("/logout", "GET")
        ).logoutSuccessUrl("/login");
        return http.build();
    }
    
    @Bean
 	public UserDetailsService userDetailsService() {    	
          JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
          manager.setUsersByUsernameQuery("select login, senha, ativo "
        		  						+ "from usuario "
        		  						+ "where login = ?");
          manager.setAuthoritiesByUsernameQuery(
				"SELECT tab.login, papel.nome "
				+ "FROM (SELECT usuario.login, usuario.codigo FROM usuario WHERE login = ?) as tab "
				+ "INNER JOIN usuario_papel ON codigo_usuario = tab.codigo "
				+ "INNER JOIN papel ON codigo_papel = papel.codigo");
          return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		String idEnconder = "argon2";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("argon2", new Argon2PasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idEnconder, encoders);
		return passwordEncoder;
	}
}
