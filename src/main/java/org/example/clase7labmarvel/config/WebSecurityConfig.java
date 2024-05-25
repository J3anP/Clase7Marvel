package org.example.clase7labmarvel.config;

import jakarta.servlet.http.HttpSession;
import org.example.clase7labmarvel.entity.User;
import org.example.clase7labmarvel.repository.CharacterRepository;
import org.example.clase7labmarvel.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.sql.DataSource;

@SuppressWarnings("removal")
@Configuration
public class WebSecurityConfig {

    final CharacterRepository characterRepository;
    final UserRepository userRepository;
    final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource, CharacterRepository characterRepository, UserRepository userRepository) {
        this.dataSource = dataSource;
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/submitLoginForm")
                .successHandler((request, response, authentication) -> {

                    DefaultSavedRequest defaultSavedRequest =
                            (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", userRepository.findByEmail(authentication.getName()));

                    //si vengo por url -> defaultSR existe
                    if (defaultSavedRequest != null) {
                        String targetURl = defaultSavedRequest.getRequestURL();
                        new DefaultRedirectStrategy().sendRedirect(request, response, targetURl);
                    } else { //estoy viniendo del botón de login
                        String rol = "";
                        for (GrantedAuthority role : authentication.getAuthorities()) {
                            rol = role.getAuthority();
                            break;
                        }

                        response.sendRedirect("/personaje/list");
                    }
                });

        http.authorizeHttpRequests()
                .requestMatchers("/personaje", "/personaje/list").hasAnyAuthority("USER", "EDITOR","ADMIN")
                .requestMatchers("/personaje/new","/personaje/edit").hasAnyAuthority("EDITOR","ADMIN")
                .requestMatchers("/personaje/delete").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll();

        http.logout()
                .logoutSuccessUrl("/loginForm")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        //para loguearse sqlAuth -> username | password | enable
        String sqlAuth = "SELECT email, password, status FROM users WHERE email = ?";

        //para autenticación -> username, nombre del rol
        String sqlAuto = "SELECT u.email, r.name FROM users u INNER JOIN roles r ON u.idrol = r.id WHERE u.email = ?";

        users.setUsersByUsernameQuery(sqlAuth);
        users.setAuthoritiesByUsernameQuery(sqlAuto);

        return users;
    }
}








