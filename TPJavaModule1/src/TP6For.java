
public class TP6For {

	public static void main(String[] args) {

		int factorielle = 1;
		
		for (int i = Integer.parseInt(args[0]); i >= 1; i--) {
			factorielle *= i;
		}
		
		System.out.println("La factorielle de " + args[0] 
				+ " est : " + factorielle);
	}

}
