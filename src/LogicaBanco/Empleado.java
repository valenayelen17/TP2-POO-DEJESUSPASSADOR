package LogicaBanco;


import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Empleado extends Usuario{
	
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
	 
	 private static LinkedList<Prestamo> prestamosPendientes = new LinkedList<>();

	 public static void agregarPrestamo(Prestamo p) {
	        prestamosPendientes.add(p);
	    }

	 public static LinkedList<Prestamo> getPrestamosPendientes() {
	        return prestamosPendientes;
	    }
	 
	 private void mostrarCuentasConFiltro() {

		    String[] opciones = {
		            "Ordenar por Nº de Cuenta",
		            "Ordenar por Cliente (A-Z)",
		            "Saldo Mayor a Menor",
		            "Saldo Menor a Mayor"
		    };

		    int seleccion = JOptionPane.showOptionDialog(
		            null,
		            "Seleccione el formato para ver las cuentas:",
		            "Filtro de cuentas",
		            0,
		            JOptionPane.PLAIN_MESSAGE,
		            null,
		            opciones,
		            opciones[0]
		    );

		    LinkedList<Cuenta> lista = new LinkedList<>(Cuenta.getCuentas());

		    switch (seleccion) {
		        case 0:
		            lista.sort((a, b) -> a.getNumCuenta() - b.getNumCuenta());
		            break;

		        case 1:
		            lista.sort((a, b) ->
		                    a.getCliente().getNombre().compareToIgnoreCase(b.getCliente().getNombre()));
		            break;

		        case 2:
		            lista.sort((a, b) ->
		                    Double.compare(b.getSaldo(), a.getSaldo()));
		            break;

		        case 3:
		            lista.sort((a, b) ->
		                    Double.compare(a.getSaldo(), b.getSaldo()));
		            break;
		    }
		    
		    String texto = "LISTA DE CUENTAS:\n\n";

		    for (Cuenta c : lista) {
		        texto += c.toString() + "\n";
		    }

		    JOptionPane.showMessageDialog(null, texto);
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
					// Ver movimientos generales
	                    for (Cuenta c : Cuenta.getCuentas()) {
	                        movimientosGenerales.addAll(c.getMovimientos());
	                    }
	                    JOptionPane.showMessageDialog(null, movimientosGenerales);
	                    break;
				case 1:
					// Ver cuentas
					mostrarCuentasConFiltro();
					break;
					
				case 2:
					//Ver usuarios
					JOptionPane.showMessageDialog(null, Cuenta.getCuentasString());
					break;
				case 3:
					 if (prestamosPendientes.isEmpty()) {
	                        JOptionPane.showMessageDialog(null, "No hay préstamos pendientes.");
	                        break;
	                    }

	                    String texto = "PRÉSTAMOS PENDIENTES:\n\n";
	                    
	                    int i = 1;
	                    for (Prestamo p : prestamosPendientes) {
	                        texto += i + ". " + p.toString() + "\n";
	                        i++;
	                    }

	                    JOptionPane.showMessageDialog(null, texto);

	                    int seleccionar = Validaciones.ValidarInt("Ingrese el número del préstamo a aprobar/rechazar (0 para salir):");
	                    if (seleccionar <= 0 || seleccionar > prestamosPendientes.size()) break;

	                    Prestamo p = prestamosPendientes.get(seleccionar - 1);
	                    int opcionAprobar = JOptionPane.showConfirmDialog(null, 
	                        "¿Desea aprobar el préstamo de $" + String.format("%.2f", p.getMonto()) + 
	                        " para " + p.getCliente().getNombre() + "?", 
	                        "Aprobar préstamo", JOptionPane.YES_NO_OPTION);

	                    if (opcionAprobar == JOptionPane.YES_OPTION) {
	                        p.setAprobado(true);
	                        double nuevoSaldo = p.getCliente().getCuenta().getSaldo() + p.getMonto();
	                        nuevoSaldo = Math.round(nuevoSaldo * 100.0) / 100.0;
	                        p.getCliente().getCuenta().setSaldo(nuevoSaldo);

	                        Movimiento movimientoPrestamo = new Movimiento(p.getCliente().getCuenta(), p.getMonto(), "Préstamo");
	                        p.getCliente().getCuenta().getMovimientos().add(movimientoPrestamo);

	                        JOptionPane.showMessageDialog(null, "Préstamo aprobado y acreditado.");
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Préstamo rechazado.");
	                    }

	                    prestamosPendientes.remove(p);
	                    break;
				case 4:
					// Eliminar cuenta
					 JOptionPane.showMessageDialog(null, Cuenta.getCuentasString());

					    int numEliminar = Validaciones.ValidarInt("Ingrese número de cuenta a eliminar:");

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
				case 5:
					// Salir
					String[] opciones = {"Si", "No"};
					
					int confirmar = JOptionPane.showOptionDialog(
					        null,
					        "¿Está seguro que desea cerrar sesión?",
					        "Confirmar cierre de sesión",
					        0,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        opciones,
					        opciones[0]
					    );

					    if (confirmar == 0) {
					        JOptionPane.showMessageDialog(null, "Sesión cerrada. Volviendo al menú principal.");
					        continuar = false;
					    }
					    break;
			}
		}
	
		
	}


}
