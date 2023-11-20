
package bd;

/**
 *
 * @author ilich
 */
public class login {
    private int id;
    private String nombre, usuario, pass;

    public login() {
    }

    public login(int id, String nombre, String usuario, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String correo) {
        this.usuario = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Login{" + "id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", pass=" + pass + '}';
    }
    
    
    
    
}
