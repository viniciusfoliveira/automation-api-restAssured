package steps;

import helpers.*;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Requisicao;
import model.Simulacao;
import org.junit.Assert;
import org.junit.BeforeClass;

public class SimulacaoStep {

    private String url;
    private Response response;
    private Simulacao simulacao;
    private static int idSimulacao;

    @BeforeClass()
    public static void setup() {

        Simulacao simulacao = Simulacao.builder()
                .cpf("460.930.788-05")
                .nome("vinicius")
                .email("viniciusferreira@gmail.com")
                .parcelas(3)
                .valor(1000)
                .seguro(true)
                .build();
        Requisicao requisicao = new Requisicao(HelperLerProperties.getProperty("api.url"), "simulacoes");
        HelperRestApi.post(requisicao, simulacao.getSimulacao());

        simulacao = Simulacao.builder()
                .cpf("460.930.788-07")
                .nome("vinicius")
                .email("viniciusferreira@gmail.com")
                .parcelas(3)
                .valor(1000)
                .seguro(true)
                .build();

        Response response = HelperRestApi.post(requisicao, simulacao.getSimulacao());
        JsonPath js = new JsonPath(response.asString());
        idSimulacao = Integer.parseInt(js.get("id").toString());
        System.out.println(idSimulacao);
    }
    @Dado("que eu esteja com a url para simulacao")
    public void queEuEstejaComAUrlParaSimulacao() { url = HelperLerProperties.getProperty("api.url"); }

    @Quando("enviar as informações da simulacao correta")
    public void enviarAsInformacesDaSimulacaoCorreta() {

        simulacao = Simulacao.builder()
                .cpf(HelperGerarCPF.cpf(true))
                .nome("vinicius")
                .email("viniciusferreira@gmail.com")
                .parcelas(3)
                .valor(1000)
                .seguro(true)
                .build();

        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.post(requisicao, simulacao.getSimulacao());
        System.out.println(response.asString());
    }

    @Entao("validar o status code da simulacao {int}")
    public void validarOStatusCodeDaSimulacao(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }
    @Entao("validar o contrato retornado")
    public void validarOContratoRetornado() {
        HelperValidacaoContrato.validarDadosRetornado(simulacao, response);
    }

    @Quando("enviar as informacoes da simulacao com cpf ja existente")
    public void enviarAsInformaceesDaSimulacaoComCpfJaExistente() {
        simulacao = Simulacao.builder()
                .cpf("460.930.788-09")
                .nome("vinicius")
                .email("viniciusferreira@gmail.com")
                .parcelas(3)
                .valor(1000)
                .seguro(true)
                .build();
        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.post(requisicao, simulacao.getSimulacao());
    }

    @Entao("validar a critica retornada {string}")
    public void validarACriticaRetornada(String mensagem) {
        JsonPath js = new JsonPath(response.asString());
        Assert.assertEquals(js.get("mensagem").toString(), mensagem);
    }

    @Quando("enviar as informacoes da simulacao com informacoes invalidas")
    public void enviarAsInformacoesDaSimulacaoComInformacoesInvalidas() {
        simulacao = Simulacao.builder()
                .cpf(HelperGerarCPF.cpf(true))
                .nome("vinicius")
                .email("viniciusferreira@gmail.com")
                .parcelas(1)
                .valor(10)
                .seguro(true)
                .build();

        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.post(requisicao, simulacao.getSimulacao());
    }

    @Entao("validar a mensagem retornada com o campo {string} e a valor {string}")
    public void validarAMensagemRetornadaComOCampoEAValor(String chave, String valor) {
        HelperValidarMensagens.ValidarMensagemErro(response,chave, valor);
    }

    @Quando("alterar as informações da simulacao")
    public void alterarAsInformaceesDaSimulacao() {
        simulacao = Simulacao.builder()
                .cpf("460.930.788-09")
                .nome("vinicius")
                .email("viniciusferreira@gmail.com")
                .parcelas(6)
                .valor(800)
                .seguro(true)
                .build();

        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.put(requisicao, simulacao.getSimulacao(), "460.930.788-09");
    }

    @Quando("alterar as informacoes porem com cpf que nao possui simulacao")
    public void alterarAsInformaceesPoremComCpfQueNaoPossuiSimulacao() {

        simulacao = Simulacao.builder()
                .cpf("460.930.788-09")
                .nome("vinicius")
                .email("viniciusferreira@gmail.com")
                .parcelas(6)
                .valor(800)
                .seguro(true)
                .build();

        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.put(requisicao, simulacao.getSimulacao(), "460.930.788-08");
    }

    @Quando("acessar a requisicao da simulacao")
    public void acessarARequisicaoDaSimulacao() {
        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.get(requisicao, "460.930.788-09");
    }
    @Quando("acessar a requisicao da simulacao com cpf que não possui simulacao")
    public void acessarARequisicaoDaSimulacaoComCpfQueNaoPossuiSimulacao() {

        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.get(requisicao, "460.930.788-08");
    }

    @Quando("acessar a requisicao retornando todas as simulacoes")
    public void acessarARequisicaoRetornandoTodasAsSimulacees() {
        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.getAll(requisicao);
    }

    @Quando("acessar a requisicao para excluir a simulacao")
    public void acessarARequisicaoParaExcluirASimulacao() {
        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.delete(requisicao, idSimulacao);
    }

    @Quando("acessar a requisicao para excluir a simulacao com id invalido")
    public void acessarARequisicaoParaExcluirASimulacaoComIdInvalido() {
        Requisicao requisicao = new Requisicao(url, "simulacoes");
        response = HelperRestApi.delete(requisicao, -1);
    }
}
