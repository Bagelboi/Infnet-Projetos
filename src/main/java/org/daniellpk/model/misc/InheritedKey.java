package org.daniellpk.model.misc;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.daniellpk.Main;
import org.daniellpk.model.grupo.Grupo;
import org.daniellpk.model.peao.Peao;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Objects;

public class InheritedKey implements Serializable {
    public Integer id = -1;
    public String classname = "";

    public InheritedKey(Integer id, String classname) {
        this.id = id;
        this.classname = classname;
    }

    public static InheritedKey parse(DbObj obj) {
        return new InheritedKey(obj.getId(), obj.getClass().getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InheritedKey)) {
            return false;
        }
        InheritedKey ik = (InheritedKey) obj;
        return this.id == ik.id && this.classname == ik.classname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.classname);
    }
}

