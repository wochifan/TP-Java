import java.io.Serializable;

public class Carre extends Rectangle implements Surfacable, Serializable {

	private static final long serialVersionUID = -5818859785152297975L;

	public Carre(Point p, int cote) {
		this(p, cote, Couleur.getCouleurDefaut());
	}
	
	public Carre(Point p, int cote, Couleur couleur) {
		super(p, cote, cote, couleur);
	}
	
	protected String getType() {
		return "CARRE";
	}
}
