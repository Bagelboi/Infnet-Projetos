package org.daniellpk.controller;

import org.daniellpk.Main;
import org.daniellpk.model.misc.DbObj;
import org.daniellpk.model.misc.VelocityManager;
import org.daniellpk.service.DbService;
import spark.*;
import spark.template.velocity.VelocityTemplateEngine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static spark.Spark.*;

public class DbController<T extends DbService> {
    protected T dbService;
    private final Type dbObjClass;
    public DbController(T _dbService, Type dbObjClass) {
        this.dbService = _dbService;
        this.dbObjClass = dbObjClass;
        System.out.println(dbObjClass.getTypeName());
    }

    public String incluir(Request req, Response res) {
        dbService.incluir(Main.gsonInstance.fromJson( req.body(), dbObjClass ) );
        return "";
    }

    public String atualizar( Request req, Response res ) {
        dbService.atualizar( Integer.parseInt( req.params("id") ), Main.gsonInstance.fromJson( req.body(), dbObjClass ) );
        return "";
    }

    public String excluir( Request req, Response res ) {
        dbService.excluir( Integer.parseInt( req.params("id") ) );
        return "";
    }

    public String get(Request req, Response res) {
        return dbService.obter( Integer.parseInt( req.params("id") ) ).toString();
    }

    public String getAll(Request req, Response res) {
        StringBuilder objs = new StringBuilder();
        for ( Object dbObj : dbService.obterTodos() )
            objs.append(dbObj.toString() + '\n');
        return objs.toString();
    }

    public ModelAndView getAllHTML(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();

        List<DbObj> dbObjList = new ArrayList<>();
        DbObj dbobj = null; //polimorfisa

        for ( Object dbObji : dbService.obterTodos() ) {
            dbobj = (DbObj) dbObji;
            dbObjList.add(dbobj);
        }

        if (dbobj != null) {
            model.put("tableheaders", dbobj.getTableHeaders());
            model.put("dataname", dbobj.getDataName());
        }
        else
            model.put("dataname", "Nenhum registro do objeto encontrado ainda!");
        model.put("tableitems", dbObjList);

        return VelocityManager.getPagina("tabela.vm", model);
    }

    public ModelAndView getHTML(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();

        DbObj dbobj = dbService.obter( Integer.parseInt( req.params("id") ) );
        if (dbobj != null) {
            List<DbObj> dbObjList = new ArrayList<>();
            dbObjList.add(dbobj);
            model.put("tableheaders", dbobj.getTableHeaders());
            model.put("dataname", dbobj.getDataName() + "(ID:" + dbobj.getId() + ")");
            model.put("tableitems", dbObjList);
        } else
            model.put("dataname", "Nenhum objeto com esse ID encontrado!");

        return VelocityManager.getPagina("tabela.vm", model);
    }

    /*
    Para formularios POST no futuro
    public ModelAndView incluirHTML(Request req, Response res) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HashMap<String, Object> model = new HashMap<>();
        model.put("inputfields", dbObjClass.getDeclaredConstructor().newInstance().getFormInputs());
        return VelocityManager.getPagina("formulario.vm", model);
    }
    */

    public void pathApi(String pathroot) {
        Spark.path(pathroot, () -> {
            Spark.get("/all", (req, res) -> {return this.getAllHTML(req, res);}, new VelocityTemplateEngine());
            Spark.get("/:id", (req, res) -> { return this.getHTML(req, res); }, new VelocityTemplateEngine() );
            Spark.post("/incluir", (req, res) -> this.incluir(req, res));
            Spark.put("/atualizar/:id", (req, res) -> this.atualizar(req, res));
            Spark.delete("/excluir/:id", (req, res) -> this.excluir(req, res));
            //Futuro : Spark.get("/criar", (req, res) -> {return this.incluirHTML(req, res);}, new VelocityTemplateEngine());

            Spark.post("/excluirPOST/:id", (req, res) -> { this.excluir(req, res); res.redirect("../all");  return ""; }); // para o HTML só
        });
    }
}
