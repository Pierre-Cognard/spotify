# SpOtify dU Pauvre

### Contexte.

L’application ICE & SOUP se compose de deux blocs ayant un impact sur l’évaluation du travail:
Dans le premier bloc obligatoire, il s'agira de développer un programme permettant de gérer
une collection distribuée de morceaux de musique. L'application sera composée d'un ensemble
de serveurs, chacun gérant une partie de la collection localisée sur un site (une machine). Le
client référencera l'ensemble de ces serveurs, il permettra des recherches sur différents critères.
Dans un second temps, l'application pourra permettre de jouer les morceaux de musique. Cette
dernière partie implique donc un serveur de streaming. Les fonctionnalités obligatoires sont:

1. Ajout, suppression et modification d’un morceau (i.e avec upload du fichier audio).


2. Recherche par titre et par auteur des musiques.


3. Streaming audio avec VLC. Il faut donc pouvoir jouer, mettre en pause et arrêter la lecture.


4. Langage client / serveur de votre choix.

### Dans un second temps, il s’agira soit:
๏ D’intégrer le cryptage des communications entre les clients et les serveurs. Dans ce cas,
il sera demandé de fournir une preuve de l’efficacité de ce cryptage i.e. essayer de
simuler l’attaque d’un client qui obtiendrait donc de l’information.

๏ De développer un service de type ICE GRID permettant un découpage des services et
donc des serveurs. Ce découpage est laissé à votre appréciation. Cela pourrait inclure
un découpage par thème musical, par distance du client, ou tout autre chose.

๏ D’intégrer ICESTORM, et donc de permettre aux différentes applications ICE & SOUP
d’être notifiées lors de la mise à jour des bibliothèques. 
