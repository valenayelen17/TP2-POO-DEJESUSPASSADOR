package LogicaBanco;

public enum Azar {
	
	Palabras( new String[] {
		"casino",
		"anillo",
		"caballo",
		"dedo",
		"casa",
		"mesa",
		"tasa",
		"jinete",
		"sorteo",
		"dado"
		
	});
	
	private String[] palabras;
	
	private Azar(String[] palabras) {
		this.palabras = palabras;
	}

	public String[] getPalabras() {
		return palabras;
	}

	public void setPalabras(String[] palabras) {
		this.palabras = palabras;
	}
	
	
}
