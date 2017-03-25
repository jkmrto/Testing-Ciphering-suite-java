import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CifradoAES {
    private KeyGenerator keygenerator;
    private  SecretKey myAESKey;
    private Cipher cipher;

    public CifradoAES() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        keygenerator = KeyGenerator.getInstance("AES");
        myAESKey = keygenerator.generateKey();
        cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, myAESKey);
    }


    public byte[] Cifrar(byte[] texto) throws BadPaddingException, IllegalBlockSizeException {
        byte[] cifrado = cipher.doFinal(texto);
        return cifrado;
    }

}
