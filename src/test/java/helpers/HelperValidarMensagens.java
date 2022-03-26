package helpers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class HelperValidarMensagens {

    public static void ValidarMensagemErro(Response response, String chave, String valor){

        JsonPath js = new JsonPath(response.asString());
        System.out.println(response.asString());
        Map<Object, String> conteudoRetornado = js.get("erros");
        Assert.assertEquals(conteudoRetornado.get(chave).toString(), valor);

    }
}
