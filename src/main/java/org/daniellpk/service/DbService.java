package org.daniellpk.service;

import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import org.daniellpk.Main;
import org.daniellpk.model.misc.DbObj;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class DbService<T extends DbObj> {
    protected HashMap<Integer, T> dbMap = new HashMap<>();
    public boolean verificarIncluir(Integer id, T dbObj)  {return true;} //verificação extra

    public boolean verificarAtualizar(Integer id, T dbObj, T dbObjAntigo)  {return true;} //verificação extra

    public void excluirExtra(T dbObj) {} //verificação extra

    public boolean incluir(Integer id, T dbObj)  {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        id = Math.abs(id);
        while (contemId(id)) {
            id = Math.abs(rand.nextInt());
        }

        if ( !verificarIncluir(id, dbObj))
            return false;

        dbObj.setId(id);
        dbMap.put(id, dbObj);
        return true;

    }

    public boolean incluir(T dbObj) {
        return incluir(dbObj.getId(), dbObj);
    }


    public boolean atualizar(Integer id, T dbObj) {
        if (contemId(id) && verificarAtualizar(id, dbObj, this.obter(id))) {
            dbObj.setId(id);
            dbMap.put(id, dbObj);
            return true;
        }
        return false;
    }

    public boolean atualizar(T dbObj) {
        return atualizar(dbObj.getId(), dbObj);
    }

    public boolean excluir(Integer id) {
        if (dbMap.containsKey(id)) {
            excluirExtra(dbMap.get(id));
            dbMap.remove(id);
            return true;
        }
        return false;
    }

    public boolean excluir(T dbObj) {
        return excluir(dbObj.getId());
    }


    public T obter(Integer id) {
        return dbMap.get(id);
    }


    public Collection<T> obterTodos() {
        return dbMap.values();
    }


    public boolean contemId(Integer id) {
        return dbMap.containsKey( id );
    }

    public boolean contem(T dbObj) {
        return dbMap.containsValue( dbObj );
    }

}
