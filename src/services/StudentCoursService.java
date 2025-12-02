/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Connexion.Connexion;
import dao.IDao;
import entities.Student;
import entities.StudentCours;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Destockafric
 */
public class StudentCoursService implements IDao<StudentCours> {
    StudentService ss=new StudentService();
    CoursService cs=new CoursService();
    @Override
    public boolean create(StudentCours o) {
        String req = "INSERT INTO `studentCours` (`id`, `student_id`, `cours_id`, `date_inscription`, `score`) VALUES (NULL, ?, ?, ?, ?)";
        try {
            PreparedStatement ps= Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getStudent().getId());
            ps.setInt(2, o.getCours().getId());
            ps.setDate(3,o.getDate());
            ps.setInt(4,o.getScore());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant ");
            return false;
        }
    }

    @Override
    public boolean update(StudentCours o) {
        String req="UPDATE `studentCours` SET `score` = ?  WHERE `studentCours`.`student_id` = ? AND `studentCours`.`cours_id` = ?";
        try {
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getScore());
            ps.setInt(2, o.getStudent().getId());
            ps.setInt(3,o.getCours().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant update the Score");
            return false;
        }
    }

    @Override
    public boolean delete(StudentCours o) {
       String req="delete from studentCours where student_id = ? and cours_id=? ";
        try {
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getStudent().getId());
            ps.setInt(2, o.getCours().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant delete ");
            return false;
        }   
    }

    @Override
    public StudentCours findById(int id) {
       try {
            String req = "select * from studentCours where id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new StudentCours(ss.findById(rs.getInt("student_id")), cs.findById(rs.getInt("cours_id")),rs.getDate("date_inscription"), rs.getInt("score"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCours.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<StudentCours> findHistoryByCours(int coursId) {
        List<StudentCours> list1 = new ArrayList<>();
        String req = "select * from studentCours where cours_id = ?";
        try {
            PreparedStatement ps=Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, coursId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list1.add(new StudentCours(ss.findById(rs.getInt("student_id")), cs.findById(rs.getInt("cours_id")),rs.getDate("date_inscription"), rs.getInt("score")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCoursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list1;
    }
    
    @Override
    public List<StudentCours> findAll() {
        List<StudentCours> SCs=new ArrayList<>();
       try {
            String req = "select * from studentCours ";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                SCs.add(new StudentCours(rs.getInt("id"),ss.findById(rs.getInt("student_id")), cs.findById(rs.getInt("cours_id")),rs.getDate("date_inscription"), rs.getInt("score")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCours.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SCs;
    }
    public Map<String, Double> getAverageScorePerCourse() {
        Map<String, Double> map = new HashMap<>();
        String sql = "SELECT cours_id, AVG(score) AS avg_score FROM studentCours GROUP BY cours_id";

        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int coursId = rs.getInt("cours_id");
                double avg = rs.getDouble("avg_score");

                // Get the course title using CoursService
                String title = cs.findById(coursId).getTitle();

                map.put(title, avg);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return map;
}

}
