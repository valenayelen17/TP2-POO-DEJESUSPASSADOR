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
	private int pin;
	
	public Cliente() {
		super();
		this.setRol(Rol.Cliente);
	}
	
	public Cliente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, String telefono, String domicilio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.setRol(Rol.Cliente);
		this.cuenta = new Cuenta(this,1000);
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

	public void registrarCliente() {
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

	public void menu() {
		boolean continuar = true;
		
		while (continuar) {
			double saldo = this.getCuenta().getSaldo();
			
			int opcion = JOptionPane.showOptionDialog(
					null, 
					"Hola " + this.getNombre() + "\n" +
					"Saldo actual: $" + saldo + "\n\n" +
					"Seleccione una opción:",
					"Menu cliente",
					0,
					0,
					null,
					getRol().getOpciones(),
					getRol().getOpciones());
			
			switch (opcion) {
				case 0:
					// Ingresar dinero
					 double montoDeposito = Double.parseDouble(JOptionPane.showInputDialog("Ingrese monto a depositar:"));
	                    cuenta.depositar(montoDeposito);
	                    break;
				case 1:
					// Transferir dinero
					 int numDestino = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de cuenta destino:"));
	                    Cuenta destino = Cuenta.getCuentas().stream()
	                            .filter(c -> c.getNumCuenta() == numDestino)
	                            .findFirst().orElse(null);
	                    if (destino != null) {
	                    	
	                        double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese monto a transferir:"));
	                        if (cuenta.getSaldo() <  monto) {
	                            JOptionPane.showMessageDialog(null, "Saldo insuficiente");
	                        } else {
	                        	
	                        	cuenta.transferir(destino, monto);
	                        	JOptionPane.showMessageDialog(null, "Transferencia enviada");
	                        	break;
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada");
	                        break;
	                    }
	                    break;
				case 2:
					// Pedir préstamo
					JOptionPane.showMessageDialog(null, "Función de préstamo no implementada.");
                    break;
				case 3:
					// Ver movimientos
					JOptionPane.showMessageDialog(null, this.getCuenta().getMovimientosString());
					
					break;
					
				case 4:
					continuar = false;
					break;
			}
		}
	
	}
	
}
