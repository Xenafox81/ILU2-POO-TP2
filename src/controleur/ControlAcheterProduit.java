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
	public String[] getVendeurs(String produit) {
        Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);  // Recherche des vendeurs
        if (vendeurs == null || vendeurs.length == 0) {
            return new String[0]; // Aucun vendeur trouvé pour ce produit
        }

        // Créer un tableau de noms à partir des objets Gaulois
        String[] nomsVendeurs = new String[vendeurs.length];
        for (int i = 0; i < vendeurs.length; i++) {
            nomsVendeurs[i] = vendeurs[i].getNom();  // Récupérer seulement le nom
        }
        return nomsVendeurs; // Retourner les noms
    }
	
	public Etal getEtalVendeur(String vendeur) {
		return controlTrouverEtalVendeur.trouverEtalVendeur(vendeur);
	}
	
	public int acheterProduit(String nomVendeur, int quantite) {
		return getEtalVendeur(nomVendeur).acheterProduit(quantite);
	}
}