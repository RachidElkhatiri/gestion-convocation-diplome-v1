export interface Candidat {
  id: number;
  cin: string;
  nom: string;
  prenom: string;
  sexe: string;
  province: string;
  centre: string;
  categorie: string;
  dateAttribution: string;
  dateNaissance: string;
  niveauEtude: string;
}

export interface PagedResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
}