/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ncib
 */
public class Categorie {
    private int id;
    private String libelle;
    private int quantite;
public Categorie(){}
    public Categorie(int id, String libelle, int quantite) {
        this.id = id;
        this.libelle = libelle;
        this.quantite = quantite;
    }

    public Categorie(String libelle, int quantite) {
        this.libelle = libelle;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }




  
    
    
    
    
    
}
