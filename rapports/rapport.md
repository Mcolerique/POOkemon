# Rapport du Projet

## Introduction
Ce projet est une simulation de jeu de combat de Pokémon. Il permet à un joueur s'affronter une IA en utilisant des Pokémons qui ont des capacités spéciales, appelées pouvoirs. 
Chaque Pokémon a un nom, des points de vie, une attaque, une défense et peut avoir un pouvoir.

## Structure du Projet
Le projet est structuré en plusieurs packages et classes :

- Le package `pokemons` contient les classes `Pokemon`, `Affinite`, `Elements` et `GenerateurPokemon`. Ces classes gèrent les informations relatives aux Pokémons, leurs affinités élémentaires, et la génération de nouveaux Pokémons.

- Le package `pokemons.pouvoirs` contient les classes qui définissent les différents pouvoirs que les Pokémons peuvent avoir. Chaque pouvoir est une classe qui hérite de la classe abstraite `Pouvoir`.

- Le package `joueurs` contient les classes `Joueur`, `Humain`, `IA`, `Main`, `Defausse` et `Terrain`. Ces classes gèrent les informations relatives aux joueurs, leurs mains de cartes, leurs défausses et le terrain de jeu.

- Le package `tests` contient les classes de tests unitaires pour tester les différentes fonctionnalités du jeu.

- Le package `affichage` contient les classes pour gérer l'affichage du jeu et la musique.

- Le package `jeu` contient la classe `Jeu` qui est le point d'entrée du programme.

## Fonctionnement du Jeu
Dans ce jeu, deux joueurs s'affrontent en utilisant leurs Pokémons. Chaque joueur a une main de cartes (des Pokémons) et une défausse. 
Les Pokémons sont placés sur le terrain pour combattre. Chaque Pokémon a une affinité élémentaire (eau, feu, terre, air) qui peut lui donner un avantage ou un désavantage lors d'un combat contre un autre Pokémon. 
Certains Pokémons ont des pouvoirs spéciaux qui peuvent être utilisés pendant le jeu. 
Ces pouvoirs peuvent avoir des effets variés, comme soigner un Pokémon, augmenter l'attaque ou la défense, ou même ressusciter un Pokémon de la défausse.

## Choix artistiques
Nous avons un magnifique affichage en console, avec des couleurs et des musiques qui nous plongent dans le jeu.
Nous avons essayé de varier les pouvoirs des pokemons pour qu'ils soient tous uniques et intéressants à jouer, certains se ressemble mais on a recyclé certains trucs au bout d'un moment.

## Répartition des tâches
- Gaétan : base du jeu, quelques pouvoirs, réglage de bugs, tests unitaires, rapport, musique (30%)
- Déborah : affichage, uml, rapports, correction de bugs, éléments des pokemons (30%)
- Favien : développement du jeu, grosses maj et corrections de bugs, pouvoirs, Ordinateur et son algo... (40%)

## Conclusion
On en a beaucoup bavé, on en peut plus des pokemons, mais on a réussi à sortir quelque chose de potable.