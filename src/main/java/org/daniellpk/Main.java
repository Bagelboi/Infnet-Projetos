package org.daniellpk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.daniellpk.controller.*;
import org.daniellpk.model.api.Cidade;
import org.daniellpk.model.api.IbgeApi;
import org.daniellpk.model.grupo.Estrutura;
import org.daniellpk.model.grupo.Pelotao;
import org.daniellpk.model.misc.*;
import org.daniellpk.model.peao.Soldado;
import org.daniellpk.model.peao.Veiculo;
import org.daniellpk.service.*;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    //services
    public static BracoService bracoService = new BracoService();
    public static ComandanteService comandanteService = new ComandanteService();
    public static EstruturaService estruturaService = new EstruturaService();
    public static PelotaoService pelotaoService = new PelotaoService();
    public static SoldadoService soldadoService = new SoldadoService();
    public static VeiculoService veiculoService = new VeiculoService();

    public static CidadeService cidadeService = new CidadeService();
    //controllers
    public static BracoController bracoControl = new BracoController(bracoService);
    public static ComandanteController comandanteControl = new ComandanteController(comandanteService);
    public static PelotaoController pelotaoControl = new PelotaoController(pelotaoService);
    public static EstruturaController estruturaControl = new EstruturaController(estruturaService);
    public static SoldadoController soldadoControl = new SoldadoController(soldadoService);
    public static VeiculoController veiculoControl = new VeiculoController(veiculoService);


    public static Gson gsonInstance = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .registerTypeAdapter(InheritedKey.class, new InheritedKeyTypeAdapter())
            .setDateFormat("dd/MM/YYYY")
            .create();

    public static void main(String[] args)  {

        carregarExemplos();

        //Logica Spark
        port(8080);

        get("/", (req, res) -> {
            return VelocityManager.getPagina("index.vm");
        }, new VelocityTemplateEngine());


        path("/api", () -> {
            pelotaoControl.pathApi("/pelotao");
            estruturaControl.pathApi("/estrutura");
            soldadoControl.pathApi("/soldado");
            comandanteControl.pathApi("/comandante");
            bracoControl.pathApi("/braco");
            pelotaoControl.pathApi("/pelotao");
            veiculoControl.pathApi("/veiculo");
        });
    }

    private static void carregarExemplos() {
        Pelotao p = new Pelotao();
        Soldado s = new Soldado();
        Estrutura e = new Estrutura();
        Comandante c = new Comandante();
        Braco b = new Braco();
        Veiculo v = new Veiculo();

        p.addPeao(s, 4);
        p.addPeao(v, 1);
        p.setBraco_id(b.getId());
        p.setCidade_id(1600303);
        System.out.println(Main.gsonInstance.toJson(p));
        b.setNome("cool squad");
        b.setComandante_id(c.getId());
        c.setNome("Comandante");
        c.setBraco_id(b.getId());
        e.setNivel_seguranca(4);
        e.setCidade_id(1600303);
        v.setPassageiros_max(8);
        v.setVelocidade_kmh(200);
        s.setSalario(50);
        s.setNome("BOPE");

        soldadoService.incluir(s);
        veiculoService.incluir(v);
        bracoService.incluir(b);
        comandanteService.incluir(c);
        estruturaService.incluir(e);
        pelotaoService.incluir(p);

    }

}
