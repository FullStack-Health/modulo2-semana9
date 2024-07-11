package com.example.aula11.infra.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

@Configuration
@EnableWebSecurity //indica que essa classe configura e ativa a segurança do spring security
@EnableMethodSecurity // segurança a nível de métodos
public class SecurityConfig {

    @Value("${jwt.public.key}") //busca o valor da application.propperties ou variáveis de ambiente
    private RSAPublicKey pub; // chave publica

    @Value("${jwt.private.key}")
    private RSAPrivateKey priv; // chave privada

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(//lambda para definir quais endpoints serão liberados e quais não
                        auth -> auth // define quais métodos não precisam de permissão para acesso e quais precisam
                                .requestMatchers(HttpMethod.GET, "/hello")// para a requisição com /hello
                                .permitAll() // permite todos os acessos, aplica para o comando anterior
                                .requestMatchers(HttpMethod.POST, "/hello", "/login").permitAll()
                                .anyRequest() // demais requisições
                                .authenticated() // todas as requisições precisam de autorização
                )
                .csrf(csrf -> csrf.disable())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // define que o jwt servidor de recursos vai usar as configurações padrão
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // define que cada requisição precisa de autenticação, e no caso vamos usar o toke jwt
        ;

        return http.build(); // transforma as configurações de HttpSecurity em SecurityFilterChain
    }


    @Bean // usuário em memória que é deletado ao encerrar o programa
    UserDetailsService users() { // Gerencia os usuários do sistema
        return new InMemoryUserDetailsManager( // cria um usuário em memória
                User.withUsername("user")
                        .password("{noop}password")
                        .authorities("app") //perfil de acesso desse usuário
                        .build()
        );
    }


    @Bean // criador de JWT
    JwtEncoder jwtEncoder() throws ParseException, JOSEException {
        JWK jwk = new RSAKey.Builder(pub).privateKey(this.priv).build(); // cria um gerenciador de chaves no java, esse recebe a chave publica e privada
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean // leitor/validador de JWT
    JwtDecoder jwtDecoder() throws ParseException, JOSEException {
        return NimbusJwtDecoder.withPublicKey(this.pub).build();
    }

    @Bean // Encripta senhas, quando recebemos senhas salvamos a senha encriptada e
          // validamos através do encriptador
          // cria um hash para as nossa senhas
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(); // codifica as senhas com o padrão BCrypt
    }

}