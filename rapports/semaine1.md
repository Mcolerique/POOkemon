### Rapport de Conception Semaine 1: Justification des Choix de Classes et de Relations

Dans le cadre de ce projet de développement de jeu basé sur le concept de Pokémon, nous avons pris des décisions spécifiques en matière de conception des classes et des relations pour assurer la structure et la fonctionnalité optimales du système. Voici les justifications de ces choix :

#### Classes Principales

1. **`pokemons.Pokemon` :**
   - **Attributs Significatifs :** Nous avons choisi d'attribuer à la classe `pokemons.Pokemon` des caractéristiques telles que les points de vie (`m_pv`), l'attaque (`m_attaque`), et l'élément (`m_element`) pour représenter les principales propriétés d'un Pokémon dans un combat.
   - **Méthodes Fonctionnelles :** Les méthodes `attaquer`, `subirDegats`, et `estVivant` sont nécessaires pour la simulation des interactions entre les Pokémon pendant un combat.

2. **`joueurs.Joueur` :**
   - **Responsabilités Clés :** La classe `joueurs.Joueur` gère la collection de Pokémon du joueur (`m_main`) ainsi que les interactions avec la pioche (`m_pioche`) et la défausse (`m_defausse`).
   - **Gestion de jeu.Jeu :** Les méthodes telles que `piocherPokemon`, `ajouterPokemon`, et `retirerPokemon` permettent au joueur d'effectuer des actions pendant le jeu.

3. **`jeu.Jeu` :**
   - **Centralisation du jeu.Jeu :** La classe `jeu.Jeu` agit comme le contrôleur principal du jeu, contenant les instances de joueurs (`m_j1`, `m_j2`), le terrain (`m_terrain`), et les éléments de jeu (`m_listePokemon`).
   - **Fonctions de Contrôle :** Les méthodes `jouer`, `finirTour`, et `estFini` dirigent le déroulement du jeu et déterminent les conditions de victoire.

4. **`jeu.Tour` :**
   - **Gestion de jeu.Tour :** La classe `jeu.Tour` encapsule les actions qu'un joueur peut effectuer pendant son tour, telles que piocher une carte, attaquer, et changer de tour.
   - **Coordination des Actions :** Les méthodes comme `piocher`, `attaquer`, et `finirTour` assurent un déroulement fluide des actions pendant le tour d'un joueur.

#### Relations et Associations

1. **Association `joueurs.Joueur` - `joueurs.Pioche`, `joueurs.Main`, `joueurs.Defausse` :**
   - **Encapsulation des Actions :** Chaque joueur possède une pioche, une main de cartes, et une défausse, ce qui permet de gérer efficacement les interactions liées au jeu de cartes Pokémon.
   - **Séparation des Responsabilités :** Chaque composant (pioche, main, défausse) a des rôles distincts dans le jeu, facilitant ainsi la gestion des actions du joueur.

2. **Association `joueurs.Joueur` - `joueurs.Terrain` :**
   - **Délimitation de l'Espace de jeu.Jeu :** Chaque joueur utilise un terrain pour jouer ses cartes Pokémon et gérer les interactions avec l'adversaire.
   - **Facilitation de l'Interaction :** Le terrain agit comme un espace partagé où les Pokémon sont déployés et où les combats ont lieu.

3. **Héritage `joueurs.Joueur` - `joueurs.Ordinateur` :**
   - **Flexibilité de la Jouabilité :** L'utilisation de l'héritage permet d'étendre le jeu pour inclure des joueurs contrôlés par l'ordinateur sans réécrire le code principal du joueur humain.

#### Autres justifications

1. **Un tableau d'éléments des pokémons dans `jeu.Jeu` plutôt que des classes héritées**
    - C'est beaucoup plus simple d'utiliser un tableau 2d avec les éléments en ligne et en colonnes, il suffit de récupérer la case qui joint les 2 pour connaitre l'importance de l'attaque