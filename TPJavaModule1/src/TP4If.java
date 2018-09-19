
public class TP4If {

	public static void main(String[] args) {

		int valeur = 0;
		if (valeur < 1 ) {
			System.out.println("Taille invalide");
		} else {
			switch(valeur) {
			case 1 :
				System.out.println("petit");
				break;
			case 2 :
				System.out.println("moyen");
				break;
			default :
				System.out.println("grand");
			}
		}

	}

}
