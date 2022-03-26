package steps;

import helpers.HelperLerProperties;
import helpers.HelperRestApi;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Requisicao;
import org.junit.Assert;

public class RestricaoStep {

    private String url;
    private Response response;

    @Dado("que eu esteja com a url")
    public void queEuEstejaComAUrl() { url = HelperLerProperties.getProperty("api.url"); }

    @Quando("acessar a requisicao informando o cpf {string}")
    public void acessarARequisicaoInformandoOCpf(String cpf) {
        Requisicao requisicao = new Requisicao(url, "restricoes");
        response = HelperRestApi.get(requisicao, cpf);
    }


    @Entao("validar a mensagem {string}")
    public void validarAMensagem(String mensagem) {
        JsonPath js = new JsonPath(response.asString());
        Assert.assertEquals(js.get("mensagem"), mensagem);
    }

    @Entao("validar o status code {int}")
    public void validarOStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);

    }
}
