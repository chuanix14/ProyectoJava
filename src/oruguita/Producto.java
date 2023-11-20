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
public class Producto {
    private int id, codigo, cantidad,precio, stock;
    private String descripcion, proveedor;

    public Producto() {
    }

    public Producto(int id, int codigo, int cantidad, int precio, int stock, String descripcion, String proveedor) {
        this.id = id;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", codigo=" + codigo + ", cantidad=" + cantidad + ", precio=" + precio + ", stock=" + stock + ", descripcion=" + descripcion + ", proveedor=" + proveedor + '}';
    }
    
    Connection connection;
    PreparedStatement ps;
    Conexion cn = new Conexion();
    ResultSet rs;

    public boolean RegistrarProducto(Producto pro) {
        String sql = "INSERT INTO producto ( codigo_producto, descripcion_producto, cantidad_producto,precio_producto, proveedor_producto) VALUES(?,?,?,?,?)";

        try {
            connection = cn.obtenerConexion();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pro.getCodigo());
            ps.setString(2, pro.getDescripcion());
            ps.setInt(3, pro.getCantidad());
            ps.setInt(4, pro.getPrecio());
            ps.setString(5, pro.getProveedor());
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
    
    public List ListarProducto() {
        List<Producto> ListaPro = new ArrayList();
        String sql = "SELECT * FROM producto";
        try {
            connection = cn.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id_producto"));
                pro.setCodigo(rs.getInt("codigo_producto"));
                pro.setDescripcion(rs.getString("descripcion_producto"));
                pro.setCantidad(rs.getInt("cantidad_producto"));
                pro.setPrecio(rs.getInt("precio_producto"));
                pro.setProveedor(rs.getString("proveedor_producto"));                
                ListaPro.add(pro);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPro;
    }
    
    public boolean EliminarProducto(int id){
        String sql = "DELETE FROM producto WHERE id_producto = ?";
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
    
    
    public boolean ModificarProducto(Producto pro) {
        String sql = "UPDATE producto SET codigo_producto=?, descripcion_producto=?, cantidad_producto=?,precio_producto=?, proveedor_producto=?  WHERE id_producto=? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pro.getCodigo());
            ps.setString(2, pro.getDescripcion());
            ps.setInt(3, pro.getCantidad());
            ps.setInt(4, pro.getPrecio());
            ps.setString(5, pro.getProveedor());            
            ps.setInt(6, pro.getId());
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
    
      public Producto BuscarPro(String cod){
        Producto producto = new Producto();
        String sql = "SELECT * FROM producto WHERE codigo_producto = ?";
        try {
            connection = cn.obtenerConexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                
                producto.setDescripcion(rs.getString("descripcion_producto"));
                producto.setPrecio(rs.getInt("precio_producto"));
                producto.setCantidad(rs.getInt("cantidad_producto"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
}
