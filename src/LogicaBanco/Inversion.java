package LogicaBanco;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Inversion {
	private static LinkedList<Inversion> inversiones = new LinkedList<Inversion>();
	private static int numInversiones = 0;
	
	private int idInversion;
	private Cliente cliente;
	private double saldoInversion;
	private double montoInicial;
	private LocalDate fechaCreacion;
	private String estado;
	private int diasInversion;
	
	public Inversion(Cliente cliente, double montoInicial) {
		numInversiones++;
		this.idInversion = numInversiones;
		this.cliente = cliente;
		this.montoInicial = montoInicial;
		this.saldoInversion = montoInicial;
		this.fechaCreacion = LocalDate.now();
		this.estado = "Activa";
		this.diasInversion = 0;
		
		inversiones.add(this);
		
		if (cliente != null && cliente.getCuenta() != null) {
			Movimiento mov = new Movimiento(cliente.getCuenta(), montoInicial, 
				"Inversión creada - ID: #" + idInversion);
			cliente.getCuenta().getMovimientos().add(mov);
		}
	}
	
	public int getIdInversion() {
		return idInversion;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public double getSaldoInversion() {
		return saldoInversion;
	}
	
	public void setSaldoInversion(double saldoInversion) {
		this.saldoInversion = saldoInversion;
	}
	
	public double getMontoInicial() {
		return montoInicial;
	}
	
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getDiasInversion() {
		return diasInversion;
	}
	
	public static LinkedList<Inversion> getInversiones() {
		return inversiones;
	}
	
	public static double generarTasaAleatoriaDelMercado() {
		// Rango: -0.10 a +0.10
		return (Math.random() * 0.20) - 0.10;
	}
	
	public void simularRendimientoDiario() {
		if (!estado.equals("Activa")) {
			return;
		}
		
		double tasaDiaria = generarTasaAleatoriaDelMercado();
		double rendimiento = saldoInversion * tasaDiaria;
		saldoInversion += rendimiento;
		
		saldoInversion = Math.round(saldoInversion * 100.0) / 100.0;
		
		diasInversion++;
		
		if (cliente != null && cliente.getCuenta() != null) {
			String tipoMov = rendimiento >= 0 ? "Rendimiento positivo" : "Rendimiento negativo";
			Movimiento mov = new Movimiento(cliente.getCuenta(), 
				Math.abs(rendimiento), 
				tipoMov + " - Inversión #" + idInversion + " - Tasa: " + 
				String.format("%.2f%%", tasaDiaria * 100));
			cliente.getCuenta().getMovimientos().add(mov);
		}
	}
	
	public double calcularRendimientoNeto() {
		return saldoInversion - montoInicial;
	}
	
	public double calcularRendimientoPorcentual() {
		if (montoInicial == 0) {
			return 0.0;
		}
		return ((saldoInversion - montoInicial) / montoInicial) * 100.0;
	}
	
	public String cancelarInversion() {
		if (!estado.equals("Activa")) {
			return "Error: La inversión no está activa.";
		}
		
		estado = "Cancelada";
		
		if (cliente != null && cliente.getCuenta() != null) {
			cliente.getCuenta().setSaldo(
				cliente.getCuenta().getSaldo() + saldoInversion
			);
			
			Movimiento mov = new Movimiento(cliente.getCuenta(), 
				saldoInversion, 
				"Cancelación de inversión #" + idInversion);
			cliente.getCuenta().getMovimientos().add(mov);
		}
		
		String comprobante = "=== Comprobante de Cancelación de Inversión ===\n" +
			"ID Inversión: " + idInversion + "\n" +
			"Monto inicial: $" + String.format("%.2f", montoInicial) + "\n" +
			"Saldo final: $" + String.format("%.2f", saldoInversion) + "\n" +
			"Rendimiento neto: $" + String.format("%.2f", calcularRendimientoNeto()) + "\n" +
			"Rendimiento %: " + String.format("%.2f%%", calcularRendimientoPorcentual()) + "\n" +
			"Días invertidos: " + diasInversion + "\n";
		
		return comprobante;
	}
	
	@Override
	public String toString() {
		String resultado = "Inversion ID: " + idInversion + "\n";
		resultado += "  Cliente: " + (cliente != null ? cliente.getNombre() : "N/A") + "\n";
		resultado += "  Monto inicial: $" + String.format("%.2f", montoInicial) + "\n";
		resultado += "  Saldo actual: $" + String.format("%.2f", saldoInversion) + "\n";
		resultado += "  Rendimiento: $" + String.format("%.2f", calcularRendimientoNeto());
		resultado += " (" + String.format("%.2f%%", calcularRendimientoPorcentual()) + ")\n";
		resultado += "  Días: " + diasInversion + "\n";
		resultado += "  Estado: " + estado;
		
		return resultado;
	}
	
	public static void menuInversion(Cliente cliente) {
		boolean continuarInversiones = true;
		
		while (continuarInversiones) {
			String[] opcionesInversiones = {"Crear inversión", "Ver inversiones", "Simular rendimiento diario", "Cancelar inversión", "Volver"};
			
			int opcionInversion = JOptionPane.showOptionDialog(
				null,
				"=== Menú de Inversiones ===\n" +
				"Saldo disponible: $" + String.format("%.2f", cliente.getCuenta().getSaldo()),
				"Inversiones",
				0,
				0,
				null,
				opcionesInversiones,
				opcionesInversiones[0]
			);
			
			switch (opcionInversion) {
				case 0:
					// Crear inversión
					double montoInversion = Validaciones.ValidarDouble("Ingrese monto a invertir:");
					
					if (montoInversion <= 0) {
						JOptionPane.showMessageDialog(null, "El monto debe ser mayor a 0.");
						break;
					}
					
					if (montoInversion > cliente.getCuenta().getSaldo()) {
						JOptionPane.showMessageDialog(null, "Saldo insuficiente para invertir ese monto.");
						break;
					}
					
					cliente.getCuenta().setSaldo(cliente.getCuenta().getSaldo() - montoInversion);
					Inversion inversion = new Inversion(cliente, montoInversion);
					
					JOptionPane.showMessageDialog(null, "Inversión creada exitosamente.\n" +
						"ID: " + inversion.getIdInversion() + "\n" +
						"Monto: $" + String.format("%.2f", montoInversion));
					break;
					
				case 1:
					// Ver inversiones
					if (Inversion.getInversiones().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay inversiones registradas.");
						break;
					}
					
					String inversionesMsg = "";
					for (Inversion inv : Inversion.getInversiones()) {
						if (inv.getCliente() == cliente && inv.getEstado().equals("Activa")) {
							inversionesMsg += inv.toString() + "\n\n";
						}
					}
					
					if (inversionesMsg.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No tienes inversiones activas.");
					} else {
						JOptionPane.showMessageDialog(null, inversionesMsg);
					}
					break;
					
				case 2:
					// Simular rendimiento diario
					if (Inversion.getInversiones().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay inversiones para simular.");
						break;
					}
					
					String resultadoSimulacion = "";
					for (Inversion inv : Inversion.getInversiones()) {
						if (inv.getCliente() == cliente && inv.getEstado().equals("Activa")) {
							double tasaDiaria = Inversion.generarTasaAleatoriaDelMercado();
							inv.simularRendimientoDiario();
							
							resultadoSimulacion += "Inversión #" + inv.getIdInversion() + "\n";
							resultadoSimulacion += "  Tasa: " + String.format("%+.2f%%", tasaDiaria * 100) + "\n";
							resultadoSimulacion += "  Nuevo saldo: $" + String.format("%.2f", inv.getSaldoInversion()) + "\n";
							resultadoSimulacion += "  Rendimiento acumulado: $" + String.format("%.2f", inv.calcularRendimientoNeto()) + "\n\n";
						}
					}
					
					if (resultadoSimulacion.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No tienes inversiones activas para simular.");
					} else {
						JOptionPane.showMessageDialog(null, "=== Simulación Diaria ===\n" + resultadoSimulacion);
					}
					break;
					
				case 3:
					// Cancelar inversión
					if (Inversion.getInversiones().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No hay inversiones para cancelar.");
						break;
					}
					
					int idInversionCancelar = Validaciones.ValidarInt("Ingrese ID de inversión a cancelar:");
					
					boolean encontrada = false;
					for (Inversion inv : Inversion.getInversiones()) {
						if (inv.getIdInversion() == idInversionCancelar && inv.getCliente() == cliente) {
							encontrada = true;
							String comprobante = inv.cancelarInversion();
							JOptionPane.showMessageDialog(null, comprobante);
							break;
						}
					}
					
					if (!encontrada) {
						JOptionPane.showMessageDialog(null, "Inversión no encontrada.");
					}
					break;
					
				case 4:
					// Volver
					continuarInversiones = false;
					break;
			}
		}
	}
}
