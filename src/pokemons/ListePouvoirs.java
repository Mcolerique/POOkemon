package pokemons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ListePouvoirs {

    // Attributs
    private static ArrayList<String> m_nomsPouvoirs = new ArrayList<String>(Arrays.asList(
            "Extension du territoire", "Confusion", "Kamikaze"));
    private static ArrayList<String> m_pouvoirsUtilises = new ArrayList<String>();

    // Methodes
    public static void reinitialiser() {
        m_nomsPouvoirs.addAll(m_pouvoirsUtilises);
        m_pouvoirsUtilises.clear();
    }

    // Getters
    private static int getIndexAleatoire() {
        if (m_nomsPouvoirs.isEmpty()) {
            return -1;
        }
        Random random = new Random();
        return random.nextInt(m_nomsPouvoirs.size());
    }

    public static String getNom() {
        int index = getIndexAleatoire();
        String nom = m_nomsPouvoirs.get(index);
        m_pouvoirsUtilises.add(nom);
        m_nomsPouvoirs.remove(index);
        return nom;
    }
}