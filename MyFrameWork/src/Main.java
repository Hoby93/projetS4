/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import etu1839.framework.annotation.AnnotationUrl;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ITU
 */

public class Main {
    
    public static String[] getDataFromURL(String url) {
        String[] urlData = url.split("/");
        String[] data = new String[urlData.length - 1];
        for (int i = 0; i < data.length; i++) {
            System.out.println("URL " + urlData[i + 1]);
            data[i] = urlData[i + 1];
        }
        return data;
    }
    
    public static List<String> getAllPackages(List<String> packages, String path, String debut){
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
                //System.out.println(debut + concat + file.getName());
                packages = getAllPackages(packages, file.getPath(), debut + concat + file.getName());
            }
        }
        return packages;
    }
     
     public static void addOnList(String packageName) {
         System.out.println("--------------------------");
        String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\MyFrameWork\\src\\";
        File pack = new File(path + packageName.replace('.', '\\'));
        File[] allClass = pack.listFiles();
            System.out.println("before split:" + packageName);
        String[] pck = packageName.split("\\.");
        String pckName = packageName;
         System.out.println("Len:" + pck.length);
        if(pck.length > 0) {
            pckName = pck[pck.length - 1];
        }
        
         try {
                 System.out.println("localPath: " + path + packageName.replace('.', '\\'));
                 System.out.println("package: " + pckName);
                for(int i = 0; i < allClass.length; i++) {
                    try {
                        String className = pckName + "." + allClass[i].getName().split(".class")[0];
                        Class MyClass = Class.forName(className);
                        System.out.println("Name : " + MyClass.getName());
        
                        Method[] listMethode = MyClass.getDeclaredMethods();
                        System.out.println("\tMethodes Annotee [AnnotationUrl] :");
                        for(int x = 0; x < listMethode.length; x++) {
                            if(listMethode[x].getAnnotation(AnnotationUrl.class) != null) {
                                System.out.println("\t> " + listMethode[x].getName() + " > " + listMethode[x].getAnnotation(AnnotationUrl.class).url());
                            }
                        }
                    } catch(Exception e) {
//                        e.printStackTrace();
                    }
                }
         } catch (Exception e) {
             System.out.println("Error : " + packageName);
         }
     }
     
     public static void init() {
         String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\MyFrameWork\\src";
         List<String> allPackage = getAllPackages(null, path, null);
         
         for(int i = 0; i < allPackage.size(); i++) {
             addOnList(allPackage.get(i));
         }
     }
     
    public static void main(String args[]) {
        String[] slug  = getDataFromURL("add/add/emp-add");
        init();
    }
}
