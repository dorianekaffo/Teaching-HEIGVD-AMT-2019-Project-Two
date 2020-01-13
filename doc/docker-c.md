## Configuration Docker
Pour ce projet nous avons 5 conteneurs docker:
- Deux conteneurs MySQL 5.7.1. Chaque conteneur contient la base de données pour un serveur.
- Deux conteneurs contenant chacun un des backends que nous avons développé.
- Un conteneur contenant Traefik, un reverse-proxy qui redirige le trafic vers le serveur correspondant selon les règles que nous avons définies.

## Configuration du reverse proxy
Traefik est mis en avant dans le réseau, et redirige les requêtes vers les backends correspondant. Nous l'avons coniguré comme suit:
- Toutes les routes préfixées par "users" ou "auth" sont redirigé vers le premier backend.
- Toutes les routes préfixées par "students", "enrollments" ou "courses" sont redirigé vers le deuxième backend.

Pour la configuration complète de l'architecture docker vous pouvez consulter le fichier docker compose.