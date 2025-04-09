package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
    private ControlAcheterProduit controlAcheterProduit;

    public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
        this.controlAcheterProduit = controlAcheterProduit;
    }

    public void acheterProduit(String nomAcheteur) {
        if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
            System.out.println("Je suis désolé " + nomAcheteur + ", mais il faut être un habitant de notre village pour commencer ici.");
            return;
        }

        String produit = demanderProduit();
        String[] nomsVendeurs = controlAcheterProduit.getVendeurs(produit);
        if (nomsVendeurs == null) {
            System.out.println("Désolé, personne ne vend ce produit au marché.");
            return;
        }

        String nomVendeur = choisirVendeur(nomsVendeurs, produit);
        int quantite = demanderQuantite(produit);
        int nbAchat = controlAcheterProduit.acheterProduit(nomVendeur, quantite);
        traiterAchat(nomAcheteur, quantite, nomVendeur, nbAchat, produit);
    }

    private String demanderProduit() {
        return Clavier.entrerChaine("Quel produit voulez-vous acheter ?\n");
    }

    private String choisirVendeur(String[] nomsVendeurs, String produit) {
        StringBuilder phrase = new StringBuilder("Chez quel marchand voulez-vous acheter des " + produit + " ?\n");
        for (int i = 0; i < nomsVendeurs.length; i++) {
            phrase.append((i + 1) + " - " + nomsVendeurs[i] + "\n");
        }

        int idVendeur = -1;
        while (true) {
            idVendeur = Clavier.entrerEntier(phrase.toString()) - 1;
            if (idVendeur >= 0 && idVendeur < nomsVendeurs.length) break;
            System.out.println("Veuillez entrer un numéro valide.");
        }
        return nomsVendeurs[idVendeur];
    }

    private int demanderQuantite(String produit) {
        return Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?\n");
    }

    private void traiterAchat(String nomAcheteur, int quantite, String nomVendeur, int nbAchat, String produit) {
        if (nbAchat == quantite) {
            System.out.println(nomAcheteur + " achète " + quantite + " " + produit + " à " + nomVendeur + ".");
        } else if (nbAchat == 0) {
            System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", malheureusement il n'y en a plus.");
        } else {
            System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ", mais " + nomVendeur
                    + " n’en a que " + nbAchat + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
        }
    }
}
