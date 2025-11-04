package LogicaBanco;

public enum Rol {
	Cliente (new String[] {"Ingresar dinero", "Transferir dinero", "Pedir préstamo", "Salir"})
	, Empleado (new String[] {
			
	});
	
	private String [] opciones;

	
	
	private Rol(String[] opciones) {
		this.opciones = opciones;
	}
}
