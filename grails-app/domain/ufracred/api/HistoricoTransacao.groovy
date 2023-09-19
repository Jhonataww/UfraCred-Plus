package ufracred.api

class HistoricoTransacao {

    Long idProposta
    Date dataOperacaoProposta
    Date dataPrimeiroVencimentoProposta
    Integer quantidadeParcelasProposta
    Double taxaOperacaoProposta
    Double valorPrincipalProposta
    Double valorJurosProposta
    Double valorReajusteProposta
    Double valorParcelaProposta
    String modalidadeProposta
    String finalidadeProposta
    String localizacaoProposta
    String restritivosProposta
    String statusProposta
    String tipoPropostaProposta
    String checkListsProposta
    Long numeroContratoProposta
    Long numeroAditivoProposta
    Date dataContratoProposta
    Integer assessorProposta
    Integer clienteProposta
    String nomeAssessorProposta
    String nomeClienteProposta

    Date dataOperacaoTransacao
    String nomeAssessorLogado
    String statusTransacao


    HistoricoTransacao(Proposta proposta, String nomeAssessorLogado, String status) {
        this.idProposta = proposta.id
        this.dataOperacaoProposta = proposta.dataOperacao
        this.dataPrimeiroVencimentoProposta = proposta.dataPrimeiroVencimento
        this.quantidadeParcelasProposta = proposta.quantidadeParcelas
        this.taxaOperacaoProposta = proposta.taxaOperacao
        this.valorPrincipalProposta = proposta.valorPrincipal
        this.valorJurosProposta = proposta.valorJuros
        this.valorReajusteProposta = proposta.valorReajuste
        this.valorParcelaProposta = proposta.valorParcela
        this.modalidadeProposta = proposta.modalidade
        this.finalidadeProposta = proposta.finalidade
        this.localizacaoProposta = proposta.localizacao
        this.restritivosProposta = proposta.restritivos
        this.statusProposta = proposta.status
        this.tipoPropostaProposta = proposta.tipoProposta
        this.checkListsProposta = proposta.checkLists
        this.numeroContratoProposta = proposta.numeroContrato
        this.numeroAditivoProposta = proposta.numeroAditivo
        this.dataContratoProposta = proposta.dataContrato
        this.assessorProposta = proposta.assessor
        this.clienteProposta = proposta.cliente
        this.nomeAssessorProposta = proposta.nomeAssessor
        this.nomeClienteProposta = proposta.nomeCliente
        this.dataOperacaoTransacao = new Date();
        this.nomeAssessorLogado = nomeAssessorLogado
        this.statusTransacao = status
    }

    static constraints = {
        valorReajusteProposta nullable: true, blank: true
    }
}
