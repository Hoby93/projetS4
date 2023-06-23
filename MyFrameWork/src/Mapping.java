/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1839.framework;

/**
 *
 * @author ITU
 */
public class Mapping {
    String className;
    String methode;
    String authentification;
    boolean needSession;

    public Mapping(String className, String methode, String auth, boolean isSession) {
        this.className = className;
        this.methode = methode;
        this.authentification = auth;
        this.needSession = isSession;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public String getAutentification() {
        return this.authentification;
    }

    public void setAutentification(String auth) {
        this.authentification = auth;
    }

    public boolean  isNeedSession() {
        return this.needSession;
    }

    public void setNeedSession(boolean val) {
        this.needSession = val;
    }
    
}
