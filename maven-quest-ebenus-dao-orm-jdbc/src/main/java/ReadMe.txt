Reponse question 1 :
Réflexivité = L'introspection consiste en la découverte dynamique des informations propres à une classe Java ou à un objet. 
Ce mécanisme est notamment utilisé au niveau de la machine virtuelle Java lors de l'exécution de votre programme, et donne lieu à une API.
(Java Reflection API)

Reponse question 2 :
+ Controle totale sur les objets meme ce qui sont en private
+ Modification des classes dans le runtime

- Temps d'access aux methodes et variables est long
- La voulume du code est tres importante

Reponse question 3 :

+ Acess aux variables des API externes
+ Annotations


Reponse question 4 :

DataSource.getConnection retourne une connection d'un poll des conections disponibles ou ouvre une nouvelle si la configuration de DataSource le permet , et a la fermeture retrourne la connection dans le pool.
ca fait gagner des ressources, car la ouverture d'une nouvelle connection est tres couteux.
Egalement permet de utiliser les differentes parametres de connection dans le runtime. getConnection(user, mp)
Donc DataSource permet avoir une bonne scalabilite.

DriverManager.getConnection  retourne une nouvelle connection , et a la fermeture la detruit.

Reponse question 5:

Si on limite a une seule connection de la DataSource.getConnection et on commente la methode ConnectionHelper.closeSqlResources()  , 
Des la premiere execution on prend la seul connection disponible dans le pool et on ne la rend jamais . Donc toutes les autres apelles vont faire la queue et vont recevoir le TimeOut erreur avec une connection NULL vue que elle n'etait jamais close (rendu dans le pool)


