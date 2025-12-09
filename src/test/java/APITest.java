import com.fasterxml.jackson.databind.ObjectMapper;
import org.dlpk.Exercicio3.Endereco;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class APITest {
    private final String URL = "https://viacep.com.br/";

    private HttpResponse<String> sendRequest(String path) throws IOException, InterruptedException {
        //https://www.twilio.com/pt-br/blog/5-maneiras-de-fazer-uma-chamada-http-em-java
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                        URI.create(URL + path + "/json/"))
                .header("accept", "application/json")
                .build();
        System.out.println(request.uri());
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }


    @Test
    public void cepInvalido() throws IOException, InterruptedException {
        String[] ceps = { "344", "123455664745", "3234ola11", "" };
        for (String cep : ceps)
            assertEquals(400, sendRequest("ws/" + cep).statusCode());
    }

    @Test
    public void consultaEndereco() throws IOException, InterruptedException {
        Endereco[] enderecos = {
                new Endereco("SP", "Sao Paulo", "Praca da Se"),
                new Endereco("RS", "Porto Alegre", "Domingos")
        };

        for (Endereco endereco : enderecos) {
            assertEquals(200, sendRequest("ws/" + endereco.toPath()).statusCode());
        }
    }

    @Test
    //DEMORADO!!!
    public void enderecosDecisionTable() throws IOException, InterruptedException {
        Endereco[] enderecos = {
                new Endereco("SP", "Sao Paulo", "Praca da Se"),
                new Endereco("RS", "Porto Alegre", "Domingos")
        };

        //Variaveis de query validos e invalidos para cada endereço
        Map<String, String[]> uf_validos = Map.of(
                "SP", new String[]{"SP", "sp"},
                "RS", new String[]{"RS", "rs"}
        );

        Map<String, String[]> uf_invalido = Map.of(
                "SP", new String[]{"S P", "Sao Paulo"},
                "RS", new String[]{"R S", "Rio Grande do Sul"}
        );

        Map<String, String[]> cidade_validos = Map.of(
                "Sao Paulo", new String[]{"São Paulo", "sao paulo"},
                "Porto Alegre", new String[]{"Porto Aleg", "porto alegre"}
        );

        Map<String, String[]> cidade_invalida = Map.of(
                "Sao Paulo", new String[]{"SaoPaulo", "SP"},
                "Porto Alegre", new String[]{"PortoAlegre", "port0"}
        );

        Map<String, String[]> rua_validos = Map.of(
                "Praca da Se", new String[]{"Praca da Se", "praca da se"},
                "Domingos", new String[]{"Domingo", "domingos"}
        );

        Map<String, String[]> rua_invalida = Map.of(
                "Praca da Se", new String[]{"", "pracadase"},
                "Domingos", new String[]{"", "Dom ingos"}
        );


        for (Endereco end : enderecos) {
            //Unir validos e invalidos
            String[] allUF = Stream.concat(
                    Arrays.stream(uf_validos.get(end.UF())),
                    Arrays.stream(uf_invalido.get(end.UF()))
            ).toArray(String[]::new);

            String[] allCidades = Stream.concat(
                    Arrays.stream(cidade_validos.get(end.cidade())),
                    Arrays.stream(cidade_invalida.get(end.cidade()))
            ).toArray(String[]::new);

            String[] allRuas = Stream.concat(
                    Arrays.stream(rua_validos.get(end.rua())),
                    Arrays.stream(rua_invalida.get(end.rua()))
            ).toArray(String[]::new);

            for (String uf : allUF) {
                boolean uf_valida = Arrays.asList(uf_validos.get(end.UF())).contains(uf); // ve se a string testada esta na lista dos validos
                for (String cidade : allCidades) {
                    boolean cidade_valida = Arrays.asList(cidade_validos.get(end.cidade())).contains(cidade);
                    for (String rua : allRuas) {
                        boolean rua_valida = Arrays.asList(rua_validos.get(end.rua())).contains(rua);

                        boolean path_valido = uf_valida && cidade_valida && rua_valida;

                        HttpResponse<String> res = sendRequest( "ws/" + new Endereco(uf, cidade, rua).toPath() );
                        System.out.println(path_valido);

                        if (path_valido)
                            assertEquals(res.statusCode(), 200);
                        else {
                            if ( res.statusCode() == 200 ) //algumas vezes outras partes do path estão validas e retorna OK porem vazio
                                assertEquals("[]", res.body());
                            else
                                assertEquals(res.statusCode(), 400);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void cepValorLimite() throws IOException, InterruptedException {
        for ( String val : new String[] { "99999999", "00000000" } ) {
            String body = sendRequest("ws/" + val).body();
            assertTrue( body.contains("erro") );
            //Deve retorna erro : true
        }
    }


}
