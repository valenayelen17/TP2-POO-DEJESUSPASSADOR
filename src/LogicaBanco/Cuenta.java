package LogicaBanco;

import java.util.LinkedList;
import java.util.stream.Collectors;

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
		this.saldo = Math.round((Math.random() * maximo + 100) * 100.0) / 100.0;
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

		return this.movimientos;
	}
	
	public String getMovimientosString() {
		if (this.movimientos == null || this.movimientos.isEmpty()) {
			return "No hay movimientos";
		}

		return "=== Movimientos ===\n" +
			movimientos.stream()
				.map(Movimiento::toString)
				.collect(Collectors.joining("\n-----------------------\n"));
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

	public static String getCuentasString() {

		if(cuentas == null || cuentas.isEmpty()) {
			return "No hay cuentas registradas.";
		}

		return "=== Cuentas ===\n" +
			cuentas.stream()
				.map(Cuenta::toString)
				.collect(Collectors.joining("\n-----------------------\n"));
	}

	public void depositar(double monto) {
		this.saldo += monto;
		Movimiento deposito = new Movimiento(this, monto, "Depósito");
		this.movimientos.add(deposito);
		
	}
	
	public String retirar(double monto) {
		String mensaje;
        if (monto <= 0) {
            mensaje = "Error: el monto debe ser mayor a 0.";
        }

        if (saldo < monto) {
        	mensaje = "Error: saldo insuficiente.\n" +
                   "Saldo actual: $" + saldo + "\n" +
                   "Monto solicitado: $" + monto;
        }

        saldo -= monto;
        Movimiento retiro = new Movimiento(this, monto, "Retiro");
        this.movimientos.add(retiro);

        mensaje = "=== Comprobante de retiro ===\n" +
                             "Cuenta: " + this.numCuenta + "\n" +
                             "Titular: " + cliente.getNombre() + " " + cliente.getApellido() + "\n" +
                             "Monto retirado: $" + monto + "\n" +
                             "Saldo restante: $" + saldo + "\n" +
                             "Movimiento: " + retiro.toString();
        
        return mensaje;
    }
	
    public String transferir(Cuenta destino, double monto) {
    	String mensaje;

        if (destino == this) {
        	mensaje = "Error: no se puede transferir a la misma cuenta.";
        }

        if (monto <= 0) {
        	mensaje = "Error: el monto debe ser mayor a 0.";
        }

        if (this.saldo < monto) {
        	mensaje = "Error: saldo insuficiente para realizar la transferencia.\n" +
                   "Saldo actual: $" + this.saldo + "\n" +
                   "Monto a transferir: $" + monto;
        }

        this.saldo -= monto;
        Movimiento envio = new Movimiento(this, monto, "Transferencia enviada a cuenta " + destino.getNumCuenta());
        this.movimientos.add(envio);

        destino.saldo += monto;
        Movimiento recibo = new Movimiento(destino, monto, "Transferencia recibida de cuenta " + this.getNumCuenta());
        destino.movimientos.add(recibo);

        mensaje = "=== Comprobante de transferencia ===\n" +
                             "Cuenta origen: " + this.getNumCuenta() + " - " + this.cliente.getNombre() + " " + this.cliente.getApellido() + "\n" +
                             "Cuenta destino: " + destino.getNumCuenta() + " - " + destino.getCliente().getNombre() + " " + destino.getCliente().getApellido() + "\n" +
                             "Monto transferido: $" + monto + "\n" +
                             "Saldo cuenta origen luego de la operación: $" + this.saldo + "\n";
        
        return mensaje;
    }
	
	@Override
	public String toString() {
		return "Cuenta [saldo=" + saldo + ", numCuenta=" + numCuenta + ", cliente=" + cliente + ", movimientos="
				+ movimientos + "]";
	}
	
}
