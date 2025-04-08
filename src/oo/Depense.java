package oo;

import utils.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Depense{
    private int iddepense;
    private int idcredit;
    private String libelle;
    private double montant;
    
    public Depense(double montant,int idcredit) {
        this.montant = montant;
        this.idcredit = idcredit;
    }
    
    public Depense(int iddepense,int idcredit ,double montant) {
        this.iddepense = iddepense;
        this.idcredit = idcredit;
        this.montant = montant;
    }
    
    public int getId() {
        return iddepense;
    }
    
    public void setId(int id) {
        this.iddepense = id;
    }

    public int getIdcredit() {
        return idcredit;
    }
    
    public void setIdcredit(int id) {
        this.idcredit = id;
    }
    
    public double getMontant() {
        return montant;
    }
    
    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    
    
    
    public void save() throws SQLException {
        Connection conn = null;
        try {
            conn = DB.getconnect();
            save(conn);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void save(Connection conn) throws SQLException {
        String query = "INSERT INTO depense (idcredit, montant) VALUES (?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.idcredit);
            ps.setDouble(2, this.montant);
            
            ps.executeUpdate();
        }
    }
    
    public static Depense findById(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = DB.getconnect();
            return findById(conn, id);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public static Depense findById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM depense WHERE iddepense = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Depense(
                        rs.getInt("iddepense"),
                        rs.getInt("idcredit"),
                        rs.getDouble("montant")
                    );
                }
            }
        }
        
        return null;
    }
    
    
    public static List<Depense> findAll() throws SQLException {
        Connection conn = null;
        try {
            conn = DB.getconnect();
            return findAll(conn);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public static List<Depense> findAll(Connection conn) throws SQLException {
        List<Depense> depenses = new ArrayList<>();
        String query = "SELECT * FROM depense group by iddepense";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                depenses.add(new Depense(
                    rs.getInt("iddepense"),
                    rs.getInt("idcredit"),
                    rs.getDouble("montant")
                ));
            }
        }
        
        return depenses;
    }
    
    public void delete() throws SQLException {
        Connection conn = null;
        try {
            conn = DB.getconnect();
            delete(conn);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void delete(Connection conn) throws SQLException {
        String query = "DELETE FROM depense WHERE id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.iddepense);
            ps.executeUpdate();
        }
    }

}