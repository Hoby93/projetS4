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
    HashMap<String,Object> data = new HashMap<String,Object>();;

    public ModelView() {

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

    public void addItem(String key, Object value) {
        data.put(key, value);
    }
}
