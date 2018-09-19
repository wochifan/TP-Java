import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		Point p = new Point(10, 10);
		System.out.println(p);

		Point c = new Point(10, 10);
		System.out.println(c);

		Rond a = new Rond(p, 10);
		a.affiche();

		Rectangle b = new Rectangle(p, 10, 20);
		b.affiche();

		System.out.println(p.equals(b.getPointBasGauche()));

		Rectangle randomRectangle = FigureUtil.getRandomRectangle();
		randomRectangle.affiche();

		Rond randomRond = FigureUtil.getRandomRond();
		randomRond.affiche();

		Carre carre = new Carre(p, 10);
		carre.affiche();

		FigureUtil.genere(10);

		Dessin d = new Dessin();
//		for(Figure f : FigureUtil.genere(20)) {
//			d.add(f);
//		}
		d.addAll(FigureUtil.genere(20));

		try {
			FigureUtil.imprime(d);
			FigureUtil.sauvegarde(d, File.createTempFile("xxx", ".txt"));
		} catch (ImpressionHorsLimiteException ex) {
			System.out.println("Impression hors limite : " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Erreur à l'écriture du fichier : " + ex.getMessage());
		}
	}

}
