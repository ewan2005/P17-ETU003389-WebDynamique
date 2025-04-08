package oo;

import utils.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Credit {
    private int idcredit;
    private String libelle;
    private int montant;
    
    // Constructor with fields
    public Credit(String libelle,int montant) {
        this.libelle = libelle;
        this.montant = montant;
    }

    public Credit(int montant,String libelle) {
        this.libelle = libelle;
        this.montant = montant;
    }
    
    // Constructor with all fields
    public Credit(int idcredit, int montant, String libelle) {
        this.idcredit = idcredit;
        this.montant = montant;
        this.libelle = libelle;
    }
    
    // Getters and Setters
    public int getId() {
        return idcredit;
    }
    
    public void setId(int id) {
        this.idcredit = id;
    }
    
    public int getMontant() {
        return montant;
    }
    
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
        String query = "INSERT INTO credit (montant,libelle) VALUES (?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, this.montant);
            ps.setString(2, this.libelle);
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    this.idcredit = rs.getInt(1);
                }
            }
        }
    }
    
    public static Credit findById(int id) throws SQLException {
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
    
    public static Credit findById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM credit WHERE idcredit = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Credit(
                        rs.getInt("idcredit"),
                        rs.getInt("montant"),
                        rs.getString("libelle")
                    );
                }
            }
        }
        
        return null;
    }
    
    public static List<Credit> findAll() throws SQLException {
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
    
    public static List<Credit> findAll(Connection conn) throws SQLException {
        List<Credit> credits = new ArrayList<>();
        String query = "SELECT * FROM credit group by idcredit";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                credits.add(new Credit(
                    rs.getInt("idcredit"),
                    rs.getInt("montant"),
                    rs.getString("libelle")
                ));
            }
        }
        
        return credits;
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
        String query = "DELETE FROM credit WHERE id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.idcredit);
            ps.executeUpdate();
        }
    }

}