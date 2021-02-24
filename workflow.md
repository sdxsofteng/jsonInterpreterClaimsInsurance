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
* Une fois une branche complétée (la fonctionnalité est testée et validée), il faut la fusionner dans `master` afin de regrouper tous les commits de la branche en un seul commit pour faciliter les retours en arrière. Le nom du commit devra inclure le nom de la branche; par exemple, `Fusion de DG_AJT_ModuleValidation dans master`. 
* Chaque tâche doit avoir sa propre branche.
* Vu l'envergure du projet ainsi que nous nous considérons comme développeurs principaux du projet, nous ne prendrons pas la peine de faire de forks pour le moment.
* Pour la partie 2 du projet, puis la partie 3 la branche `DEV_PartieX` (X étant le no. de partie) nous servira de branche de développement. Le produit final sera ensuite merger à `master` pour la correction.