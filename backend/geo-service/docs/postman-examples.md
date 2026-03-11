# Exemples Postman - geo-service

## Centres

### POST /api/centres
```json
{
  "nom": "Centre Fes",
  "capaciteJournaliere": 140,
  "adresse": "Bd Hassan II",
  "ville": "Fes",
  "actif": true
}
```

### GET /api/centres
`GET http://localhost:8082/api/centres`

### GET /api/centres/{id}
`GET http://localhost:8082/api/centres/1`

### PUT /api/centres/{id}
```json
{
  "nom": "Centre Rabat MAJ",
  "capaciteJournaliere": 150,
  "adresse": "Nouvelle adresse",
  "ville": "Rabat",
  "actif": true
}
```

### DELETE /api/centres/{id}
`DELETE http://localhost:8082/api/centres/2`

### GET /api/centres/by-province/{provinceId}
`GET http://localhost:8082/api/centres/by-province/1`

## Provinces

### POST /api/provinces
```json
{
  "nom": "Temara",
  "code": "TEM",
  "centreId": 1,
  "actif": true
}
```

### GET /api/provinces
`GET http://localhost:8082/api/provinces`

### GET /api/provinces/{id}
`GET http://localhost:8082/api/provinces/1`

### PUT /api/provinces/{id}
```json
{
  "nom": "Temara MAJ",
  "code": "TEM2",
  "centreId": 1,
  "actif": true
}
```

### DELETE /api/provinces/{id}
`DELETE http://localhost:8082/api/provinces/3`

### GET /api/provinces/by-centre/{centreId}
`GET http://localhost:8082/api/provinces/by-centre/1`

## Endpoint inter-services (compatibilité candidat-service)

### GET /api/geo/centres/by-province/{provinceName}
`GET http://localhost:8082/api/geo/centres/by-province/Rabat`
