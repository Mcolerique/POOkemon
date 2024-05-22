package pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ListePokemons {

    // Attributs
    private static ArrayList<String> m_nomsDisponibles = new ArrayList<String>(Arrays.asList(
            "Pikachu", "Bulbizarre", "Carapuce", "Salamèche", "Herbizarre",
            "Dracaufeu", "Tortank", "Raichu", "Carabaffe", "Reptincel",
            "Aquali", "Florizarre", "Mewtwo", "Miaouss", "Ronflex",
            "Magicarpe", "Roucool", "Rattata", "Nidoran", "Abra",
            "Machoc", "Ptitard", "Kokiyas", "Hypotrempe", "Mimitoss",
            "Voltorbe", "Otaria", "Porygon", "Tadmorv", "Amonita",
            "Kabuto", "Natu", "Hoothoot", "Cornèbre", "Soporifik",
            "Mysdibule", "Girafarig", "Wattouat", "Toudoudou", "Tarsal", "Evoli"));
    private static ArrayList<String> m_nomsUtilises = new ArrayList<String>();

    // Methodes
    public static void reinitialiser() {
        m_nomsDisponibles.addAll(m_nomsUtilises);
        m_nomsUtilises.clear();
    }

    // Getters
    private static int getIndexAleatoire() {
        if (m_nomsDisponibles.isEmpty()) {
            throw new IllegalStateException("Il n'y a plus de noms de Pokémon disponibles.");
        }
        Random random = new Random();
        return random.nextInt(m_nomsDisponibles.size());
    }

    public static String getNom() {
        int index = getIndexAleatoire();
        String nom = m_nomsDisponibles.get(index);
        m_nomsUtilises.add(nom);
        m_nomsDisponibles.remove(index);
        return nom;
    }
}
