#language: pt

  @todos
Funcionalidade: Simulação de crédito
  Eu como usuario da api
  Gostaria de cadastrar um simulação
  Para que eu consiga guardar crédito como valor, parcelas e dados de contato

  Cenario: Validar cadastro de simulação com sucesso
    Dado que eu esteja com a url para simulacao
    Quando enviar as informações da simulacao correta
    Entao validar o status code da simulacao 201
    E validar o contrato retornado


  Cenario: Validar cadastro de simulação com cpf existente
    Dado que eu esteja com a url para simulacao
    Quando enviar as informacoes da simulacao com cpf ja existente
    Entao validar o status code da simulacao 400
    E validar a critica retornada "CPF duplicado"


  Cenario: Validar cadastro de simulação com parcelas não respeitando a regra
    Dado que eu esteja com a url para simulacao
    Quando enviar as informacoes da simulacao com informacoes invalidas
    Entao validar o status code da simulacao 400
    E validar a mensagem retornada com o campo "parcelas" e a valor "Parcelas deve ser igual ou maior que 2"

  Cenario: Validar cadastro de simulação alterando uma simulação existente
    Dado que eu esteja com a url para simulacao
    Quando alterar as informações da simulacao
    Entao validar o status code da simulacao 200
    E validar o contrato retornado


  Cenario: Validar cadastro de simulação alterando uma simulação existente
    Dado que eu esteja com a url para simulacao
    Quando alterar as informacoes porem com cpf que nao possui simulacao
    Entao validar o status code da simulacao 404
    E validar a critica retornada "CPF 460.930.788-08 não encontrado"


  Cenario: Validar a consulta de um cpf que possui simulação
    Dado que eu esteja com a url para simulacao
    Quando acessar a requisicao da simulacao
    Entao validar o status code da simulacao 200


  Cenario: Validar a consulta de um cpf que não possui simulação
    Dado que eu esteja com a url para simulacao
    Quando acessar a requisicao da simulacao com cpf que não possui simulacao
    Entao validar o status code da simulacao 404
    E validar a critica retornada "CPF 460.930.788-08 não encontrado"


  Cenario: Validar retornando todas as simulações
    Dado que eu esteja com a url para simulacao
    Quando acessar a requisicao retornando todas as simulacoes
    Entao validar o status code da simulacao 200


  Cenario: Validar a exclusão de uma simulação
    Dado que eu esteja com a url para simulacao
    Quando acessar a requisicao para excluir a simulacao
    Entao validar o status code da simulacao 200

  Cenario: Validar a exclusão de uma simulação
    Dado que eu esteja com a url para simulacao
    Quando acessar a requisicao para excluir a simulacao com id invalido
    Entao validar o status code da simulacao 200