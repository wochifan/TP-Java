import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Segment extends Figure implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Point debut;
	private Point fin;
	
	public Segment(Point point, int longueur, boolean horizontal) {
		this(point, longueur, horizontal, Couleur.getCouleurDefaut());
	}
	
	public Segment(Point point, int longueur, boolean horizontal, Couleur couleur) {
		super(couleur);
		this.debut = point;
		int x = point.getX() + (horizontal ? longueur : 0);
		int y = point.getY() + (horizontal ? 0 : longueur);
		this.fin = new Point(x, y);
	}
	
	public String toString() {
		return "[SEGM " + debut + fin + "]";
	}
	
	public Point getCentre() {
		int x = (debut.getX() + fin.getX()) /2;
		int y = (debut.getY() + fin.getY()) /2;
		return new Point(x, y);
	}
	
	public Iterator<Point> getPoints() {
		Collection<Point> c = new HashSet<>();
		c.add(debut);
		c.add(fin);
		return c.iterator();
	}
	
	public boolean couvre(Point p) {
		boolean estCouvertX = (debut.getX() <= p.getX()) && (p.getX() <= fin.getX());
		boolean estCouvertY = (debut.getY() <= p.getY()) && (p.getY() <= fin.getY());
		return estCouvertX && estCouvertY;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Segment) {
			Segment s = (Segment) obj;
			return (debut.equals(s.debut) && fin.equals(s.fin)
					|| debut.equals(s.fin) && fin.equals(s.debut))
					&& this.getCouleur() == s.getCouleur();
		}
		return false;
	}
}
