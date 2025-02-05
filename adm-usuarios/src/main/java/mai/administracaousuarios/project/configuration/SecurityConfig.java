package mai.administracaousuarios.project.configuration;

import mai.administracaousuarios.api.middleware.AuthFilter;
import mai.administracaousuarios.model.enums.Roles;
import mai.administracaousuarios.service.UsuarioAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthFilter authFilter;
    private final UsuarioAuthenticationService usuarioAuthenticationProvider;

    public SecurityConfig(AuthFilter authFilter, UsuarioAuthenticationService usuarioAuthenticationProvider) {
        this.authFilter = authFilter;
        this.usuarioAuthenticationProvider = usuarioAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http.csrf(crfs -> crfs.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/usuario/find/**", "/swagger-ui/**", "/v3/api-docs/**", "/empresa/find/**",
                                "/estado/find/**", "/usuario/find/**", "/cidade/find/**", "/logradouro/find/**", "/empresas/**").hasAnyAuthority(Roles.toStringArrayFilter("USER"))
                        .requestMatchers(HttpMethod.GET, "/**", "/criar-empresa", "/actuator/**/**").permitAll()
                        // TODO: verificar a diferença entre hasRole e hasAlthority
                        .requestMatchers(HttpMethod.POST, "/register/**", "/usuario/create/**", "/inserir-empresa").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/usuario/update/**", "/cidade/update/**", "/estado/update/**", "/empresa/update/**", "/logradouro/update/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuario/delete/**", "/empresa/delete/**", "/adm/empresa/{id}/delete").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/empresa/update/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login")
                        .defaultSuccessUrl("/empresas")
                        .failureUrl("/login?error=true").permitAll())
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout=true"))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return usuarioAuthenticationProvider;
    }
}
