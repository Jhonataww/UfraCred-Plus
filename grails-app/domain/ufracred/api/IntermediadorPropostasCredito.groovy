package ufracred.api

class IntermediadorPropostasCredito {

    Date criacao
    Date atualizacao
    String status
    String descricao
    String log
    String parecerAprovacao
    String parecerReprovacao
    String parecerErro
    Integer lote
    Integer quantidadeProposta
    Integer quantidadePropostaAprovada
    Integer quantidadePropostaReprovada
    Integer quantidadePropostaErro
    Integer quantidadePropostaProcessada
    Integer quantidadePropostaPendente
    Integer quantidadePropostaCancelada
    Integer quantidadePropostaEmAnalise
    Integer quantidadePropostaEmProcessamento


    static constraints = {
        criacao nullable: true
        atualizacao nullable: true
        status nullable: true
        descricao nullable: true
        log nullable: true
        parecerAprovacao nullable: true
        parecerReprovacao nullable: true
        parecerErro nullable: true
        lote nullable: true
        quantidadeProposta nullable: true
        quantidadePropostaAprovada nullable: true
        quantidadePropostaReprovada nullable: true
        quantidadePropostaErro nullable: true
        quantidadePropostaProcessada nullable: true
        quantidadePropostaPendente nullable: true
        quantidadePropostaCancelada nullable: true
        quantidadePropostaEmAnalise nullable: true
        quantidadePropostaEmProcessamento nullable: true
    }
}
