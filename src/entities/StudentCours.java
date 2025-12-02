/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;


/**
 *
 * @author Destockafric
 */
public class StudentCours {
    private int id;
    private Student student;
    private Cours cours;
    private Date date_Inscription;
    private int score;

    public StudentCours(Student student, Cours cours, Date date, int score) {
        this.student = student;
        this.cours = cours;
        this.date_Inscription = date;
        this.score = score;
    }
    
    public StudentCours(int id,Student student, Cours cours, Date date, int score) {
        this.id=id;
        this.student = student;
        this.cours = cours;
        this.date_Inscription = date;
        this.score = score;
    }

    public StudentCours() {
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setDate(Date date) {
        this.date_Inscription = date;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentCours{" + "student=" + student + ", cours=" + cours + ", date_Inscription=" + date_Inscription + ", score=" + score + '}';
    }

    public Student getStudent() {
        return student;
    }

    public Cours getCours() {
        return cours;
    }

    public Date getDate() {
        return date_Inscription;
    }

    public int getScore() {
        return score;
    }
    
    
}
