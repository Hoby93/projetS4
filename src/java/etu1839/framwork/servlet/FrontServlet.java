/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1839.framwork.servlet;

import etu1839.framework.Mapping;
import etu1839.framework.annotation.AnnotationUrl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
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
//        response.setContentType("text/html;charset=UTF-8");
       
    }
    
    @Override
    public  void init() {
        File pack = new File("C:\\Users\\ITU\\Documents\\NetBeansProjects\\FrameWork\\build\\web\\WEB-INF\\classes\\model");
        File[] allClass = pack.listFiles();

        for(int i = 0; i < allClass.length; i++) {
            try {
                String className = "model." + allClass[i].getName().split(".class")[0];
                Class MyClass = Class.forName(className);
                //System.out.println("Name : " + MyClass.getName());

                Method[] listMethode = MyClass.getDeclaredMethods();
                for(int x = 0; x < listMethode.length; x++) {
                    if(listMethode[x].getAnnotation(AnnotationUrl.class) != null) {
                        Mapping mapping = new Mapping(className, listMethode[x].getName());
                        mappingUrls.put(listMethode[x].getAnnotation(AnnotationUrl.class).url(), mapping);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VUES/home.jsp");
        dispatcher.forward(request, response);
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
