package modeloDeNegocio.models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ibtu9
 */
public class Bebidas {
    
    public String id;
    public String nombre;
    public String precio;
    public String sabor;
    public String detalles;
    public String cantidad;
    
    public Bebidas(){
        
    }
    public Bebidas(String id,String nombre,String precio, String sabor, String detalles, String cantidad){
        this.id = id;
        this.nombre = nombre;
        this.precio =precio;
        this.sabor = sabor;
        this.detalles = detalles;
        this.cantidad = cantidad;
    }
    
}
