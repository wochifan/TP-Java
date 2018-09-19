import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Rond extends Figure implements Surfacable, Serializable {
	
	private static final long serialVersionUID = -216772938813875272L;
	
	private Point centre;
	private int rayon;
	
	public Rond(Point centre, int rayon) {
		this(centre, rayon, Couleur.getCouleurDefaut());
	}
	
	public Rond(Point centre, int rayon, Couleur couleur) {
		super(couleur);
		this.centre = centre;
		this.rayon = rayon;
	}
	
	public int getR() {
		return rayon;
	}
	
	public String toString() {
		return "[ROND " + centre + " , " + getR() + "]";
	}
		
	public Point getCentre() {
		return centre.clone();
	}
	
	public double surface() {
		return Math.PI * rayon * rayon;
	}
	
	public Iterator<Point> getPoints() {
		Collection<Point> c = new HashSet<>();
		c.add(centre);
		return c.iterator();
	}
	
	public boolean couvre(Point p) {
		int dx = p.getX() - this.centre.getX();
		int dy = p.getX() - this.centre.getY();
		int dx2 = dx * dx;
		int dy2 = dy * dy;
		double distance = Math.sqrt(dx2 + dy2);
		return distance <= rayon;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rond) {
			Rond r = (Rond) obj;
			return centre.equals(r.centre) && rayon == r.rayon && this.getCouleur() == r.getCouleur();
		}
		return false;
	}
}
