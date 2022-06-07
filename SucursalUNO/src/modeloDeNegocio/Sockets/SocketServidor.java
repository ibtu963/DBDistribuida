/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDeNegocio.Sockets;

/**
 *
 * @author ibtu9
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class SocketServidor extends Observable implements Runnable {
 
    private int puerto;
 
    public SocketServidor(int puerto) {
        this.puerto = puerto;
    }
 
    @Override
    public void run() {
 
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
 
        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");
 
            //Siempre estara escuchando peticiones
            while (true) {
 
                //Espero a que un cliente se conecte
                sc = servidor.accept();
 
                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                
                //Leo el mensaje que me envia
                String mensaje = in.readUTF();
 
                System.out.println(mensaje);
 
                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();
                 
                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");
 
            }
 
        } catch (IOException ex) {
            Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
 
}