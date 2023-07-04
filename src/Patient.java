public class Patient {
    private int patientId;
    private String nom;

    public Patient(int patientId, String nom) {
        this.patientId = patientId;
        this.nom = nom;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getNom() {
        return nom;
    }
}
