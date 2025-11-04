package LogicaBanco;

public class Empleado extends Usuario{
	private String sector;
	
		public Empleado(String mail, String contr, String sector) {
			super(mail, contr, Rol.Empleado);
		}

}
