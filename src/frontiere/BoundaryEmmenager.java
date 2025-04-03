package frontiere;

import controleur.ControlEmmenager;

public class BoundaryEmmenager {
	private ControlEmmenager controlEmmenager;

	public BoundaryEmmenager(ControlEmmenager controlEmmenager) {
		this.controlEmmenager = controlEmmenager;
	}

	public void emmenager(String nomVisiteur) {
		if (controlEmmenager.isHabitant(nomVisiteur)) {
			System.out.println("Mais vous etes deja�un habitant du village !");
		} else {
			StringBuilder question = new StringBuilder();
			question.append("Êtes-vous :\n");
			question.append("1 - un druide.\n");
			question.append("2 - un gaulois.\n");
			int choixUtilisateur = -1;
			do {
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				switch (choixUtilisateur) {
				case 1:
					emmenagerDruide(nomVisiteur);
					break;

				case 2:
					System.out.println("Bienvenue villageois " + nomVisiteur +"\n");
					int force = 0;
					force = Clavier.entrerEntier("Quelle est votre force ?");
					controlEmmenager.ajouterGaulois(nomVisiteur, force);					
					break;

				default:
					System.out
							.println("Vous devez choisir le chiffre 1 ou 2 !");
					break;
				}
			} while (choixUtilisateur != 1 && choixUtilisateur != 2);
		}
	}

	private void emmenagerDruide(String nomVisiteur) {
		System.out.println("Bienvenue druide " + nomVisiteur +"\n");
		int force = 0;
		force = Clavier.entrerEntier("Quelle est votre force ?");
		int effetPotionMin = 0;
		effetPotionMin = Clavier.entrerEntier("Quelle est la force de potion la plus faible que vous produisiez ? \n");
		int effetPotionMax = 0;
		effetPotionMax = Clavier.entrerEntier("Quelle est la force de potion la plus forte que vous produisiez ? \n");
		if (effetPotionMax < effetPotionMin) {
			do {
				System.out.println("Attention Druite, vous vous être trompé entre le minimum et le maximum\n");
				effetPotionMin = Clavier.entrerEntier("Quelle est la force de potion la plus faible que vous produisiez ? \n");
				effetPotionMax = Clavier.entrerEntier("Quelle est la force de potion la plus forte que vous produisiez ? \n");
			} while (effetPotionMax < effetPotionMin);
		}
		controlEmmenager.ajouterDruide(nomVisiteur, force,effetPotionMin,effetPotionMax);
	}
}