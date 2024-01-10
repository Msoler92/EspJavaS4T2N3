package cat.itacademy.barcelonactiva.solereina.manel.s04.t02.n03.model.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Fruita")
public class Fruita {
    @Id
    private int id;
    private String nom;
    private int quantitatQuilos;

    public Fruita() {
        nom = "";
    }

    public Fruita(String nom, int quantitatQuilos) {
        this.nom = nom;
        this.quantitatQuilos = quantitatQuilos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantitatQuilos() {
        return quantitatQuilos;
    }

    public void setQuantitatQuilos(int quantitatQuilos) {
        this.quantitatQuilos = quantitatQuilos;
    }

    @Override
    public String toString() {
        return "Fruita: id = " + id + ", nom = " + nom + ", quantitat = " + quantitatQuilos + ".";
    }
}