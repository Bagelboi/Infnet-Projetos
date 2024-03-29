package org.daniellpk.model.misc;

import spark.ModelAndView;
import spark.Route;

import java.lang.reflect.Array;
import java.util.*;

//funções para mexer com HTML
public class VelocityManager {
    public static final String public_files = "static/";

    public static ModelAndView getPagina(String vm_file, HashMap<String, Object> model) {
        return new ModelAndView(model, public_files + vm_file);
    }

    public static ModelAndView getPagina(String vm_file) {
        HashMap<String, Object> model =  new HashMap<>();
        return getPagina(vm_file, model);
    }

    public static String formatAsInnerTable(Collection<String> rowitems) {
            return String.join("<br>", rowitems);
    }

    public static String createInputField(String id, String label, String type) {
        return "<label for=\"" + id + "\">" + label + "</label><br>" +
                "<input type=\"" + type + "\" id=\"" + id + "\" name=\"" + id +"\"><br>";
    }

    public static String createInputField(String id, String type) {
        String label = id.substring(0,1).toUpperCase() + id.substring(1, id.length());
        return createInputField(id, label, type);
    }

}
