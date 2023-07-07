/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1839.framework.servlet;

import etu1839.framework.Mapping;
import etu1839.framework.FileUpload;
import etu1839.framework.ModelView;
import etu1839.framework.Utilitaire;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.Set;

import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ITU
 */

@MultipartConfig
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
    HashMap<String, Object> classInstances;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    public String setterName(String attr) {
        return "set" + attr.substring(0, 1).toUpperCase() + attr.substring(1);
    }

    // La fonction qui caste
    public void callSetter(Method theSetter, Object objectInst, Object objectVal) {
        try {
            if (objectVal instanceof Integer) {
                //int intValue = (int) objectVal; // cast to int
                theSetter.invoke(objectInst, (int) objectVal);
            } else if (objectVal instanceof Double) {
                //double doubleValue = (double) objectVal; // cast to double
                theSetter.invoke(objectInst, (double) objectVal);
            } else if (objectVal instanceof Date) {
                //Date dateValue = (Date) objectVal; // cast to Date
                theSetter.invoke(objectInst, (Date) objectVal);
            } else {
                //String stringValue = (String) objectVal; // cast to String
                theSetter.invoke(objectInst, (String) objectVal);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }

    // Instancier les attributs d'un objet
    public void fillAttribute(HttpServletRequest request, Object objectInst, boolean need) {
        try {
            Field[] attributs = objectInst.getClass().getDeclaredFields();
            for(Field field : attributs) {
                // Envoyer la session http si la methode est annotée
                if(field.getName() == "session" && need) {
                    Method setter = objectInst.getClass().getMethod(setterName(field.getName()), field.getType());
                    Object session = getSessionAttribute(request);
                    setter.invoke(objectInst, session);
                }

                String value = request.getParameter(field.getName());
                if(value != null) {
                    Method setter = objectInst.getClass().getMethod(setterName(field.getName()), field.getType());
                    Object attr = new Utilitaire().castToAppropriateClass(value, field.getType());
                    callSetter(setter, objectInst, attr);
                }
                try {
                    // Instancier la fileUpload si il y en a 
                    if( request.getPart(field.getName()) != null) {
                        Part file = request.getPart(field.getName());
                        
                        Method setter = objectInst.getClass().getMethod(setterName(field.getName()), field.getType());
                        Object fileUpload = new FileUpload("C:\\", file.getSubmittedFileName(), partToByte(file));
                        setter.invoke(objectInst, fileUpload);
                    }
                } catch (Exception e) {
                        // e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recuperer les types des parametres d'une fonction
    public Class<?>[] getParameterType(Method[] methods, String methodeName) {
        for (Method method : methods) {
            if(method.getName() == methodeName) {
                return method.getParameterTypes();
            }
        }

        return null;
    }

    // Recuperer les valeurs pour les parametres d'une fonction
    public Object[] getParameterValues(HttpServletRequest request, Parameter[] args) {
        Object[] valueArgs = new Object[args.length];

        for (int i=0; i<args.length; i++) {
            valueArgs[i] = new Utilitaire().castToAppropriateClass(request.getParameter(args[i].getName()), args[i].getType());
        }

        return valueArgs;
    }

    // Convertir un Objet Part a un tableau de bite
    public byte[] partToByte(Part file) throws Exception {
        InputStream is = file.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;

        while((bytesRead = is.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        byte[] ans = bos.toByteArray();

        bos.close();
        is.close();

        return ans;
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    //verifier si on doit creer ou reprendre une instance existante
    public Object apropriateClassInstance(Class<?> classInstance) throws Exception {
        if(classInstances.containsKey(classInstance.getName())) {
            if(classInstances.get(classInstance.getName()) == null) {
                classInstances.replace(classInstance.getName(), classInstance.newInstance());
            }

            return classInstances.get(classInstance.getName());
        }

        return classInstance.newInstance();
    }

    // Verifier si on doit redirectioner vers un vue ou envoyer un Json
    public void sendResponse(HttpServletRequest request, HttpServletResponse response, ModelView mv) {
        try {
            if(mv.getToJSON()) {
                Gson gson = new Gson();
                String json = gson.toJson(mv.getData());
                PrintWriter out = response.getWriter();
                out.print("TO JSON >>> " + json);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/web/" + mv.getView());
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
      }
    }

    //  Recuperer les valeurs dans httpsessions
    public HashMap<String, Object> getSessionAttribute(HttpServletRequest request) {
        HashMap<String, Object> res = new HashMap<String, Object>();
        // Parcourir les sessions existantes et ajouter dans un hashmap
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            Object value = request.getSession().getAttribute(key);
            res.put(key, value);
        }
        return res;
    }

    // Verifier si l'utilisateur actuel est autorisée a acceder a une methode
    public boolean checkAuthentifiction(String[] profils, String required) {
        for(String profil : profils) {
            if(profil.equalsIgnoreCase(required)) {
                return true;
            }
        }
        return false;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
          try {
                String[] url = new Utilitaire().getDataFromURL(request.getRequestURI());
                String slug = url[url.length - 1];
                // Si l'url tapée par le client existe
                if(this.mappingUrls.containsKey(slug)) {

                    Mapping relative = mappingUrls.get(slug);

                    Class<?> classInstance = Class.forName(relative.getClassName());

                    Object objectInstance = apropriateClassInstance(classInstance);
                    fillAttribute(request, objectInstance, relative.isNeedSession());
                    
                    Class<?>[] functionParameters = getParameterType(classInstance.getDeclaredMethods(), relative.getMethode());
                    Method function = objectInstance.getClass().getMethod(relative.getMethode(), functionParameters);
                    Parameter[] args = function.getParameters();
                    Object[] valueArgs = getParameterValues(request, args);

                    // Si la methode est accessible ou l'utilisateur est autorisée
                    if(relative.getAutentification() == "*" || checkAuthentifiction((String[]) request.getSession().getAttribute("profil"), relative.getAutentification())) {
                        if(!relative.returnJson()) {
                            ModelView view = (ModelView) function.invoke(objectInstance, valueArgs);
                            // Supprimer les sessions spécifiées
                            setSession(request, view.getSessionRemove(), view.isInvalidateSession());
                            if(!view.getSession().isEmpty()) {
                                // Ajouter ou modifier des sessions
                                addSession(request, view.getSession());
                            }
                            // Renvoyer dans request.attribute les données envoyée par le modelview si il y en a
                            for(HashMap.Entry<String, Object> entry : view.getData().entrySet()) {
                                request.setAttribute(entry.getKey(), entry.getValue());
                            }

                            this.sendResponse(request, response, view);
                        } else {
                            Gson gson = new Gson();
                            String json = gson.toJson(function.invoke(objectInstance, valueArgs));
                            PrintWriter out = response.getWriter();
                            out.print("TO JSON >>> " + json);
                        }
                    } else {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/web/error.jsp");
                        request.setAttribute("error", "Privilege " + relative.getAutentification() + " requis pour executer cette action");
                        dispatcher.forward(request, response);
                    }
               } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/web/index.html");
                    if(url.length != 1) {
                        dispatcher = request.getRequestDispatcher("/web/error.jsp");
                        request.setAttribute("error", "Erreur: url inconnue");
                    }
                    dispatcher.forward(request, response);
               }
        } catch (Exception e) {
              e.printStackTrace();
        }
    }

    // Fonction qui ajout ou modifie les sessions
    public void addSession(HttpServletRequest request, HashMap<String, Object> session) {
        Set<String> keys = session.keySet();
        
        for (String key : keys) {
            request.getSession().setAttribute(key, session.get(key));
        }
    }

    // Fonction qui supprime ou invalide les valeurs sessions spécifiées
    public void setSession(HttpServletRequest request, ArrayList<String> listSession, boolean invalidate) {
        if(invalidate) {
            request.getSession().invalidate();
        } else {
            for(String session : listSession) {
                request.getSession().removeAttribute(session);
            }
        }
    }
    
    @Override
    public  void init() {
        this.mappingUrls = new HashMap<String, Mapping>();
        this.classInstances = new HashMap<String, Object>();
        new Utilitaire().fillMappingUrlValues(this.mappingUrls, this.classInstances);
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
        return "";
    }// </editor-fold>

}
