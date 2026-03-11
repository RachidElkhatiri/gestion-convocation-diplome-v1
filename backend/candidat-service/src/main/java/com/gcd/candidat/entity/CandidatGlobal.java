package com.gcd.candidat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "CANDIDATS_GLOBALE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidatGlobal {

    @Id
    @Column(name = "ID_DEMANDE", nullable = false)
    private Long idDemande;

    @Column(name = "ID_CITOYEN", length = 1000)
    private String idCitoyen;

    @Column(name = "EST_MRE", length = 1000)
    private String estMre;

    @Column(name = "EST_VOLONTAIRE", length = 1000)
    private String estVolontaire;

    @Column(name = "ANNEE_RECENSEMENT", length = 1000)
    private String anneeRecensement;

    @Column(name = "NUM_RECENSEMENT", length = 1000)
    private String numRecensement;

    @Column(name = "NUM_IMMATRICULATION", length = 1000)
    private String numImmatriculation;

    @Column(name = "NCIN", length = 1000)
    private String ncin;

    @Column(name = "DATE_VALIDITE", length = 1000)
    private LocalDate dateValidite;

    @Column(name = "NOM_A", length = 1000)
    private String nomA;

    @Column(name = "PRENOM_A", length = 1000)
    private String prenomA;

    @Column(name = "NOM_F", length = 1000)
    private String nomF;

    @Column(name = "PRENOM_F", length = 1000)
    private String prenomF;

    @Column(name = "PRENOM_PERE_FR", length = 1000)
    private String prenomPereFr;

    @Column(name = "PRENOM_PERE_AR", length = 1000)
    private String prenomPereAr;

    @Column(name = "PRENOM_MERE_FR", length = 1000)
    private String prenomMereFr;

    @Column(name = "PRENOM_MERE_AR", length = 1000)
    private String prenomMereAr;

    @Column(name = "C_SEXE", length = 1000)
    private String cSexe;

    @Column(name = "LIBF_SEXE", length = 1000)
    private String libfSexe;

    @Column(name = "LIBA_SEXE", length = 1000)
    private String libaSexe;

    @Column(name = "DATE_NAISS", length = 1000)
    private LocalDate dateNaiss;

    @Column(name = "C_PAYS_NAISS", length = 1000)
    private String cPaysNaiss;

    @Column(name = "LIBF_PAYS_NAISS", length = 1000)
    private String libfPaysNaiss;

    @Column(name = "LIBA_PAYS_NAISS", length = 1000)
    private String libaPaysNaiss;

    @Column(name = "ID_PROVINCE_NAISS", length = 1000)
    private String idProvinceNaiss;

    @Column(name = "LIBF_PROVINCE_NAISS", length = 1000)
    private String libfProvinceNaiss;

    @Column(name = "LIBA_PROVINCE_NAISS", length = 1000)
    private String libaProvinceNaiss;

    @Column(name = "ID_COMMUNE_NAISS", length = 1000)
    private String idCommuneNaiss;

    @Column(name = "LIBF_COMMUNE_NAISS", length = 1000)
    private String libfCommuneNaiss;

    @Column(name = "LIBA_COMMUNE_NAISS", length = 1000)
    private String libaCommuneNaiss;

    @Column(name = "VILLE_ETRANG_NAISS", length = 1000)
    private String villeEtrangNaiss;

    @Column(name = "C_PAYS_RESID", length = 1000)
    private String cPaysResid;

    @Column(name = "LIBF_PAYS_RESID", length = 1000)
    private String libfPaysResid;

    @Column(name = "LIBA_PAYS_RESID", length = 1000)
    private String libaPaysResid;

    @Column(name = "ID_PROVINCE_RESID", length = 1000)
    private String idProvinceResid;

    @Column(name = "LIBA_PROVINCE", length = 1000)
    private String libaProvince;

    @Column(name = "LIBF_PROVINCE", length = 1000)
    private String libfProvince;

    @Column(name = "LIBF_PROVINCE_RESID", length = 1000)
    private String libfProvinceResid;

    @Column(name = "LIBA_PROVINCE_RESID", length = 1000)
    private String libaProvinceResid;

    @Column(name = "ID_COMMUNE_RESID", length = 1000)
    private String idCommuneResid;

    @Column(name = "LIBF_COMMUNE_RESID", length = 1000)
    private String libfCommuneResid;

    @Column(name = "LIBA_COMMUNE_RESID", length = 1000)
    private String libaCommuneResid;

    @Column(name = "ID_ANNEXE_ADM_RESID", length = 1000)
    private String idAnnexeAdmResid;

    @Column(name = "LIBF_ANNEXE_RESID", length = 1000)
    private String libfAnnexeResid;

    @Column(name = "LIBA_ANNEXE_RESID", length = 1000)
    private String libaAnnexeResid;

    @Column(name = "VILLE_ETRANG_RESID", length = 1000)
    private String villeEtrangResid;

    @Column(name = "ADRESSE_RESID", length = 1000)
    private String adresseResid;

    @Column(name = "ADRESSE_ETRANG_RESID", length = 1000)
    private String adresseEtrangResid;

    @Column(name = "N_TEL_FIXE", length = 1000)
    private String nTelFixe;

    @Column(name = "N_TEL_GSM", length = 1000)
    private String nTelGsm;

    @Column(name = "EMAIL", length = 1000)
    private String email;

    @Column(name = "EST_MEME_ADRESSE_PARENTS", length = 1000)
    private String estMemeAdresseParents;

    @Column(name = "C_PAYS_RESID_PARENTS", length = 1000)
    private String cPaysResidParents;

    @Column(name = "LIBF_PAYS_RESID_PARENTS", length = 1000)
    private String libfPaysResidParents;

    @Column(name = "LIBA_PAYS_RESID_PARENTS", length = 1000)
    private String libaPaysResidParents;

    @Column(name = "ID_PROVINCE_RESID_PARENTS", length = 1000)
    private String idProvinceResidParents;

    @Column(name = "LIBF_PROVINCE_RESID_PARENTS", length = 1000)
    private String libfProvinceResidParents;

    @Column(name = "LIBA_PROVINCE_RESID_PARENTS", length = 1000)
    private String libaProvinceResidParents;

    @Column(name = "ID_COMMUNE_RESID_PARENTS", length = 1000)
    private String idCommuneResidParents;

    @Column(name = "LIBF_COMMUNE_RESID_PARENTS", length = 1000)
    private String libfCommuneResidParents;

    @Column(name = "LIBA_COMMUNE_RESID_PARENTS", length = 1000)
    private String libaCommuneResidParents;

    @Column(name = "ID_ANNEXE_ADM_RESID_PARENTS", length = 1000)
    private String idAnnexeAdmResidParents;

    @Column(name = "LIBF_ANNEXE_RESID_PARENTS", length = 1000)
    private String libfAnnexeResidParents;

    @Column(name = "LIBA_ANNEXE_RESID_PARENTS", length = 1000)
    private String libaAnnexeResidParents;

    @Column(name = "VILLE_RESID_PARENTS", length = 1000)
    private String villeResidParents;

    @Column(name = "ADRESSE_RESID_PARENTS", length = 1000)
    private String adresseResidParents;

    @Column(name = "EST_ACTUEL_ACTIVITE", length = 1000)
    private String estActuelActivite;

    @Column(name = "LIBF_EST_ACTUEL_ACTIVITE", length = 1000)
    private String libfEstActuelActivite;

    @Column(name = "LIBA_EST_ACTUEL_ACTIVITE", length = 1000)
    private String libaEstActuelActivite;

    @Column(name = "EST_QUALITE_ACTIVITE", length = 1000)
    private String estQualiteActivite;

    @Column(name = "LIBF_EST_QUALITE_ACTIVITE", length = 1000)
    private String libfEstQualiteActivite;

    @Column(name = "LIBA_EST_QUALITE_ACTIVITE", length = 1000)
    private String libaEstQualiteActivite;

    @Column(name = "EST_ADM_ENTREP", length = 1000)
    private String estAdmEntrep;

    @Column(name = "LIBF_EST_ADM_ENTREP", length = 1000)
    private String libfEstAdmEntrep;

    @Column(name = "LIBA_EST_ADM_ENTREP", length = 1000)
    private String libaEstAdmEntrep;

    @Column(name = "NOM_ADM_ENTREP", length = 1000)
    private String nomAdmEntrep;

    @Column(name = "C_PAYS_ADM_ENTREP", length = 1000)
    private String cPaysAdmEntrep;

    @Column(name = "LIBF_PAYS_ADM_ENTREP", length = 1000)
    private String libfPaysAdmEntrep;

    @Column(name = "LIBA_PAYS_ADM_ENTREP", length = 1000)
    private String libaPaysAdmEntrep;

    @Column(name = "ID_PROVINCE_ADM_ENTREP", length = 1000)
    private String idProvinceAdmEntrep;

    @Column(name = "LIBF_PROVINCE_ADM_ENTREP", length = 1000)
    private String libfProvinceAdmEntrep;

    @Column(name = "LIBA_PROVINCE_ADM_ENTREP", length = 1000)
    private String libaProvinceAdmEntrep;

    @Column(name = "ID_COMMUNE_ADM_ENTREP", length = 1000)
    private String idCommuneAdmEntrep;

    @Column(name = "LIBF_COMMUNE_ADM_ENTREP", length = 1000)
    private String libfCommuneAdmEntrep;

    @Column(name = "LIBA_COMMUNE_ADM_ENTREP", length = 1000)
    private String libaCommuneAdmEntrep;

    @Column(name = "VILLE_ADM_ENTREP", length = 1000)
    private String villeAdmEntrep;

    @Column(name = "FONCTION_ADM_ENTREP", length = 1000)
    private String fonctionAdmEntrep;

    @Column(name = "PROF_LIBERALE", length = 1000)
    private String profLiberale;

    @Column(name = "AUTRE_PROF", length = 1000)
    private String autreProf;

    @Column(name = "L_C_LANGUES_AR", length = 1000)
    private String lCLanguesAr;

    @Column(name = "L_C_DIALECTE", length = 1000)
    private String lCDialecte;

    @Column(name = "L_L_DIALECTE", length = 1000)
    private String lLDialecte;

    @Column(name = "L_C_LANGUES_ETRANG", length = 1000)
    private String lCLanguesEtrang;

    @Column(name = "L_L_LANGUES_ETRANG", length = 1000)
    private String lLLanguesEtrang;

    @Column(name = "EST_AUTRE_LANGUE", length = 1000)
    private String estAutreLangue;

    @Column(name = "AUTRE_LANGUE", length = 1000)
    private String autreLangue;

    @Column(name = "EST_OUTIL_INFORMATIQUE", length = 1000)
    private String estOutilInformatique;

    @Column(name = "EST_TECHNIQUES_BUREAUTIQUES", length = 1000)
    private String estTechniquesBureautiques;

    @Column(name = "AUTRES_QUALIFICATION", length = 1000)
    private String autresQualification;

    @Column(name = "C_STADE_ETUDE", length = 1000)
    private String cStadeEtude;

    @Column(name = "LIBF_STADE_ETUDE", length = 1000)
    private String libfStadeEtude;

    @Column(name = "LIBA_STADE_ETUDE", length = 1000)
    private String libaStadeEtude;

    @Column(name = "EST_LIRE_ECRIRE", length = 1000)
    private String estLireEcrire;

    @Column(name = "C_NIVEAU_INFERIEUR_BAC", length = 1000)
    private String cNiveauInferieurBac;

    @Column(name = "LIBF_NIVEAU_INFERIEUR_BAC", length = 1000)
    private String libfNiveauInferieurBac;

    @Column(name = "LIBA_NIVEAU_INFERIEUR_BAC", length = 1000)
    private String libaNiveauInferieurBac;

    @Column(name = "NIVEAU_INFERIEUR_BAC", length = 1000)
    private String niveauInferieurBac;

    @Column(name = "ANNEE_INFERIEUR_BAC", length = 1000)
    private String anneeInferieurBac;

    @Column(name = "TYPE_BAC_OBTENU", length = 1000)
    private String typeBacObtenu;

    @Column(name = "SERIE_BAC_OBTENU", length = 1000)
    private String serieBacObtenu;

    @Column(name = "ANNEE_BAC_OBTENU", length = 1000)
    private String anneeBacObtenu;

    @Column(name = "EST_CYCLES_UNIVERSITAIRES", length = 1000)
    private String estCyclesUniversitaires;

    @Column(name = "LIBF_EST_CYCLES_UNIVERSITAIRES", length = 1000)
    private String libfEstCyclesUniversitaires;

    @Column(name = "LIBA_EST_CYCLES_UNIVERSITAIRES", length = 1000)
    private String libaEstCyclesUniversitaires;

    @Column(name = "DIPLOME_UNIVERSITAIRE", length = 1000)
    private String diplomeUniversitaire;

    @Column(name = "SERIE_UNIVERSITAIRE", length = 1000)
    private String serieUniversitaire;

    @Column(name = "ANNEE_UNIVERSITAIRE", length = 1000)
    private String anneeUniversitaire;

    @Column(name = "NOM_FACULTE", length = 1000)
    private String nomFaculte;

    @Column(name = "ID_PROVINCE_FACULTE", length = 1000)
    private String idProvinceFaculte;

    @Column(name = "LIBF_PROVINCE_FACULTE", length = 1000)
    private String libfProvinceFaculte;

    @Column(name = "LIBA_PROVINCE_FACULTE", length = 1000)
    private String libaProvinceFaculte;

    @Column(name = "EST_TYPE_ECOLE_SUPERIEURE", length = 1000)
    private String estTypeEcoleSuperieure;

    @Column(name = "LIBF_EST_TYPE_ECOLE_SUPERIEURE", length = 1000)
    private String libfEstTypeEcoleSuperieure;

    @Column(name = "LIBA_EST_TYPE_ECOLE_SUPERIEURE", length = 1000)
    private String libaEstTypeEcoleSuperieure;

    @Column(name = "NOM_ECOLE_SUPERIEURE", length = 1000)
    private String nomEcoleSuperieure;

    @Column(name = "ID_PROVINCE_ECOLE_SUPERIEURE", length = 1000)
    private String idProvinceEcoleSuperieure;

    @Column(name = "LIBF_PROVINCE_ECOLE_SUPERIEURE", length = 1000)
    private String libfProvinceEcoleSuperieure;

    @Column(name = "LIBA_PROVINCE_ECOLE_SUPERIEURE", length = 1000)
    private String libaProvinceEcoleSuperieure;

    @Column(name = "DIPLOME_ECOLE_SUPERIEURE", length = 1000)
    private String diplomeEcoleSuperieure;

    @Column(name = "SPECIALITE_ECOLE_SUPERIEURE", length = 1000)
    private String specialiteEcoleSuperieure;

    @Column(name = "ANNEE_ECOLE_SUPERIEURE", length = 1000)
    private String anneeEcoleSuperieure;

    @Column(name = "EST_FPROF", length = 1000)
    private String estFprof;

    @Column(name = "EST_OFPPT_PRIVE", length = 1000)
    private String estOfpptPrive;

    @Column(name = "NOM_ETAB_FPROF", length = 1000)
    private String nomEtabFprof;

    @Column(name = "C_PAYS_ETAB_FPROF", length = 1000)
    private String cPaysEtabFprof;

    @Column(name = "LIBF_PAYS_ETAB_FPROF", length = 1000)
    private String libfPaysEtabFprof;

    @Column(name = "LIBA_PAYS_ETAB_FPROF", length = 1000)
    private String libaPaysEtabFprof;

    @Column(name = "ID_PROVINCES_ETAB_FPROF", length = 1000)
    private String idProvincesEtabFprof;

    @Column(name = "LIBF_PROVINCE_ETAB_FPROF", length = 1000)
    private String libfProvinceEtabFprof;

    @Column(name = "LIBA_PROVINCE_ETAB_FPROF", length = 1000)
    private String libaProvinceEtabFprof;

    @Column(name = "VILLE_ETAB_FPROF", length = 1000)
    private String villeEtabFprof;

    @Column(name = "C_DIPLOME_FPROF", length = 1000)
    private String cDiplomeFprof;

    @Column(name = "LIBF_DIPLOME_FPROF", length = 1000)
    private String libfDiplomeFprof;

    @Column(name = "LIBA_DIPLOME_FPROF", length = 1000)
    private String libaDiplomeFprof;

    @Column(name = "SERIE_FPROF", length = 1000)
    private String serieFprof;

    @Column(name = "ANNEE_FPROF", length = 1000)
    private String anneeFprof;

    @Column(name = "AUTRE_FPROF", length = 1000)
    private String autreFprof;

    @Column(name = "EST_ETAB_CLASSIQUE", length = 1000)
    private String estEtabClassique;

    @Column(name = "NOM_ETAB_CLASSIQUE", length = 1000)
    private String nomEtabClassique;

    @Column(name = "ID_PROVINCES_ETAB_CLASSIQUE", length = 1000)
    private String idProvincesEtabClassique;

    @Column(name = "LIBF_PROVINCE_ETAB_CLASSIQUE", length = 1000)
    private String libfProvinceEtabClassique;

    @Column(name = "LIBA_PROVINCE_ETAB_CLASSIQUE", length = 1000)
    private String libaProvinceEtabClassique;

    @Column(name = "CERTIFICAT_NIVEAU_FCLASSIQUE", length = 1000)
    private String certificatNiveauFclassique;

    @Column(name = "SERIE_FCLASSIQUE", length = 1000)
    private String serieFclassique;

    @Column(name = "ANNEE_FCLASSIQUE", length = 1000)
    private String anneeFclassique;

    @Column(name = "AUTRE_FCLASSIQUE", length = 1000)
    private String autreFclassique;

    @Column(name = "C_ETAT_MATRIMONIALE", length = 1000)
    private String cEtatMatrimoniale;

    @Column(name = "LIBF_ETAT_MATRIMONIALE", length = 1000)
    private String libfEtatMatrimoniale;

    @Column(name = "LIBA_ETAT_MATRIMONIALE", length = 1000)
    private String libaEtatMatrimoniale;

    @Column(name = "NOMPRENOM_CONJOINT", length = 1000)
    private String nomprenomConjoint;

    @Column(name = "ANNEE_MARIAGE", length = 1000)
    private String anneeMariage;

    @Column(name = "NBRE_ENFANTS", length = 1000)
    private String nbreEnfants;

    @Column(name = "EST_FRERE_SOEUR_APPELE", length = 1000)
    private String estFrereSoeurAppele;

    @Column(name = "ANNEE_APPEL", length = 1000)
    private String anneeAppel;

    @Column(name = "EST_FRERE_SOEUR_APPELE_MEMETEMP", length = 1000)
    private String estFrereSoeurAppeleMemetemp;

    @Column(name = "EST_PERMIS_CONDUIRE", length = 1000)
    private String estPermisConduire;

    @Column(name = "L_C_TYPES_PERMIS", length = 1000)
    private String lCTypesPermis;

    @Column(name = "L_L_TYPES_PERMIS", length = 1000)
    private String lLTypesPermis;

    @Column(name = "EST_BREVET_PILOTE_AVION", length = 1000)
    private String estBrevetPiloteAvion;

    @Column(name = "EST_NATATION", length = 1000)
    private String estNatation;

    @Column(name = "EST_ART_MARTIAL_COMBAT", length = 1000)
    private String estArtMartialCombat;

    @Column(name = "TYPE_ART_MARTIAL_COMBAT", length = 1000)
    private String typeArtMartialCombat;

    @Column(name = "BREVET_ART_MARTIAL_COMBAT", length = 1000)
    private String brevetArtMartialCombat;

    @Column(name = "AUTRE_SPORT", length = 1000)
    private String autreSport;

    @Column(name = "DATE_VALIDE", length = 1000)
    private LocalDate dateValide;

    @Column(name = "LIBA_PAYS_NAT", length = 1000)
    private String libaPaysNat;

    @Column(name = "C_NATIONALITE_ETRANGERE", length = 1000)
    private String cNationaliteEtrangere;

    @Column(name = "LIBF_PAYS_NAT", length = 1000)
    private String libfPaysNat;

    @Column(name = "C_PAYS_FACULTE", length = 1000)
    private String cPaysFaculte;

    @Column(name = "VILLE_FACULTE", length = 1000)
    private String villeFaculte;

    @Column(name = "LIBF_PAYS_FACULTE", length = 1000)
    private String libfPaysFaculte;

    @Column(name = "LIBA_PAYS_FACULTE", length = 1000)
    private String libaPaysFaculte;

    @Column(name = "C_PAYS_ECOLE_SUPERIEURE", length = 1000)
    private String cPaysEcoleSuperieure;

    @Column(name = "LIBF_PAYS_ECOLE_SUP", length = 1000)
    private String libfPaysEcoleSup;

    @Column(name = "LIBA_PAYS_ECOLE_SUP", length = 1000)
    private String libaPaysEcoleSup;

    @Column(name = "VILLE_ECOLE_SUPERIEURE", length = 1000)
    private String villeEcoleSuperieure;

    @Column(name = "C_TYPE_IDENTIFIANT_CONJOINT", length = 1000)
    private String cTypeIdentifiantConjoint;

    @Column(name = "N_IDENTIFIANT_CONJOINT", length = 1000)
    private String nIdentifiantConjoint;

    @Column(name = "EST_SAISIE_ARABE", length = 1000)
    private String estSaisieArabe;

    @Column(name = "PAYS_RESIDENCE_DGSN", length = 1000)
    private String paysResidenceDgsn;

    @Column(name = "PROVINCE_RESIDENCE_DGSN", length = 1000)
    private String provinceResidenceDgsn;

    @Column(name = "COMMUNE_RESIDENCE_DGSN", length = 1000)
    private String communeResidenceDgsn;

    @Column(name = "ADRESSE_DGSN", length = 1000)
    private String adresseDgsn;

    @Column(name = "CLASSEMENT", length = 1000)
    private Integer classement;

    @Column(name = "AGE", length = 1000)
    private Integer age;

}

