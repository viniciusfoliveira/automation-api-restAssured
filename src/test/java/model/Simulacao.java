package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Simulacao {

    private String cpf;
    private String email;
    private String nome;
    private int parcelas;
    private boolean seguro;
    private double valor;

    private Map<Object, Object> simulacao = new HashMap<>();

    @Builder()
    public Simulacao(String cpf, String email, String nome, int parcelas ,boolean seguro, double valor){

        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.parcelas = parcelas;
        this.seguro = seguro;
        this.valor = valor;

        simulacao.put("cpf", cpf);
        simulacao.put("nome", nome);
        simulacao.put("email", email);
        simulacao.put("parcelas", parcelas);
        simulacao.put("seguro", seguro);
        simulacao.put("valor", valor);
    }
}
