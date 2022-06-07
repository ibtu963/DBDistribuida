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
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class SocketCliente implements Runnable {
    private String host;
    private int puerto;
    private String mensaje;
 
    public SocketCliente(String host,int puerto, String mensaje) {
        this.host = host;
        this.puerto = puerto;
        this.mensaje = mensaje;
    }
 
    @Override
    public void run() {
        //Host del servidor
        //Puerto del servidor
        DataOutputStream out;
 
        try {
      
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(host, puerto);
 
            out = new DataOutputStream(sc.getOutputStream());
 
            //Envio un mensaje al cliente
            out.writeUTF(mensaje);
 
            sc.close();
 
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
     
    }
    }
}
