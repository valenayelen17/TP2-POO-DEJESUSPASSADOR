package LogicaBanco;

import javax.swing.JOptionPane;

public abstract class Validaciones {

	public static String ValidarString(String mensaje) {
		String dato;
		do {
			dato = JOptionPane.showInputDialog(mensaje);

			if (dato.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No puede estar vacio");
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
				JOptionPane.showMessageDialog(null, "No puede estar vacio");
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

	public static double ValidarDouble(String mensaje) {
		String dato ="";
		
		boolean flag ;
		do {
			flag = true;
			dato = JOptionPane.showInputDialog(mensaje);

			if (dato.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No puede estar vacio");
				flag = false;
			}else {
				try {
					Double.parseDouble(dato);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "No puede ser un dato no numerico");
					flag = false;
				}
			}
		} while (flag == false);
		return Double.parseDouble(dato) ;
	}

	public static String ValidarEmail(String mensaje) {
		String email = "";
		boolean flag;
		do {
			flag = true;
			email = JOptionPane.showInputDialog(mensaje);

			if (email.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No puede estar vacio");
				flag = false;
			} else {
				if (!email.contains("@") || !email.contains(".")) {
					JOptionPane.showMessageDialog(null, "No es un email valido");
					flag = false;
				}
			}
		} while (flag == false);
		return email;
	}
}
