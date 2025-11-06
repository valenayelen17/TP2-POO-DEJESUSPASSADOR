package Banco;

import LogicaBanco.Usuario;
import LogicaBanco.Cliente;
import javax.swing.JOptionPane;

public class Main { 
	public static void main(String[] args) {

		boolean continuar = true;
		Usuario.agregarUsuariosPrueba();
		
		while (continuar) {
			String[] opciones = {"Registrarse", "Iniciar sesión", "Salir"};
			int opcion = JOptionPane.showOptionDialog(
				null,
				"Bienvenido/a, seleccione una opción:",
				"Menú Principal",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				opciones,
				opciones[0]
			);
			
			switch (opcion) {
				case 0:
					Cliente nuevoCliente = new Cliente();
					nuevoCliente.registrarCliente();
					break;
					
				case 1:
					Usuario usuario = Usuario.iniciarSesion();
					if (usuario != null) {
						menuCuenta((Cliente) usuario);
					}
					continuar = false;
					break;
					
				case 2:
					continuar = false;
					break;
			}
		}
	}
	
	public static void menuCuenta(Cliente cliente) {
		boolean continuar = true;
		
		while (continuar) {
			double saldo = cliente.getCuenta().getSaldo();
			
			String[] opciones = {"Ingresar dinero", "Transferir dinero", "Pedir préstamo", "Salir"};
			int opcion = JOptionPane.showOptionDialog(
				null,
				"Hola " + cliente.getNombre() + "\n" +
				"Saldo actual: $" + saldo + "\n\n" +
				"Seleccione una opción:",
				"Mi Cuenta",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				opciones,
				opciones[0]
			);
			
			switch (opcion) {
				case 0:
					// Ingresar dinero
					break;
					
				case 1:
					// Transferir dinero
					break;
					
				case 2:
					// Pedir préstamo
					break;
					
				case 3:
					continuar = false;
					break;
			}
		}
	
	}
}
