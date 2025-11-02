package LogicaBanco;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente extends Usuario {
	
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNacimiento;
	private String telefono;
	private String domicilio;
	private Cuenta cuenta;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, String telefono, String domicilio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.domicilio = domicilio;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta(this);
        }
        return cuenta;
    }
    
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

	public void registrarUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        setNombre(nombre);

        String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");
        setApellido(apellido);

        String dni = JOptionPane.showInputDialog("Ingrese su DNI:");
        setDni(dni);

        String fechaStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy):");
        LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        setFechaNacimiento(fecha);

        String telefono = JOptionPane.showInputDialog("Ingrese su teléfono:");
        setTelefono(telefono);

        String domicilio = JOptionPane.showInputDialog("Ingrese su domicilio:");
        setDomicilio(domicilio);

        String emailIngresado = JOptionPane.showInputDialog("Ingrese su email:");
        while (emailIngresado == null || !emailIngresado.contains("@")) {
            emailIngresado = JOptionPane.showInputDialog("Email inválido. Ingrese nuevamente:");
        }
        setEmail(emailIngresado);

        String contraseniaIngresada = JOptionPane.showInputDialog("Cree una contraseña:");
        while (contraseniaIngresada == null || contraseniaIngresada.trim().length() < 4) {
            contraseniaIngresada = JOptionPane.showInputDialog("La contraseña debe tener al menos 4 caracteres. Ingrese nuevamente:");
        }
        
        String confirmacionContrasenia = JOptionPane.showInputDialog("Confirme su contraseña:");
        while (!contraseniaIngresada.equals(confirmacionContrasenia)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden. Intente nuevamente.");
            contraseniaIngresada = JOptionPane.showInputDialog("Cree una contraseña:");
            while (contraseniaIngresada == null || contraseniaIngresada.trim().length() < 4) {
                contraseniaIngresada = JOptionPane.showInputDialog("La contraseña debe tener al menos 4 caracteres. Ingrese nuevamente:");
            }
            confirmacionContrasenia = JOptionPane.showInputDialog("Confirme su contraseña:");
        }
        setContrasenia(contraseniaIngresada);

        Usuario.getUsuarios().add(this);
        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
    }
}
