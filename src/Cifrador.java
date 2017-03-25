import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Cifrador {
    private KeyGenerator keygenerator;
    private final String cipheringAlgorithm;
    private final String cipheringMode;
    private SecretKey myKey;
    private Cipher cipher;
    private Cipher decipher;

    public Cifrador(String CipheringAlgorithm, String CipheringMode, int NumeroBytesMensaje)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        keygenerator = KeyGenerator.getInstance(CipheringAlgorithm);
        cipheringAlgorithm = CipheringAlgorithm;
        cipheringMode = CipheringMode;
        myKey = keygenerator.generateKey();


        if (Objects.equals("AES/CFB/NoPadding", CipheringMode)
                ||Objects.equals("AES/CTR/NoPadding", CipheringMode)
                ||Objects.equals("AES/OFB/NoPadding", CipheringMode)
                ||Objects.equals("AES/CBC/NoPadding", CipheringMode)){
            byte[] iv = Util.mensajeAleatorio(16);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher = Cipher.getInstance(CipheringMode);

            cipher.init(Cipher.ENCRYPT_MODE, myKey, ivParameterSpec);

            decipher = Cipher.getInstance(CipheringMode);
            decipher.init(Cipher.DECRYPT_MODE, myKey, ivParameterSpec);
        }else{
            cipher = Cipher.getInstance(CipheringMode);
            cipher.init(Cipher.ENCRYPT_MODE, myKey);

            decipher = Cipher.getInstance(CipheringMode);
            decipher.init(Cipher.DECRYPT_MODE, myKey);
        }


    }

    public byte[] Cifrar(byte[] texto) throws BadPaddingException, IllegalBlockSizeException {
        return cipher.doFinal(texto);
    }
    public byte[] Descifrar(byte[] texto) throws BadPaddingException, IllegalBlockSizeException {
        return decipher.doFinal(texto);
    }

}

