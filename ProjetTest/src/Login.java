/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.HashMap;
import etu1839.framework.annotation.AnnotationUrl;
import etu1839.framework.ModelView;

/**
 *
 * @author ITU
 */
public class Login {
    String nom;
    String mdp;


    public Login() {
    }

    @AnnotationUrl(url = "login")
    public ModelView login() {
        ModelView view = new ModelView("login.jsp");

        System.out.println("etu1839.framework.Login.login()");
        
        return view;
    }

    @AnnotationUrl(url = "login-check")
    public ModelView checkLogin() {
        ModelView view = new ModelView("login.jsp");
        
        setAuthentification(view);

        System.out.println("etu1839.framework.Login.checkLogin()");

        System.out.println("Nom: " + this.nom);
        System.out.println("Mdp: " + this.mdp);
        
        return view;
    }

    public void setAuthentification(ModelView view) {
        String[] profil = {"Manager", "Admin"};
        view.putToSession("profil", profil);
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String val) {
        this.nom = val;
    }

    public String getMdp() {
        return this.nom;
    }

    public void setMdp(String val) {
        this.mdp = val;
    }
}
