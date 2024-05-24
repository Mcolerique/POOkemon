package joueurs;

import pokemons.Pokemon;
import jeu.Jeu;

import java.util.List;

public abstract class Joueur {
    public String m_nom;
    public Pioche m_pioche;
    public Main m_main;
    public Defausse m_defausse;
    public int m_tailleTerrain;

    public Joueur(String nom, int taillePioche) {
        this.m_nom = nom;
        this.m_pioche = new Pioche(taillePioche);
        this.m_main = new Main();
        this.m_defausse = new Defausse();
        this.m_tailleTerrain = 3;
    }

    private void piocher() {
        this.m_pioche.piocherMain(this.m_main);
    }

    public abstract Boolean attaquer(Terrain terrain, Joueur adversaire);

    public boolean aPerdu() {
        return this.m_main.getListePokemon().isEmpty() && this.m_pioche.getM_pioche().isEmpty();
    }

    public String getNom() {
        return this.m_nom;
    }

    public Main getMain() {
        return this.m_main;
    }

    public Pioche getPioche() {
        return this.m_pioche;
    }
    public Defausse getDefausse() {
        return this.m_defausse;
    }

    public void piocherPokemon() {
        while (this.m_main.getNbPokemon() < 5 && !this.m_pioche.getM_pioche().isEmpty()) {
            this.m_pioche.piocherMain(this.m_main);
        }
    }

    public void defausser(Pokemon pokemon) {
        m_defausse.defausser(pokemon);
    }

    public Pokemon getPokemon(int pokemonCible) {
        List<Pokemon> listePokemon = this.m_main.getListePokemon();
        if (pokemonCible >= 0 && pokemonCible < listePokemon.size()) {
            // Vérifiez que l'index est valide avant d'accéder à la liste
            Pokemon pokemon = listePokemon.get(pokemonCible);
            // Supprimez le pokemon de la main seulement s'il existe dans la liste
            this.m_main.retirerPokemon(pokemon);
            return pokemon;
        } else {
            // Gérez le cas où l'index est invalide
            System.out.println("L'index du Pokémon est invalide.");
            return null;
        }
    }
    public boolean mort(Terrain terrain, int attaque) {
        if (!(terrain.getPokemon(this, attaque)).estVivant()) {
            System.out.println("Le pokemon " + (terrain.getPokemon(this, attaque)).getNom() + " est mort");
            this.defausser(terrain.getPokemon(this, attaque));
            // retirer le pokemon du terrain
            terrain.retirerPokemon(this, attaque);
            if (terrain.estVide(this)) {
                return true;
            }
        }
    return false;
    }
    public boolean mort(Terrain terrain) {
        for (int i = 0; i < this.m_tailleTerrain; i++) {
            if (!(terrain.getPokemon(this, i)).estVivant()) {
                System.out.println("Le pokemon " + (terrain.getPokemon(this, i)).getNom() + " est mort");
                this.defausser(terrain.getPokemon(this, i));
                // retirer le pokemon du terrain
                terrain.retirerPokemon(this, i);
                if (terrain.estVide(this)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void viderMain()
    {
        // fonction qui vide la main pour la remettre dans la pioche
        for (int i = 0; i < this.m_main.getListePokemon().size(); i++)
        {
            // mettre le pokemon de la main vers la pioche
            this.m_pioche.getM_pioche().add(this.m_main.getListePokemon().get(i));
        }
        melangerPioche();
    }
    public void remplirMain() {
        // fonction qui remplit la main avec la pioche
        while (this.m_main.getNbPokemon() < 5 && !this.m_pioche.getM_pioche().isEmpty()) {
            this.m_pioche.piocherMain(this.m_main);
        }
    }
    public void melangerPioche() {
        for (int i = 0; i < this.m_pioche.getM_pioche().size(); i++)
        {
            int random = (int) (Math.random() * this.m_pioche.getM_pioche().size());
            Pokemon temp = this.m_pioche.getM_pioche().get(i);
            this.m_pioche.getM_pioche().set(i, this.m_pioche.getM_pioche().get(random));
            this.m_pioche.getM_pioche().set(random, temp);
        }
    }
    public abstract void placerPokemon(Terrain terrain);
    public void ajouterPlaceTerrain(){
        this.m_tailleTerrain++;
    }
    public void enleverPlaceTerrain(){
        this.m_tailleTerrain--;
    }
    public abstract boolean utiliserPouvoir(Terrain terrain, Joueur adversaire);


}
