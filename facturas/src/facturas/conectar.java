package facturas;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Alex
 */
public class conectar {
    Connection con=null;
    
    public Connection CrearBD(){
        try{        
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");        
            con=DriverManager.getConnection("jdbc:derby:.\\DB\\Facturas.DB;create=true");
            if(con!=null) {
                JOptionPane.showMessageDialog(null,"Creando la base de datos 'Gestor de Facturas'.\nPor favor espere.","Proceso en progreso", JOptionPane.INFORMATION_MESSAGE);
                String tab_usuario ="create table usuario ("
                                      + "cedUsuario varchar(13)," //not null
                                      + "nomUsuario varchar(45)," //not null
                                      + "dirUsuario varchar(45)," //not null
                                      + "telUsuario varchar(10),"
                                      + "passUsuario varchar(8)," //not null
                                      + "Primary Key (cedUsuario) )";
                String tab_proveedor="create table proveedor ("
                                      + "rucProveedor varchar(13)," //not null
                                      + "razProveedor varchar(45)," //not null
                                      + "nomcomProveedor varchar(45),"
                                      + "dirProveedor varchar(45)," //not null
                                      + "telProveedor varchar(10),"
                                      + "Primary Key (rucProveedor) )";
                String tab_factura="create table factura ("
                                      + "numFactura varchar(10)," //not null
                                      + "rucProveedor varchar(13)," //not null
                                      + "cedUsuario varchar(13)," //not null
                                      + "fecFactura date," //not null
                                      + "eduFactura numeric(6, 2)," 
                                      + "ivaEFactura numeric(6, 2),"//Debe ser sí o no, S o N
                                      + "saldFactura numeric(6, 2)," 
                                      + "ivaSFactura numeric(6, 2),"//Debe ser sí o no, S o N
                                      + "vestFactura numeric(6, 2)," 
                                      + "ivaVTFactura numeric(6, 2),"//Debe ser sí o no, S o N
                                      + "vivFactura numeric(6, 2)," 
                                      + "ivaVFactura numeric(6, 2),"//Debe ser sí o no, S o N
                                      + "alimFactura numeric(6, 2)," 
                                      + "ivaAFactura numeric(6, 2),"//Debe ser sí o no, S o N
                                      + "otroFactura numeric(6,2),"
                                      + "ivaOFactura numeric(6, 2),"//Debe ser sí o no. S o N
                                      + "iceFactura numeric(6, 2),"//puede ser null porque hay cosas que tienen y no tienen ice
                                      + "descFactura numeric(6, 2),"
                                      + "ivaFactura numeric(6, 2),"//Debe ser sí o no. S o N
                                      + "totFactura numeric(6, 2)," //not null, campo calculado
                                      + "constraint fk_proveedor Foreign Key (rucProveedor) references proveedor(rucProveedor),"
                                      + "constraint fk_usuario Foreign Key (cedUsuario) references usuario(cedUsuario),"
                                      + "Primary Key ( numFactura, rucProveedor))";
                try {
                    PreparedStatement psm1 = con.prepareStatement(tab_usuario);
                    psm1.execute();
                    psm1.close();
                    
                    PreparedStatement psm2 = con.prepareStatement(tab_proveedor);
                    psm2.execute();
                    psm2.close();
                    
                    PreparedStatement psm3 = con.prepareStatement(tab_factura);
                    psm3.execute();
                    psm3.close();

                    JOptionPane.showMessageDialog(null,"La creaciónn de la base de datos 'Gestor de Facturas' ha finalizado con éxito.\nYa puede empezar a trabajar.","Proceso terminado", JOptionPane.INFORMATION_MESSAGE);
                    aplicacion.corrida=1;
                }//Fin try
                catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al intentar crear la base de datos.\nRevise el código según:\n"+ex.getMessage(),"Error" ,  JOptionPane.ERROR_MESSAGE);
                }//fin catch
            } //Fin if
        }//fin try   
        catch(ClassNotFoundException | SQLException e){          
            JOptionPane.showMessageDialog(null, "Error de conexión: "+e, "Error", JOptionPane.ERROR_MESSAGE);   
        }//fin catch
        return con;
    }//fin operador CrearBD

    public Connection Conectar(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:.\\DB\\Facturas.DB;");
        }//fin try
        catch(SQLException e){          
            JOptionPane.showMessageDialog(null, "No se encontró la base de datos 'Gestor de Facturas'.\nLa aplicación creará la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }//fin catch
        catch(ClassNotFoundException e){          
            JOptionPane.showMessageDialog(null, "No se localiza el driver.", "Error", JOptionPane.ERROR_MESSAGE);   
        }//fin catch
        return con;
    }//fin Conectar
    
    public void CerrarConexion() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//fin operador CerrarConexiónn

}
