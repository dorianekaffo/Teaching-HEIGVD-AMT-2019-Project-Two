Feature: Fonctionnalités CRUD sur l'entité "Employé"

  Scenario: Création d'un employé d'identifiant 10
    Given Je veux créer un employé d'identifiant 10 et de nom "Carl Friedrich Gauss"
    When Je fais un POST vers le chemin "/employees"
    Then Je reçois une réponse de code 200

  Scenario: Récupèration de tous les employées
    When Je fais un GET vers le chemin "/employees?page=1&sort=name,asc"
    Then Je reçois une réponse de code 200

  Scenario: Récupération de l'employé d'identifiant 10
    Given J'ai l'identifiant 10 d'un employé
    When Je fais un GET vers le chemin "/employees/10"
    Then Je reçois une réponse de code 200

  Scenario: Mise à jour de l'employé d'identifiant 10
    Given J'ai l'identifiant 10 d'un employé
    When Je fais un PUT vers le chemin "/employees/10"
    Then Je reçois une réponse de code 200

  Scenario: Suppression de l'employé d'identifiant 10
    Given J'ai l'identifiant 10 d'un employé
    When Je fais un DELETE vers le chemin "/employees/10"
    Then Je reçois une réponse de code 200
