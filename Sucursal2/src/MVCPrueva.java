
import Vista.Ventas;
import Vista.insertar;
import Vista.main;
import controlador.ControladorView;
import java.util.Observer;
import modeloDeNegocio.ComunicacionServer.ComunicacionServer;
import modeloDeNegocio.Sockets.SocketServidor;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ibtu9
 */
public class MVCPrueva {
    public static void main(String[]args){
        Ventas vn = new Ventas();
        main mn = new main();
        insertar ins = new insertar();
        
        ComunicacionServer model = new ComunicacionServer();
        ControladorView ctrl = new ControladorView(mn,model,vn,ins);
      //  ctrl.Iniciar();        
        mn.setVisible(true);
     //   mn.lblLoading.setVisible(false);
    }
}
