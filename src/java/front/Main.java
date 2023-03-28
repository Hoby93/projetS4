/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front;

import etu1839.framework.annotation.AnnotationUrl;
import java.io.File;
import java.lang.reflect.Method;

/**
 *
 * @author ITU
 */

public class Main {
     public static void init() {
        File pack = new File("C:\\Users\\ITU\\Documents\\NetBeansProjects\\FrameWork\\build\\web\\WEB-INF\\classes\\model");
        File[] allClass = pack.listFiles();

        for(int i = 0; i < allClass.length; i++) {
            try {
                String className = "model." + allClass[i].getName().split(".class")[0];
                Class MyClass = Class.forName(className);
                //System.out.println("Name : " + MyClass.getName());

                Method[] listMethode = MyClass.getDeclaredMethods();
                System.out.println("\tMethodes Annotee [AnnotationUrl] :");
                for(int x = 0; x < listMethode.length; x++) {
                    if(listMethode[x].getAnnotation(AnnotationUrl.class) != null) {
                        System.out.println("\t> " + listMethode[x].getName() + " > " + listMethode[x].getAnnotation(AnnotationUrl.class).url());
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
     }
     
    public static void main(String args[]) {
        init();
    }
}
