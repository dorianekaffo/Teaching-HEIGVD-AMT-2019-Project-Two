Feature: Test sur les fonctionnalités d'un utilisateur non-admin

  Background:
    Given Je suis connecté
    And J'ai un utilisateur d'identifiant "bernhardriemann@heigvd.ch" dont je suis le propriétaire
    And J'ai un utilisateur d'identifiant "davidhilbert@heigvd.ch" dont je ne suis pas le propriétaire

  Scenario: Récupèration de tous les utilisateurs
    When Je fais un GET vers le chemin "/users?page=1&sort=firstname,asc"
    Then Je reçois une réponse de code 200
    And L'objet sous forme paginée des utilisateurs

  Scenario: Récupération de l'utilisateur "bernhardriemann@heigvd.ch"
    Given J'ai l'identifiant d'un utilisateur "bernhardriemann@heigvd.ch"
    When Je fais un GET vers le chemin "/users/bernhardriemann@heigvd.ch"
    Then Je reçois une réponse de code 200

  Scenario: Récupération de l'utilisateur "davidhilbert@heigvd.ch"
    Given J'ai l'identifiant d'un utilisateur "davidhilbert@heigvd.ch"
    When Je fais un GET vers le chemin "/users/davidhilbert@heigvd.ch"
    Then Je reçois une réponse de code 401

  Scenario: Mise à jour de l'utilisateur "bernhardriemann@heigvd.ch"
    Given J'ai un utilisateur d'identifiant "bernhardriemann@heigvd.ch"
    When Je fais un PUT vers le chemin "/users/bernhardriemann@heigvd.ch"
    Then Je reçois une réponse de code 200

  Scenario: Mise à jour de l'utilisateur "davidhilbert@heigvd.ch"
    Given J'ai un utilisateur d'identifiant "davidhilbert@heigvd.ch"
    When Je fais un PUT vers le chemin "/users/davidhilbert@heigvd.ch"
    Then Je reçois une réponse de code 401

  Scenario: Suppression de l'utilisateur "bernhardriemann@heigvd.ch"
    Given J'ai l'identifiant "bernhardriemann@heigvd.ch"
    When Je fais un DELETE vers le chemin "/users/bernhardriemann@heigvd.ch"
    Then Je reçois une réponse de code 200

  Scenario: Suppression de l'utilisateur "davidhilbert@heigvd.ch"
    Given J'ai l'identifiant "davidhilbert@heigvd.ch"
    When Je fais un DELETE vers le chemin "/users/davidhilbert@heigvd.ch"
    Then Je reçois une réponse de code 401

  Scenario: Changement de mot de passe de l'utilisateur "pythagore@heigvd.ch"
    Given Je veux changer mon mot de passe "pythagorepwd" en "pythagorenewpwd"
    When Je fais un PUT vers le chemin "/users/password"
    Then Je recois une réponse de code 200