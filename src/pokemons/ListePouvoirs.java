package pokemons;

import pokemons.pouvoirs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ListePouvoirs {

    // Attributs
    private static ArrayList<Pouvoir> m_nomsPouvoirs = new ArrayList<>();
    private static ArrayList<Pouvoir> m_pouvoirsUtilises = new ArrayList<>();

    public ListePouvoirs() {
        m_nomsPouvoirs.addAll(Arrays.asList(new ExtensionTerritoire(), new Confusion()));
    }

    // Methodes
    public static void reinitialiser() {
        m_nomsPouvoirs.addAll(m_pouvoirsUtilises);
        m_pouvoirsUtilises.clear();
    }

    // Getters
    public static int getIndexAleatoire() {
        if (m_nomsPouvoirs.isEmpty()) {
            return -1;
        }
        Random random = new Random();
        return random.nextInt(m_nomsPouvoirs.size());
    }

    public static Pouvoir getPouvoir() {
        int index = getIndexAleatoire();
        if(index == -1)
        {
            return null;
        }
        else {
            Pouvoir pouvoir = m_nomsPouvoirs.get(index);
            m_pouvoirsUtilises.add(pouvoir);
            m_nomsPouvoirs.remove(index);
            return pouvoir;
        }
    }
}