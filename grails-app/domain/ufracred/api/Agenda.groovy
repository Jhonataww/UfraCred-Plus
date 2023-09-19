package ufracred.api

class Agenda {

    Date dataInicial
    Date dataFinal
    Date checkin
    Date checkout
    Date conclusao
    Date cancelamento
    Date atualizacao
    String tipoVisita
    String tipoEndereco
    String situacao
    String resumo
    String parecer
    String descricao
    String endereco
    String referencia
    Integer excluido
    String cep

    static belongsTo = [assessor: Assessor, cliente: Cliente]

    static constraints = {
        dataInicial nullable: false
        dataFinal nullable: false
        checkin nullable: false
        checkout nullable: false
        conclusao nullable: false
        cancelamento nullable: false
        atualizacao nullable: false
        tipoVisita nullable: false
        tipoEndereco nullable: false
        situacao nullable: false
        resumo nullable: false
        parecer nullable: false
        descricao nullable: false
        endereco nullable: false
        referencia nullable: false
        excluido nullable: false
        cep nullable: false



    }
}
