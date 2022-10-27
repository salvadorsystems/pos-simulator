package com.sanms.siso.eft.utils;

import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.proxy.ProxyCommResult;
import com.sanms.siso.eft.proxy.ProxyResult;
import com.sft.core.socket.DefaultSocketClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author salvador
 */
public class MiHilo implements Runnable {

    Thread hilo;
    DefaultSocketClient socketProcessor;

    //Construye un nuevo hilo.
    MiHilo(String nombre) {
        hilo = new Thread(this, nombre);
    }

    //Un método de fábrica que crea e inicia un hilo.
    public static MiHilo crearYComenzar(String nombre) {
        MiHilo miHilo = new MiHilo(nombre);
        miHilo.hilo.start(); //Inicia el hilo
        return miHilo;
    }

    //Punto de entrada del hilo
    //Los hilos comienzan a ejecutarse aquí
    @Override
    public void run() {
        System.out.println(hilo.getName() + " iniciando.");
        String linea = "activo";
        
        try {
            for (int count = 0; count < 2; count++) {
                System.out.println("En " + hilo.getName() + ", el recuento es " + count);
                //Thread.sleep(15);
                //EFTMCProxy proxy = new Proxy();
                linea = leerAchivo();
                //System.out.println("linea: "+linea);
                //proxy.setup("192.168.115.149", "4000", false, 30);
                while(linea.equals("activo")){
                    linea = leerAchivo();
                }
                
               /** Thread.sleep(10);
                EFTMCProxyResult apiResult = new EFTMCProxyResult();
                EFTMCProxyCommResult resultProxy = proxy.process("0200E23A440188E08002000000000400010016459334000207305943000007251538170010041538170725072559420511100000007963110000004040400000000098122340880299999490000   PROCESOS MC PRUEBAS SA                  604012CC200035477620108012321000349631  0021530150299999429900401", apiResult);
                System.out.println("resultProxy.getResponse: " + resultProxy.getStringResponse());
                while (!resultProxy.getStringResponse().equals("")) {
                    System.out.println("ingreso while");
                    proxy.release();
                    Thread.sleep(10);
                    break;
                }*/
               Thread.sleep(10);
                
            }
        } catch (InterruptedException exc) {
            System.out.println(hilo.getName() + " interrumpudo.");
        }
        System.out.println(hilo.getName() + " terminado.");
    }

    public static String leerAchivo() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea1 = "activo";
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\SISO\\pruebas\\fichero.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                //System.out.println(linea);
                linea1 = linea;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return linea1;
    }
}
