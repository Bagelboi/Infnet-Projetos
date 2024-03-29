package org.daniellpk.model.misc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbObj {
    protected Integer id = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public DbObj() {}

    //Funções HTML

    public String getDataName() {
        return this.getClass().getName();
    }
    public List<String> getTableHeaders() {
        List<String> membros = new ArrayList<>();
        for (Field membro : this.getClass().getDeclaredFields()) {
            membros.add(membro.getName());
        }
        return membros;
    }

    public List<String> getAsTableItem() {
        List<String> membros = new ArrayList<>();
        for (Field membro : this.getClass().getDeclaredFields()) {
            membros.add(membro.toGenericString());
        }
        return membros;
    }

    /*
    public List<String> getFormInputs() {
        return (List<String>) Arrays.asList(
                "id"
        );
    }*/
}
