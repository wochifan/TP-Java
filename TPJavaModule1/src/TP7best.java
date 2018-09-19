
public class TP7best {

	public static void main(String[] args) {

		/*
		 * Afficher les n nombres premiers
		 */

		int nombre = 5;
		int compteur = 0;
		int valeur = 2;
		boolean estPremier;
		String nbrsPremiers = "";

		while (compteur < nombre) {
			if (estPremier(valeur)) {

					if (!"".equals(nbrsPremiers)) {
						nbrsPremiers += ", ";
					}
					nbrsPremiers += valeur;
					compteur++;
				}

				valeur++;
			}

			System.out.println("Les " + nombre + " premiers nombres premiers sont : " + nbrsPremiers);
		}

	public static boolean estPremier(int nombre) {

		for (int diviseur = 2; diviseur < nombre; diviseur++) {
			if (nombre % diviseur == 0) {
				return false;
			}
		}
		return true;
	}

}
