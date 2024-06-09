package pokemons;

import pokemons.pouvoirs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * La classe GenerateurPokemon est responsable de la création de Pokémons avec des noms et des pouvoirs aléatoires.
 */
public class GenerateurPokemon {

    //ATTRIBUTS
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
    private static ArrayList<Pouvoir> m_nomsPouvoirs = new ArrayList<>(Arrays.asList(new ExtensionTerritoire(), new AlleKahida(), new SoinDeZone(), new FerveurGuerriere(), new SoinTotal(), new Necromancie(), new Confusion(), new Resistance(), new Kamikaze()));
    private static ArrayList<Pouvoir> m_pouvoirsUtilises = new ArrayList<>();




    //METHODES

    /**
     * Obtient un index aléatoire pour sélectionner un nom de Pokémon disponible.
     *
     * @return Un entier représentant l'index d'un nom de Pokémon disponible.
     * @throws IllegalStateException si aucun nom de Pokémon n'est disponible.
     */
    public static int getIndexAleatoire() {
        if (m_nomsDisponibles.isEmpty()) {
            throw new IllegalStateException("Il n'y a plus de noms de Pokémon disponibles.");
        }
        Random random = new Random();
        return random.nextInt(m_nomsDisponibles.size());
    }



    /**
     * Attribue un nom de Pokémon aléatoire à partir de la liste des noms disponibles.
     *
     * @return Le nom attribué au Pokémon.
     */
    public static String attribuerNom() {
        int index = getIndexAleatoire();
        String nom = m_nomsDisponibles.get(index);
        m_nomsUtilises.add(nom);
        m_nomsDisponibles.remove(index);
        return nom;
    }



    /**
     * Attribue un pouvoir aléatoire à partir de la liste des pouvoirs disponibles.
     *
     * @return Le pouvoir attribué ou null s'il n'y a pas de pouvoirs disponibles.
     */
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


    /**
     * Réinitialise les listes de noms et de pouvoirs en les remettant dans les listes disponibles.
     */
    public static void reinitialiser() {
        getNomsDisponibles().addAll(getNomsUtilises());
        getNomsUtilises().clear();
        getNomsPouvoirs().addAll(getPouvoirsUtilises());
        getPouvoirsUtilises().clear();
    }


    /**
     * Crée un nouveau Pokémon avec un nom et un pouvoir attribués aléatoirement.
     *
     * @return Un objet Pokemon avec un nom et un pouvoir attribués.
     */
    public static Pokemon creePokemon(){
        return new Pokemon(attribuerNom(), attribuerPouvoir());
    }





    //GETTERS

    /**
     * Obtient la liste des noms de Pokémons disponibles.
     *
     * @return Une liste de chaînes de caractères représentant les noms disponibles.
     */
    public static ArrayList<String> getNomsDisponibles() {
        return m_nomsDisponibles;
    }


    /**
     * Obtient la liste des noms de Pokémons déjà utilisés.
     *
     * @return Une liste de chaînes de caractères représentant les noms utilisés.
     */
    public static ArrayList<String> getNomsUtilises() {
        return m_nomsUtilises;
    }


    /**
     * Obtient un index aléatoire pour sélectionner un pouvoir disponible.
     *
     * @return Un entier représentant l'index d'un pouvoir disponible ou -1 si aucun pouvoir n'est disponible.
     */
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


    /**
     * Obtient la liste des pouvoirs disponibles.
     *
     * @return Une liste de pouvoirs disponibles.
     */
    public static ArrayList<Pouvoir> getNomsPouvoirs() {
        return m_nomsPouvoirs;
    }


    /**
     * Obtient la liste des pouvoirs déjà utilisés.
     *
     * @return Une liste de pouvoirs utilisés.
     */
    public static ArrayList<Pouvoir> getPouvoirsUtilises() {
        return m_pouvoirsUtilises;
    }

}
