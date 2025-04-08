package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import oo.Credit;

public class CreditServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        try {
            String libelle = request.getParameter("libelle");
            int montant = Integer.parseInt(request.getParameter("montant"));
            Credit c = new Credit(montant,libelle);
            c.save();

            response.sendRedirect("Accueil.jsp");
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}