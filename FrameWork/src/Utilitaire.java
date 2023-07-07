/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1839.framework;

import etu1839.framework.annotation.AnnotationUrl;
import etu1839.framework.annotation.AnnotationScop;
import etu1839.framework.annotation.AnnotationSession;
import etu1839.framework.annotation.AnnotationJson;
import etu1839.framework.annotation.Authentification;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.text.*;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ITU
 */
public class Utilitaire {

    public Utilitaire() {
    }
    
    // Fonction qui split et renvoye les données d'un url
    public static String[] getDataFromURL(String url) {
        String[] urlData = url.split("/");
        String[] data = new String[urlData.length - 1];
        for (int i = 0; i < data.length; i++) {
            data[i] = urlData[i + 1];
        }
        return data;
    }
    
    // Fonction qui retourne les packages/sous-package/...
    public List<String> getAllPackages(List<String> packages, String path, String debut) {
        String concat = ".";
        if(packages == null)
            packages = new ArrayList<>();
        if(debut == null) {
            debut = "";
            concat = "";
        }

        File dossier = new File(path);
        File[] files = dossier.listFiles();
        for (File file : files) {
            if(file.isDirectory()) {
                packages.add(debut + concat + file.getName());
                packages = getAllPackages(packages, file.getPath(), debut + concat + file.getName());
            }
        }

        return packages;
    }

    // Fonction qui set l'authentification d'une mapping.methode
    public String getAuthentification(Method method) {
        String ans = "*";
        if(method.isAnnotationPresent(Authentification.class)) {
            ans = method.getAnnotation(Authentification.class).required();
        }

        return ans;
    }

    // Fonction qui set le besoin de recuperer une session
    public boolean isNeedSession(Method method) {
        boolean ans = false;
        if(method.isAnnotationPresent(AnnotationSession.class)) {
            ans = true;
        }

        return ans;
    }

    // Fonction qui verifie si une mapping.methode doit retourner JSON
    public boolean isReturnJson(Method method) {
        boolean ans = false;
        if(method.isAnnotationPresent(AnnotationJson.class)) {
            ans = true;
        }

        return ans;
    }
    

    // Fonction initialise et remplie une HashMap mappingUrl pour une package donnée
    public void addMappingUrl(HashMap<String, Mapping> mappingUrls, HashMap<String, Object> classInstances, String packageName) {
        String path =  this.getClass().getClassLoader().getResource("").getPath().toString().replace("%20", " ");
        
        File pack = new File(path + packageName.replace('.', '\\'));
        File[] allClass = pack.listFiles();
        
        String[] pck = packageName.split("\\.");
        String pckName = packageName;
        if(pck.length > 0) {
            pckName = pck[pck.length - 1];
        }
        
         try {
                for(int i = 0; i < allClass.length; i++) {
                    try {
                        String className = pckName + "." + allClass[i].getName().split(".class")[0];
                        Class MyClass = Class.forName(className);
        
                        Method[] listMethode = MyClass.getDeclaredMethods();

                        //ajouter dans la list si la class doit etre singleton
                        if(MyClass.isAnnotationPresent(AnnotationScop.class)) {
                            classInstances.put(MyClass.getName(), null);
                        }

                        for(int x = 0; x < listMethode.length; x++) {
                            //ajouter dans la list si la methode est annotee AnnotationURL
                            if(listMethode[x].getAnnotation(AnnotationUrl.class) != null) {
                                Mapping mapping = new Mapping(className, listMethode[x].getName(), getAuthentification(listMethode[x]), isNeedSession(listMethode[x]), isReturnJson(listMethode[x]));
                                mappingUrls.put(listMethode[x].getAnnotation(AnnotationUrl.class).url(), mapping);
                            }
                        }
                    } catch(Exception e) {
                        //e.printStackTrace();
                    }
                }
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    // Fonction initialise et remplie une HashMap mappingUrl pour tout les packages
    public void fillMappingUrlValues(HashMap<String, Mapping> mappingUrls, HashMap<String, Object> classInstances) {
        String path =  this.getClass().getClassLoader().getResource("").getPath().toString().replace("%20", " ");
        List<String> allPackage = getAllPackages(null, path, null);
         
        for(int i = 0; i < allPackage.size(); i++) {
            addMappingUrl(mappingUrls, classInstances, allPackage.get(i));
        }
    }

    // Fonction qui effectue les castes
    public  Object castToAppropriateClass(String valueInst, Class<?> classInst) {
        try {
            if(classInst.getSimpleName() == "int" || classInst.getSimpleName() == "Integer") {
                return Integer.parseInt(valueInst); // try to parse the valueInst as an integer
            } else if(classInst.getSimpleName() == "double" || classInst.getSimpleName() == "Double") {
                return Double.parseDouble(valueInst); // try to parse the valueInst as a double
            } else if(classInst.getSimpleName() == "Date") { 
                return new SimpleDateFormat("yyyy-MM-dd").parse(valueInst); // try to parse the valueInst as a date
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return valueInst; // return the value as a string
    }
}
