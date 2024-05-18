public class Ordinateur extends Joueur {

    public Ordinateur(String nom, Jeu jeu, int taillePioche) {
        super(nom, jeu, taillePioche);
    }

    @Override
    public void placerPokemon(Terrain terrain) {
        terrain.placerPokemons(this, 0);
    }

    @Override
    public Boolean attaquer(Terrain terrain, Joueur adversaire) {
        return null;
    }
}
