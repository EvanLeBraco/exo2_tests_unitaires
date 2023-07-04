import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


// CA DEV DOCTOLIB
public class GestionRendezVous {
	
	// Utilsisation de MAP pour simplifier la structure des donn�es
    private Map<Integer, Integer> 		patientRdvCount; // Compteur de rendez-vous par patient
    private Map<Integer, Integer> 		medecinRdvCount; // Compteur de rendez-vous par m�decin
    private Map<LocalDateTime, Integer> rdvSlots; // Emplacements disponibles pour les rendez-vous



    // Instanciation de m�decin
    Medecin medecin1 = new Medecin(1, "Jean Lasalle", 56);
    Medecin medecin2 = new Medecin(2, "Michel Ngondara", 42);
    
    // Instanciation de patient
    Patient patient1 = new Patient(1, "Evan Rougetet");
    Patient patient2 = new Patient(2, "Jonny Jonh");
	
    public GestionRendezVous() {
    	//Instanciation de la classe MAP pour les compteurs de RDV m�decin et les RDV dispos
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
    
    //Classe de r�servation
    public boolean reserverRendezVous(int patientId, int medecinId, LocalDateTime dateTime) {
        // V�rification de la validit� du patient
        if (!isValidPatient(patientId)) {
            return false;
        }

        // V�rification de la validit� du m�decin
        if (!isValidMedecin(medecinId)) {
            return false;
        }

        // V�rification de la validit� du cr�neau en fonction du MEDECIN
        if (!isMedecinAvailable(medecinId, dateTime)) {
            return false;
        }

        // V�rification de doublon sur les RDV d'un patient
        if (hasPatientConflict(patientId, dateTime)) {
            return false;
        }
        /*
        // V�rification du nombre max de RDV par jour
        if (hasMedecinReachedMaxRdv(medecinId, dateTime.toLocalDate())) {
            return false;
        }
		*/
        // Prise de RDV
        reserveRdvSlot	  (medecinId, dateTime);
        incrementRdvCounts(patientId, medecinId);

        return true;
    }

    // V�rification de l'existance du patient
    private boolean isValidPatient(int patientId) {
        // V�rifier si le patient existe dans la Map patientRdvCount
        if (patientRdvCount.containsKey(patientId)) {
            System.out.println("Le patient existe");
            return true;
        } else {
            System.out.println("Le patient n'existe pas");
            return false;
        }
    }


    private boolean isValidMedecin(int medecinId) {
        // V�rifier si l'ID du m�decin est n�gatif
        if (medecinId < 0) {
            throw new IllegalArgumentException("L'ID du m�decin ne peut pas �tre n�gatif");
        }

        // V�rifier si le m�decin existe dans la Map medecinRdvCount
        if (medecinRdvCount.containsKey(medecinId)) {
            System.out.println("Le m�decin existe");
            return true;
        } else {
            System.out.println("Le m�decin n'existe pas");
            return false;
        }
    }

    private boolean isMedecinAvailable(int medecinId, LocalDateTime dateTime) {
        // V�rifier si le m�decin existe dans la Map medecinRdvCount
        if (medecinRdvCount.containsKey(medecinId)) {
                System.out.println("Le m�decin et l'heure choisie sont disponibles");
                return true;
            } else {
                System.out.println("Le m�decin a atteint le nombre maximal de rendez-vous");
                return false;
            }
        }




    // Voir si conflit de Patient sur le m�me heure
    private boolean hasPatientConflict(int patientId, LocalDateTime dateTime) {
        // Parcourir les cr�neaux horaires dans la Map rdvSlots
        for (LocalDateTime slot : rdvSlots.keySet()) {
            // V�rifier si le cr�neau horaire est le m�me que celui choisi
            if (slot.equals(dateTime)) {
                // V�rifier si le patient du cr�neau horaire est le m�me que celui donn�
                if (rdvSlots.get(slot) == patientId) {
                    System.out.println("Heure deja prise deso");
                    return true; // Conflit 
                }
            }
        }
        System.out.println("OK, pas de RDV � la m�me heure");
        return false; // Pas de conflit
    }



    
    
    private void reserveRdvSlot(int medecinId, LocalDateTime dateTime) {
        // V�rifier si le m�decin existe dans la Map rdvSlots
        if (rdvSlots.containsKey(dateTime)) {
            // Obtenir l'identifiant du m�decin d�j� r�serv� pour le cr�neau
            int medecinIdExistant = rdvSlots.get(dateTime);

            System.out.println("Le cr�neau est d�j� r�serv� par l'ID : " + medecinIdExistant);
            return; // Sortir de la fonction car le cr�neau est d�j� r�serv�
        }

        // R�server le cr�neau pour le m�decin en ajoutant une entr�e dans la Map rdvSlots
        rdvSlots.put(dateTime, medecinId);
        System.out.println("Le rendez-vous a �t� r�serv� avec succ�s pour l'ID m�decin : " + medecinId);
    }


    // Incr�mente de + 1 le nombre de RDV sur le jour
    	private void incrementRdvCounts(int patientId, int medecinId) {
    	    // Incr�mente le nombre de rendez-vous pour le patient
    	    int patientRdvCount = this.patientRdvCount.getOrDefault(patientId, 0);
    	    this.patientRdvCount.put(patientId, patientRdvCount + 1);

    	    // Incr�mente le nombre de rendez-vous pour le m�decin
    	    int medecinRdvCount = this.medecinRdvCount.getOrDefault(medecinId, 0);
    	    this.medecinRdvCount.put(medecinId, medecinRdvCount + 1);
    	}
    
}
