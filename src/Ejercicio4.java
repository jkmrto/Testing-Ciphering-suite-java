import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Ejercicio4 {

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        int bloque = 1024 * 1024;

        float DESede_tiempo = Ejercicio4_TiempoEjecucion(100, "DESede", bloque, "DESede/ECB/NoPadding");
        float DESede_mb = (float)1/DESede_tiempo;

        float DES_tiempo = Ejercicio4_TiempoEjecucion(100, "DES", bloque, "DES/ECB/NoPadding");
        float DES_mb = (float)1/DES_tiempo;


        float AES_tiempo = Ejercicio4_TiempoEjecucion(100, "AES", bloque, "AES/ECB/NoPadding");
        float AES_mb = (float)1/AES_tiempo;

        System.out.println("Ejecucion en Java: s/Mb,   Mb/s:");

        System.out.printf("3DES\t\t%.5f s/MB,   %.5f MB/s\n", DESede_tiempo, DESede_mb);
        System.out.printf(" DES\t\t%.5f s/MB,   %.5f MB/s\n", DES_tiempo, DES_mb);
        System.out.printf(" AES\t\t%.5f s/MB,   %.5f MB/s\n", AES_tiempo, AES_mb);


        //System.out.print(DES1);

    }

    public static float Ejercicio4_TiempoEjecucion(int NumeroEjecuciones, String CipheringAlgorithm, int NumeroBytesMensaje, String CipheringMode)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        long startTime;
        long endTime;
        long TiempoAcumulado = 0;
        long Tiempo_medio_nanosegundos = 0;
        byte[] text;

        for(int i=0; i < NumeroEjecuciones; i++){
            text = Util.mensajeAleatorio(NumeroBytesMensaje);
            Cifrador C = new Cifrador(CipheringAlgorithm, CipheringMode, NumeroBytesMensaje);

            startTime = System.nanoTime();
            C.Cifrar(text);
            endTime = System.nanoTime();

            TiempoAcumulado += endTime - startTime;
        }

        Tiempo_medio_nanosegundos =  TiempoAcumulado/(NumeroEjecuciones);
        return (float)Tiempo_medio_nanosegundos/(long)1000000000;
    }
}