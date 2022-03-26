#language: pt

  @todos
Funcionalidade: Consultar CPF's
   Eu como usuario da api
   Gostaria de consultar cpf's com e sem restrição
   Para que eu consiga verificar a situação do cpf

  Cenario: Validar a consulta de um cpf com restrição
    Dado que eu esteja com a url
    Quando acessar a requisicao informando o cpf "97093236014"
    Entao validar a mensagem "O CPF 97093236014 tem problema"
    E validar o status code 200

  Cenario: Validar a consulta de um cpf sem restrição
    Dado que eu esteja com a url
    Quando acessar a requisicao informando o cpf "46093078809"
    Entao validar o status code 204