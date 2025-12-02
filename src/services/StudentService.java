/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Connexion.Connexion;
import dao.IDao;
import entities.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Destockafric
 */
public class StudentService implements IDao<Student>{

    @Override
    public boolean create(Student o) {
        String req="INSERT INTO `student` (`id`, `name`, `email`, `date`) VALUES (NULL, ?, ?, ?);";
        try {
            PreparedStatement ps=Connexion.getConnection().prepareStatement(req);
            ps.setString(1,o.getName());
            ps.setString(2,o.getEmail());
            ps.setDate(3, o.getDate());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant add the student");
            return false;
        }
    }

    @Override
    public boolean update(Student o) {
        String req="UPDATE `student` SET `name` = ?, `email` = ?, `date` = ? WHERE `student`.`id` = ?";
        try {
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getName());
            ps.setString(2,o.getEmail());
            ps.setDate(3, o.getDate());
            ps.setInt(4,o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant update Student");
            return false;
        }
    }

    @Override
    public boolean delete(Student o) {
        String req="delete from student where id = ?";
        try {
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setInt(1,o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant delete Student");
            return false;
        }    
    }

    @Override
    public Student findById(int id) {
        String req="select * from student where id = ?";
        try {
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Student s= new Student(rs.getString("name"),rs.getString("email"),rs.getDate("date"));
                s.setId(rs.getInt("id"));
                return s;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant find the Student");
            
        }     
        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            String req = "select * from Student";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getDate("date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
}
    

