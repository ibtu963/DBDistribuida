package modeloDeNegocio.ComunicacionServer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import Vista.Ventas;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modeloDeNegocio.VariablesUniversales;
import modeloDeNegocio.models.*;


public class ComunicacionServer extends Conectar {
    public Postres[] auxPostres;
    public Comidas[] auxComidas;
    public Bebidas[] auxBebidas;
    public Bebidas Bebida;
    public Pedidos pedido;
    public AuxPedidos AuxPedido;
    private String AuxPedidoMSJ;
    private String PedidoMSJ;
    Connection cn = conexion();  
    public VariablesUniversales varUni = new VariablesUniversales();
    //Para sucursal 1
    int contProductosSockests;
    //Para sucursal 2
    int contComidasSockests;
    //Para sucursal 3
    int contBebidasSockests;
    
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
    
   public ComunicacionServer(){    
     
    }
   
   // private ComunicacionServer modeloNegocio;
   public void iniciar(){
       Bebida = new Bebidas();;
       pedido = new Pedidos();
   }
   
  
   public TextAutoCompleter getNamePostres(JTextField buscador){
       TextAutoCompleter textAutoCompleter = new TextAutoCompleter(buscador); 
       for(Postres auxPostre: auxPostres){
           textAutoCompleter.addItem(auxPostre.nombre);
       }
        return textAutoCompleter; 
    }
   
   public TextAutoCompleter getNameComidas(JTextField buscador){
       TextAutoCompleter textAutoCompleter = new TextAutoCompleter(buscador); 
       for(Comidas auxComida: auxComidas){
           textAutoCompleter.addItem(auxComida.nombre);
       }
        return textAutoCompleter; 
    }
 
   public TextAutoCompleter getNameBebidas(JTextField buscador){
       TextAutoCompleter textAutoCompleter = new TextAutoCompleter(buscador); 
       for(Bebidas auxBebida: auxBebidas){
           textAutoCompleter.addItem(auxBebida.nombre);
       }
        return textAutoCompleter; 
    }
   
  //Para sucursal 1
  private String getBebidasSockets(){
    String []datos = new String [6];
    StringBuilder postres = new StringBuilder();
    contProductosSockests =0;
    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(varUni.SeBebidaSQL);
        while(rs.next()) {
            datos [0] = rs.getString(1);//id
            datos [1] = rs.getString(2);//nombre
            datos [2] = rs.getString(3);//precio
            datos [3] = rs.getString(4);//ingredientes
            datos [4] = rs.getString(5);//detalles
            datos [5] = rs.getString(6);//disponibilidad       
            String resul = "{id:'"+datos[0]+"',nombre:'"+datos[1]+"',precio:'"+datos[2]+"',sabor:'"+datos[3]+"',detalles:'"+datos[4]+"',cantidad:'"+datos[5]+"'}";
        postres.append(resul);
     contProductosSockests++;
        }
      //  System.out.println(contProductosSockests);
        System.out.println(postres);
      
        } catch (SQLException ex) {
            System.out.println("getProductosSockets " + ex);
          }
        return String.valueOf(postres);
 }
  //Para sucursal 1 
  private int getLengthBebidasSockets(){
      return contProductosSockests;
  }
 //Para sucursal 1 
  public String socketsGetBebidas(){
    String items = getBebidasSockets();
    int cantidad = getLengthBebidasSockets();
      return "["+cantidad+";"+items+"]";
  }
  //Para Sucursal 1
  
  //Se ocupa para decifrar
  public Postres[] itemsSocketMsj(String msj){
    //Se quitan los []
    String msjSinCorchetes = msj.substring(1, msj.length() -1 );
    //Se dividi en cantidad y datos
    String[] partesSocket = msjSinCorchetes.split(";");
    int can = Integer.parseInt(partesSocket[0]);
     
    Postres[] items = new Postres[can];
    //Deberian ser igual que la cantidad
    String[] datos = partesSocket[1].split("}");
     
    // System.out.println("Sus valores son "+(partesSocket[1]));
     for(int i = 0; i < datos.length;i++){
         
     String msjSinLlaves = datos[i].substring(1, datos[i].length());
     
        String[] datosPostres = msjSinLlaves.split(",");
        Postres aux = new Postres();
        for(int j = 0; j < datosPostres.length; j++){
            System.out.println(datosPostres[j]);
            String[] valorVar = datosPostres[j].split(":");
            System.out.println(valorVar[1]);
            String datoAux = valorVar[1].substring(1,valorVar[1].length()-1);
            switch(j){
                case 0:
                    aux.id = datoAux;
                    break;
                    case 1:
                        aux.nombre = datoAux;
                    break;
                    case 2:
                        aux.precio = datoAux;
                    break;
                    case 3:
                        aux.ingredientes = datoAux;
                    break;
                    case 4:
                        aux.detalles = datoAux;
                    break;
                    case 5:
                        aux.cantidad = datoAux;
                    break;
            }
            items[i]= aux;
           }
     }
    return items;
  }
  //Para Sucursal 1
  public void mostrarPostresPorSocket(Postres[] item, JTable tabla){
      DefaultTableModel modelo=new DefaultTableModel();
      modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PRECIO");
        modelo.addColumn("INGREDIENTES");
        modelo.addColumn("DETALLES");
        modelo.addColumn("DISPONIBILIDAD");
        tabla.setModel(modelo);
        String[] datos = new String[6];
        for (Postres item1 : item) {
             datos [0] = item1.id;//id
        datos [1] = item1.nombre;//nombre
        datos [2] = item1.precio;//precio
        datos [3] = item1.ingredientes;//ingredientes
        datos [4] = item1.detalles;//detalles
        datos [5] = item1.cantidad;
        modelo.addRow(datos);
        
        }
  }
 
  //Para Sucursal 2
  public Comidas[] comidasSocketMsj(String msj){
      //Se quitan los []
     String msjSinCorchetes = msj.substring(1, msj.length() -1 );
     //Se dividi en cantidad y datos
     String[] partesSocket = msjSinCorchetes.split(";");
     int can = Integer.parseInt(partesSocket[0]);
     
     Comidas[] items = new Comidas[can];
     //Deberian ser igual que la cantidad
     String[] datos = partesSocket[1].split("}");
     
    // System.out.println("Sus valores son "+(partesSocket[1]));
     for(int i = 0; i < datos.length;i++){
         
     String msjSinLlaves = datos[i].substring(1, datos[i].length());
     
        String[] datosPostres = msjSinLlaves.split(",");
        Comidas aux = new Comidas();
        for(int j = 0; j < datosPostres.length; j++){
            String[] valorVar = datosPostres[j].split(":");
            String datoAux = valorVar[1].substring(1,valorVar[1].length()-1);
            switch(j){
                case 0:
                    aux.id = datoAux;
                    break;
                    case 1:
                        aux.nombre = datoAux;
                    break;
                    case 2:
                        aux.precio = datoAux;
                    break;
                    case 3:
                        aux.ingredientes = datoAux;
                    break;
                    case 4:
                        aux.detalles = datoAux;
                    break;
                    case 5:
                        aux.cantidad = datoAux;
                    break;
            }
            items[i]= aux;
        }
   }
    return items;
  }
  //Para Sucursal 2
  public void mostrarComidasPorSocket(Comidas[] item, JTable tabla){
      DefaultTableModel modelo=new DefaultTableModel();
       
       modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PRECIO");
        modelo.addColumn("INGREDIENTES");
        modelo.addColumn("DETALLES");
        modelo.addColumn("DISPONIBILIDAD");
        tabla.setModel(modelo);
        String[] datos = new String[6];
        for (Comidas item1 : item) {
             datos [0] = item1.id;//id
        datos [1] = item1.nombre;//nombre
        datos [2] = item1.precio;//precio
        datos [3] = item1.ingredientes;//ingredientes
        datos [4] = item1.detalles;//detalles
        datos [5] = item1.cantidad;
        modelo.addRow(datos);
        
        }
  }
  
//Para Sucursal 3
  public Bebidas[] bebidasSocketMsj(String msj){
      //Se quitan los []
     String msjSinCorchetes = msj.substring(1, msj.length() -1 );
     //Se dividi en cantidad y datos
     String[] partesSocket = msjSinCorchetes.split(";");
     int can = Integer.parseInt(partesSocket[0]);
     
     Bebidas[] items = new Bebidas[can];
     //Deberian ser igual que la cantidad
     String[] datos = partesSocket[1].split("}");
     
    // System.out.println("Sus valores son "+(partesSocket[1]));
     for(int i = 0; i < datos.length;i++){
         
     String msjSinLlaves = datos[i].substring(1, datos[i].length());
     
        String[] datosPostres = msjSinLlaves.split(",");
        Bebidas aux = new Bebidas();
        for(int j = 0; j < datosPostres.length; j++){
            String[] valorVar = datosPostres[j].split(":");
            String datoAux = valorVar[1].substring(1,valorVar[1].length()-1);
            switch(j){
                case 0:
                    aux.id = datoAux;
                    break;
                    case 1:
                        aux.nombre = datoAux;
                    break;
                    case 2:
                        aux.precio = datoAux;
                    break;
                    case 3:
                        aux.sabor = datoAux;
                    break;
                    case 4:
                        aux.detalles = datoAux;
                    break;
                    case 5:
                        aux.cantidad = datoAux;
                    break;
            }
            items[i]= aux;
        }
   }
    return items;
  }
  //Para Sucursal 3
  public void mostrarBebidasPorSocket(Bebidas[] item, JTable tabla){
      DefaultTableModel modelo=new DefaultTableModel();
       
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PRECIO");
        modelo.addColumn("SABOR");
        modelo.addColumn("DETALLES");
        modelo.addColumn("DISPONIBILIDAD");
        tabla.setModel(modelo);
        String[] datos = new String[6];
        for (Bebidas item1 : item) {
             datos [0] = item1.id;//id
        datos [1] = item1.nombre;//nombre
        datos [2] = item1.precio;//precio
        datos [3] = item1.sabor;//ingredientes
        datos [4] = item1.detalles;//detalles
        datos [5] = item1.cantidad;
        modelo.addRow(datos);
        
        }
  }

  //Para ingresar datos en Pedidos Sucursales 1,2,3 Mediante sockets
  public void ingresarBebidas(Bebidas bebida){
      try{
      //Si no hay ninguna dirección(Imagenes) entonces el código es el siguiente          
        PreparedStatement pst = cn.prepareStatement("INSERT INTO "
                + "bebida(nombre,precio,sabor,detalles,cantidad) VALUES (?,?,?,?,?)");
        pst.setString(1, bebida.nombre);
        pst.setString(2, bebida.precio);
        pst.setString(3, bebida.sabor );
        pst.setString(4, bebida.detalles);// Este valor se asigna al momento de verificar que el proveedor exista
        pst.setString(5, bebida.cantidad);
        
        pst.executeUpdate();
        
        }catch(HeadlessException | SQLException e){
        System.out.println("El error IngresarPostres "+e); 
          }
  }
  
  public String msjSocketUpBebida(Bebidas bebida){
      String dato = "{id:'"+bebida.id+"',nombre:'"+bebida.nombre+"',precio:'"+
              bebida.precio+"',sabor:'"+bebida.sabor+"',detalles:'"+bebida.detalles+"',disponibilidad:'"+bebida.cantidad+"'}"; 
  return "[bebida;"+dato+"]";
  }
  public Bebidas converMsjABebida(String msj){
     //Se quitan los []
     String msjSinCorchetes = msj.substring(1, msj.length() -1 );
     //Se dividi en cantidad y datos
     String[] partesSocket = msjSinCorchetes.split(";");
     //Los datos es el item
         System.out.println(partesSocket[1]);
        String msjSinLlaves = partesSocket[1].substring(1, partesSocket[1].length()-1);
        System.out.println(msjSinLlaves);
        String[] datosPostres = msjSinLlaves.split(",");
        System.out.println("datos postre");
        System.out.println(datosPostres[1]);
        Bebidas aux = new Bebidas();
        for(int j = 0; j < datosPostres.length; j++){
            System.out.println(datosPostres[j]);
            String[] valorVar = datosPostres[j].split(":");
            System.out.println(valorVar[1]);
            String datoAux = valorVar[1].substring(1,valorVar[1].length()-1);
            switch(j){
                case 0:
                    aux.id = datoAux;
                    break;
                    case 1:
                        aux.nombre = datoAux;
                    break;
                    case 2:
                        aux.precio = datoAux;
                    break;
                    case 3:
                        aux.sabor = datoAux;
                    break;
                    case 4:
                        aux.detalles = datoAux;
                    break;
                    case 5:
                        aux.cantidad = datoAux;
                    break;
            }
         }
         System.out.println(aux.nombre+" " +aux.id);
     
    return aux;
     
  }
  
  public void updateBebidas(Bebidas bebida){
      try{
           PreparedStatement pst = cn.prepareStatement("UPDATE "
                + "bebida SET nombre ='"+bebida.nombre+"', precio ='"+bebida.precio+"',sabor='"+
                   bebida.sabor+"', detalles='"+bebida.detalles+"', cantidad='"+bebida.cantidad+"'"+
                   "WHERE id='"+bebida.id+"'");
       pst.executeUpdate();    
       System.out.println("Segun si se hizo");
      }catch(SQLException es){
          System.out.println(es);
      }                    
  } 
   public void DeleteBebidas(Bebidas bebida){
      try{
           PreparedStatement pst = cn.prepareStatement("DELETE FROM "
                + "bebida WHERE id='"+bebida.id+"'");
       pst.executeUpdate();    
       System.out.println("Segun si se hizo");
      }catch(SQLException es){
          System.out.println(es);
      }                    
  } 
   
   public String socketChat(String txt){
       return "[chat;"+txt+"]" ;
   }
  
  public String SocketVenta(Ventas venta){
      String msj = "[ventas;"+AuxPedidoFillMSJ(venta)+";"+PedidoFillMSJ(venta)+"]";
      return msj;
  }
  private String AuxPedidoFillMSJ(Ventas venta){
        StringBuilder dato = new StringBuilder();   
         //String item[] = new String[3];
         
         for(int fil =0;fil < venta.tabla.getRowCount();fil++){
             dato.append('{');
             dato.append("id:'");
             dato.append(venta.tabla.getValueAt(fil, 0).toString());
             dato.append("',nombre:'");
             dato.append(venta.tabla.getValueAt(fil, 1).toString());
             dato.append("',tipo:'");
             dato.append(venta.tabla.getValueAt(fil, 2).toString());
             dato.append("',precio:'");
             dato.append(venta.tabla.getValueAt(fil, 3).toString());
             dato.append("',cantidad:'");
             dato.append(venta.tabla.getValueAt(fil, 4).toString());
             dato.append("',subtotal:'");
             dato.append(venta.tabla.getValueAt(fil, 5).toString());
             dato.append("'}");
         }
         
         System.out.println(dato);
         AuxPedidoMSJ = dato.toString();
       return venta.tabla.getRowCount()+";"+AuxPedidoMSJ;
       
     }
  private String PedidoFillMSJ(Ventas venta){
        StringBuilder dato = new StringBuilder();
         iniciar();
           dato.append('{');
           dato.append("nombre:'");
           dato.append(pedido.cliente = venta.tfCliente.getText());
           dato.append("',fecha:'");
           dato.append(pedido.fecha = dtf.format(LocalDateTime.now()));
           dato.append("'}");
         PedidoMSJ = dato.toString();
         return PedidoMSJ;
       
     }   
  public String elIDAuxPedido(){
       String sql = "SELECT * FROM pedidos";
       String CualAuxPedido ="";
        
        try {
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql);
            
              while(rs.next()) {
              CualAuxPedido = rs.getString(1);
              
              }
              System.out.println("Aqui esta el pedido :"+CualAuxPedido+":");   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        if(CualAuxPedido.equals("")) CualAuxPedido ="0";
        int num = Integer.parseInt(CualAuxPedido);
        num++;
        CualAuxPedido= String.valueOf(num);
     return CualAuxPedido;
    }
 
  public String[] decifrarSocketVenta(String msj){
     String[] arrayDatos = new String[3];
      String msjSinCorchetes = msj.substring(1, msj.length() -1 );
     //Se dividi en cantidad y datos 
     return msjSinCorchetes.split(";");
  }
  
//Convertir obj Venntas En socket
  public void insertarVentas(AuxPedidos[] auxPedido, Pedidos pedido){
      String contador = elIDAuxPedido();
      double tot = 00.0;
       try{ 
           for(AuxPedidos item : auxPedido){
          System.out.println("AuxPedidos");
          System.out.println("Contador: "+contador);
          System.out.println("idItem: "+item.idItem);
          System.out.println("Tipo: "+item.tipoItem);
          System.out.println("Canti: "+item.cantidad);
          System.out.println("Subtotal: "+item.subTotal);
          System.out.println("");
              PreparedStatement pst = cn.prepareStatement("INSERT INTO auxpedido(id_aux_pedido,id_item,tipo,cantidad,subtotal) VALUES (?,?,?,?,?)");
              pst.setString(1, contador);
              pst.setString(2, item.idItem);
              pst.setString(3, item.tipoItem);
              pst.setString(4, item.cantidad);
              if(item.tipoItem.equals("Bebidas")){
                  disminuirDatos(item.idItem,item.cantidad); 
                }
              pst.setString(5, item.subTotal);
             // double c = Double.parseDouble(item.subTotal);
              tot += Double.parseDouble(item.subTotal);
              pst.executeUpdate();    
        }
           System.out.println("Pedidos");
      System.out.println("");
      pedido.idAux = contador;
      pedido.total = String.valueOf(tot);
      System.out.println("PediIDAUx: "+pedido.idAux);
      System.out.println("total: "+pedido.total);
      System.out.println("cliente: "+pedido.cliente);
      System.out.println("fecha: "+pedido.fecha);
      
      PreparedStatement pst2 = cn.prepareStatement("INSERT INTO pedidos(cliente,fecha,total,id_aux_pedido) VALUES (?,?,?,?)");
             pst2.setString(1, pedido.cliente);
              pst2.setString(2, pedido.fecha);
              pst2.setString(3, pedido.total);
              pst2.setString(4, contador);
              //pst2.setString(5, pedido.estado);
              pst2.executeUpdate();
        
         }catch(HeadlessException | SQLException e){
              System.out.println("El error surge InsertarVenta "+e);}
  }
 
  public void disminuirDatos(String id, String cant){
      try{
          String sql3="SELECT * FROM bebida WHERE id ='"+id+"'";
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql3);
                String cantidad = "0";
                    while(rs.next()){
                    cantidad = rs.getString(6);
                    }
                    
                    int canti2 = Integer.parseInt(cantidad);
                    int canti = Integer.parseInt(cant);
                    int newCantidad = canti2-canti;
                        String sql2 = "UPDATE bebida SET cantidad ='"+String.valueOf(newCantidad)+"' WHERE id ='"+id+"'";
                        PreparedStatement pst2 = cn.prepareStatement(sql2);
                        pst2.executeUpdate();
                }catch(HeadlessException | SQLException e){
              System.out.println("Es al disminuir "+e);}
  }
              
  //Para Sucursal 3
  int contAuxPedido;
 
  public AuxPedidos[] AuxPedidosSocketMsj(String msj, String canti){
      //Se quitan los []
     String msjSinCorchetes = msj.substring(1, msj.length() -1 );
     //Se dividi en cantidad y datos
     AuxPedidos[] items = new AuxPedidos[Integer.parseInt(canti)];
     //Deberian ser igual que la cantidad
     String[] datos = msj.split("}");
    // System.out.println("Sus valores son "+(partesSocket[1]));
     for(int i = 0; i < datos.length;i++){   
     String msjSinLlaves = datos[i].substring(1, datos[i].length());
     String[] datosPostres = msjSinLlaves.split(",");
       AuxPedidos aux = new AuxPedidos();
        for(int j = 0; j < datosPostres.length; j++){
            System.out.println(datosPostres[j]);
            String[] valorVar = datosPostres[j].split(":");
            System.out.println(valorVar[1]);
            String datoAux = valorVar[1].substring(1,valorVar[1].length()-1);
            switch(j){
             
                case 0:
                     aux.idItem = datoAux;
                    break;
                 case 2:
                        aux.tipoItem = datoAux;
                    break;
                    case 3:
                        aux.precio = datoAux;
                    break;
                   
                    case 4:
                        aux.cantidad = datoAux;
                    break;
                     case 5:
                        aux.subTotal = datoAux;
                    break;
            }
            items[i]= aux;
        }
   }
    return items;
  }

  public Pedidos PedidosSocketMsj(String msj){
    String msjSinCorchetes = msj.substring(0, msj.length() -1 );
    System.out.println("datos pedido"+msjSinCorchetes);
        String msjSinLlaves = msjSinCorchetes.substring(1, msjSinCorchetes.length()-1);
        System.out.println(msjSinLlaves);
        String[] datosPostres = msjSinLlaves.split(",");
        
        System.out.println(datosPostres[1]);
        Pedidos aux = new Pedidos();
        for(int j = 0; j < datosPostres.length; j++){
            System.out.println(datosPostres[j]);
            String[] valorVar = datosPostres[j].split(":");
            System.out.println(valorVar[1]);
            String datoAux = valorVar[1].substring(1,valorVar[1].length()-1);
            System.out.println(datoAux);
            switch(j){
                    case 0:
                        aux.cliente= datoAux;
                    break;
                    case 1:
                        aux.fecha = datoAux;
                    break;
            }
         }
         System.out.println(aux.idAux+" " +aux.id);
     
    return aux;
   }

}
