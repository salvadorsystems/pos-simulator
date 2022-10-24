/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.constantes;

import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.proxy.ProxyCommResult;
import com.sanms.siso.eft.proxy.ProxyResult;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
//import java.util.logging.Level;
import org.slf4j.Logger;
//import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author salvador
 */
public class NewClass extends Thread {



 @Override
 public void run()
  {
              try {
            Proxy proxy = new Proxy();
            int conecct = proxy.setup("192.168.115.149", "4000", false, 30);
            ProxyResult apiResult = new ProxyResult(); 
            System.out.println("connect: " + conecct);

            System.out.println("apiResult: " + apiResult.getResult());

        } catch (Exception e) {

            System.out.println(e);
        }
  }
    
   /* public static void main(String[] args) throws IOException {
        /**
         * Cliente cli = new Cliente(); //Se crea el cliente
         *
         * System.out.println("Iniciando cliente\n"); cli.startClient(); //Se
         * inicia el cliente
         */
        //Socket socket = new Socket("192.168.184.149", 4000);

        /**
         * try (Socket socket = new Socket("192.168.184.149", 4000)) {
         *
         *
         * while (true) { System.out.println("Cliente conectado");
         *
         * }
         * }
         */
        
        
        


   /* }*/

}
