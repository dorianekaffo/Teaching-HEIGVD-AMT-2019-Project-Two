Feature: Test des fonctionnalités exclusives à l'administrateur

  Background:
    Given Je suis connecté comme admin

  Scenario: Création de l'utilisateur "renedescartes@heigvd.ch"
    Given Je veux créer un utilisateur d'identifiant "renedescartes@heigvd.ch" et mot de passe "descartespwd"
    When Je fais un POST vers le chemin "/users"
    Then Je reçois une réponse de code 200

  Scenario: Bloquer l'utilisateur d'identifiant "renedescartes@heigvd.ch"
    Given  J'ai un utilisateur d'identifiant "renedescartes@heigvd.ch"
    When Je fais un PUT vers le chemin "/users/block/renedescartes@heigvd.ch"
    Then Je reçois une réponse de code 200

  Scenario: Débloquer l'utilisateur d'identifiant "renedescartes@heigvd.ch"
    Given J'ai un utilisateur d'identifiant "renedescartes@heigvd.ch"
    When Je fais un DELETE vers le chemin "/users/block/renedescartes@heigvd.ch"
    Then Je reçois une réponse de code 200




