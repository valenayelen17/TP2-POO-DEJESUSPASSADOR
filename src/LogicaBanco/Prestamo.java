package LogicaBanco;

public class Prestamo {
    private Cliente cliente;
    private double monto;
    private boolean aprobado;

    public Prestamo(Cliente cliente, double monto) {
        this.cliente = cliente;
        this.monto = monto;
        this.aprobado = false; 
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getMonto() {
        return monto;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    @Override
    public String toString() {
        return "Préstamo de $" + String.format("%.2f", monto) +
               " para " + cliente.getNombre() + " " + cliente.getApellido() +
               " - " + (aprobado ? "Aprobado" : "Pendiente");
    }

}
