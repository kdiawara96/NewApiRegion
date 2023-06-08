package region.ml.tourismAppli.SecurityConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;



@ConfigurationProperties(prefix = "rsa")
//@ConfigurationPropertiesScan("rsa")
public record RsakeysConfig(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}


//----------------------------------AUTRE MANIERE-----------------------------------

/*
 * @Configuration
@ConfigurationProperties(prefix = "rsa")
public class RsakeysConfig {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
 */