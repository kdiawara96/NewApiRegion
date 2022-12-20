package region.ml.tourismAppli.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController

public class AuthController {

    //Pour generer un token à l'auth nous devons injecter jwt encoder

    private JwtEncoder jwtEncoder;

    private JwtDecoder jwtDecoder;

    //Pour faire l'auth j'ai besoin d'utiliser ceci
    @Autowired
    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;


  public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService){
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
       this.authenticationManager = authenticationManager;
       this.userDetailsService = userDetailsService;
    }


    @PostMapping("/authentification")
    //(1)   Pour faire l'authentification j'aurais besoin d'envoyé le userName et le mot de pass
    //(1)   Ici comme nous utilisons l'auth basic (dans la requête http nous envoyons username et le role en mode base64 et spring securité va s'en charger de l'auth )nous pouvons utiliser l'autentification
    //(1)   ça va nous permettre de recuperer username et les roles

    //(2)  Utilisateur va envoyé le userName et le password et nous allons faire l'authentification et pour le faire je dois déclarer  l'authenticationManager
    //(2) Parce que dans le auth basique ces spring qui le fait automatiquement (dans la requête http nous envoyons username et le role en mode base64 et spring securité va s'en charger de l'auth )nous pouvons utiliser l'autentification)
    //(2)  Mais maintenant c'est à nous de le faire au niveau du code


    // tout d'abord il faut souligner qu'il n'est pas récommander de créer un refresh token pour les
    //application web mais pour les application mobile
    //Nous allons créer un booléan


    public ResponseEntity<Map<String, String>> jwtToken(
            String grantType,
            String email,
            String password,
            boolean withRefreshToken,
            String refreshToken
    ){

        //Nous allons demander à spring ici authentifie nous cette utilisateur
        //pour cela nous allons lui trensmettre un objet New UsernamePasswordAuthenticationToken(username, password)

        String subject = null;
        String scope = null;

        if (grantType.equals("password")){

            System.err.println("------------"+email+"---------------------"+password);
            Authentication authentication = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(email, password)

            );

            subject= authentication.getName();

            // Nous allons prendre les autorisations et les séparer avec de l'espace et cela va nous donner
            //Une chaine de caractère

            scope = authentication
                    .getAuthorities()
                    .stream()
                    //Cette maniere avec lamda ou cette partie commenter
                    .map(GrantedAuthority::getAuthority)
                    //.map(auth-> auth
                    // .getAuthority())
                    .collect(Collectors
                            .joining(" "));


        }else if(grantType.equals("refreshToken")){
            if (refreshToken == null){
                //Un message si la durée du refresh token à expiré
                return new ResponseEntity<>(Map.of("errorMessage : ","Refresh Token is requeried"), HttpStatus.UNAUTHORIZED);
            }
            //A partir du refresh token je connais le user name
            //et à partir de l'user je recupère les roles de cette user

            Jwt decodeJWT = null;
            try {
                //quand nous decodons il va verifier s'il n'est pas expirer
                decodeJWT = jwtDecoder.decode(refreshToken);
            } catch (JwtException e) {
                return new ResponseEntity<>(Map.of("errorMessage :",e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
            //String subjet = decodeJWT.getSubject();
            subject = decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        }


        Map<String, String> idToken = new HashMap<>();


        //l'objet instance va nous permettre de capturer la date instante
        Instant instant = Instant.now();





        //Dans le jwt nous avons un ensemble de claim

        //Nous avons subject qui represente username et nous allons recuperer avec le authentication.getName



        //expirestAt va contenir la date d'expiration du token et nous spefissons la date d'expiration



        // claim va contenir les autorisation

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                //issuedAt contient la date de la generation du token c'est pour ça nous utilisons instant
                .issuedAt(instant)
                //expirestAt va contenir la date d'expiration du token et nous spefissons la date d'expiration
                //Nous allons mettre une condition si refreshToken est demander nous donnons 5 minutes au access token sinon nous aurons 30 minutes
                .expiresAt(instant.plus(withRefreshToken?10:30, ChronoUnit.MINUTES))
                //issuer va contenir le nom de l'application qui à generer le token
                .issuer("security-com")
                //Va nous permettre d'envoyer les rôles
                .claim("scope", scope)
                .build();
        String jwtAccesToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        idToken.put("accessToken", jwtAccesToken);
        //lol
        if(withRefreshToken){
            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    //Nous allons mettre une condition si refreshToken est demander nous donnons 5 minutes sinon nous aurons 30 minutes
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("security-com")
                    //dans le refresh Token nous n'avons pas besoin d'envoyer les rôles du coup nous allons commenter la ligne suivante
                    // .claim("scope", scope)
                    .build();
            String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            idToken.put("refreshToken",jwtRefreshToken);
        }
        return  new ResponseEntity<>(idToken, HttpStatus.OK);

    }
}
