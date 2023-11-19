
package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public login log(String usuario, String pass) {
        login l = new login();
        String sql = "SELECT * FROM usuario WHERE nombre = ? AND pass = ?";
        try {
            connection = cn.obtenerConexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setUsuario(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return l;
    }
}

