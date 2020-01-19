# Rapport Final du projet
Dans le cadre de ce projet nous étions censé faire des 

## Liste des fonctionnalités
 Nous avons différentes api que nous avons développé dans ce projet. Toutes les a part certaines nécessitent une authentification avant de pouvoir les consomer. Ceci se fait par token **JWT(Json Web Token)**.
Donc pour pouvoir s'authentifier, il est nécessaire  Nous avons les endpoints suivants:

#### Fonctionnalités du premier backend
- `auth/login`: Cette route permet à un utilisateur de s'authentifier. Pour s'authentifier, l'utilisateur devra donner son adresse e-mail et il recevra un token. Ce token le permerttra de s'authentifier pour le autres fonctionnalités.

- `users`: Cette route permet 

## Rapport sur les tests de validations sur Cucumber
Pour le développment de l'application, nous avons utilisé l'approche __BDD (Behaviour Driven Development)__. Donc pour valider le design nos APIs, nous avons utilisé l'approche BDD avec Cucumber JVM. Nous avons décrit des scénarios avec les valeurs que nous nous attendions de recevoir dans la logique d'un scénario qu'un utilisateur pourrait suivre.

## Les difficultés rencontrées
- La configuration de Traefik n'a pas été rapide. Bien que sa configuration est simple, aboûtir à cette configuration n'a pas été facile. Pour plus d'information sur la configuration de Traefik pour le projet, vous pouvez consulter le document [docker.md](./docker.md).
- L'écriture des tests sur Cucumber. Sa prise en main, bien qu'étant simple, fut assez compliqué pour moi. La conception de tests adéquates pour valider les fonctionnalités que nous avons implémenté fit compliqué.

## Bugs et limitations
- Dans le cadre de ce projet, nous n'étions pas obligé de gérer ajouter la __gestion des tokens__. En effet, les tokens sont octroyé aux utilisateurs dès qu'ils se sont authentifiés mais ne sont pas conservé dans le système. Si un utilisateur entre en possession de ce token et l'utilise dans le système pendant qu'il est encore valide, il pourrait réaliser des opérations sur le système comme le détenteur du token.
- 