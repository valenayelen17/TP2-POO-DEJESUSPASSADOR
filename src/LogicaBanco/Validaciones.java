package LogicaBanco;

import javax.swing.JOptionPane;

public abstract class Validaciones {

	public static String ValidarString(String mensaje) {
		String dato;
		do {
			dato = JOptionPane.showInputDialog(mensaje);

			if (dato.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error");
			}
		} while (dato.isEmpty());
		return dato;
	}
	public static int ValidarInt(String mensaje) {
		String dato ="";
		
		boolean flag ;
		do {
			flag = true;
			dato = JOptionPane.showInputDialog(mensaje);

			if (dato.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error");
				flag = false;
			}else {
				for (int i = 0; i < dato.length(); i++) {
					if (!Character.isDigit(dato.charAt(i)) && dato.charAt(0) != '-') {
						JOptionPane.showMessageDialog(null, "No puede ser un dato no numerico");
						flag = false;
						break;
					}
				}
			}
		} while (flag == false);
		return Integer.parseInt(dato) ;
	}
}
