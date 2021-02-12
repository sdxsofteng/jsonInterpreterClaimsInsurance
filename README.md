## INF2050-20-H21
### Équipe 1
------
------
Ce projet est un projet Maven. Nous gérons les dépendences, compilations et tests (tout le build workflow) via Maven ce qui renrenddre la configuration du projet plus simple.

Note que les actions de Maven appropriés dans IntelliJ performent les commandes Maven correspondantes listées ci-dessous. Il est donc possible de simplement faire un *Maven package* via l'interface d'IntelliJ afin de compiler, rouler les tests et créé le jar en output en un seul clic. Les instructions ci-dessous sont données par souci de clarté, et afin d'assister toute personne qui ne compte pas utiliser IntelliJ (si applicable).
------
## Procédure pour la configuration du projet
------

### Importation
1- Dans IntelliJ (ou autre IDEA), il faut importer le projet en tant que *Projet Maven*.
2- Dans IntelliJ, pour que l'Intellisense soit correcte, il faut regarder l'onglet module du projet pour vous assurez qu'il détecte bien que le dossier `src/main/java` est le dossier source, `src/test` est le dossier test et `src/main/resources` est le dossier ressource.
### Compilation
Par défaut, la version de Java du projet est 15. Le programme est testé pour cette version, mais il est possible de changer la version si vous avez une version de Java >11, mais <15 en changeant la variable `java.version` dans le fichier `pom.xml`. Cependant, il n'est pas garanti que le code marche pour une version différente de Java. Un cas pour lequel il serait valable de modifier la version est le suivant: il est possible que votre JRE (Java Runtime Environment) soit d'une version plus ancienne, ce qui lancerait une exception lorsque le jar est lancé puisque le code compilé est plus récent.

Pour compilé le code, il s'agit de faire la commande `mvn compile`.

### Tests Unitaires
Pour rouler les tests, il s'agit de faire la commande `mvn test`.

Dans IntelliJ, la fonction de base pour rouler tous les tests fonctionne si le projet est importé proprement tel que décrit dans la section [Importation](#Importation).

### Paquetage (jar)
Pour produire le fichier .jar, il suffit de faire la commande `mvn package`.

Deux jar sont créés:
- Refund-<versionNo>.jar : Ce jar contient que notre code compilé, et n'est utile que dans un cas pour lequel les dépendances n'ont pas besoin d'être incluses dans le jar.
- Refund-<versionNo>-jar-with-dependencies.jar : Ce jar inclues toutes les dépendances nécessaires pour rouler le code (comme un format "standalone") et est le jar à ce servir pour la correction puisqu'il est autosuffisant.


