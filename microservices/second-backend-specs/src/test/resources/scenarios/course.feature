Feature: Fonctionnalités CRUD sur l'entité "Département"

  Scenario: Création d'un cours d'identifiant 10
    Given Je veux créer un departement d'identifiant 10 et d'intitulé "Programmation"
    When Je fais un POST vers le chemin "/courses"
    Then Je reçois une réponse de code 200

  Scenario: Récupèration de tous les départements
    When Je fais un GET vers le chemin "/departments?page=1&sort=name,asc"
    Then Je reçois une réponse de code 200

  Scenario: Récupération du département d'identifiant 10
    Given J'ai l'identifiant 10 d'un département
    When Je fais un GET vers le chemin "/departments/10"
    Then Je reçois une réponse de code 200

  Scenario: Mise à jour du département d'identifiant 10
    Given J'ai l'identifiant 10 d'un département
    When Je fais un PUT vers le chemin "/departments/10"
    Then Je reçois une réponse de code 200

  Scenario: Suppression du département d'identifiant 10
    Given J'ai l'identifiant 10 d'un département
    When Je fais un DELETE vers le chemin "/departments/10"
    Then Je reçois une réponse de code 200
