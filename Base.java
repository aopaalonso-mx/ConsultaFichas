/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fdconsultawebservice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author telecom
 */
public class Base {
    private String usuario = null;
    private String contrasenia = null;
    private String url = null;
    private String base = null;
    private Connection connection = null;
    private Properties properties = null;
    private Statement statement = null;
    private ResultSet resultset = null;
    private String insert = null;
    private PreparedStatement preparedStmt = null;
    
    Base (){
        this.contrasenia = "8Lucid$Armhole$6weal$6virtuous$icicle";
        this.usuario = "fichas";
        this.base = "solicitudes_prod";
        this.url = "jdbc:mysql://localhost:3306/";
        this.insert = "INSERT INTO fichas_deposito (sol_cve, monto, descripcion, convenio, referencia, fecha, fecha_vencimiento) "  
                      + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }
    
    public boolean conecta () {
        boolean conexion = false; 
        try {
            properties = new Properties();
            properties.setProperty("user", usuario);
            properties.setProperty("password", contrasenia);
            connection = DriverManager.getConnection(url + "/" + base, properties);
            preparedStmt = connection.prepareStatement(insert);
            statement = connection.createStatement();
            conexion = true;
        } catch (SQLException ex) { 
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conexion;
    }
    
    public boolean inserta(int folio, int monto, String descripcion, int convenio, String referencia, String f_inicial, String f_final ) {
        try {
            DateFormat formateador = new SimpleDateFormat("yy-M-DD");
            java.sql.Date f_emision = new java.sql.Date(formateador.parse(f_inicial).getTime());
            java.sql.Date f_vencimiento = new java.sql.Date(formateador.parse(f_final).getTime());

            resultset = statement.executeQuery("select * from solicitudes");
            preparedStmt.setInt(1, folio);
            preparedStmt.setInt(2, monto);
            preparedStmt.setString(3, descripcion);
            preparedStmt.setInt(4, convenio);
            preparedStmt.setString(5, referencia);
            preparedStmt.setDate(6, f_emision);
            preparedStmt.setDate(7, f_vencimiento);
            preparedStmt.execute();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void cierre(){
        try {
            resultset.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
