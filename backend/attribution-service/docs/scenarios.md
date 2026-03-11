# Scenarios de test rapide

1. Geo indisponible:
- arreter geo-service
- lancer `POST /api/attribution/run`
- attendu: erreur metier claire.

2. Capacite centre = 2 et 5 candidats:
- attendu: 3 journees ouvrees au total.

3. Depart vendredi avec capacite depassee:
- attendu: pas de samedi/dimanche, reprise lundi.

4. Jour ferie configure:
- ajouter une date dans `holidaysOverride`
- attendu: la date est sautee.
