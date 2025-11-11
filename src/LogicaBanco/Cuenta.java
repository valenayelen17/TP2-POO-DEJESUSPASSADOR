package LogicaBanco;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Cuenta {
	private static LinkedList<Cuenta> cuentas = new LinkedList<Cuenta>();
	private double saldo;
	private int numCuenta;
	private static int num = 0;
	private Cliente cliente;
	private LinkedList<Movimiento> movimientos;
	
	public Cuenta(Cliente cliente) {
		num++;
		this.cliente = cliente;
		this.saldo = 0.0;
		this.numCuenta = num;
		this.movimientos = new LinkedList<Movimiento>();
		cuentas.add(this);
	}
	public Cuenta(Cliente cliente, int maximo) {
		num++;
		this.cliente = cliente;
		this.saldo = Math.random()*maximo+100;
		this.numCuenta = num;
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
	
	public void Retirar(Cliente cliente, int monto) {
		
	}

	@Override
	public String toString() {
		return "Cuenta [saldo=" + saldo + ", numCuenta=" + numCuenta + ", cliente=" + cliente + ", movimientos="
				+ movimientos + "]";
	}
	
}
