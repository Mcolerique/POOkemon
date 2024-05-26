package joueurs;

import jeu.Jeu;
import pokemons.Pokemon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ordinateur extends Joueur {

    //Constructeur
    public Ordinateur(String nom, int taillePioche) {
        super(nom, taillePioche);
    }


    //Methodes refefinies
    @Override
    public void placerPokemon(Terrain terrain) {
        terrain.placerPokemons(this, 0);
    }

    @Override
    public boolean attaquer(Terrain terrain, Joueur adversaire) {
        List<Integer> pokemonAttaquer = new ArrayList<>();
        int pokemonAttaque;
        for (int i = 0; i<terrain.getNbPokemonsJoueur(this);i++){
            for (int j = 0; j<terrain.getNbPokemonsJoueur(adversaire);j++){
                if (terrain.getPokemon(this,i).avantageSur(terrain.getPokemon(adversaire,j))){
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

            //Si le pokemon attaqué est mort, le défausser
            if (adversaire.mort(terrain, pokemonAttaque)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean utiliserPouvoir(Terrain terrain, Joueur adversaire) {
        List<Pokemon> pokeQuiAttaque = new ArrayList<>();
        for(int i =0; i<terrain.getNbPokemonsJoueur(this);i++)
        {
            if (terrain.getPokemonsJoueur(this).get(i).getNomPouvoir()!="Aucun" && !terrain.getPokemonsJoueur(this).get(i).getPouvoir().getUtilise())
            {
                pokeQuiAttaque.add(terrain.getPokemonsJoueur(this).get(i));
            }
        }
        if(pokeQuiAttaque.isEmpty()){
            return false;
        }
        for (int i = 0; i<pokeQuiAttaque.size();i++){
            int pokemonAttaquant = selection(pokeQuiAttaque);
            pokeQuiAttaque.remove(pokemonAttaquant);
            pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().utiliser(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant),pokemonAttaquant);
            if (this.mort(terrain)|| adversaire.mort(terrain)){
                if(Jeu.getPokemonAvecPouvoir().get(pokeQuiAttaque.get(pokemonAttaquant)) != null){
                    pokeQuiAttaque.get(pokemonAttaquant).getPouvoir().annulerPouvoir(terrain, this, adversaire,pokeQuiAttaque.get(pokemonAttaquant));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int selection(List<Pokemon> list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }
}
