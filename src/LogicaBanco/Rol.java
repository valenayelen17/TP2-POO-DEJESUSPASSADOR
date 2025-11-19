package LogicaBanco;

public enum Rol {
	Cliente (new String[] {"Ingresar dinero", "Transferir dinero", "Pedir préstamo","Ver movimientos", "Salir"})
	, Empleado (new String[] {"Ver movimientos generales", "Ver usuarios", "Ver cuentas", "Eliminar cuenta", "Salir"});
	
	private String[] opciones;

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

	private Rol(String[] opciones) {
		this.opciones = opciones;
	}
	
	
}
