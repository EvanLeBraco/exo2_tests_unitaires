// Création de la classe d'un médecin
public class Medecin {
    private int medecinId;
    private String nom;
    private int maxRDV;

    public Medecin(int medecinId, String nom, int maxRDV) {
        this.medecinId = medecinId;
        this.nom = nom;
        this.maxRDV = maxRDV;
    }
    
    public int getMedecinId() {
        return medecinId;
    }

    public String getNom() {
        return nom;
    }
    
    public int getMaxRDV() {
        return maxRDV;
    }

}