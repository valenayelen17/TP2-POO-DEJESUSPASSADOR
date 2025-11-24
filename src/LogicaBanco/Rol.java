package LogicaBanco;

public enum Rol {
	Cliente (new String[] {"Ingresar dinero", "Transferir dinero", "Pedir préstamo","Ver movimientos", "Inversiones", "Cerrar sesión"})
	, Empleado (new String[] {"Ver movimientos generales", "Ver cuentas", "Ver usuarios","Solicitudes de préstamos", "Eliminar cuenta", "Cerrar sesión"});
	
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
