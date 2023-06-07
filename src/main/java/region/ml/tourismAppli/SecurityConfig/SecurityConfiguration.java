package region.ml.tourismAppli.SecurityConfig;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import region.ml.tourismAppli.Services.UtilisateursService;
import region.ml.tourismAppli.modele.Utilisateurs;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//pour spécifier les role dans le controller il faut cette annotation dans le security config



public class SecurityConfiguration {

    //Nous allons injecter ces attributs et declarer dans le constructeur
    private RsakeysConfig rsakeysConfig_;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    private UtilisateursService utilisateursService;

    public SecurityConfiguration(RsakeysConfig rsakeysConfig_, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UtilisateursService utilisateursService) {
        this.rsakeysConfig_ = rsakeysConfig_;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.utilisateursService = utilisateursService;
    }


    @Bean
    public AuthenticationManager authenticationManager(){
        var authProvider = new DaoAuthenticationProvider();

        //Nous allons attacher le passwordEncoder que nous volons utiliser
        authProvider.setPasswordEncoder(passwordEncoder);
        //
        authProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(authProvider);
    }


    public UserDetailsService userDetailsService(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

                Utilisateurs utilisateurs = utilisateursService.readByEmail(email);

                Collection<GrantedAuthority> authorities = new ArrayList<>();
                utilisateurs.getRole().forEach(role->{
                    authorities.add(new SimpleGrantedAuthority(role.getRole()));
                });
                return new User(utilisateurs.getEmail(), utilisateurs.getPassword(),authorities);
            }
        });

        return null;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws  Exception{


        return httpSecurity
                //Pour eviter les attaques csrf
                .csrf(csrf -> csrf.disable())
                //Il donner l'autorisation au user à s'authentifier à travers ce url
                .authorizeRequests(auth -> auth.antMatchers("/authentification/**").permitAll())
                /*.authorizeRequests(auth-> {
                             try {
                                 //auth.anyRequest().authenticated()
                                       //  .and()
                                        // .oauth2Login();
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }
                         }
                 )*/
                .formLogin(withDefaults())

                //.sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)


                .httpBasic(Customizer.withDefaults())
                .build();
    }


    //Pour verifier la signature du token j'ai besoin du public key
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsakeysConfig_.publicKey()).build();
    }

    //Pour signer le token j'ai besoin d'utiliser le private key et public key
    @Bean
    JwtEncoder jwtEncoder(){

        JWK jwk = new RSAKey.Builder(rsakeysConfig_.publicKey()).privateKey(rsakeysConfig_.privateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    //Nous avons besoin de créer un netpoint qui permet de generer le token



}
