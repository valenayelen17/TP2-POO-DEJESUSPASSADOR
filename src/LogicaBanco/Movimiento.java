package LogicaBanco;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movimiento {
	private Cuenta cuenta;
	private LocalDateTime fecha;
	private double monto;
	private String tipo;
	
	public Movimiento(double monto, String tipo) {
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

	@Override
	public String toString() {
		return "Movimiento [cuenta=" + cuenta + ", fecha=" + fecha + ", monto=" + monto + ", tipo=" + tipo + "]";
	}
	
}
