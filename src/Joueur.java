import java.util.List;

public abstract class Joueur {
    protected String m_nom;
    protected Pioche m_pioche;
    protected Main m_main;
    protected Defausse m_defausse;

    public Joueur(String nom, Jeu jeu, int taillePioche) {
        this.m_nom = nom;
        this.m_pioche = new Pioche(jeu, taillePioche);
        this.m_main = new Main();
        this.m_defausse = new Defausse();
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
    public abstract void placerPokemon(Terrain terrain);

}
