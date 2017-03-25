import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by usuario on 08/12/2016.
 */
public class Ejercicio3  {


    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        System.out.println("Evaluando para cifrado AES con modo ECB:");
        Analisis_para_cada_modo("AES/ECB/NoPadding");
        System.out.println("Evaluando para cifrado AES con modo CFB:");
        Analisis_para_cada_modo("AES/CFB/NoPadding");
        System.out.println("Evaluando para cifrado AES con modo CTR:");
        Analisis_para_cada_modo("AES/CTR/NoPadding");
        System.out.println("Evaluando para cifrado AES con modo OFB:");
        Analisis_para_cada_modo("AES/OFB/NoPadding");
        System.out.println("Evaluando para cifrado AES con modo CBC:");
        Analisis_para_cada_modo("AES/CBC/NoPadding");
    }


    public static void Analisis_para_cada_modo(String modo)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        int NumeroBytesMensaje = 48;          //3 bloques para cifrar AES);
        byte[] texto = Util.mensajeAleatorio(NumeroBytesMensaje);
        byte[] mascara = GenerarMascaraBitAleatorio(NumeroBytesMensaje);
        String CipheringAlgorithm = "AES";

        Cifrador AES = new Cifrador(CipheringAlgorithm, modo, NumeroBytesMensaje);
        byte[] cifrado = AES.Cifrar(texto);
        byte[] cifrado_modificado =   Util.xor(cifrado, mascara);

        byte[] descifrado = AES.Descifrar(cifrado);
        byte[] descifrado_modificado = AES.Descifrar(cifrado_modificado);

        int BitsCambiados = Util.contarDiferencias(descifrado, descifrado_modificado);

        System.out.printf("Mensaje Original             : %s \n", Util.bytesToHexString(texto));
        System.out.printf("Mensaje Cifrado              : %s \n", Util.bytesToHexString(cifrado));
        System.out.printf("Mascara                      : %s \n", Util.bytesToHexString(mascara));
        System.out.printf("Mensaje Cifrado Modificado   : %s \n", Util.bytesToHexString(cifrado_modificado));
        System.out.printf("Mensaje Descifrado Original  : %s \n", Util.bytesToHexString(descifrado));
        System.out.printf("Mensaje Descifrado Modificado: %s \n", Util.bytesToHexString(descifrado_modificado));
        System.out.printf("Numero de bits diferentes: %d\n\n", BitsCambiados);

    }


    public static byte[] GenerarMascaraBitAleatorio(int NumeroBytesBloque) {
        int randomNum = (int) (Math.random() * 16);
        byte[] mascara = new byte[NumeroBytesBloque];
        mascara[randomNum] = 1;
        return  mascara;
    }
}
