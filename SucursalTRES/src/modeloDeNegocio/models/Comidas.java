/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDeNegocio.models;

/**
 *
 * @author ibtu9
 */
public class Comidas {
    public String id;
    public String nombre;
    public String precio;
    public String ingredientes;
    public String detalles;
    public String cantidad;
     
    public Comidas(){
        
    }
    public Comidas(String id,String nombre,String precio, String ingredientes, String detalles, String cantidad){
        this.id = id;
    this.nombre = nombre;
    this.precio =precio;
    this.ingredientes = ingredientes;
    this.detalles = detalles;
    this.cantidad = cantidad;
    }
    
}
