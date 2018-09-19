
public class TP4Switch {

	public static void main(String[] args) {

		double montant = Double.parseDouble(args[0]);
		double remise;
		
		if (montant < 1000) {
			remise = 0;
		} else if (montant < 2000) {
			remise = montant * 0.01;

		} else if (montant < 5000) {
			remise = montant * 0.03;

		} else {
			remise = montant * 0.05;
		}
		
		double prix = montant - remise;
		
		System.out.println("Remise : " + remise + " €");
		System.out.println("Montant : " + prix + " €");

	}

}
