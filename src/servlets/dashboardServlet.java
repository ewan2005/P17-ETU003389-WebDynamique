package servlets;

import java.io.IOException;
import java.util.Vector;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import oo.Dashboard;

public class dashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Vector<Dashboard> dashboards = Dashboard.getDashboard();
            req.setAttribute("dashboards", dashboards);
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
