import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class ReservationTest {
	
    @Test
    public void testReservationValide() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

        // Jeu de donn�es
        int patientId = 1; // Identifiant du patient existant
        int medecinId = 1; // Identifiant du m�decin existant
        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles
        
        // Cr�ation d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient � la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        
        // Cr�ation d'un m�decin
        Medecin medecin = new Medecin(medecinId, "Jean Lasalle", 10);

        // Ajouter le m�decin � la liste des m�decins
        gestionRendezVous.ajouterMedecin(medecin);

        // Cr�ation d'un bool pour v�rifier la disponibilit� du RDV en fonction de la date du patient et du m�decin
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime);
        
        // Utilisation d'assert pour v�rifier le succes de la reservation
        assertTrue(reservationSuccess);
    }
    
    @Test
    public void testReservationPatientInvalide() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();
        int patientId = -20; // Identifiant de patient invalide
        int medecinId = 1; // Identifiant du m�decin existant

        // Jeu de donn�es avec un identifiant de patient invalide
        // Cr�ation d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient � la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        
        // Cr�ation d'un m�decin
        Medecin medecin = new Medecin(medecinId, "Jean Lasalle", 10);

        // Ajouter le m�decin � la liste des m�decins
        gestionRendezVous.ajouterMedecin(medecin);

        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles

        // Tentative de r�servation avec un identifiant de patient invalide
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime);

        // V�rification que la r�servation a �chou�e
        assertFalse(reservationSuccess);
    }
    
    
    @Test
    public void testReservationMedecinInvalide() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

        int patientId = 1; // Identifiant de patient invalide

        // Jeu de donn�es avec un identifiant de patient invalide
        // Cr�ation d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient � la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        

        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles

        // Tentative de r�servation avec un identifiant de m�decin invalide
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, 15, dateTime);

        // V�rification que la r�servation a �chou�e
        assertFalse(reservationSuccess);
    }
    
    
    @Test
    public void testReservationDateIndisponible() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

     // Jeu de donn�es
        int patientId = 1; // Identifiant du patient existant
        int medecinId = 1; // Identifiant du m�decin existant
        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles
        
        // Cr�ation d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient � la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        
        // Cr�ation d'un m�decin
        Medecin medecin = new Medecin(medecinId, "Jean Lasalle", 10);

        // Ajouter le m�decin � la liste des m�decins
        gestionRendezVous.ajouterMedecin(medecin);
        
        // Jeu de donn�es avec une date et heure non disponibles pour le m�decin
        LocalDateTime dateTime1 = LocalDateTime.of(2023, 7, 4, 9, 0); // Date et heure non disponibles pour le m�decin

        // Tentative de r�servation � une date et heure non disponibles pour le m�decin
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime1);

        // V�rification que la r�servation a �chou�e
        assertFalse(reservationSuccess);
    }
    
    @Test
    public void testReservationPatientOccupe() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

        // Jeu de donn�es
        int patientId = 1; // Identifiant du patient existant
        int medecinId = 1; // Identifiant du m�decin existant
        LocalDateTime dateTime = LocalDateTime.now(); // Date et heure actuelles
        
        // Cr�ation d'un patient
        Patient patient = new Patient(patientId, "Evan Rougetet");
        

        // Ajouter le patient � la liste des patients
        gestionRendezVous.ajouterPatient(patient);

        
        // Cr�ation d'un m�decin
        Medecin medecin = new Medecin(medecinId, "Jean Lasalle", 10);

        // Ajouter le m�decin � la liste des m�decins
        gestionRendezVous.ajouterMedecin(medecin);
        LocalDateTime dateTime1 = LocalDateTime.of(2023, 7, 4, 10, 0); // Date et heure d�j� occup�es par le patient

        // Tentative de r�servation avec un patient ayant d�j� un rendez-vous � la m�me date et heure
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime1);

        // V�rification que la r�servation a �chou�e
        assertFalse(reservationSuccess);
    }
    
    @Test
    public void testReservationMedecinOccupe() {
        // Instanciation de la classe GRV 
        GestionRendezVous gestionRendezVous = new GestionRendezVous();

        // Jeu de donn�es avec un m�decin ayant d�j� 4 rendez-vous � la m�me date
        int patientId = 1; // Identifiant du patient existant
        int medecinId = 2; // Identifiant du m�decin existant
        LocalDateTime dateTime = LocalDateTime.of(2023, 7, 4, 11, 0); // Date et heure avec 4 rendez-vous pour le m�decin

        // Tentative de r�servation avec un m�decin ayant d�j� 4 rendez-vous � la m�me date
        boolean reservationSuccess = gestionRendezVous.reserverRendezVous(patientId, medecinId, dateTime);

        // V�rification que la r�servation a �chou�e
        assertFalse(reservationSuccess);
    }
    
    

}