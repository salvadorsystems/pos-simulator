/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.constantes;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author salvador
 */
public class Cliente extends ConectarServidor
{
    public Cliente() throws IOException{super("cliente");} //Se usa el constructor para cliente de Conexion

    public void startClient() //Método para iniciar el cliente
    {
        try
        {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());

            //Se enviarán dos mensajes
            for (int i = 0; i < 1; i++)
            {
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("0200E23A440188E18002000000000000010016888888880000000093550007211402010009661402010721072159420511100000007963110000004040400000000094322340880299999490000   PROCESOS MC PRUEBAS SA                  00898491260604012CC20003547860021530150299999429900401");
            }

            cs.close();//Fin de la conexión

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
