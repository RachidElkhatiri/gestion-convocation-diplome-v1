export interface AttributionRunRequest {
  startDate?: string;
  categorie?: string;
  processByCategory?: boolean;
  resetResults?: boolean;
}

export interface AttributionRunResponse {
  runId: string;
  totalCandidats: number;
  totalLignesResultat: number;
  mode: string;
}

export interface AttributionResult {
  id: number;
  runId: string;
  centreNom: string;
  provinceNom: string;
  dateConvocation: string;
  nombreCandidats: number;
  categorie: string;
  statut: string;
}