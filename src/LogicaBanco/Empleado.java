package LogicaBanco;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario{
	
	private String sector;
	private String nombre;
	private String Apellido;
	private LinkedList<Cliente> clientes = new LinkedList<Cliente>();
	private LinkedList<Movimiento> MovimientosGen = new LinkedList<Movimiento>();
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	
	public LinkedList<Movimiento> getMovimientosGen() {
		return MovimientosGen;
	}

	public void setMovimientosGen(LinkedList<Movimiento> movimientosGen) {
		MovimientosGen = movimientosGen;
	}

	public Empleado() {
		super();
	}
	
	public Empleado(String email, String contrasenia, String sector) {
			super(email, contrasenia, Rol.Empleado);
			this.sector = sector;
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
					this.getRol().getOpciones(),
					this.getRol().getOpciones());
			
			switch (opcion) {
				case 0:
					JOptionPane.showMessageDialog(null, this.getMovimientosGen());
					break;
					
				case 1:
					
					clientes = new LinkedList<Cliente>();
					for(Usuario usuario : Usuario.getUsuarios()) {
						if (usuario.getRol()== Rol.Cliente) {
							clientes.add((Cliente)usuario);
						}
					}
					break;
					
				case 2:
					continuar = false;
					break;
				case 3:
					continuar = false;
					break;
			}
		}
	
		
	}


}
