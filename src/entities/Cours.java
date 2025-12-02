/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Destockafric
 */
public class Cours {
    private int id;
    private String categorie;
    private String title;
    private int nb_H;

    public Cours(String categorie, String title, int nb_H) {
        this.categorie = categorie;
        this.title = title;
        this.nb_H = nb_H;
    }

    public Cours(int id, String categorie, String title, int nb_H) {
        this.id=id;
        this.categorie = categorie;
        this.title = title;
        this.nb_H = nb_H;; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getTitle() {
        return title;
    }

    public int getNb_H() {
        return nb_H;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNb_H(int nb_H) {
        this.nb_H = nb_H;
    }
    
}
