import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Ejercicio2 {



    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        int NumeroBytesMensaje = 48;          //3 bloques para cifrar AES
        byte[] text = Util.mensajeAleatorio(NumeroBytesMensaje);

        byte[] mascara = GenerarMascaraBitAleatorio(NumeroBytesMensaje);
        byte[] texto_modificado = Util.xor(text, mascara);
        System.out.printf("Original:         %s\nMascara aplicada: %s\nModificado:       %s\n\n",
                Util.bytesToHexString(text), Util.bytesToHexString(mascara), Util.bytesToHexString(texto_modificado));


        System.out.println("Evaluando para cifrado AES con modo ECB:");
        Analisis_para_cada_modo("AES/ECB/NoPadding", text, texto_modificado, NumeroBytesMensaje);
        System.out.println("Evaluando para cifrado AES con modo CFB:");
        Analisis_para_cada_modo("AES/CFB/NoPadding", text, texto_modificado, NumeroBytesMensaje);
        System.out.println("Evaluando para cifrado AES con modo CTR:");
        Analisis_para_cada_modo("AES/CTR/NoPadding", text, texto_modificado, NumeroBytesMensaje);
        System.out.println("Evaluando para cifrado AES con modo OFB:");
        Analisis_para_cada_modo("AES/OFB/NoPadding", text, texto_modificado, NumeroBytesMensaje);
        System.out.println("Evaluando para cifrado AES con modo CBC:");
        Analisis_para_cada_modo("AES/CBC/NoPadding", text, texto_modificado, NumeroBytesMensaje);
    }


    public static void Analisis_para_cada_modo(String modo, byte[] texto, byte[] texto_modificado, int NumeroBytesMensaje)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        int BitsCambiados = 0;
        String CipheringAlgorithm = "AES";

        Cifrador AES = new Cifrador(CipheringAlgorithm, modo, NumeroBytesMensaje);
        byte[] cifrado = AES.Cifrar(texto);
        byte[] cifrado_modificado =  AES.Cifrar(texto_modificado);
        BitsCambiados = Util.contarDiferencias(cifrado,cifrado_modificado);

        System.out.printf("Cifrado del original  :%s \n", Util.bytesToHexString(cifrado));
        System.out.printf("Cifrado del modificado:%s \n", Util.bytesToHexString(cifrado_modificado));
        System.out.printf("Numero de bits diferentes: %d\n\n", BitsCambiados);
    }


    public static byte[] GenerarMascaraBitAleatorio(int NumeroBytesBloque) {
        int randomNum = (int) (Math.random() * 16);
        byte[] mascara = new byte[NumeroBytesBloque];
        mascara[randomNum] = 1;
        return  mascara;
    }
}
