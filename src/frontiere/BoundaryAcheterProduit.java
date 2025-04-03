package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis desole " + nomAcheteur + "mais il faut etre un habitant de notre village pour commencer ici.");
		} else {
			StringBuilder phrase = new StringBuilder();
			phrase.append("Quel produit voulez-vous acheter ?\n");
			String produit = Clavier.entrerChaine(phrase.toString());
			phrase.delete(0,phrase.length());
			Gaulois[] listeVendeur = controlAcheterProduit.getVendeurs(produit);
			if (listeVendeur == null) {
				System.out.println("Desole, personne ne vend ce produit au marche.");
			} else {
				phrase.append("Chez quel marchand voulez-vous acheter des " + produit + "?\n");
				for (int i=0; i<listeVendeur.length;i++) {
					phrase.append((i+1) + "-" + listeVendeur[i].getNom() + "\n");
				}
				int idVendeur = -1;
				do {
					idVendeur = Clavier.entrerEntier(phrase.toString())-1;
					if(idVendeur <0 || idVendeur >= listeVendeur.length) {
						System.out.println("Veuillez entrer un numéro valide.");
					}
				} while (idVendeur < 0 && idVendeur >= listeVendeur.length);
				phrase.append(nomAcheteur + " se deplace jusqu'a l'etal du vendeur " + listeVendeur[idVendeur].getNom() + "\n");
				phrase.append("Bonjour " + nomAcheteur + "\n");
				phrase.append("Combien de " + produit + "voulez-vous acheter ?\n");
				int quantite = Clavier.entrerEntier(phrase.toString());
				phrase.delete(0,phrase.length());
				int nbAchat = controlAcheterProduit.acheterProduit(listeVendeur[idVendeur].getNom(), quantite);
				if (nbAchat == quantite) {
					System.out.println(nomAcheteur + " achete " + quantite + " " + produit + " a " + listeVendeur[idVendeur].getNom());
				} else if (nbAchat == 0) {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement il n'y en a plus.");
				} else {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement "
							+ listeVendeur[idVendeur].getNom() + "n'en a plus que " + nbAchat + ". " + nomAcheteur 
							+ " achete tout le stock de " + listeVendeur[idVendeur].getNom());
				}
			}
			
		}
	}
}