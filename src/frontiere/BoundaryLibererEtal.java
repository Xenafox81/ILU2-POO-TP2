package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		boolean vendeurReconnu = controlLibererEtal.isVendeur(nomVendeur);
		if (!vendeurReconnu) {
			System.out.println("Mais vous n'etes pas inscrit sur notre marche aujourd'hur !");
		} else {
			String[] donneesEtal = controlLibererEtal.libererEtal(nomVendeur);
			if (donneesEtal[0].equals("true")) {
				System.out.println("Vous avez vendu " + donneesEtal[3] + " sur " + donneesEtal[2] + " " + donneesEtal[1] + ".");
	        	System.out.println("En revoir " + nomVendeur + ", passez une bonne journee.");
			}
		}
	}

}