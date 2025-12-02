/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Cours;
import services.StudentService;
import entities.Student;
import entities.StudentCours;

/**
 *
 * @author Destockafric
 */
import java.sql.Date;
import services.CoursService;
import services.StudentCoursService;
public class testStudent {
    public static void main(String[] args){
        StudentService ss=new StudentService();
        CoursService cs=new CoursService();
        StudentCoursService scs=new StudentCoursService();
        //cs.create(new Cours("Programation","C#",30));
        //cs.findById(1);
        //ss.create(new Student("wissam","wissam@gmail.com",Date.valueOf("2025-11-14")));
        //System.out.println(ss.findById(1));
        //scs.create(new StudentCours(ss.findById(3),cs.findById(2),new java.sql.Date(System.currentTimeMillis()),0));
        
        //StudentCours sc =new StudentCours(ss.findById(3),cs.findById(2),new java.sql.Date(System.currentTimeMillis()),0);
        //tudentCours sc=scs.findById(1);
        //sc.setScore(11);
        //scs.update(sc);
        //System.out.println(sc);
        //System.out.println(scs.findHistoryByCours(1));
        //System.out.println(scs.findAll());
        //System.out.println(ss.findAll());
        //System.out.println(ss.findById(2));
        System.out.println(cs.findById(2));


        
    }
}
