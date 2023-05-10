/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.codename1.ui.TextField;

/**
 *
 * @author ncib
 */
public class Produit {
    private int id;
    private String nom;
    private int prix;
    private int stock ;
    

    public Produit(){}
    public Produit(int id, String nom, int prix, int stock) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
      
    }

    public Produit(String nom, int prix, int stock) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
       
    }

    public Produit(TextField nom, TextField prix, TextField stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    
    
}
