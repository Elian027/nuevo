import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    public static Connection getConecction() {
        Connection cn = null;
        String base = "estudiantesN"; //Nombre de la BD
        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y nombre BD
        String user = "root"; //Usuario
        String pass = "dariel17"; //Contraseña
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return cn;
    }
}
