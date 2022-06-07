package controlador;



import Vista.Ventas;
import Vista.insertar;
import Vista.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Float.parseFloat;
import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloDeNegocio.ComunicacionServer.ComunicacionServer;
import modeloDeNegocio.Sockets.SocketCliente;
import modeloDeNegocio.Sockets.SocketServidor;
import modeloDeNegocio.models.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ibtu9
 */
public class ControladorView extends MouseAdapter implements ActionListener, Observer {
    
    private main vista;
    private Ventas venta;
    private ComunicacionServer modeloNegocio; 
    private SocketServidor server;
    private insertar insertar;
    
    private int contPedido= 0;
    private String ped;
    private String auxPed;
      
    
 
    public ControladorView(){   
        
    }
    private void crearModeloTabla(){
            //modelo.addColumn("ID");
            venta.modelo.addColumn("IDITEM");
            venta.modelo.addColumn("NOMBRE");//IdItem
            venta.modelo.addColumn("TIPO");
            venta.modelo.addColumn("PRECIO");
            venta.modelo.addColumn("CANTIDAD");
            venta.modelo.addColumn("SUBTOTAL");
            venta.tabla.setModel(venta.modelo);
            venta.tabla.setEnabled(true);
        
           venta.tabla.getColumnModel().getColumn(0).setMaxWidth(0);
           venta.tabla.getColumnModel().getColumn(0).setMinWidth(0);
           venta.tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
           venta.tabla.getColumnModel().getColumn(0).setResizable(false);
        
        
            venta.tabla.getColumnModel().getColumn(2).setMaxWidth(0);
            venta.tabla.getColumnModel().getColumn(2).setMinWidth(0);
            venta.tabla.getColumnModel().getColumn(2).setPreferredWidth(0);
            venta.tabla.getColumnModel().getColumn(2).setResizable(false);
        
    }
    
    public ControladorView(main vista, ComunicacionServer modeloNegocio, Ventas venta, insertar insertar){
        this.insertar = insertar;
        this.vista = vista;
        this.venta = venta;
        vista.getRootPane().setDefaultButton(vista.btnComida);
        server = new SocketServidor(modeloNegocio.varUni.Puerto3);
         Thread t = new Thread(server);
        t.start();
        server.addObserver(this);
        //Botones del main
        this.vista.btnVentas.addActionListener(this); 
        this.vista.btnPostres.addActionListener(this);
        this.vista.btnComida.addActionListener(this);
        this.vista.btnBebidas.addActionListener(this);
        this.vista.btnAdd.addActionListener(this);
        this.vista.btnEnviarChat.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEditar.setVisible(false);
        
        
        //Botones del insertar
         this.insertar.btnInsertar.addActionListener(this);
        this.insertar.btnActualizar.addActionListener(this);
        this.insertar.btnBorrar.addActionListener(this);
     
       
        //Botones de la venta
        this.venta.btnAgregar.addActionListener(this);
        this.venta.tipoItemCB.addActionListener(this);
        this.venta.btnCancelar .addActionListener(this);
        this.venta.btnConfirmarVenta.addActionListener(this);
        //this.vista.ta
        this.vista.tabla.addMouseListener(this);
        
        this.modeloNegocio = modeloNegocio;
        
        }

     @Override
    public void mouseReleased(MouseEvent me) {
            if(me.getSource() == vista.tabla){
                  int fila= vista.tabla.getSelectedRow();
        if (fila>=0) {
            getPostreTabla(fila);
            vista.btnEditar.setVisible(true);       
        }
            }
            
      
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
   
        //Botones de la vista principa
    if(e.getSource()==vista.btnPostres){
        actBtnPostres();
    }
    if(e.getSource()==vista.btnComida){ 
        actBtnComidas();
    }
    if(e.getSource()==vista.btnBebidas){
        actBtnBebidas();  
    }
    if(e.getSource()==vista.btnVentas){
        actBtnVentas();
    }
    if(e.getSource()== vista.btnAdd){
           actBtnAdd();
    }
    if(e.getSource()== vista.btnEditar){
           actBtnEdit();
    }
    if(e.getSource()== vista.btnEnviarChat){
           actBtnEnviarChat();
    }
    //Botones de las Ventas
    if(e.getSource()==venta.btnCancelar){
        venta.setVisible(false);
        vista.setVisible(true);
    }
    if(e.getSource()== venta.btnAgregar){
       String  tipoItem = String.valueOf(venta.tipoItemCB.getSelectedItem());
       if(!venta.tabla.isEnabled()){
            crearModeloTabla();
            addDatos(tipoItem,venta.inputName.getText());
       }else{
            addDatos(tipoItem,venta.inputName.getText());
         }
    }
        
    if(e.getSource()== venta.tipoItemCB){
        String  tipoItem = String.valueOf(venta.tipoItemCB.getSelectedItem());
        try {
            //Verificar Esto, unos se traen por Socket depende de la sucursal
            actBtnTipoItem(tipoItem);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    if(e.getSource()== venta.btnConfirmarVenta){

              String d1 = modeloNegocio.SocketVenta(venta);
              String[] aux = modeloNegocio.decifrarSocketVenta(d1);
              if(aux[0].equals("ventas")){
                   modeloNegocio.insertarVentas(modeloNegocio.AuxPedidosSocketMsj(aux[2],aux[1]), modeloNegocio.PedidosSocketMsj(aux[3]));     
              }
                System.out.println(d1);
        
             SocketCliente c = new SocketCliente(modeloNegocio.varUni.direccionIpSucu2,
             modeloNegocio.varUni.Puerto2, d1);
             Thread t = new Thread(c);
             t.start();
            
            c = new SocketCliente(modeloNegocio.varUni.direccionIpSucu1,
             modeloNegocio.varUni.Puerto1, d1);
             t = new Thread(c);
             t.start();
            JOptionPane.showMessageDialog(null,"Venta Éxitosa");
             venta.modelo.setRowCount(0);
             venta.tfCliente.setText("");
             venta.inputCantidad.setText("");
             venta.inputName.setText("");
        venta.setVisible(false);
        vista.setVisible(true);
    
    }
    
    //Esto es para lo de insertar 
    if(e.getSource() == insertar.btnInsertar){
        actBtnInsertar();
    }
    if(e.getSource()== insertar.btnBorrar){
           actBtnDeletePostre();
    }
    if(e.getSource()== insertar.btnActualizar){
        actBtnUpdatePostre();
    }
      
       
    }  
    
    private void actBtnTipoItem(String tipoItem) throws InterruptedException{
        switch(tipoItem){
            case "Postres" -> {
               actBtnPostres();
            }        
            case "Comidas" -> {
                actBtnComidas();
            }
            
            case "Bebidas" -> {
                 modeloNegocio.socketsGetBebidas();
                venta.completer = modeloNegocio.getNameBebidas(venta.inputName);
                }
           }    
        
    }
    
    private void actBtnEdit(){
        insertar.tfNombre.setText(modeloNegocio.Bebida.nombre);
        insertar.tfPrecio.setText(modeloNegocio.Bebida.precio);
        insertar.tfIngredientes.setText(modeloNegocio.Bebida.sabor);       
        insertar.tfDetalles.setText(modeloNegocio.Bebida.detalles);
        insertar.tfCantidadStock.setText(modeloNegocio.Bebida.cantidad);
        insertar.btnInsertar.setVisible(false);
        insertar.btnActualizar.setVisible(true);
        insertar.btnBorrar.setVisible(true);
        
        insertar.setVisible(true);
        vista.setVisible(false);
        
       
    }
    private void  actBtnEnviarChat(){
        vista.txtApend.append("PC3: "+vista.tfChat.getText()+"\n");
       String msj = modeloNegocio.socketChat("PC3: "+vista.tfChat.getText()+"\n");
       SocketCliente c = new SocketCliente(modeloNegocio.varUni.direccionIpSucu2,
       modeloNegocio.varUni.Puerto2, msj);
       Thread t = new Thread(c);
       t.start();
       c = new SocketCliente(modeloNegocio.varUni.direccionIpSucu1,
       modeloNegocio.varUni.Puerto1, msj);
       t = new Thread(c);
       t.start();
    }
    
    private void actBtnPostres(){
          SocketCliente c = new SocketCliente(modeloNegocio.varUni.direccionIpSucu1,
        modeloNegocio.varUni.Puerto1,modeloNegocio.varUni.getPostresCode3);
        modeloNegocio.varUni.waitPostres = true;
        Thread t = new Thread(c);
        t.start();
       }
    
    private void actBtnComidas(){
     SocketCliente c = new SocketCliente(modeloNegocio.varUni.direccionIpSucu2,
             modeloNegocio.varUni.Puerto2,modeloNegocio.varUni.getComidasCode3);
             modeloNegocio.varUni.waitComidas = true;
             Thread t = new Thread(c);
             t.start();
     }
    
    private void actBtnBebidas(){ 
        modeloNegocio.auxBebidas = modeloNegocio.bebidasSocketMsj(modeloNegocio.socketsGetBebidas());
      modeloNegocio.mostrarBebidasPorSocket(modeloNegocio.auxBebidas, vista.tabla);    
    
   
     }
    
    private void actBtnInsertBebida(){
        modeloNegocio.iniciar();
        auxPostreFill(); 
        modeloNegocio.ingresarBebidas(modeloNegocio.Bebida);
        JOptionPane.showMessageDialog(null,"Guardado con Éxito");
        insertar.setVisible(false);
        vista.setVisible(true);
    }
  
    private void actBtnUpdatePostre(){
        auxPostreFill();
        modeloNegocio.updateBebidas(modeloNegocio.converMsjABebida( modeloNegocio.msjSocketUpBebida(modeloNegocio.Bebida)));  
        JOptionPane.showMessageDialog(null,"Actualizado con Éxito");
        insertar.setVisible(false);
        vista.setVisible(true);
    }
     private void actBtnDeletePostre(){
        auxPostreFill();
        
        modeloNegocio.DeleteBebidas(modeloNegocio.converMsjABebida( modeloNegocio.msjSocketUpBebida(modeloNegocio.Bebida)));  
        JOptionPane.showMessageDialog(null,"Eliminado con Éxito");
        insertar.setVisible(false);
        vista.setVisible(true);
     }
   
    private void auxPostreFill(){
        modeloNegocio.Bebida.nombre = insertar.tfNombre.getText();
        modeloNegocio.Bebida.precio = insertar.tfPrecio.getText();
        modeloNegocio.Bebida.sabor = insertar.tfIngredientes.getText();
        modeloNegocio.Bebida.detalles = insertar.tfDetalles.getText();
        modeloNegocio.Bebida.cantidad = insertar.tfCantidadStock.getText();
        }

    private void actBtnInsertar(){
        actBtnInsertBebida();  
    }
  
    private void actBtnVentas(){
        venta.setVisible(true);
        
        vista.setVisible(false);  
        venta.completer = null;
        venta.completer = modeloNegocio.getNamePostres(venta.inputName);
     }
    
    private void actBtnAdd(){
        insertar.setVisible(true);
         insertar.btnInsertar.setVisible(true);
        insertar.btnActualizar.setVisible(false);
        insertar.btnBorrar.setVisible(false);
        vista.setVisible(false);  
     }
     
    public void addDatos(String tipoItem,String name){
        String []datos1 = new String [6];
        switch(tipoItem){
            case "Postres":
                for(Postres aux: modeloNegocio.auxPostres){
                    if(aux.nombre.equals(name)){
                    datos1[0] = aux.id; 
                    datos1[1] = aux.nombre;
                    datos1[3] = aux.precio;
                    break;
                    }   
                 }
            break;
            case "Bebidas":
                 for(Bebidas aux: modeloNegocio.auxBebidas){
                    if(aux.nombre.equals(name)){
                        System.out.println(aux.id+aux.nombre+aux.precio);
                    datos1[0] = aux.id; 
                    datos1[1] = aux.nombre;
                    datos1[3] = aux.precio;
                    break;
                    }   
                 }
                
                break;
            case "Comidas":
                 for(Comidas aux: modeloNegocio.auxComidas){
                    if(aux.nombre.equals(name)){
                    datos1[0] = aux.id; 
                    datos1[1] = aux.nombre;
                    datos1[3] = aux.precio;
                    break;
                    }   
                 }
                break;
        }
        //El dato 0 y el 2 esta oculto en la tabla
        datos1 [2] = tipoItem;
        int cantidad = Integer.parseInt( venta.inputCantidad.getText());
        datos1 [4] = String.valueOf(cantidad);
        float num1 = parseFloat(datos1[3]);
        float total = num1 * cantidad;
        datos1 [5] = String.valueOf(total);
    
        System.out.println(Arrays.toString(datos1));
        venta.modelo.addRow(datos1);
        venta.inputCantidad.setText("");
             venta.inputName.setText("");

}
    
    private void getPostreTabla(int fila){
        modeloNegocio.iniciar(); 
         System.out.println(vista.tabla.getValueAt(fila, 0).toString()); 
         modeloNegocio.Bebida.id = vista.tabla.getValueAt(fila, 0).toString();
         modeloNegocio.Bebida.nombre = vista.tabla.getValueAt(fila, 1).toString();
         modeloNegocio.Bebida.precio = vista.tabla.getValueAt(fila, 2).toString();
         modeloNegocio.Bebida.sabor = vista.tabla.getValueAt(fila, 3).toString();
         modeloNegocio.Bebida.detalles = vista.tabla.getValueAt(fila, 4).toString();
         modeloNegocio.Bebida.cantidad = vista.tabla.getValueAt(fila, 5).toString();
        // modeloNegocio.Bebida.nombre = vista.tabla.getValueAt(fila, 1).toString();
         //System.out.println(modeloNegocio.Bebida);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        String dato = (String)arg; 
        String[] aux = modeloNegocio.decifrarSocketVenta(dato);
        if(aux[0].equals("ventas")){
            modeloNegocio.insertarVentas(modeloNegocio.AuxPedidosSocketMsj(aux[2],aux[1]), modeloNegocio.PedidosSocketMsj(aux[3]));     
        }
        if(aux[0].contains("chat")){
            System.out.println("PAso en el chat");
            vista.txtApend.append(aux[1]);
        }else{
                              
        System.out.println(dato);
        if(dato.equals(modeloNegocio.varUni.getBebidasCode2)){
            String a = modeloNegocio.socketsGetBebidas();
            //Sucursal 2
             SocketCliente c = new SocketCliente(
                modeloNegocio.varUni.direccionIpSucu2,
                modeloNegocio.varUni.Puerto2,
                a);
            Thread t = new Thread(c);
            t.start();
            //Sucursal 3
            }
        if(dato.equals(modeloNegocio.varUni.getBebidasCode1)){
                String a = modeloNegocio.socketsGetBebidas();
                       //Sucursal 1
                       SocketCliente c = new SocketCliente(
                               modeloNegocio.varUni.direccionIpSucu1,
                               modeloNegocio.varUni.Puerto1,
                               a);
                        Thread t = new Thread(c);
                        t.start();
                        //Sucursal 3
                }
        if(dato.contains("{")){
            if(modeloNegocio.varUni.waitPostres){
                modeloNegocio.auxPostres = modeloNegocio.itemsSocketMsj(dato);
                modeloNegocio.mostrarPostresPorSocket(modeloNegocio.auxPostres,vista.tabla);
                modeloNegocio.varUni.waitPostres = false;
                //Actualizamos los datos
                venta.completer = null;
                       
                venta.completer = modeloNegocio.getNamePostres(venta.inputName);
                modeloNegocio.varUni.textAutoCompleterVenta = null;
                modeloNegocio.varUni.textAutoCompleterVenta = modeloNegocio.getNamePostres(venta.inputName);
                } else{
           if(modeloNegocio.varUni.waitComidas){ 
                         modeloNegocio.auxComidas = modeloNegocio.comidasSocketMsj(dato);
                         modeloNegocio.mostrarComidasPorSocket(modeloNegocio.auxComidas,vista.tabla);
                         modeloNegocio.varUni.waitComidas = false; 
                         //Actualizamos los datos
                         venta.completer = null;
                         venta.completer = modeloNegocio.getNameComidas(venta.inputName);
                         modeloNegocio.varUni.textAutoCompleterVenta = null;
                         modeloNegocio.varUni.textAutoCompleterVenta = modeloNegocio.getNameComidas(venta.inputName);
                   
                         } 
                    }
                 }
        }
    }
}


