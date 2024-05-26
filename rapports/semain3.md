### Rapport de conception Semaine 3 : Organisation et début de phase 2
====================================================================================================
Après avoir conceptualisé la phase 1 du jeu, nous avons commencé à organiser la phase 2 en définissant les tâches à accomplir et en répartissant les responsabilités entre les membres de l'équipe. Voici un aperçu de notre planification pour la semaine 3 :
- **Ajout de nouvelles classes :** Nous avons ajouté les classes nécessaires à la gestion des pouvoirs
- **Implémentation des pouvoirs :** Les premières classes Pouvoirs sont terminées et prêtes à être intégrées au jeu
  - **`pouvoirs.Pouvoir` :** Classe de base pour tous les pouvoirs
  - **'pouvoirs.Confusion' :** Pouvoir qui mélange la main et la pioche de l'adversaire
  - **'pouvoirs.ExtensionTerritoire' :** Pouvoir qui permet au joueur d'ajouter un 4e pokemon sur le terrain
  - **'pouvoirs.FerveurGerrière' :** Pouvoir qui augmente l'attaque d'un pokemon de 10 points jusqu'à sa mort
  - **'pouvoirs.Kamikaze' :** Pouvoir qui sacrifie un pokemon pour éliminer un pokemon adverse
  - **'pouvoirs.SoinDeZone' :** Pouvoir qui rend 10 pv à tous les pokemons du joueur
  - **'pouvoirs.SoinTotal' :** Pouvoir qui rend toute sa vie à un pokemon
- **tri des classes :** Nous avons ajouté des packages pour ranger les classes par catégories
  - **`pokemons` :** Contient les classes relatives aux pokemons
  - **`joueurs` :** Contient les classes relatives aux joueurs
    - **`pouvoirs` :** Contient les classes relatives aux pouvoirs
  - **`jeu` :** Contient les classes relatives au jeu
- **refonte de la classe Elements**
- **revue de l'affichage**

**A faire**
- **Implémenter d'autres pouvoirs**
- **Régler un bug de fin de jeu**
- **Ajouter des tests unitaires**
- **Améliorer l'affichage**
- **Ajouter des commentaires**