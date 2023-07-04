import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class ReservationTest {
	
    @Test
    public void testReservationValide() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

        // Jeu de données
        int patientId = 1; // Identifiant du patient existant
        int medecinId = 1; // Identifiant du médecin existant
        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles
        
        // Création d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient à la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        
        // Création d'un médecin
        Medecin medecin = new Medecin(medecinId, "Jean Lasalle", 10);

        // Ajouter le médecin à la liste des médecins
        gestionRendezVous.ajouterMedecin(medecin);

        // Création d'un bool pour vérifier la disponibilité du RDV en fonction de la date du patient et du médecin
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime);
        
        // Utilisation d'assert pour vérifier le succes de la reservation
        assertTrue(reservationSuccess);
    }
    
    @Test
    public void testReservationPatientInvalide() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();
        int patientId = -20; // Identifiant de patient invalide
        int medecinId = 1; // Identifiant du médecin existant

        // Jeu de données avec un identifiant de patient invalide
        // Création d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient à la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        
        // Création d'un médecin
        Medecin medecin = new Medecin(medecinId, "Jean Lasalle", 10);

        // Ajouter le médecin à la liste des médecins
        gestionRendezVous.ajouterMedecin(medecin);

        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles

        // Tentative de réservation avec un identifiant de patient invalide
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime);

        // Vérification que la réservation a échouée
        assertFalse(reservationSuccess);
    }
    
    
    @Test
    public void testReservationMedecinInvalide() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

        int patientId = 1; // Identifiant de patient invalide

        // Jeu de données avec un identifiant de patient invalide
        // Création d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient à la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        

        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles

        // Tentative de réservation avec un identifiant de médecin invalide
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, 15, dateTime);

        // Vérification que la réservation a échouée
        assertFalse(reservationSuccess);
    }
    
    
    @Test
    public void testReservationDateIndisponible() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

     // Jeu de données
        int patientId = 1; // Identifiant du patient existant
        int medecinId = 1; // Identifiant du médecin existant
        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles
        
        // Création d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient à la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        
        // Création d'un médecin
        Medecin medecin = new Medecin(medecinId, "Jean Lasalle", 10);

        // Ajouter le médecin à la liste des médecins
        gestionRendezVous.ajouterMedecin(medecin);
        
        // Jeu de données avec une date et heure non disponibles pour le médecin
        LocalDateTime dateTime1 = LocalDateTime.of(2023, 7, 4, 9, 0); // Date et heure non disponibles pour le médecin

        // Tentative de réservation à une date et heure non disponibles pour le médecin
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime1);

        // Vérification que la réservation a échouée
        assertFalse(reservationSuccess);
    }
    
    @Test
    public void testReservationPatientOccupe() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

        // Jeu de données
        int patientId = 1; // Identifiant du patient existant
        int medecinId = 1; // Identifiant du médecin existant
        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles
        
        // Création d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient à la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        
        // Création d'un médecin
        Medecin medecin = new Medecin(medecinId, "Jean Lasalle", 10);

        // Ajouter le médecin à la liste des médecins
        gestionRendezVous.ajouterMedecin(medecin);
        LocalDateTime dateTime1 = LocalDateTime.of(2023, 7, 4, 10, 0); // Date et heure déjà occupées par le patient

        // Tentative de réservation avec un patient ayant déjà un rendez-vous à la même date et heure
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime1);

        // Vérification que la réservation a échouée
        assertFalse(reservationSuccess);
    }
    
    @Test
    public void testReservationMedecinOccupe() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

        // Jeu de données avec un médecin ayant déjà 4 rendez-vous à la même date
        int patientId = 1; // Identifiant du patient existant
        int medecinId = 2; // Identifiant du médecin existant
        LocalDateTime dateTime = LocalDateTime.of(2023, 7, 4, 11, 0); // Date et heure avec 4 rendez-vous pour le médecin

        // Tentative de réservation avec un médecin ayant déjà 4 rendez-vous à la même date
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime);

        // Vérification que la réservation a échouée
        assertFalse(reservationSuccess);
    }
    
    

}