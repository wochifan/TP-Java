
public enum Couleur {
	Rouge ('R'), Vert ('V'), Bleu ('B'), Jaune ('J'), Noir ('N');
	
	private char code;
	
	private Couleur(char a){
		code = a;
	}
	
	public String getCode() {
		return String.valueOf(code);
	}
	
	public static Couleur getCouleurDefaut() {
		return Noir;
	}
}
