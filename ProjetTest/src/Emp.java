/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import etu1839.framework.annotation.AnnotationUrl;
import etu1839.framework.ModelView;

public class Emp {
    int idEmploye;
    String nom;
    String prenom;
    int age;

    public Emp() {
    }

    public Emp(int idEmploye, String nom, String prenom, int age) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }
    
    @AnnotationUrl(url = "emp-add")
    public ModelView add() {
        ModelView view = new ModelView("go.html");
        System.out.println("etu1839.framework.Employe.add()");
        
        return view;
    }
}
