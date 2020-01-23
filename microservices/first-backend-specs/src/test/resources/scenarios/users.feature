Feature: Test sur les fonctionnalités d'un utilisateur non-admin

  Background:
    Given J'ai un serveur
    And J'ai l'adresse email "doriane.tedongmokaffo@heig-vd.ch" et le mot de passe "administrator"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token

  Scenario: Changement de mot de passe
    Given Je veux créer un utilisateur d'identifiant "arthurcayley@heig-vd.ch", de mot de passe "arthurcayley", de prénom "Arthur", de nom "Cayley" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    And J'ai l'adresse email "arthurcayley@heig-vd.ch" et le mot de passe "arthurcayley"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    And Je veux changer mon mot de passe "arthurcayley" en "cayleypwd"
    And Je fais un PUT vers le chemin /users/password
    And Je reçois une réponse de code 200
    And J'ai l'adresse email "arthurcayley@heig-vd.ch" et le mot de passe "cayleypwd"
    And Je fais un POST vers le chemin /auth/login
    Then Je reçois une réponse de code 200
    And Je reçois un token

  Scenario: Changement de mot de passe ne connaissant pas l'ancien mot de passe
    Given Je veux créer un utilisateur d'identifiant "richarddedekind@heig-vd.ch", de mot de passe "richarddedekind", de prénom "Richard", de nom "Dedekind" et de role "NORMAL"
    When Je fais un POST vers le chemin /users
    And Je reçois une réponse de code 201
    And J'ai l'adresse email "richarddedekind@heig-vd.ch" et le mot de passe "richarddedekind"
    And Je fais un POST vers le chemin /auth/login
    And Je reçois une réponse de code 200
    And Je reçois un token
    And Je veux changer mon mot de passe "dedekindincorrectpwd" en "dedekindpwd"
    And Je fais un PUT vers le chemin /users/password
    And Je reçois une réponse de code 400
