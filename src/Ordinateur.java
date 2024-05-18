import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        List<Integer> pokemonAttaquer = new ArrayList<>();
        int pokemonAttaque;
        for (int i = 0; i<terrain.getNbPokemonsJoueur(this);i++){
            for (int j = 0; j<terrain.getNbPokemonsJoueur(adversaire);j++){
                if (terrain.getPokemon(this,i).estAvantager(terrain.getPokemon(adversaire,j))){
                    pokemonAttaquer.add(j);
                }
            }
            if (pokemonAttaquer.isEmpty()){
                for(int j = 0; j<terrain.getNbPokemonsJoueur(adversaire);j++){
                    pokemonAttaquer.add(j);
                }
            }
            if(pokemonAttaquer.size()>1){
                for (int j = 0; j<pokemonAttaquer.size()-1;j++){
                    if (terrain.getPokemon(adversaire, pokemonAttaquer.get(j)).getPv()<=terrain.getPokemon(adversaire, pokemonAttaquer.get(j+1)).getPv()){
                        pokemonAttaquer.remove(j+1);
                    }
                }
                if (pokemonAttaquer.size()>1){
                    Random random = new Random();
                    pokemonAttaque = random.nextInt(pokemonAttaquer.size());
                    System.out.println(terrain.getPokemon(this,i).getNom()+" a attaquer "+terrain.getPokemon(adversaire,pokemonAttaque).getNom());
                    terrain.getPokemon(this,i).attaquer(terrain.getPokemon(adversaire,pokemonAttaque));
                }
                else {
                    pokemonAttaque = pokemonAttaquer.getFirst();
                    System.out.println(terrain.getPokemon(this,i).getNom()+" a attaquer "+terrain.getPokemon(adversaire,pokemonAttaque).getNom());
                    terrain.getPokemon(this,i).attaquer(terrain.getPokemon(adversaire,pokemonAttaque));
                }
            }
            else {
                pokemonAttaque = pokemonAttaquer.getFirst();
                System.out.println(terrain.getPokemon(this,i).getNom()+" a attaquer "+terrain.getPokemon(adversaire,pokemonAttaque).getNom());
                terrain.getPokemon(this,i).attaquer(terrain.getPokemon(adversaire,pokemonAttaque));
            }

            // si le pokemon attaqué est mort, le défausser
            if (!(terrain.getPokemon(adversaire, pokemonAttaque)).estVivant()) {
                System.out.println("Le pokemon " + (terrain.getPokemon(adversaire, pokemonAttaque)).getNom() + " est mort");
                adversaire.defausser(terrain.getPokemon(adversaire, pokemonAttaque));
                // retirer le pokemon du terrain
                terrain.retirerPokemon(adversaire, pokemonAttaque);
                if(terrain.estVide(adversaire)){
                    System.out.println("Le "+this.m_nom+" a gagné");
                    return true;
                }
            }
            pokemonAttaquer.clear();
        }
        return false;
    }
}
