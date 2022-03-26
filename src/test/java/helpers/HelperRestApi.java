package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Requisicao;

import java.util.Map;

public class HelperRestApi {

    public static Response get(Requisicao requisicao, String cpf) {
        return baseUrl(requisicao.getUrl())
                .when()
                .get(requisicao.getEndpoint() + "/" + cpf);
    }

    public static Response getAll(Requisicao requisicao) {
        return baseUrl(requisicao.getUrl())
                .when()
                .get(requisicao.getEndpoint());
    }

    public static Response delete(Requisicao requisicao, int id) {
        return baseUrl(requisicao.getUrl())
                .when()
                .delete(requisicao.getEndpoint() + "/" + id);
    }

    public static Response post(Requisicao requisicao, Map<Object, Object> simulacao) {
        return baseUrl(requisicao.getUrl())
                .when()
                .body(simulacao)
                .post(requisicao.getEndpoint());
    }

    public static Response put(Requisicao requisicao, Map<Object, Object> simulacao, String cpf) {
        return baseUrl(requisicao.getUrl())
                .when()
                .body(simulacao)
                .put(requisicao.getEndpoint() + "/" + cpf);
    }

    private static RequestSpecification baseUrl(String url){
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .baseUri(url);
    }
}
