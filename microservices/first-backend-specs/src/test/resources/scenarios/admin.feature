Feature: Test des fonctionnalités exclusives à l'administrateur

  Background:
    Given J'ai l'adresse email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And J'obtiens un token d'authentification

  Scenario: Création de l'utilisateur "renedescartes@heigvd.ch"
    Given Je veux créer un utilisateur d'identifiant "renedescartes@heig-vd.ch", de mot de passe "descartespwd", de prénom "René" et de nom "Descartes"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 200
    And Je fais un GET vers le chemin /users?page=0&size=25
    Then L'utilisateur est présent

  Scenario: Bloquer l'utilisateur d'identifiant "renedescartes@heig-vd.ch"
    Given  J'ai un utilisateur d'identifiant "renedescartes@heig-vd.ch"
    When Je fais un PUT vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 200

  Scenario: Débloquer l'utilisateur d'identifiant "renedescartes@heigvd.ch"
    Given J'ai un utilisateur d'identifiant "renedescartes@heig-vd.ch"
    When Je fais un DELETE vers le chemin /users/block/ avec email
    Then Je reçois une réponse de code 200




