### 1. Exigences Fonctionnalitées à tester

| Identifiants des fonctionnalitées | Description des fonctionnalitées |
| :------------------: | :---------------------------------------|
| EF-001 | Prendre un fichier JSON en argument à l'entrée du programme.|
| EF-002 | Contenir une banque d'information sur les soins.|
| EF-003 | Vérifier les appels au programme. |
| EF-004 | Valider que le JSON est syntaxiquement correcte.|
| EF-005 | Valider les informations entrées dans le JSON.|
| EF-006 | Transformer les informations du JSON en objet Dossier et Soins|
| EF-007 | Calculer le remboursement avec la classe Monnaie |
| EF-008 | Calculer les statistiques. |
| EF-009 | Afficher les statistiques. |
| EF-010 | Sortir du programme avec des erreures précises|
| EF-011 | Générer un fichier JSON sortant bien formattaté contenant les informations du dossier sur les remboursements pour chaque soins. |

### 2. Suites et cas de tests
|Identifiants des fonctionnalitées|Identifiants des suites de tests|Description des suites de tests|Nombre de cas de tests|
|:--------------------:|:-----------------:|:-------------------------------------|------:|
| EF-001 | ST-001 | | |
| EF-002 | ST-002 | | |
| EF-003 | ST-003 | Vérifier que le programme supporte toutes les options et arguments requis au programme. | |
| EF-004 | ST-004 | | |
| EF-005 | ST-005 | | |
| EF-006 | ST-006 | | |
| EF-007 | ST-007 | | |
| EF-008 | ST-008 | Vérifier que les statistiques sont calculées adéquatements. | |
| EF-009 | ST-009 | Vérifier que les statistiques sont affichées adéquatements. | |
| EF-010 | ST-010 | | |
| EF-011 | ST-011 | | |

### 3. Cas de tests
|Identifiant des suites de tests|Identifiant des cas de tests|Description des cas de tests|Préconditions|Sortie Attendue|Priorité|
|:-----------:|:---------:|:------------------------------|:---------------|:------:|:----------:|
|ST-001|CT-001| | | | |
|ST-002|CT-001| | | | |
|ST-003|CT-001| Appeler le programme avec seulement l'option -S, sans avoir traité de demande | Avoir traiter aucune demande. (Ou avoir réinitialisé les statistiques) | Le programme se termine après avoir afficher toutes les statistiques à 0 ainsi qu'une liste vide de soin. | Haute |
|      |CT-002| Appeler le programme avec seulement l'option -S, après avoir traité une/des demande(s) | Avoir traiter au moins une demande avant l'appel de programme | Afficher toutes les statistiques pertinentes entrées jusqu'à maintenant: le nombre de demande rejetées, le nombre de soins traités ainsi que chaque type de soins traités incluant sont nombre, max et moyenne de coût par soin. | Haute |
|      |CT-003| Appeler le programme avec seulement l'option -SR | Avoir traité au moins une demande (valide ou non). | Le programme se termine après avoir afficher "Statistique remise à zéro". Si on suit cet appel au programme de l'appel avec option -S, les statistiques affichées seront tous à 0.| Haute |
|      |CT-004| Appeler le programme avec 2 arguments qui représentent deux chemins/noms de fichiers valides. | Les arguments sont des chemins/noms de fichier valide.  | Le programme s'exécute et se termine, un fichier sera créé au chemin et au nom spécifié par le deuxième argument. Ce fichier contient les résultats attendus dans le fichier de sortie. Les statistiques seront changé de façon approprié pour reflété la nouvelle demande traitée. Note: le comportement reste le même, peu importe si la demande traitée est valide ou non. | Haute |
|      |CT-005| Appeler le programme avec 2 arguments qui représentent autre chose que deux chemins/noms de fichiers valides. | Le premier argument n'est pas un fichier de réclamation valide, le deuxième argument n'est pas un chemin de fichier possible. | Le programme s'exécute et se termine, produisant un fichier output.json contenant un message d'erreur. | Haute |
|      |CT-006| Appeler le programme avec 2 arguments qui représentent deux chemins/noms de fichiers valides, précédés de l'option -p | Le deuxième argument est un chemin/nom de fichier valide. | Le programme s'exécute et se termine, un fichier sera créé au chemin et au nom spécifié par le deuxième argument. Ce fichier contient les résultats attendus dans le fichier de sortie. Les statistiques demeurent inchangées. | Haute |
|      |CT-007| Appeler le programme avec 2 arguments qui représentent autre chose que deux chemins/noms de fichiers valides, précédés de l'option -p| Le premier argument n'est pas un fichier de réclamation valide, le deuxième argument n'est pas un chemin de fichier possible. | Le programme s'exécute et se termine, produisant un fichier output.json contenant un message d'erreur. | Faible |
|      |CT-008| Appeler le programme sans option ni argument. | Aucun argument n’est présent dans l'appel de programme | Le programme s'exécute et se termine, produisant un fichier output.json contenant un message d'erreur au sujet du manque d'argument/option. | Haute |
|      |CT-009| Appeler le programme avec plus de 2 arguments.| >2 arguments sont présents dans l'appel de programme | Le programme s'exécute et se termine, produisant un fichier output.json contenant un message d'erreur au sujet du nombre d'arguments/options invalides. | Faible |
|      |CT-010| Appeler le programme avec plus de 2 arguments, précédé de l'option -p.| >2 arguments sont présents dans l'appel de programme, précédé de l'option -p | Le programme s'exécute et se termine, produisant un fichier output.json contenant un message d'erreur au sujet du nombre d'arguments/options invalides. | Faible |
|      |CT-011| Appeler le programme avec 2 arguments qui représentent deux chemins/noms de fichiers valides, précédés de l'option -S. | Le fichier pointé par le premier argument contient une réclamation valide selon les spécifications. Le deuxième argument est un chemin/nom de fichier valide. L'option -S est utilisé | Le programme s'exécute et se termine, produisant un fichier output.json contenant un message d'erreur au sujet du nombre d'arguments/options invalides.  | Faible |
|      |CT-012| Appeler le programme avec 2 arguments qui représentent deux chemins/noms de fichiers valides, précédés de l'option -SR. | Le fichier pointé par le premier argument contient une réclamation valide selon les spécifications. Le deuxième argument est un chemin/nom de fichier valide. L'option -SR est utilisée | Le programme s'exécute et se termine, produisant un fichier output.json contenant un message d'erreur au sujet du nombre d'arguments/options invalides. | Faible |
|      |CT-013| Appeler le programme avec 2 arguments qui représentent deux chemins/noms de fichiers valides, précédés de >2 options.| Le fichier pointé par le premier argument contient une réclamation valide selon les spécifications. Le deuxième argument est un chemin/nom de fichier valide. Deux options quelconques sont utilisées | Le programme s'exécute et se termine, produisant un fichier output.json contenant un message d'erreur au sujet du nombre d'arguments/options invalides. | Faible |
|ST-004|CT-001| | | | |
|ST-005|CT-001| | | | |
|ST-006|CT-001| | | | |
|ST-007|CT-001| | | | |
|ST-008|CT-001| | | | |
|      |CT-002| | | | |
|      |CT-003| | | | |
|      |CT-004| | | | |
|      |CT-005| | | | |
|      |CT-006| | | | |
|      |CT-007| | | | |
|      |CT-008| | | | |
|      |CT-009| | | | |
|      |CT-010| | | | |
|      |CT-011| | | | |
|      |CT-012| | | | |
|      |CT-013| | | | |
|      |CT-014| | | | |
|      |CT-015| | | | |
|      |CT-016| | | | |
|      |CT-017| | | | |
|ST-009|CT-001| | | | |
|      |CT-002| | | | |
|      |CT-003| | | | |
|      |CT-004| | | | |
|      |CT-005| | | | |
|      |CT-006| | | | |
|      |CT-007| | | | |
|      |CT-008| | | | |
|      |CT-009| | | | |
|      |CT-010| | | | |
|      |CT-011| | | | |
|      |CT-012| | | | |
|      |CT-013| | | | |
|      |CT-014| | | | |
|      |CT-015| | | | |
|      |CT-016| | | | |
|      |CT-017| | | | |
|ST-010|CT-001| | | | |
|ST-011|CT-001| | | | |