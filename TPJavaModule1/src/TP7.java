
public class TP7 {

	public static void main(String[] args) {

		
		int nombre = 12;
		int compteur = 0;
		int valeur = 2;
		boolean estPremier;
		String nbrsPremiers = "";
		
		while(compteur < nombre) {
			estPremier = true;
			
			for(int diviseur = 2; diviseur < valeur; diviseur++) {
				if (valeur % diviseur == 0) {
					estPremier = false;
					break;
				}
			}
			
			if (estPremier) {
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
	

}
