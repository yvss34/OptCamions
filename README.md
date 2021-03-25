# Projet Symone : optimisation de tournées de camion

Outil d’aide à la décision intégrant une méthode de résolution permettant de résoudre un problème de planification et proposant une solution sous le format .csv.

# Guide d'installation :

Outils utilisés :
- IDE : IntelliJ
- JDK 15.0.2

Dépendances :
- jgoodies-common-1.8.1.jar
- jgoodies-forms-1.8.0.jar

1- Telecharger le zip du projet ou cloner le repertoire.

2- Telecharger les archives jgoodies-common-1.8.1 et jgoodies-forms-1.8.1 
http://www.jgoodies.com/downloads/archive/

3- Ajouter jgoodies-common-1.8.1.jar et jgoodies-forms-1.8.0.jar aux dépendances du projet.
 - Projet Structure
 - Modules
 - Dependencies
 - Add JARs

# Guide d'utilisation :

Run src>vue>InterfacePrincipale

![image](https://user-images.githubusercontent.com/56535810/112488770-2456dc00-8d7e-11eb-8e4d-d7b0ba016a1d.png)

1- Modifier les paramètres par défaut, si necessaire, en respectant le format des valeurs par défaut.

2- Ajout trajet non fixe ou trajet fixe

3- Lancement de la résolution

![image](https://user-images.githubusercontent.com/56535810/112489035-5b2cf200-8d7e-11eb-9430-8c8799106fc9.png)

1- Modifier les paramètres par défaut, en fonction du trajet souhaité, en respectant le format des valeurs par défaut.

2- Valider pour ajouter le trajet à la liste des trajets

![image](https://user-images.githubusercontent.com/56535810/112489112-6d0e9500-8d7e-11eb-9e82-5b3eabfa3932.png)

1- Modifier les paramètres par défaut, en fonction du trajet souhaité, en respectant le format des valeurs par défaut.

2- Valider pour ajouter le trajet à la liste des trajets

![image](https://user-images.githubusercontent.com/56535810/112490004-4a30b080-8d7f-11eb-8ad9-b4f6f961e7e6.png)

Lors de la résolution si aucune solution n'est trouvée, cette indication est affichée à l'utilisateur.

![image](https://user-images.githubusercontent.com/56535810/112490342-92e86980-8d7f-11eb-9bfa-ba9f926d86a7.png)

Lors de la résolution si une solution est trouvée, cette indication est affichée à l'utilisateur et un fichier Solution.csv est crée à la racine du projet.

![image](https://user-images.githubusercontent.com/56535810/112491562-b8c23e00-8d80-11eb-923b-d4a32c6b1c69.png)
