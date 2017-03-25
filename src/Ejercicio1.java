import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class Ejercicio1 {

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {

        float DESede100 = Ejercicio1_EvaluarCambios(100, "DESede", 8, "DESede/ECB/NoPadding");
        float DESede1000 = Ejercicio1_EvaluarCambios(1000, "DESede", 8, "DESede/ECB/NoPadding");
        float DES100 = Ejercicio1_EvaluarCambios(100, "DES", 8, "DES/ECB/NoPadding");
        float DES1000 = Ejercicio1_EvaluarCambios(1000, "DES", 8,"DES/ECB/NoPadding");
        float AES100 = Ejercicio1_EvaluarCambios(100, "AES", 16, "AES/ECB/NoPadding");
        float AES1000 = Ejercicio1_EvaluarCambios(1000, "AES", 16, "AES/ECB/NoPadding");

        System.out.println("El porcentaje de bits cambiados para cada cifrado es (100 y 1000 ejecuciones):");
        System.out.printf("3DES\t\t%.2f\t\t%.2f\n", DESede100, DESede1000);
        System.out.printf(" AES\t\t%.2f\t\t%.2f\n", AES100, AES1000);
        System.out.printf(" DES\t\t%.2f\t\t%.2f\n", DES100, DES1000);

        //System.out.print(DES1);

    }

    public static float Ejercicio1_EvaluarCambios(int NumeroEjecuciones, String CipheringAlgorithm, int NumeroBytesMensaje, String CipheringMode)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        float Media_Cambios = 0;
        int NumeroBits = NumeroBytesMensaje * 8;
        int BitsCambiados= 0;

        for(int i=0; i < NumeroEjecuciones; i++){

            byte[] text = Util.mensajeAleatorio(NumeroBytesMensaje);
            byte[] texto_modificado = Util.xor(text, GenerarMascaraBitAleatorio(NumeroBytesMensaje));
            Cifrador AES = new Cifrador(CipheringAlgorithm, CipheringMode, NumeroBytesMensaje);
            byte[] cifrado = AES.Cifrar(text);
            byte[] cifrado_modificado =  AES.Cifrar(texto_modificado);
            BitsCambiados += Util.contarDiferencias(cifrado,cifrado_modificado);
        }
        Media_Cambios = (float)BitsCambiados/(NumeroEjecuciones * NumeroBits);
        return Media_Cambios * 100;
    }


    public static byte[] GenerarMascaraBitAleatorio(int NumeroBytesBloque) {
        int randomNum = (int) (Math.random() * NumeroBytesBloque);
        byte[] mascara = new byte[32];
        mascara[randomNum] = 1;
        return  mascara;
    }
}