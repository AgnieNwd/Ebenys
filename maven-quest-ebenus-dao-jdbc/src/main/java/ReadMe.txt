Reponse question 1 :

Une injection SQL, c'est une faille de sécurité qui consiste à injecter du code sql dans une requête qui sera exécuter et qui pourrait impacter la sécurité.


Reponse question 2 :

Statement fournit des méthodes permettant d'exécuter des requêtes sql vers la base de données.

PreparedStatement on l’utilise pour exécuter des requêtes paramétrée (ou pré-compilée) . 
L’utilisation de PreparedStatement rend l’application performante car les requêtes paramètres sont compilées une seule fois.


Reponse question 3 :

Une jointure total permet de combiner les résultats des 2 tables, 
les associer entre eux grâce à une condition et remplir avec des valeurs NULL si la condition n’est pas respectée

INNER JOIN  eretourne les enregistrements lorsqu’il y a au moins une ligne dans chaque colonne qui correspond à la condition.

LEFT JOIN permet de lister tous les résultats de la table de gauche même s’il n’y a pas de correspondance dans la deuxième tables.

RIGHT JOIN fait la même chose que LEFT JOIN sauf que elle le fait pour la table de droite.

