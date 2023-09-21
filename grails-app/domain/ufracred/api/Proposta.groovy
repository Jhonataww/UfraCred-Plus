package ufracred.api


class Proposta {

    Date dataOperacao
    Date dataPrimeiroVencimento
    Integer quantidadeParcelas
    Double taxaOperacao
    Double valorPrincipal
    Double valorJuros
    Double valorReajuste
    Double valorParcela
    String modalidade
    String finalidade
    String localizacao
    String restritivos
    String status
    String tipoProposta
    String checkLists
    Long numeroContrato
    Long numeroAditivo
    Date dataContrato
    Integer assessor
    Integer cliente
    String nomeAssessor
    String nomeCliente

    Double receitaOperacional
    Double receitaNaoOperacional
    Double custoMercadoria
    Double pagamentoPessoal
    Double pagamentoFuncionarios
    Double transporte
    Double agua
    Double luz
    Double telefone
    Double taxaAluguel
    Double outrosCustos
    Double fluxoCaixa
    Double outrasDespesas
    Double outrosPagamentos

    static hasMany = [comites: Comite]

    static constraints = {

        comites cascade: 'all-delete-orphan'
        dataContrato nullable: true
        dataOperacao nullable: true
        dataPrimeiroVencimento nullable: true
        quantidadeParcelas nullable: true
        taxaOperacao nullable: true
        valorPrincipal nullable: true
        valorJuros nullable: true
        valorReajuste nullable: true
        valorParcela nullable: true
        modalidade nullable: true
        finalidade nullable: true
        localizacao nullable: true
        restritivos nullable: true
        status nullable: true
        tipoProposta nullable: true
        checkLists nullable: true
        numeroContrato nullable: true
        numeroAditivo nullable: true
        nomeAssessor nullable: true
        nomeCliente nullable: true
        receitaOperacional nullable: true
        receitaNaoOperacional nullable: true
        custoMercadoria nullable: true
        pagamentoPessoal nullable: true
        pagamentoFuncionarios nullable: true
        transporte nullable: true
        agua nullable: true
        luz nullable: true
        telefone nullable: true
        taxaAluguel nullable: true
        outrosCustos nullable: true
        fluxoCaixa nullable: true
        outrasDespesas nullable: true
        outrosPagamentos nullable: true
        assessor nullable: true
        cliente nullable: true
    }
}
