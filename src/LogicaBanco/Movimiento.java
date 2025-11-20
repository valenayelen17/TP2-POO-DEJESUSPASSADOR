package LogicaBanco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Movimiento {
	private Cuenta cuenta;
	private LocalDateTime fecha;
	private double monto;
	private String tipo;
	private String estado; 
	
	public Movimiento(Cuenta cuenta, double monto, String tipo) {
		this.cuenta = cuenta;
		this.fecha = LocalDateTime.now();
		this.monto = monto;
		this.tipo = tipo;
		this.estado = "Pendiente";
		
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public String getFechaString() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    return fecha.format(formatter);
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
	public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	@Override
	public String toString() {
		return "Movimiento:\n" +
			"  Cuenta: " + cuenta.getNumCuenta() + "\n" +
			"  Fecha: " + getFechaString() + "\n" +
			"  Monto: $" + String.format("%.2f", monto) + "\n" +
			"  Tipo: " + tipo + "\n" + "  Estado: " + estado + "\n";
	}

	
}
