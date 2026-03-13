# Exemples Postman - candidat-service

## 1) Import Excel
- Method: `POST`
- URL: `http://localhost:8081/api/candidats/import`
- Body: `form-data`
  - key: `file` (type `File`)

## 2) Transformation globale -> métier
- Method: `POST`
- URL: `http://localhost:8081/api/candidats/transform`

## 3) Liste paginée
- Method: `GET`
- URL: `http://localhost:8081/api/candidats?page=0&size=20&sort=nom,asc`

## 4) Détail candidat
- Method: `GET`
- URL: `http://localhost:8081/api/candidats/1`

## 5) Filtrage
- Method: `GET`
- URL: `http://localhost:8081/api/candidats/filter?province=Rabat&categorie=CAT2&sexe=M&page=0&size=20&sort=nom,asc`
