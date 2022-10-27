/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author salvador
 */
public class test {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor(); 
        Future<String> future = executor.submit(new Task());

        try {
            System.out.println("Started.."); 
            Cliente cli = new Cliente(); //Se crea el cliente
            System.out.println(future.get(4, TimeUnit.SECONDS)); 
            System.out.println("Finished!");
        } catch (Exception e) {
            System.out.println("Terminated!");
        }
        executor.shutdownNow();
    }

}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(4000);
        return "Ready";
    }
}


