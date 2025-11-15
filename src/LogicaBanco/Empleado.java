package LogicaBanco;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario{
	
	private String sector;
	private String nombre;
	private String apellido;
	private LinkedList<Cliente> clientes = new LinkedList<Cliente>();
	private LinkedList<Movimiento> movimientosGenerales = new LinkedList<>();
	
	public Empleado() {
		super();
		this.setRol(Rol.Empleado);
	}
	
	public Empleado(String nombre, String apellido, String email, String contrasenia) {
			super(email, contrasenia, Rol.Empleado);
			this.nombre = nombre;
			this.apellido = apellido;
			this.clientes = new LinkedList<>();
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
	
	 public LinkedList<Movimiento> getMovimientosGenerales() { 
		 return movimientosGenerales; 
	}
	 public void setMovimientosGenerales(LinkedList<Movimiento> movimientosGenerales) {
		   this.movimientosGenerales = movimientosGenerales; 
	}


	@Override
	public void menu() {
		boolean continuar = true;
		
		while (continuar) {
			
			int opcion = JOptionPane.showOptionDialog(
					null, 
					"Hola " + this.getNombre() + "\n" +
					"Seleccione una opción:",
					"Menu empleado",
					0,
					0,
					null,
					getRol().getOpciones(),
					getRol().getOpciones());
			
			switch (opcion) {
				case 0:
					 movimientosGenerales.clear();
	                    for (Cuenta c : Cuenta.getCuentas()) {
	                        movimientosGenerales.addAll(c.getMovimientos());
	                    }
	                    JOptionPane.showMessageDialog(null, movimientosGenerales);
	                    break;
				case 1:
					
					clientes = new LinkedList<Cliente>();
					for(Usuario usuario : Usuario.getUsuarios()) {
						if (usuario.getRol()== Rol.Cliente) {
							clientes.add((Cliente)usuario);
						}
					}
					JOptionPane.showMessageDialog(null, clientes);
					break;
					
				case 2:
					 int numEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de cuenta a eliminar:"));
	                    Cuenta eliminar = Cuenta.getCuentas().stream()
	                            .filter(c -> c.getNumCuenta() == numEliminar)
	                            .findFirst().orElse(null);
	                    if (eliminar != null) {
	                        Cuenta.getCuentas().remove(eliminar);
	                        JOptionPane.showMessageDialog(null, "Cuenta eliminada.");
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
	                    }
					break;
				case 3:
					break;
			}
		}
	
		
	}


}
