/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturas;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Alex
 */
public class conectar {
    Connection con=null;
public Connection conexion(){
try{
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","ROOT");



}
catch(ClassNotFoundException | SQLException e){
    System.out.println("Error de coneccion");
    JOptionPane.showMessageDialog(null, "error de coneccion"+e);
}
return con;
}

    conectar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
