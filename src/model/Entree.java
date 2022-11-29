package model;

public class Entree {

    private String mot;

    private String definition;

    public Entree(String mot, String definition) {
        this.mot = mot;
        this.definition = definition;
    }

    public String getMot() {
        return mot;
    }

    public String getDefinition() {
        return definition;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String toString() {
        return mot + " & " + definition;
    }
}
