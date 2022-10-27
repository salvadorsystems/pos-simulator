package com.sanms.siso.eft.utils;

/**
 *
 * @author salvador
 */
public class ConHilosRunable implements Runnable{
    String atributo;

    public ConHilosRunable(int i)
    {
        atributo = "algo" + i;
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 3; i++)
        {
            Thread ch = new Thread(new ConHilosRunable(i));
            ch.start();
        }
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println(i + ": " + atributo);
        }
    }
}
