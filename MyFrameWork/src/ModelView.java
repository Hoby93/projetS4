/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etu1839.framework;

import java.util.HashMap;

/**
 *
 * @author ITU
 */
public class ModelView {
    String view;
    boolean toJSON;

    HashMap<String, Object> data = new HashMap<String,Object>();
    HashMap<String, Boolean> session = new HashMap<String, Boolean>();

    public ModelView() {
        toJSON = false;
    }

    public ModelView(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public HashMap<String,Object> getData() {
        return data;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean getToJSON() {
        return this.toJSON;
    }

    public void setToJSON(boolean toJSON) {
        this.toJSON = toJSON;
    }

    public void addItem(String key, Object value) {
        data.put(key, value);
    }

    public HashMap<String, Boolean> getSession() {
        return this.session;
    }

    public void setSession(HashMap<String, Boolean> s) {
        this.session = s;
    }

    public void putToSession(String key, Boolean value) {
        this.session.put(key, value);
    }
}
