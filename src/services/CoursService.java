/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Connexion.Connexion;
import dao.IDao;
import entities.Cours;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Destockafric
 */
public class CoursService implements IDao<Cours>{

    @Override
    public boolean create(Cours o) {
        String req="INSERT INTO `cours` (`id`, `categorie`, `title`, `nb_H`) VALUES (NULL, ?, ?, ?);";
        try {
            PreparedStatement ps=Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getCategorie());
            ps.setString(2, o.getTitle());
            ps.setInt(3, o.getNb_H());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant add the cours");
            return false;
        }
    }

    @Override
    public boolean update(Cours o) {
        String req="UPDATE `cours` SET `categorie` = ?, `title` = ?, `nb_H` = ? WHERE `cours`.`id` = ?";
        try {
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getCategorie());
            ps.setString(2, o.getTitle());
            ps.setInt(3, o.getNb_H());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant update the cours");
            return false;
        }
    }

    @Override
    public boolean delete(Cours o) {
        String req="delete from cours where id = ?";
        try {
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant delete the cours");
            return false;
        }    
    }

    @Override
    public Cours findById(int id) {
        String req="select * from cours where id = ?";
        try {
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Cours c= new Cours(rs.getString("categorie"),rs.getString("title"),rs.getInt("nb_H"));
                c.setId(rs.getInt("id"));
                return c;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("cant find the cours");
            
        }     
        return null;
    }

    @Override
    public List<Cours> findAll() {
        List<Cours> courses = new ArrayList<>();
        try {
            String req = "select * from cours";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courses.add(new Cours(rs.getInt("id"),rs.getString("categorie"),rs.getString("title"),rs.getInt("nb_H")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }
}