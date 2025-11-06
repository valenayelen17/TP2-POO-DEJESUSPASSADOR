package LogicaBanco;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario{
	
	private String sector;
	
	private static LinkedList<Movimiento> MovimientosGen = new LinkedList<Movimiento>();
	
	public Empleado(String email, String contrasenia, String sector) {
			super(email, contrasenia, Rol.Empleado);
			this.sector = sector;
		}

	@Override
	public void menu() {
		// TODO Auto-generated method stub
		
	}


}
