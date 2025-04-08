package oo;

import java.sql.*;
import java.util.Vector;

import jakarta.servlet.http.HttpServlet;

import utils.DB;

public class Dashboard {
    public Dashboard(String typeCredit, float montantDepense, float reste) {
        this.typeCredit = typeCredit;
        this.montantDepense = montantDepense;
        this.reste = reste;
    }

    String typeCredit;
    float montantDepense;
    float reste;
    
    public static Vector<Dashboard> getDashboard() throws Exception{
        Connection conn = null;
        Vector<Dashboard> result = new Vector<>();
        String sql = "SELECT c.libelle type_credit, COALESCE(SUM(d.montant),0) montant_depense,c.montant reste FROM depense d right join credit c on d.idcredit=c.idcredit GROUP BY c.idcredit,c.libelle";
        try {
            conn = DB.getconnect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.add(new Dashboard(rs.getString(1), rs.getFloat(2), rs.getFloat(3)));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw e;
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return result;
    }

    public String getTypeCredit() {
        return typeCredit;
    }

    public void setTypeCredit(String typeCredit) {
        this.typeCredit = typeCredit;
    }

    public float getMontantDepense() {
        return montantDepense;
    }

    public void setMontantDepense(float montantDepense) {
        this.montantDepense = montantDepense;
    }

    public float getReste() {
        return reste;
    }

    public void setReste(float reste) {
        this.reste = reste;
    }
}
