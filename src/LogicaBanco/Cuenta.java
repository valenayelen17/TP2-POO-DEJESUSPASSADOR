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
	
	public int getNumCuenta() {
		return numCuenta;
	}
	
	public static LinkedList<Cuenta> getCuentas() {
		return cuentas;
	}

	public void depositar(double monto) {
		this.saldo += monto;
		new Movimiento(this, monto, "Depósito");
	}
	
	public boolean retirar(double monto) {
		if (saldo >= monto) {
			saldo -= monto;
			new Movimiento(this, monto, "Retiro");
			return true;
		}
		return false;
	}
	
	public boolean transferir(Cuenta destino, double monto) {
		if(retirar(monto)) {
			destino.depositar(monto);
			new Movimiento(this, monto, "Transferencia enviada");
			new Movimiento(destino, monto, "Transferencia recibida");
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Cuenta [saldo=" + saldo + ", numCuenta=" + numCuenta + ", cliente=" + cliente + ", movimientos="
				+ movimientos + "]";
	}
	
}
