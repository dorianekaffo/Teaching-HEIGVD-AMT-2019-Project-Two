Feature: Test des fonctionnalités d'authentification

  Background:
    Given J'ai un utilisateur d'identifiant "henripoincare@heigvd.ch" et mot de passe "poincarepwd"

  Scenario: Authentification de l'utilisateur "euclide@heigvd.ch"
    Given J'ai l'adresse email "euclide@heigvd.ch" et le mot de passe "euclidepwd"
    When Je fais un POST vers le chemin "/auth/login"
    Then Je reçois une réponse de code 403

  Scenario: Authentification de l'utilisateur "henripoincare@heigvd.ch"
    Given J'ai l'adresse email "henripoincare@heigvd.ch" et le mot de passe "poincarepwd"
    When Je fais un POST vers le chemin "/auth/login"
    Then Je reçois une réponse de code 200
    And Un token d'authentification

  Scenario: Déconnexion de l'utilisateur "henripoincare@heigvd.ch
    Given Je suis connecté comme "henripoincare@heigvd.ch"
    When Je fais un POST vers le chemin "/auth/logout"
    Then Je reçois une réponse de code 200
