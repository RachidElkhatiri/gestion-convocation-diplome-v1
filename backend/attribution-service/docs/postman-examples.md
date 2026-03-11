# Exemples Postman - attribution-service

## Lancer une attribution globale
- `POST http://localhost:8083/api/attribution/run`
```json
{
  "startDate": "2026-03-16",
  "processByCategory": false,
  "resetResults": true
}
```

## Lancer une attribution par categorie
- `POST http://localhost:8083/api/attribution/run`
```json
{
  "startDate": "2026-03-16",
  "processByCategory": true,
  "holidaysOverride": ["2026-03-18"]
}
```

## Lancer uniquement CAT2
- `POST http://localhost:8083/api/attribution/run`
```json
{
  "startDate": "2026-03-16",
  "categorie": "CAT2"
}
```

## Consulter resultats pagines
- `GET http://localhost:8083/api/attribution/results?page=0&size=20&sort=dateConvocation,asc`

## Consulter resultats d'un run precis
- `GET http://localhost:8083/api/attribution/results?runId=<RUN_ID>&page=0&size=20`

## Detail d'un resultat
- `GET http://localhost:8083/api/attribution/results/1`
