import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class FigureUtil {

	private static int MIN_X = 0;
	private static int MIN_Y = 0;
	private static int MAX_X = 100;
	private static int MAX_Y = 100;

	private static int MAX_TAILLE = 20;

	private static final Map<String, Figure> figures = new HashMap<String, Figure>();

	private FigureUtil() {
		// On crée un constructeur en private pour éviter de l'instancier
		// malencontreusement
	}

	public static Point getRandomPoint(int min_x, int max_x, int min_y, int max_y) {
		int x = getRandomInteger(min_x, max_x);
		int y = getRandomInteger(min_y, max_y);
		return new Point(x, y);
	}

	public static Rond getRandomRond() {
		int rayon = getRandomInteger(1, MAX_TAILLE / 2);
		Point centre = getRandomPoint(MIN_X + MAX_TAILLE / 2, MAX_X - MAX_TAILLE / 2, MIN_Y + MAX_TAILLE / 2,
				MAX_Y - MAX_TAILLE / 2);
		Rond r = new Rond(centre, rayon);
		figures.put(r.getKey(), r);
		return r;
	}

	public static Rectangle getRandomRectangle() {
		int hauteur = getRandomInteger(1, MAX_TAILLE);
		int largeur = getRandomInteger(1, MAX_TAILLE);
		Point p = getRandomPoint(MIN_X, MAX_X - largeur, MIN_Y, MAX_Y - hauteur);
		Rectangle r = new Rectangle(p, hauteur, largeur);
		figures.put(r.getKey(), r);
		return r;
	}

	public static Carre getRandomCarre() {
		int cote = getRandomInteger(1, MAX_TAILLE);
		Point p = getRandomPoint(MIN_X, MAX_X - cote, MIN_Y, MAX_Y - cote);
		Carre c = new Carre(p, cote);
		figures.put(c.getKey(), c);
		return c;
	}

	public static Segment getRandomSegment() {
		int longueur = getRandomInteger(1, MAX_TAILLE);
		boolean horizontal = Math.random() < 0.5;
		Point p = getRandomPoint(MIN_X, MAX_X - (horizontal ? longueur : 0), MIN_Y,
				MAX_Y - (horizontal ? 0 : longueur));
		Segment s = new Segment(p, longueur, horizontal);
		figures.put(s.getKey(), s);
		return s;
	}

	private static int getRandomInteger(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

//	public static Point[] getPoints(Figure... figures) {
//		int nb = 0;
//		
//		Point[][] pointsDeFigures = new Point [figures.length][];
//		int i = 0;
//		for (Figure figure: figures) {
//			pointsDeFigures[i] = figure.getPoints();
//			i++;
//		}
//				
//		for (int j=0; j<pointsDeFigures.length; j++) {
//			nb += pointsDeFigures[j].length;
//		}
//		
//		Point[] points = new Point[nb];
//		int index = 0;
//		for (int j = 0; j < pointsDeFigures.length; j++) {
//			for (Point p: pointsDeFigures[j]) {
//				points[index] = p;
//				index++;
//			}
//		}
//		return points;
//	}
	public static Iterator<Point> getPoints(Figure... figures) {
		Collection<Point> c = new HashSet<>();
		for (Figure f : figures) {
			Iterator<Point> it = f.getPoints();
			while (it.hasNext()) {
				c.add(it.next());
			}
		}
		return c.iterator();
	}

	public static Collection<Figure> genere(int nombre) {
		Collection<Figure> collection = new HashSet<Figure>();
		while (collection.size() < nombre) {
			collection.add(getRandomFigure());
		}
		return collection;
	}

	public static Figure getRandomFigure() {
		byte choix = (byte) (Math.random() * 4);
		switch (choix) {
		case 0:
			return getRandomRond();
		case 1:
			return getRandomRectangle();
		case 2:
			return getRandomCarre();
		case 3:
		default:
			return getRandomSegment();
		}
	}

	public static Figure getFigureEn(Point p, Dessin d) {
		Iterator<Figure> it = d.getFigures();
		while (it.hasNext()) {
			Figure f = it.next();
			if (f.couvre(p)) {
				return f;
			}
		}
		return null;
	}

	public static Figure procheZero(Dessin d) {
		Iterator<Figure> it = d.getFigures();
		// Collection<Figure> figures = new ArrayList<Figure>();
		TreeSet<Figure> figures = new TreeSet<Figure>();
		while (it.hasNext()) {
			figures.add(it.next());
		}
		// return Collections.min(figures);
		return figures.first();
	}

	public static Collection<Figure> trieDominant(Dessin d) {
		List<Figure> figures = new ArrayList<Figure>();
		Iterator<Figure> it = d.getFigures();
		while (it.hasNext()) {
			figures.add(it.next());
		}

		Collections.sort(figures, new Comparator<Figure>() {
			public int compare(Figure f1, Figure f2) {
				int s1 = 0;
				int s2 = 0;

				if (f1 instanceof Surfacable) {
					s1 = (int) ((Surfacable) f1).surface();
				}
				if (f2 instanceof Surfacable) {
					s2 = (int) ((Surfacable) f2).surface();
				}

				return s2 - s1;
			}
		});
		return figures;
	}

	public static Figure get(String id) {
		Figure f = figures.get(id);
		if (f == null) {
			f = getRandomFigure();
		}
		return f;
	}

	public static void imprime(Dessin d) throws IOException, ImpressionHorsLimiteException {
		File file = File.createTempFile("monDessin", ".txt");
		PrintWriter sortie = new PrintWriter(new FileOutputStream(file));
		Iterator<Figure> it = d.getFigures();
		while (it.hasNext()) {
			Figure f = it.next();
			Iterator<Point> itp = f.getPoints();
			while(itp.hasNext()) {
				Point p = itp.next();
				if(p.getX()<MIN_X || p.getX()>MAX_X 
						|| p.getY()<MIN_Y || p.getY()>MAX_Y) {
					throw new ImpressionHorsLimiteException();
				}
			}
		}
		for (int i = 0; i < 100; i++) {
			sortie.print("=");
		}
		sortie.println();
		
		for (int y = MAX_Y; y > MIN_Y; y--) {
			for (int x = MIN_X; x < MAX_X; x++) {
				Figure f = getFigureEn(new Point(x,y), d);
				if (f == null) {
					sortie.print(" ");
				} else {
					sortie.print(f.getCouleur().getCode());
				}
			}
			sortie.println();
		}
		sortie.close();
		System.out.println("Dessin imprimé dans " + file.getAbsolutePath());
	}
	
	
	public static void sauvegarde(Dessin d, File f) throws IOException {
		ObjectOutputStream sortie = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(f)));
		sortie.writeObject(d);
		System.out.println("Dessin sauvegardé dans " + f.getAbsolutePath());
		sortie.close();
	}
	
	public static Dessin charge(File file) throws IOException, ClassNotFoundException {
		Dessin dessin;
		try {		
			ObjectInputStream entree = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			dessin = (Dessin) entree.readObject();
			entree.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Le fichier n'existe pas ! " + ex.getMessage());
			dessin = new Dessin();
		}
		return dessin;
	}

}
