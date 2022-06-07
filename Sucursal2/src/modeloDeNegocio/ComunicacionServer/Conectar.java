package modeloDeNegocio.ComunicacionServer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ibtu9
 */
class Conectar {
    Connection conectar=null;
public Connection conexion() {
    try {
        conectar =DriverManager.getConnection("jdbc:mysql://localhost:3306/sucursal_dos","root","");
        Statement stmt=conectar.createStatement();  
        ResultSet rs=stmt.executeQuery("show databases;");
        System.out.println("Connected"); 
    } catch (SQLException e) {
    System.out.print("Esta en conectar  "+e.getMessage());
    }
        return conectar;
}
}
