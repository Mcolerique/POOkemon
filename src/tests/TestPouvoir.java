package tests;

import affichage.Affichage;
import joueurs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pokemons.Pokemon;
import pokemons.pouvoirs.*;
import joueurs.Joueur;
import joueurs.Terrain;
import jeu.Jeu;
import joueurs.*;

import static org.junit.jupiter.api.Assertions.*;

class TestPouvoir
{
    private Joueur joueur1;
    private Joueur joueur2;
    private Terrain terrain;
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    @BeforeEach
    void setUp() {
        joueur1 = new Humain("Joueur 1", 20);
        joueur2 = new Humain("Joueur 2", 20);
        terrain = new Terrain();
    }

    @Test
    void testAlleKahida() {
        // Initialisation des Pokémons
        Pouvoir alleKahida = new AlleKahida();
        pokemon1 = new Pokemon("Pikachu", alleKahida);
        pokemon2 = new Pokemon("Bulbizarre", null);

        terrain.placerPokemons(joueur1, pokemon1);
        terrain.placerPokemons(joueur2, pokemon2);

        alleKahida.utilisertest(terrain, joueur1, joueur2, pokemon1, 0, 0);

        assertTrue(terrain.estVide(joueur1));
        assertTrue(terrain.estVide(joueur2));
    }

    @Test
    void testConfusion() {
        // Initialisation des Pokémons
        Pouvoir confusion = new Confusion();
        pokemon1 = new Pokemon("Pikachu", confusion);
        pokemon2 = new Pokemon("Bulbizarre", null);

        terrain.placerPokemons(joueur1, pokemon1);
        terrain.placerPokemons(joueur2, pokemon2);

        // Fill the opponent's hand with 5 Pokemons
        for (int i = 0; i < 5; i++) {
            joueur2.getMain().ajouterPokemon(new Pokemon("TestPokemon" + i, null));
        }

        confusion.utilisertest(terrain, joueur1, joueur2, pokemon1, 0, 0);

        // Check that the opponent's hand still has 5 Pokemons after using the power
        assertTrue(joueur2.getMain().getNbPokemon() == 5);
    }

    @Test
    void testExtensionTerritoire() {
        // Initialisation des Pokémons
        Pouvoir extensionTerritoire = new ExtensionTerritoire();
        pokemon1 = new Pokemon("Pikachu", extensionTerritoire);
        pokemon2 = new Pokemon("Bulbizarre", null);

        terrain.placerPokemons(joueur1, pokemon1);
        terrain.placerPokemons(joueur1, pokemon2);

        extensionTerritoire.utilisertest(terrain, joueur1, joueur2, pokemon1, 0, 0);

        assertTrue(joueur1.getTailleTerrain() == 4);

    }

    @Test
    void testFerveurGuerriere() {
        // Initialisation des Pokémons
        Pouvoir ferveurGuerriere = new FerveurGuerriere();
        pokemon1 = new Pokemon("Pikachu", ferveurGuerriere);
        pokemon2 = new Pokemon("Bulbizarre", null);

        terrain.placerPokemons(joueur1, pokemon1);
        terrain.placerPokemons(joueur2, pokemon2);

        ferveurGuerriere.utilisertest(terrain, joueur1, joueur2, pokemon1, 0, 0);

        assertTrue(ferveurGuerriere.getUtilise() == true);

    }

    @Test
    void testKamikaze() {
        // Initialisation des Pokémons
        Pouvoir kamikaze = new Kamikaze();
        pokemon1 = new Pokemon("Pikachu", kamikaze);
        pokemon2 = new Pokemon("Bulbizarre", null);

        terrain.placerPokemons(joueur1, pokemon1);
        terrain.placerPokemons(joueur2, pokemon2);

        kamikaze.utilisertest(terrain, joueur1, joueur2, pokemon1, 0, 0);

        assertTrue(terrain.estVide(joueur1));
        assertTrue(terrain.estVide(joueur2));

    }

    @Test
    void testNecromancie() {
        // Initialisation des Pokémons
        Pouvoir necromancie = new Necromancie();
        pokemon1 = new Pokemon("Pikachu", necromancie);
        pokemon2 = new Pokemon("Bulbizarre", null);

        // Placer le pokemon avec le pouvoir sur le terrain
        terrain.placerPokemons(joueur1, pokemon1);

        // Ajouter un pokemon à la défausse du joueur
        joueur1.getDefausse().ajouterPokemon(pokemon2);

        // Utiliser le pouvoir
        necromancie.utilisertest(terrain, joueur1, joueur2, pokemon1, 0, 0);

        // Vérifier que le pokemon de la défausse est maintenant sur le terrain
        assertTrue(terrain.getPokemonsJoueur(joueur1).contains(pokemon2));

        // Vérifier que le pokemon qui a utilisé le pouvoir est maintenant dans la défausse
        assertTrue(joueur1.getDefausse().getDefausse().contains(pokemon1));

        // Add a delay to allow the game state to update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Recheck the conditions
        assertTrue(terrain.getPokemonsJoueur(joueur1).contains(pokemon2));
        assertTrue(joueur1.getDefausse().getDefausse().contains(pokemon1));
    }

    @Test
    void testResistance() {
        // Initialisation des Pokémons
        Pouvoir resistance = new Resistance();
        pokemon1 = new Pokemon("Pikachu", resistance);
        pokemon2 = new Pokemon("Bulbizarre", null);
        int defense = pokemon1.getDefense();

        // Placer le pokemon avec le pouvoir sur le terrain
        terrain.placerPokemons(joueur1, pokemon1);

        // Utiliser le pouvoir de résistance sur le pokemon1
        resistance.utilisertest(terrain, joueur1, joueur2, pokemon1, 0, 0);

        // vérifier que le pokemon a bien la résistance
        assertTrue(pokemon1.getDefense() == defense + 10);
    }

    @Test
    void testSoinDeZone() {
        // Initialisation des Pokémons
        Pouvoir soinDeZone = new SoinDeZone();
        pokemon1 = new Pokemon("Pikachu", soinDeZone);
        pokemon2 = new Pokemon("Bulbizarre", null);

        // Placer les pokemons sur le terrain
        terrain.placerPokemons(joueur1, pokemon1);
        terrain.placerPokemons(joueur1, pokemon2);

        // Faire perdre des pv aux pokemons
        pokemon1.subirDegats(10);
        pokemon2.subirDegats(10);

        // Utiliser le pouvoir de soin de zone
        soinDeZone.utiliser(terrain, joueur1, joueur2, pokemon1, 0);

        // vérifier que les pokemons ont bien été soignés
        assertTrue(pokemon1.getPv() == pokemon1.getPvMax());
        assertTrue(pokemon2.getPv() == pokemon2.getPvMax());
    }

    @Test
    void testSoinTotal() {
        // Initialisation des Pokémons
        SoinTotal soinTotal = new SoinTotal();
        pokemon1 = new Pokemon("Pikachu", soinTotal);
        pokemon2 = new Pokemon("Bulbizarre", null);

        // Placer les pokemons sur le terrain
        terrain.placerPokemons(joueur1, pokemon1);
        terrain.placerPokemons(joueur1, pokemon2);

        // Faire perdre des pv aux pokemons
        pokemon1.subirDegats(10);

        // Utiliser le pouvoir de soin total
        soinTotal.utilisertest(terrain, joueur1, joueur2, pokemon1, 0, 0);

        // vérifier que les pokemons ont bien été soignés
        assertTrue(pokemon1.getPv() == pokemon1.getPvMax());

    }
}