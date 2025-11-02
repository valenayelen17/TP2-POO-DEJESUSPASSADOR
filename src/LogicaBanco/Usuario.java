package LogicaBanco;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.LinkedList;

public class Usuario {
	
	private static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	
    private String email;
    private String contrasenia;

    public Usuario() {
    }
    
    public Usuario(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }
    
    public static LinkedList<Usuario> getUsuarios() {
        return usuarios;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public static Usuario iniciarSesion() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            return null;
        }

        String emailIngresado = JOptionPane.showInputDialog("Ingrese su email:");
        String contraseniaIngresada = JOptionPane.showInputDialog("Ingrese su contraseña:");

        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(emailIngresado) && u.getContrasenia().equals(contraseniaIngresada)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso Bienvenido, " + ((Cliente)u).getNombre() + "!");
                return u;
            }
        }

        JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos ");
        return null;
    }
    
    public static void agregarUsuariosPrueba() {
        Cliente usuario1 = new Cliente();
        usuario1.setNombre("Juan");
        usuario1.setApellido("Carlos");
        usuario1.setDni("12345678");
        usuario1.setFechaNacimiento(LocalDate.of(1990, 5, 15));
        usuario1.setTelefono("1234567890");
        usuario1.setDomicilio("Av. Principal 123");
        usuario1.setEmail("juan@prueba.com");
        usuario1.setContrasenia("1234");
        usuarios.add(usuario1);
        
        Cliente usuario2 = new Cliente();
        usuario2.setNombre("María");
        usuario2.setApellido("García");
        usuario2.setDni("87654321");
        usuario2.setFechaNacimiento(LocalDate.of(1985, 8, 20));
        usuario2.setTelefono("0987654321");
        usuario2.setDomicilio("Calle Secundaria 456");
        usuario2.setEmail("maria@prueba.com");
        usuario2.setContrasenia("abcd");
        usuarios.add(usuario2);

    }
}
