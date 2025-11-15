package LogicaBanco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Movimiento {
	private Cuenta cuenta;
	private LocalDateTime fecha;
	private double monto;
	private String tipo;
	
	public Movimiento(Cuenta cuenta, double monto, String tipo) {
		this.cuenta = cuenta;
		this.fecha = LocalDateTime.now();
		this.monto = monto;
		this.tipo = tipo;
		
	}
	
	public LocalDateTime getfecha() {
		return fecha;
	}
	
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public double getMonto() {
		return monto;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Movimiento [cuenta=" + cuenta + ", fecha=" + fecha + ", monto=" + monto + ", tipo=" + tipo + "]";
	}
	
}
