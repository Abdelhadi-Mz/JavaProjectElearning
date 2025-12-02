









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
public class Student {
    private int id;
    private String name;
    private String email;
    private Date date;

    public Student(String name, String email, Date date) {
        this.name = name;
        this.email = email;
        this.date = date;
    }
    public Student(int id ,String name, String email, Date date) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return  name ;
    }

    public Date getDate() {
        return date;
    }
    
    
}
