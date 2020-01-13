Feature: Test des fonctionnalités d'authentification

  Background:
    Given J'ai un serveur

  Scenario: Authentification de l'utilisateur "henripoincare@heigvd.ch"
    Given J'ai l'adresse email "henripoincare@heigvd.ch" et le mot de passe "poincarepwd"
    When Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 401
    And Je reçois une le message "Email ou mot de passe invalide"

  Scenario: Authentification de l'utilisateur "euclide@heigvd.ch"
    Given J'ai l'adresse email "doriane.tedongmokaffo@heig-vd.ch " et le mot de passe "administrator"
    When Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 200
    And Je reçois un token
