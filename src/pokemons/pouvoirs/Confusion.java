package pokemons.pouvoirs;

import joueurs.Joueur;
import joueurs.Terrain;
import pokemons.Pokemon;

public class Confusion extends Pouvoir{
    public Confusion() {
        super("Confusion", true);
    }
    @Override
    public void utiliser(Terrain terrain, Joueur allie, Joueur adversaire, Pokemon pokemon) {
        System.out.println("Confusion !");
        // vider la main de l'adversaire et la mettre dans la pioche de l'adversaire
        adversaire.viderMain();
        // remplir la main de l'adversaire
        adversaire.remplirMain();
    }
}
