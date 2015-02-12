package facturas;

import javax.swing.JComboBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author SEVR
 */
public class Operaciones {
    public Operaciones () {
    }
    
    public boolean VerificarCedula(String cedula) {
        int total = 0;
        int tamanoLongitudCedula = 10;
        int[] coeficientes = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
        int numeroProvincias = 24;
        int tercerDigito = 6;
        if (cedula.matches("[0-9]*") && cedula.length() == tamanoLongitudCedula) {
            int provincia = Integer.parseInt(cedula.charAt(0) + "" + cedula.charAt(1));
            int digitoTres = Integer.parseInt(cedula.charAt(2) + "");
            if ((provincia > 0 && provincia <= numeroProvincias) && digitoTres < tercerDigito) {
                int digitoVerificadoRecibido = Integer.parseInt(cedula.charAt(9) + "");
                for (int i = 0; i < coeficientes.length; i++) {
                    int valor = Integer.parseInt(coeficientes[i] + "") * Integer.parseInt(cedula.charAt(i) + "");
                    total = valor >= 10 ? total + (valor - 9) : total + valor;
                }
                int digitoVerificadorObtenido = total >= 10 ?
                                                    (total % 10) != 0 ?
                                                        10 - (total % 10) :
                                                    (total % 10) :
                                                total;
                return digitoVerificadorObtenido == digitoVerificadoRecibido;
            }
            else
                return false;
        }
        else
            return false;
    }
    
    public boolean VerificarRuc(String ruc) {        
        //Ruc de Persona Natural
        int tamanoLongitudRuc = 13;
       /* const string establecimiento = "001"*/;
        int numeroProvincias = 24;
        int modulo = 11;
        int total = 0;
        if (ruc.matches("[0-9]*") && ruc.length()== tamanoLongitudRuc) {
            int numeroProvincia = Integer.parseInt(ruc.charAt(0) + ""+ ruc.charAt(1));
            int /*personaNatural*/ sociedad_Persona = Integer.parseInt(ruc.charAt(2)+"");
            if (numeroProvincia >= 1 && numeroProvincia <= numeroProvincias) {
                if (sociedad_Persona >= 0 && sociedad_Persona < 6) {
                    //Ruc de Persona Natural
                    return ruc.substring(10, 13).equals("001") && VerificarCedula(ruc.substring(0, 10));
                }
                else /* const int tercerDigito = 6; const string establecimiento = "0001"; */
                    if (sociedad_Persona == 6 && ruc.substring(9, 13).equals("0001")) {
                        //Ruc de Personas Públicas/Entidades Estatales
                        int[] coeficientes = { 3, 2, 7, 6, 5, 4, 3, 2 };
                        int digitoVerificadorRecibido = Integer.parseInt(ruc.charAt(8)+"");
                        for (int i = 0; i < coeficientes.length; i++) {
                            total = total + (coeficientes[i] * Integer.parseInt(ruc.charAt(i) +""));
                        }
                        int digitoVerificadorObtenido = modulo - (total % modulo);
                        return digitoVerificadorObtenido == digitoVerificadorRecibido;
                    }
                else /* const int tercerDigito = 9; const string establecimiento = "001"; */
                    if (sociedad_Persona == 9 && ruc.substring(10, 13).equals("001")) {
                        //Ruc de sociedades privadas y extranjeros sin cédula
                        int[] coeficientes = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
                        int digitoVerificadorRecibido = Integer.parseInt(ruc.charAt(9) + "");
                        for (int i = 0; i < coeficientes.length; i++) {
                            total = total + (coeficientes[i] * Integer.parseInt(ruc.charAt(i) + ""));
                        }
                        int digitoVerificadorObtenido = (total % modulo) == 0 ?
                                                                0 :
                                                            modulo - (total % modulo);
                        return digitoVerificadorObtenido == digitoVerificadorRecibido;
                    }
                else return false;
            }
            else return false;
        }
        else return false;
    }
    
    //Campos de password
    public String ExtraerPass(char[] pass) {
        String password="";
        for(int x=0;x<pass.length;x++){
            password+=pass[x];
        }
        return password;
    }
    
    //Para: ActualizarProveedor, BuscarProveedor
    public void ComboRucRazon(String campo, JComboBox combo) {
        conectar con=new  conectar();
        Connection reg=con.Conectar();
        try {
            Statement st=reg.createStatement();
            ResultSet rs=st.executeQuery("select "+campo+" from proveedor");
            while(rs.next()){
                String f=rs.getString(campo);        
                //Object datos[]={d,f,a,b,c,e};
                combo.addItem(f);
            }
            con.CerrarConexion();
        }
        catch (SQLException ex) {
            Logger.getLogger(aFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Para: EliminarProveedor, BuscarProveedor
    public void TablaProveedores(String consulta, DefaultTableModel modelo) {  
        for (int i = 0; i < modelo.getRowCount(); i++) {        
            modelo.removeRow(i);
            i-=1;
        }
        conectar con=new  conectar();
        Connection reg=con.Conectar();
        Statement st;
        try {
            st = reg.createStatement();
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                String f=rs.getString("rucProveedor");
                String a=rs.getString("razProveedor");
                String e=rs.getString("nomcomProveedor");
                String b=rs.getString("dirProveedor");
                String c=rs.getString("telProveedor");
                Object datos[]={f,a,e,b,c};
                modelo.addRow(datos);           
            }
            con.CerrarConexion();
        }
        catch (SQLException ex) {
            Logger.getLogger(elProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void TablaFactura(String consulta, DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
        }
        conectar con=new  conectar();
        Connection reg=con.Conectar();
        Statement st;
        
        try {       
            st = reg.createStatement(); //select * from factura
            ResultSet rs=st.executeQuery("select p.razProveedor, f.numFactura, f.fecFactura, descFactura, " +
                     "ivaFactura, f.totFactura from proveedor p, factura f, usuario u " +
                    "where p.rucProveedor = f.rucProveedor and u.cedUsuario = f.cedUsuario and u.cedUsuario='"+aplicacion.user+"'");
            /*select p.razProveedor, f.numFactura, u.nomUsuario, f.tipFactura, f.fecFactura,"
                    + "f.subFactura, f.descFactura, f.ivaFactura, f.iceFactura, f.totFactura from proveedor p, factura f, usuario u"
                    + "where p.rucProveedor = f.rucProveedor and u.cedUsuario = f.cedUsuario and u.cedUsuario='"+aplicacion.user+"'*/
            while(rs.next()){ 
                String num=rs.getString("razProveedor");
                String a=rs.getString("numFactura");
                
                String d=rs.getString("fecFactura");
                
                String f=rs.getString("descFactura");
                String g=rs.getString("ivaFactura");  
                
                String i=rs.getString("totFactura");  
                Object datos[]={num,a,d,f,g,i};
                modelo.addRow(datos);
            }       
        } catch (SQLException ex) {
            Logger.getLogger(elProveedor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en\n"+ex);
        }
    }
    
   // public double subFactura()
    
    //Obtener el Nombre del usuario
    public String Usuario(String user) {
        String nombre="";
        conectar con=new  conectar();
        Connection reg=con.Conectar();
        try {
            Statement st=reg.createStatement();
            ResultSet rs=st.executeQuery("select nomUsuario from usuario where cedUsuario = '"+ user + "'");
            while(rs.next()){
                nombre = rs.getString("nomUsuario");        
                //Object datos[]={d,f,a,b,c,e};    
            }
            con.CerrarConexion();
        }
        catch (SQLException ex) {
            Logger.getLogger(aFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
       return nombre;
    }
    
    //Obtiene usuarios en un combobo
    private void ComboUsuario(){
        conectar con=new  conectar();
        Connection reg=con.Conectar();
        try {
            Statement st=reg.createStatement();
            ResultSet rs=st.executeQuery("select cedUsuario from usuario ");
        while(rs.next()){               
            String f=rs.getString("cedUsuario");
        } 
            
        }
        catch (SQLException ex) {
            Logger.getLogger(aFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Obtener Ruc proveedor a partir de Razon proveedor
    public String getRucProveedor(String razon) {
        String ruc="";
        conectar con=new conectar(); 
        Connection reg=con.Conectar();
        Statement st;
        
        try {
            st = reg.createStatement();
            ResultSet rs=st.executeQuery("select rucProveedor from proveedor where razProveedor='"+razon+"'");
            while(rs.next()){                
                ruc=rs.getString("rucProveedor");
            }
            return ruc;
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se podido realizar la conexión.", "Error de conaxión", JOptionPane.ERROR_MESSAGE);
        }
        return ruc;
    }
    
}
