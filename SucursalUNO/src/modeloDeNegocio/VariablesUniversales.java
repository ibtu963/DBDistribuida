package modeloDeNegocio;

import com.mxrck.autocompleter.TextAutoCompleter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ibtu9
 */
public class VariablesUniversales {
    public final String categoriaPostre = "Postre";
    public final String categoriaComida = "Comida";
    public final String categoriaBebida = "Bebida";
    
    public final String getPostresCode2  = "[s2;get;postres;*]";
    public final String getPostresCode3  = "[s3;get;postres;*]";
    
    public final String getBebidasCode1  = "[s1;get;bebidas;*]";
    public final String getBebidasCode2  = "[s2;get;bebidas;*]";
    
     public final String getComidasCode1  = "[s1;get;comidas;*]";
    public final String getComidasCode3  = "[s3;get;comidas;*]";
    
    
    public final String SePostreSQL  = "SELECT * FROM postre";
    public final String SeBebidaSQL  = "SELECT * FROM bebida";
    public final String SeComidaSQL  = "SELECT * FROM comida";
    
    public TextAutoCompleter textAutoCompleterMain;
    public TextAutoCompleter textAutoCompleterVenta;
    
    public final int Puerto1 = 5000; 
    public final int Puerto2 = 6000; 
     public final int Puerto3 = 7000; 
    public final String direccionIpSucu2 = "127.0.0.1";
    
    public final String direccionIpSucu3 = "127.0.0.1";

    
    public  boolean waitPostres = false; 
    public  boolean waitComidas = false; 
    public  boolean waitBebidas = false; 
    
   // public boolean waitCache = false;

    public VariablesUniversales(){
         
     }
    
    
    

}
