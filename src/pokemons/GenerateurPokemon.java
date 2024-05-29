package pokemons;

import pokemons.pouvoirs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GenerateurPokemon {
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
    private static ArrayList<Pouvoir> m_nomsPouvoirs = new ArrayList<>(Arrays.asList(new ExtensionTerritoire(), new Kamikaze(), new SoinDeZone(), new FerveurGuerriere(), new SoinTotal(), new Necromancie(), new Confusion(), new Resistance() ));
    private static ArrayList<Pouvoir> m_pouvoirsUtilises = new ArrayList<>();
    public static int getIndexAleatoire() {
        if (m_nomsDisponibles.isEmpty()) {
            throw new IllegalStateException("Il n'y a plus de noms de Pokémon disponibles.");
        }
        Random random = new Random();
        return random.nextInt(m_nomsDisponibles.size());
    }

    public static String attribuerNom() {
        int index = getIndexAleatoire();
        String nom = m_nomsDisponibles.get(index);
        m_nomsUtilises.add(nom);
        m_nomsDisponibles.remove(index);
        return nom;
    }

    public static ArrayList<String> getM_nomsDisponibles() {
        return m_nomsDisponibles;
    }

    public static ArrayList<String> getM_nomsUtilises() {
        return m_nomsUtilises;
    }
    public static int getIndexPouvoirAleatoire() {
        if (m_nomsPouvoirs.isEmpty()) {
            return -1;
        }
        Random random = new Random();
        int index = random.nextInt(m_nomsPouvoirs.size())+2;
        try {
            m_nomsPouvoirs.get(index);
        }
        catch (IndexOutOfBoundsException e) {
            return -1;
        }
        return index;
    }

    public static Pouvoir attribuerPouvoir() {
        int index = getIndexPouvoirAleatoire();
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
    public static ArrayList<Pouvoir> getM_nomsPouvoirs() {
        return m_nomsPouvoirs;
    }

    public static ArrayList<Pouvoir> getM_pouvoirsUtilises() {
        return m_pouvoirsUtilises;
    }
    public static void reinitialiser() {
        getM_nomsDisponibles().addAll(getM_nomsUtilises());
        getM_nomsUtilises().clear();
        getM_nomsPouvoirs().addAll(getM_pouvoirsUtilises());
        getM_pouvoirsUtilises().clear();
    }
    public static Pokemon creePokemon(){
        return new Pokemon(attribuerNom(), attribuerPouvoir());
    }
}
