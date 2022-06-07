package modeloDeNegocio.models;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ibtu9
 */
public class AuxPedidos {
    public String idAux;
    public String idItem;
    public String tipoItem;
    public String cantidad;
    public String precio;
    public String subTotal;
   public AuxPedidos(){
       
   }
   public AuxPedidos(String idAux,String idItem, String tipoItem,String precio, String cantidad, String subTotal){
       this.idAux = idAux;
       this.idItem = idItem; 
       this.precio=precio;
   this.tipoItem = tipoItem;
    this.cantidad = cantidad;
    this.subTotal = subTotal;
   }
   
    
}
