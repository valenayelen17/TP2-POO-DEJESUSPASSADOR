package Banco;

import LogicaBanco.Usuario;
import LogicaBanco.Cliente;
import LogicaBanco.Cuenta;
import LogicaBanco.Empleado;
import LogicaBanco.Rol;

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
					usuario.menu();
					
					continuar = false;
					break;
					
				case 2:
					continuar = false;
					break;
			}
		}
	}
	
	
}
