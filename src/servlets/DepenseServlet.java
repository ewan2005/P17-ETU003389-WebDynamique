package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;

import oo.Credit;
import oo.Depense;

public class DepenseServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Credit> getAllcredit = Credit.findAll();
            request.setAttribute("credit", getAllcredit);
            List<Depense> getAlldepense = Depense.findAll();
            request.setAttribute("depense", getAlldepense);
            
            request.getRequestDispatcher("/formDepense.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idcredit = Integer.parseInt(request.getParameter("idcredit"));
            int montant = Integer.parseInt(request.getParameter("montant"));
            
            Credit credit = Credit.findById(idcredit);
            if (credit == null) {
                throw new ServletException("Credit not found");
            }
            
            if (montant > credit.getMontant()) {
                response.sendRedirect("Erreur.jsp");
                return;
            }

            Depense c = new Depense(montant, idcredit);
            c.save();
            
            response.sendRedirect("Accueil.jsp");
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}