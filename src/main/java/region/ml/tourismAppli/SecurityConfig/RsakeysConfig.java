package region.ml.tourismAppli.SecurityConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;



@ConfigurationProperties(prefix = "rsa")
//@ConfigurationPropertiesScan("rsa")
public record RsakeysConfig(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
