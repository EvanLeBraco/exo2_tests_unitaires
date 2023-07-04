import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


// CA DEV DOCTOLIB
public class GestionRendezVous {
	
	// Utilsisation de MAP pour simplifier la structure des données
    private Map<Integer, Integer> 		patientRdvCount; // Compteur de rendez-vous par patient
    private Map<Integer, Integer> 		medecinRdvCount; // Compteur de rendez-vous par médecin
    private Map<LocalDateTime, Integer> rdvSlots; // Emplacements disponibles pour les rendez-vous



    // Instanciation de médecin
    Medecin medecin1 = new Medecin(1, "Jean Lasalle", 56);
    Medecin medecin2 = new Medecin(2, "Michel Ngondara", 42);
    
    // Instanciation de patient
    Patient patient1 = new Patient(1, "Evan Rougetet");
    Patient patient2 = new Patient(2, "Jonny Jonh");
	
    public GestionRendezVous() {
    	//Instanciation de la classe MAP pour les compteurs de RDV médecin et les RDV dispos
        patientRdvCount = new HashMap<>();
        medecinRdvCount = new HashMap<>();
        rdvSlots 		= new HashMap<>();
    }

    public void ajouterPatient(Patient patient) {
        patientRdvCount.put(patient.getPatientId(), 0);
    }
    
    public void ajouterMedecin(Medecin medecin) {
        medecinRdvCount.put(medecin.getMedecinId(), 0);
    }
    
    //Classe de réservation
    public boolean reserverRendezVous(int patientId, int medecinId, LocalDateTime dateTime) {
        // Vérification de la validité du patient
        if (!isValidPatient(patientId)) {
            return false;
        }

        // Vérification de la validité du médecin
        if (!isValidMedecin(medecinId)) {
            return false;
        }

        // Vérification de la validité du créneau en fonction du MEDECIN
        if (!isMedecinAvailable(medecinId, dateTime)) {
            return false;
        }

        // Vérification de doublon sur les RDV d'un patient
        if (hasPatientConflict(patientId, dateTime)) {
            return false;
        }
        /*
        // Vérification du nombre max de RDV par jour
        if (hasMedecinReachedMaxRdv(medecinId, dateTime.toLocalDate())) {
            return false;
        }
		*/
        // Prise de RDV
        reserveRdvSlot	  (medecinId, dateTime);
        incrementRdvCounts(patientId, medecinId);

        return true;
    }

    // Vérification de l'existance du patient
    private boolean isValidPatient(int patientId) {
        // Vérifier si le patient existe dans la Map patientRdvCount
        if (patientRdvCount.containsKey(patientId)) {
            System.out.println("Le patient existe");
            return true;
        } else {
            System.out.println("Le patient n'existe pas");
            return false;
        }
    }


    private boolean isValidMedecin(int medecinId) {
        // Vérifier si l'ID du médecin est négatif
        if (medecinId < 0) {
            throw new IllegalArgumentException("L'ID du médecin ne peut pas être négatif");
        }

        // Vérifier si le médecin existe dans la Map medecinRdvCount
        if (medecinRdvCount.containsKey(medecinId)) {
            System.out.println("Le médecin existe");
            return true;
        } else {
            System.out.println("Le médecin n'existe pas");
            return false;
        }
    }

    private boolean isMedecinAvailable(int medecinId, LocalDateTime dateTime) {
        // Vérifier si le médecin existe dans la Map medecinRdvCount
        if (medecinRdvCount.containsKey(medecinId)) {
                System.out.println("Le médecin et l'heure choisie sont disponibles");
                return true;
            } else {
                System.out.println("Le médecin a atteint le nombre maximal de rendez-vous");
                return false;
            }
        }




    // Voir si conflit de Patient sur le même heure
    private boolean hasPatientConflict(int patientId, LocalDateTime dateTime) {
        // Parcourir les créneaux horaires dans la Map rdvSlots
        for (LocalDateTime slot : rdvSlots.keySet()) {
            // Vérifier si le créneau horaire est le même que celui choisi
            if (slot.equals(dateTime)) {
                // Vérifier si le patient du créneau horaire est le même que celui donné
                if (rdvSlots.get(slot) == patientId) {
                    System.out.println("Heure deja prise deso");
                    return true; // Conflit 
                }
            }
        }
        System.out.println("OK, pas de RDV à la même heure");
        return false; // Pas de conflit
    }



    
    
    private void reserveRdvSlot(int medecinId, LocalDateTime dateTime) {
        // Vérifier si le médecin existe dans la Map rdvSlots
        if (rdvSlots.containsKey(dateTime)) {
            // Obtenir l'identifiant du médecin déjà réservé pour le créneau
            int medecinIdExistant = rdvSlots.get(dateTime);

            System.out.println("Le créneau est déjà réservé par l'ID : " + medecinIdExistant);
            return; // Sortir de la fonction car le créneau est déjà réservé
        }

        // Réserver le créneau pour le médecin en ajoutant une entrée dans la Map rdvSlots
        rdvSlots.put(dateTime, medecinId);
        System.out.println("Le rendez-vous a été réservé avec succès pour l'ID médecin : " + medecinId);
    }


    // Incrémente de + 1 le nombre de RDV sur le jour
    	private void incrementRdvCounts(int patientId, int medecinId) {
    	    // Incrémente le nombre de rendez-vous pour le patient
    	    int patientRdvCount = this.patientRdvCount.getOrDefault(patientId, 0);
    	    this.patientRdvCount.put(patientId, patientRdvCount + 1);

    	    // Incrémente le nombre de rendez-vous pour le médecin
    	    int medecinRdvCount = this.medecinRdvCount.getOrDefault(medecinId, 0);
    	    this.medecinRdvCount.put(medecinId, medecinRdvCount + 1);
    	}
    
}
