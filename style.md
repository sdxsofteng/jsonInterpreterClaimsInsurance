# Équipe 1
------
------
## Convention de codage
------
------
### Nomenclature
* Les noms de classes, fonctions et variables commencent par une majuscule et suivent le style "CamelCase".
* Les constantes sont écrites exclusivement en majuscule, séparées par des `_`. Exemple: `int LEFT_MOTOR_PWM_PORT = 0`.
* Les noms des fichiers, classes, variables, etc. sont strictement en anglais. L'usage du français est utilisé seulement pour les commentaires, les commits et les fichiers textes/markdown demandés par le professeur tel que celui-ci.
* Un plus haut niveau d'abstraction nécessite un nom plus général qu'une implémentation concrète. Le nom de l'implémentation concrète doit débuter par le nom de l'abstraction. Par exemple: La méthode concrète `ValidateAllProperties` qui utilise la méthode `Validate` de la classe d'une propriété.
* Le nom d'une variable doit refléter son type. (Le pluriel désigne une liste, une préposition tel que "is" désigne un booléen, etc.)
### Espacements
* Les indentations ont une taille de 4 espaces et sont strictement des espaces.
* Il ne doit jamais y avoir plus d'une ligne vide de suite.
* Les éléments suivants sont séparés par une ligne: 
    * Les packages et les imports
    * Les imports de sources différentes
    * Les définitions de fonctions
    * La dernière définition de variable et la fonction qui suit
    * Les imports et la définition de classe
* Voici les règles concernant les espaces: 
    * Les opérateurs sont entourés d'un espace.
    * Les parenthèses n'ont jamais d'espace à l'intérieur (coussin).
    * Les arguments passés à une fonction sont séparés par une virgule suivie d'un espace.
    * Il y a un espace entre les accolades et une parenthèse ou mot clé qui précède ladite accolade.
* Le style des accolades est `1TBS`.
### Autres conventions
* Les variables de classes seront privées, rendues accessibles via des getters/setters lorsqu'appropriés. 
------
## Convention d'utilisation de Git
------
------
* Nous définissons les abréviations suivantes pour les normes qui suivent:
    * **MOD**: Signale la modification d'une fonctionnalité existante.
    * **FIX**: Signale la réparation d'un problème dans la version qui précède le commit.
    * **AJT**: Signale l'ajout d'une fonctionnalité, d'une classe, etc.
    * **SUP**: Signale la suppression d'une fonctionnalité, d'un fichier, ou autre. *Veuillez utiliser MOD si ce n'est pas strictement une suppression, ce qui veut dire que le changement inclut un ajout quelconque.*
* Chaque membre travaillera sur sa propre branche lorsqu'il fait une tâche. Il ne faut jamais travailler sur `master` directement. Le nom de la branche doit correspondre aux initiales de la personne, suivi de l'abréviation souhaitable puis suivi de la signification de la branche selon la tâche. Le tout doit être séparé par des `_`. Par exemple, si David travaille sur l'ajout du module de validation, le nom de sa branche sera `DG_AJT_ModuleValidation`.
* Un commit représente un moment où le code est *valide* (compile) et inclut une seule chose (ajout d'une fonction, réparation d'une erreur, modification d'un fichier pour respecter une norme, etc). 
* Le titre des commits commence par l'un des suffixes décrits ci-dessus, suivi de l'explication des changements. Par exemple, si le commit ajoute la classe `ValidationHelper` le nom de commit serait `AJT - Classe ValidationHelper`. Si le titre semble prendre plus d'une ligne (environ plus de 50 caractères), il est approprié de faire un résumé suivit de `(voir desc.)` et d'inclure les détails dans la description du commit plutôt que dans son titre. 
* Une fois une branche complétée (la fonctionnalité est testée et validée), il faut la fusionner dans `master` en utilisant la stratégie `Squash` afin de regrouper tous les commits de la branche en un seul commit pour faciliter les retours en arrière. Le nom du commit devra inclure le nom de la branche; par exemple, `Fusion de DG_AJT_ModuleValidation dans master`. À cause de l'utilisation de cette stratégie, les branches de développement ne doivent pas être supprimées pour que le correcteur puisse voir l'historique sans modification.
* Chaque tâche doit avoir sa propre branche.
------
## Convention d'utilisation de Trello
------
* Il est impératif de s'ajouter à la carte qu'on prévoit faire avant de la commencer.
* Un membre d'une carte dont d'autre dépendent doit compléter le tout dans les délais prescrits/des délais raisonnables.
* Les tâches peuvent avoir les statuts suivant:
    * À faire: Une tâche qui n'est pas commencée, mais qui peut avoir été choisie.
    * En cours: Une tâche qui est en train d'être analysée, conçue ou codée.
    * En test: Une tâche qui semble être accomplie. Un ou plusieurs membres la réviseront afin de la valider. Si une tâche "en test" nécessite plus de travail, elle doit être retournée au statut de "en cours" puisque ce statut désigne des tâches prêtes à être validées.
    * Complété: Une tâche qui a été fusionnée à la branche `master` et qui ne nécessite plus de travail.
* Les tâches peuvent avoir les libellés suivants:
    * Piorité basse (vert): tâche qui peut être reportée plus tard, généralement lorsqu'elle dépend d'un autre module.
    * Piorité moyenne (jaune): tâche qui n'a pas nécessairement à être faite maintenant, mais qui devrait minimalement commencer à être planifiée.
    * Piorité élevée (rouge): tâche qui doit être faite maintenant puisque d'autres en dépendent, ou une contrainte de temps arrive à échéance.
    * Bug (violet): Désigne une tâche qui est un problème à régler plutôt qu'une fonctionnalité à ajouter/modifier.