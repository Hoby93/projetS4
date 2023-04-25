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

    public Emp[] listEmp() {
        Emp[] ans = new Emp[2];
        ans[0] = new Emp(1, "RAKOTO", "Nirina", 20);
        ans[1] = new Emp(2, "RAHARY", "Soa", 19);

        return ans;
    }
    
    @AnnotationUrl(url = "emp-all")
    public ModelView findAll() {
        ModelView view = new ModelView("page.jsp");
        view.addItem("lst", this.listEmp());
        view.addItem("test", 12);

        System.out.println("etu1839.framework.Employe.findAll()");
        
        return view;
    }

    @AnnotationUrl(url = "emp-add")
    public ModelView add() {
        ModelView view = new ModelView("add.jsp");

        System.out.println("--------  Emp-Add ---------");
        System.out.println("Nom: " + this.nom);
        System.out.println("Prenom: " + this.prenom);
        System.out.println("Age: " + this.age);
        
        return view;
    }

    public int getIdEmp() {
        return this.idEmploye;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String snom) {
        this.nom = snom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String sprenom) {
        this.prenom = sprenom;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int sage) {
        this.age = sage;
    }
}
