package helpers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Simulacao;
import org.junit.Assert;

public class HelperValidacaoContrato {

    public static void validarDadosRetornado(Simulacao simulacao, Response response){
        JsonPath js = new JsonPath(response.asString());
        Assert.assertEquals(js.get("nome").toString(), simulacao.getNome());
        Assert.assertEquals(Integer.parseInt(js.get("parcelas").toString()), simulacao.getParcelas());
        Assert.assertTrue(Double.parseDouble(js.get("valor").toString()) == simulacao.getValor());
        Assert.assertEquals(js.get("email").toString(), simulacao.getEmail());
        Assert.assertEquals(Boolean.parseBoolean(js.get("seguro").toString()), simulacao.isSeguro());
    }
}
