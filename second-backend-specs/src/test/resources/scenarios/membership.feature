Feature: Fonctionnalités sur l'état de membre d'un employé

  Background:
    Given J'ai un employé d'identifiant 10
    And Un département d'identifiant 10

  Scenario: Associer un employé à un département
    Given J'ai l'identifiant 10 d'un employé
    And J'ai d'identifiant 10 d'un département
    When Je fais un POST vers le chemin "/membership"
    Then Je reçois une réponse de code 200

  Scenario: Dissocier un employé à un département
    Given J'ai l'identifiant 10 d'un employé
    And J'ai d'identifiant 10 d'un département
    When Je fais un DELETE vers le chemin "/membership"
    Then Je reçois une réponse de code 200