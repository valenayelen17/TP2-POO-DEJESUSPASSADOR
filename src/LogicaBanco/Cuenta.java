package LogicaBanco;

import java.util.LinkedList;

public class Cuenta {
	private static LinkedList<Movimiento> MovimientosGen = new LinkedList<Movimiento>();
	private static LinkedList<Cuenta> cuentas = new LinkedList<Cuenta>();
	private double saldo;
	private Cliente cliente;
	private LinkedList<Movimiento> movimientos;
	
	public Cuenta(Cliente cliente) {
		this.cliente = cliente;
		this.saldo = 0.0;
		this.movimientos = new LinkedList<Movimiento>();
		cuentas.add(this);
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public LinkedList<Movimiento> getMovimientos() {
		return movimientos;
	}
	
	public void setMovimientos(LinkedList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
}
