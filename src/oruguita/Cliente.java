package oruguita;

import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ilich
 */
public class Cliente {

    private int id, telefono, edad;
    private String rut, nombre, sexo, direccion, razonSocial;

    public Cliente() {
    }

    public Cliente(int id, int telefono, int edad, String rut, String nombre, String sexo, String direccion, String razonSocial) {
        this.id = id;
        this.telefono = telefono;
        this.edad = edad;
        this.rut = rut;
        this.nombre = nombre;
        this.sexo = sexo;
        this.direccion = direccion;
        this.razonSocial = razonSocial;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", telefono=" + telefono + ", edad=" + edad + ", rut=" + rut + ", nombre=" + nombre + ", sexo=" + sexo + ", direccion=" + direccion + ", razonSocial=" + razonSocial + '}';
    }

    Connection connection;
    PreparedStatement ps;
    Conexion cn = new Conexion();
    ResultSet rs;

    public boolean RegistrarCliente(Cliente cl) {
        String sql = "INSERT INTO cliente ( rut_cliente, nombre_cliente, edad_cliente,sexo, telefono_cliente, direccion_cliente, razon_social_cliente) VALUES(?,?,?,?,?,?,?)";

        try {
            connection = cn.obtenerConexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cl.getRut());
            ps.setString(2, cl.getNombre());
            ps.setInt(3, cl.getEdad());
            ps.setString(4, cl.getSexo());
            ps.setInt(5, cl.getTelefono());
            ps.setString(6, cl.getDireccion());
            ps.setString(7, cl.getRazonSocial());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.toString());

            }
        }
        return false;
    }

    public List ListarCliente() {
        List<Cliente> ListaCl = new ArrayList();
        String sql = "SELECT * FROM cliente";
        try {
            connection = cn.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id_cliente"));
                cl.setRut(rs.getString("rut_cliente"));
                cl.setNombre(rs.getString("nombre_cliente"));
                cl.setEdad(rs.getInt("edad_cliente"));
                cl.setSexo(rs.getString("sexo"));
                cl.setTelefono(rs.getInt("telefono_cliente"));
                cl.setDireccion(rs.getString("direccion_cliente"));
                cl.setRazonSocial(rs.getString("razon_social_cliente"));
                ListaCl.add(cl);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }

    public boolean ModificarCliente(Cliente cl) {
        String sql = "UPDATE cliente SET rut_cliente=?, nombre_cliente=?, edad_cliente=?,sexo=?, telefono_cliente=?, direccion_cliente=?, razon_social_cliente=? WHERE id_cliente=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cl.getRut());
            ps.setString(2, cl.getNombre());
            ps.setInt(3, cl.getEdad());
            ps.setString(4, cl.getSexo());
            ps.setInt(5, cl.getTelefono());
            ps.setString(6, cl.getDireccion());
            ps.setString(7, cl.getRazonSocial());
            ps.setInt(8, cl.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return false;
       
     }

    public boolean EliminarCliente(int id){
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                connection.close();

            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

    }

  
}
