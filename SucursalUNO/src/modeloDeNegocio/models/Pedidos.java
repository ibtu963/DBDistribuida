/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDeNegocio.models;

/**
 *
 * @author ibtu9
 */
public class Pedidos {
    public String id;
    public String idAux;
    public String cliente;
    public String fecha;
    public String total;
    public Pedidos(){
        
    }
    public Pedidos(String id,String idAux, String cliente, String fecha,String total){
        
        this.id = id;
        this.idAux = idAux;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }
}
