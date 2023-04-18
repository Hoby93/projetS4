/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1839.framework.servlet;

import etu1839.framework.Mapping;
import etu1839.framework.ModelView;
import etu1839.framework.Utilitaire;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ITU
 */
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> mappingUrls;
    
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
           throws ServletException, IOException {
          try {
                String[] url = new Utilitaire().getDataFromURL(request.getRequestURI());
                String slug = url[url.length - 1];
                RequestDispatcher dispatcher = request.getRequestDispatcher("/web/index.html");
                if(this.mappingUrls.containsKey(slug)) {

                    System.out.println("url : " + slug);

                    Mapping relative = mappingUrls.get(slug);

                    Class<?> classInstance = Class.forName(relative.getClassName());
                    Method function = classInstance.getMethod(relative.getMethode());

                    ModelView view = (ModelView) function.invoke(classInstance.newInstance());
                    dispatcher = request.getRequestDispatcher("/web/" + view.getView());

                       System.out.println("countData : " + view.getData().size());
                       System.out.println("modelView : " + view.getView());

                    for(HashMap.Entry<String, Object> entry : view.getData().entrySet()) {
                        request.setAttribute(entry.getKey(), entry.getValue());
                        System.out.println("key : " + entry.getKey() + "\t value: " + entry.getValue());
                    }
               } 
               dispatcher.forward(request, response);
        } catch (Exception e) {
              System.out.println("Tsy itany ilay page");
              e.printStackTrace();
        }
           
//        response.setContentType("text/html;charset=UTF-8");
    }
    
    @Override
    public  void init() {
        this.mappingUrls = new HashMap<String, Mapping>();
        new Utilitaire().fillMappingUrlValues(this.mappingUrls);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
