/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class Proveedor {
    private int id, telefono;
    private String run, nombre, direccion, region, razonSocial, tipoProducto;

    public Proveedor() {
    }

    public Proveedor(int id, int telefono, String run, String nombre, String direccion, String region, String razonSocial, String tipoProducto) {
        this.id = id;
        this.telefono = telefono;
        this.run = run;
        this.nombre = nombre;
        this.direccion = direccion;
        this.region = region;
        this.razonSocial = razonSocial;
        this.tipoProducto = tipoProducto;
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

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", telefono=" + telefono + ", run=" + run + ", nombre=" + nombre + ", direccion=" + direccion + ", region=" + region + ", razonSocial=" + razonSocial + ", tipoProducto=" + tipoProducto + '}';
    }
    Connection connection;
    PreparedStatement ps;
    Conexion cn = new Conexion();
     ResultSet rs;
    
    public boolean RegistrarProveedor(Proveedor pr) {
        String sql = "INSERT INTO proveedor ( run_proveedor, nombre_proveedor, telefono_proveedor, direccion_proveedor, region_proveedor, razon_social_proveedor, tipo_producto_proveedor) VALUES(?,?,?,?,?,?,?)";

        try {
            connection = cn.obtenerConexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, pr.getRun());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRegion());
            ps.setString(6, pr.getRazonSocial());
            ps.setString(7, pr.getTipoProducto());
            ps.execute();
            return true;
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
        }
        
    public List ListarProveedor() {
        List<Proveedor> ListaPr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            connection = cn.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id_proveedor"));
                pr.setRun(rs.getString("run_proveedor"));
                pr.setNombre(rs.getString("nombre_proveedor"));
                pr.setTelefono(rs.getInt("telefono_proveedor"));
                pr.setDireccion(rs.getString("direccion_proveedor"));
                pr.setRegion(rs.getString("region_proveedor"));
                pr.setRazonSocial(rs.getString("razon_social_proveedor"));
                pr.setTipoProducto(rs.getString("tipo_producto_proveedor"));
                ListaPr.add(pr);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPr;
    }
    
    
    public boolean EliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";
        try {
            connection = cn.obtenerConexion();
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
    
       
    public boolean ModificarProveedor(Proveedor pr) {
        String sql = "UPDATE proveedor SET run_proveedor=?, nombre_proveedor=?, telefono_proveedor=?,direccion_proveedor=?, region_proveedor=?, razon_social_proveedor=?, tipo_producto_proveedor=? WHERE id_proveedor=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, pr.getRun());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRegion());
            ps.setString(6, pr.getRazonSocial());
            ps.setString(7, pr.getTipoProducto());
            ps.setInt(8, pr.getId());
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
    
}
