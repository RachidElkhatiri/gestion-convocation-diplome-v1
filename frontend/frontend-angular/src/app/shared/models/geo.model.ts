export interface Centre {
  id: number;
  nom: string;
  capaciteJournaliere: number;
  adresse?: string;
  ville?: string;
  actif?: boolean;
}

export interface Province {
  id: number;
  nom: string;
  code?: string;
  actif?: boolean;
  centreId: number;
  centreNom?: string;
  centreCapaciteJournaliere?: number;
}