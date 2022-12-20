package region.ml.tourismAppli;


import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenerateKeyPair {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException{

        //on genère une clé de type RSA
        KeyPairGenerator keyPairGenetor = KeyPairGenerator.getInstance("RSA");

        //ça nous permet de generé une clé pair
        var keyPair = keyPairGenetor.generateKeyPair();
        byte[] pub = keyPair.getPublic().getEncoded();
        byte[] pri = keyPair.getPrivate().getEncoded();

        //cela va nous permettre de generer un fichier .pem
        PemWriter pemWriter = new PemWriter((new OutputStreamWriter(new FileOutputStream("public.pem"))));
        //l'entête du fichier
        PemObject pemObject = new PemObject("PUBLIC KEY", pub);
        //Ici nous enregistrons en base64
        pemWriter.writeObject(pemObject);
        //Et ici nous fermons
        pemWriter.close();

        PemWriter pemWriter2 = new PemWriter(new OutputStreamWriter(new FileOutputStream("private.pem")));
        PemObject pemObject2 = new PemObject("PRIVATE KEY", pri);
        pemWriter2.writeObject(pemObject2);
        pemWriter2.close();
    }
}
