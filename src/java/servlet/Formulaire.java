/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Connexion;
import modele.Liaison;
import modele.Personne;
import modele.Symptome;

/**
 *
 * @author Haingo Adrienne
 */
@WebServlet(name = "Formulaire", urlPatterns = {"/Formulaire"})
public class Formulaire extends HttpServlet {
    Connexion c = new Connexion();
    Personne personne = new Personne();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        List<Symptome> liste = Symptome.getAllSymptomes(c.connectToPostgres());
        request.setAttribute("symptome", liste);
        RequestDispatcher dispacth = request.getRequestDispatcher("Formulaire.jsp");
        dispacth.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Formulaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("date");
        Date cons = Date.valueOf(date);
        String nom = request.getParameter("nom");
        String nai = request.getParameter("naissance");
        Date nais = Date.valueOf(nai);
        String vola = request.getParameter("budget");
        double budget = Double.parseDouble(vola);
        String t = request.getParameter("temperature");
        String h = request.getParameter("double");
        String temp = t+"."+h;
        double temperature = Double.parseDouble(temp);
        String[] sympt = request.getParameterValues("temp");
        String[] eta = request.getParameterValues("etat");
        int[] listeSymptome = new int[sympt.length];
        int[] listeEtat = new int[sympt.length];
        if(sympt.length!=0){
            for(int i=0; i<sympt.length; i++){
                listeSymptome[i] = Integer.parseInt(sympt[i]);
                listeEtat[i] = Integer.parseInt(eta[i]);
            }
        }
        
        try {
            Personne.addPersonne(c.connectToPostgres(), nom, nais, cons, budget, temperature);
            personne = Personne.getAllPersonnes(c.connectToPostgres(), cons, nom);
            for(int i=0; i<listeSymptome.length; i++){
                Liaison.addLiaison(c.connectToPostgres(), personne.getId(), listeSymptome[i], listeEtat[i]);
            }
            response.sendRedirect("Liste_Personne");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("Formulaire");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
