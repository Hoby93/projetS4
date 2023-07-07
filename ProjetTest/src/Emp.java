/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

package model;

import etu1839.framework.annotation.AnnotationUrl;
import etu1839.framework.annotation.Authentification;
import etu1839.framework.annotation.AnnotationScop;
import etu1839.framework.annotation.AnnotationSession;
import etu1839.framework.annotation.AnnotationJson;
import etu1839.framework.FileUpload;
import etu1839.framework.ModelView;
import java.util.HashMap;

@AnnotationScop(scop = "singleton")
public class Emp {
    int idEmploye;
    String nom;
    String prenom;
    int age;
    FileUpload photo;

    // Ce nom est fixé par convention pour tout les classes
    HashMap<String, Object> session = new HashMap<String, Object>();

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
    
    @AnnotationSession
    @AnnotationUrl(url = "emp-all")
    public ModelView findAll() {
        ModelView view = new ModelView("page.jsp");
        view.addItem("lst", this.listEmp());
        view.addItem("test", 12);

        if(session.containsKey("idUser")) {
            System.out.println("**YES ITS'CONTAINS" + session.get("idUser"));
        }        
        return view;
    }

    @AnnotationJson
    @AnnotationUrl(url = "emp-allToJson")
    public Emp[] findAllToJson() {
        System.out.println("model.Employe.findAllToJson()");
        
        return this.listEmp();
    }

    @AnnotationUrl(url = "emp-add")
    public ModelView add() {
        ModelView view = new ModelView("add.jsp");
        try {
            System.out.println("--------  Emp-Add ---------");
            System.out.println("Nom: " + this.nom);
            System.out.println("Prenom: " + this.prenom);
            System.out.println("Age: " + this.age);
            System.out.println("PhotoFileName: " + this.photo.getFileName());
        } catch(Exception ex) {
            // ex.printStackTrace();
        }
        
        return view;
    }

    @AnnotationUrl(url = "emp-findById")
    public ModelView findById(int id) {
        System.out.println("--------  Emp-FindById ---------");
        System.out.println("*Id: " + id);

        ModelView view = new ModelView("info.jsp");
        view.putToSession("idUser", id);
        view.addItem("emp", this.listEmp()[id]);
        
        return view;
    }

    @Authentification(required = "Admin")
    @AnnotationUrl(url = "emp-delete")
    public ModelView delete() {
        ModelView view = new ModelView("page.jsp");
        view.addItem("lst", this.listEmp());

        System.out.println("model.Employe.findAll()");
        
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

    public FileUpload getPhoto() {
        return this.photo;
    }

    public void setPhoto(FileUpload fu) {
        this.photo = fu;
    }

    public void setSession(HashMap<String, Object> s) {
        this.session = s;
    }
}
