Feature: Test sur les fonctionnalités d'un utilisateur non-admin

  Background:


  Scenario: Changement de mot de passe
    Given J'ai un serveur
    And J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "renedescartes"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    And Je veux changer mon mot de passe "renedescartes" en "descartespwd"
    And Je fais un PUT vers le chemin /users/password
    And Je reçois une réponse de code 200
    And J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "descartespwd"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 200
    And Je reçois un token

  Scenario: Blocage de la modification d'un mot de passe par un utilisateur étranger
    Given J'ai un serveur
    And J'ai l'adresse email "renedescartes@heig-vd.ch" et le mot de passe "descartespwd"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    And J'ai un utilisateur d'identifiant "doriane.tedongmokaffo@heig-vd.ch"
    And Je veux changer son mot de passe en "dorianepwd"
    When Je fais un PUT vers le chemin /users/password
    Then Je reçois une réponse de code 403
