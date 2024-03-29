package org.daniellpk.model.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.daniellpk.Main;
import org.daniellpk.model.misc.Utils;

import java.io.IOException;
import java.lang.reflect.Type;

public class IbgeApi {
    private static final String API_LINK = "https://servicodados.ibge.gov.br/api/v1/localidades";
    private static final String CIDADE_PATH = "/municipios";
    private static final String ESTADO_PATH = "/estados";


    public static Cidade getCidade(Integer id)  {
        if (id < 0) {
            return new Cidade();
        }
        if (Main.cidadeService.contemId(id))
            return Main.cidadeService.obter(id);
        System.out.println(API_LINK + CIDADE_PATH + '/' + id.toString());
        JsonElement jsonEl = JsonParser.parseString(Utils.getFromApi(API_LINK + CIDADE_PATH + '/' + id.toString()));
        JsonObject json = null;
        if (!jsonEl.isJsonObject()) {
            return new Cidade();
        }
        json = jsonEl.getAsJsonObject();
        Cidade cidade_nova = new Cidade();
        cidade_nova.setId(id);
        cidade_nova.setId_api(id);
        cidade_nova.setNome(json.get("nome").getAsString());
        cidade_nova.setEstado_id( json.get("microrregiao")
                .getAsJsonObject()
                .get("mesorregiao")
                .getAsJsonObject()
                .get("UF")
                 .getAsJsonObject()
                 .get("id")
                .getAsInt() );
        Main.cidadeService.incluir(cidade_nova);
        return cidade_nova;
    }

    public static Estado getEstado(Integer id)  {
        JsonElement jsonEl = JsonParser.parseString(Utils.getFromApi(API_LINK + ESTADO_PATH + '/' + id.toString()));
        JsonObject json = null;
        if (!jsonEl.isJsonObject()) {
            return new Estado();
        }
        json = jsonEl.getAsJsonObject();
        Estado estado = new Estado();
        estado.setId(id);
        estado.setId_api(id);
        estado.setNome(json.get("nome").getAsString());
        return estado;
    }

}
