Feature: Test pour la réinitialisation du mot de passe

  Background:
    Given J'ai un utilisateur d'identifiant "evaristegalois@heigvd.ch"

  Scenario: Réinitialisation du mot de passe pour l'utilisateur d'identifiant "evaristegalois@heigvd.ch"
    Given J'ai l'identifiant "evaristegalois@heigvd.ch" d'un utilisateur
    When Je fais un DELETE vers le chemin "/users/password"
    Then Je reçois une réponse de code 200
    When Je fais un GET vers le chemin "/users/password?token="
    Then Je reçois une réponse de code 200
    When Je fais un POST vers le chemin "/users/password"
    Then Je reçois une réponse de code 200