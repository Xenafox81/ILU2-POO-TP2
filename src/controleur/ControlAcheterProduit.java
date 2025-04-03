package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean verifierIdentite(String nomClient) {
		return controlVerifierIdentite.verifierIdentite(nomClient);
	}
	
	public Gaulois[] getVendeurs(String produit) {
		return village.rechercherVendeursProduit(produit);
	}
	
	public Etal getEtalVendeur(String vendeur) {
		return controlTrouverEtalVendeur.trouverEtalVendeur(vendeur);
	}
	
	public int acheterProduit(String nomVendeur, int quantite) {
		return getEtalVendeur(nomVendeur).acheterProduit(quantite);
	}
}