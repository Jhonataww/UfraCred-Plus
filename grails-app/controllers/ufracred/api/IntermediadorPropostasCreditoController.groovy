package ufracred.api

import enums.api.LoteEnum
import enums.api.StatusEnum
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
@Secured(['ROLE_ADMIN'])
class IntermediadorPropostasCreditoController {

    IntermediadorPropostasCreditoService intermediadorPropostasCreditoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond intermediadorPropostasCreditoService.list(params), model:[intermediadorPropostasCreditoCount: intermediadorPropostasCreditoService.count()]
    }

    def show(Long id) {
        respond intermediadorPropostasCreditoService.get(id)
    }

    @Transactional
    def save(IntermediadorPropostasCredito intermediadorPropostasCredito) {
        if (intermediadorPropostasCredito == null) {
            render status: NOT_FOUND
            return
        }
        if (intermediadorPropostasCredito.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond intermediadorPropostasCredito.errors
            return
        }

        try {
            intermediadorPropostasCreditoService.save(intermediadorPropostasCredito)
        } catch (ValidationException e) {
            respond intermediadorPropostasCredito.errors
            return
        }

        respond intermediadorPropostasCredito, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(IntermediadorPropostasCredito intermediadorPropostasCredito) {
        if (intermediadorPropostasCredito == null) {
            render status: NOT_FOUND
            return
        }
        if (intermediadorPropostasCredito.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond intermediadorPropostasCredito.errors
            return
        }

        try {
            intermediadorPropostasCreditoService.save(intermediadorPropostasCredito)
        } catch (ValidationException e) {
            respond intermediadorPropostasCredito.errors
            return
        }

        respond intermediadorPropostasCredito, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || intermediadorPropostasCreditoService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def envioLoteProposta(){
        def lote = Proposta.findAllByStatus(StatusEnum.LOTE.value())
        respond lote, [status: OK]
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def recebimentoLoteProposta(){

        def json = request?.JSON
        def ids = json?.collect { it.id }
        List<Proposta> propostas = Proposta?.findAllByIdInList(ids)
        def lotes = propostas?.collect { it.lote }?.unique()
        def lote = Lote?.findAllByIdInList(lotes)

        if(!propostas){
            println("Erro ao processar lote de propostas. Proposta não existe!")
            render status: UNPROCESSABLE_ENTITY
            return
        }

        if(!(lotes.size() == lotes.size())){
            println("Erro ao processar lote de propostas. Existem propostas com lotes diferentes. Em propostas: " + lotes + " Em lote BancoDados: " + lote)
            render status: UNPROCESSABLE_ENTITY
            return
        }

        if( !(propostas.findAll { it.status != StatusEnum.LOTE_EFETIVADO.value() }?.size() == propostas?.size()) ){
            println("Erro ao processar lote de propostas. Proposta não está com status LOTE_EFETIVADO. lote: " + propostas.lote)
            render status: UNPROCESSABLE_ENTITY
            return
        }

        json.each { proposta ->
            def propostaAtual = propostas.find { it.id == proposta.id }
            if (propostaAtual) {
                propostaAtual.dataOperacao = new Date()
                propostaAtual.quantidadeParcelas = proposta.quantidadeParcelas
                propostaAtual.taxaOperacao = proposta.taxaOperacao
                propostaAtual.valorPrincipal = proposta.valorPrincipal
                propostaAtual.valorJuros = proposta.valorJuros
                propostaAtual.valorReajuste = proposta.valorReajuste
                propostaAtual.valorParcela = proposta.valorParcela
                propostaAtual.modalidade = proposta.modalidade
                propostaAtual.finalidade = proposta.finalidade
                propostaAtual.localizacao = proposta.localizacao
                propostaAtual.restritivos = proposta.restritivos
                propostaAtual.status = StatusEnum.LOTE_EFETIVADO.value()
                propostaAtual.tipoProposta = proposta.tipoProposta
                propostaAtual.checkLists = proposta.checkLists
                propostaAtual.numeroContrato = proposta.numeroContrato
                propostaAtual.numeroAditivo = proposta.numeroAditivo
                //propostaAtual.dataContrato = proposta.dataContrato
                //propostaAtual.dataPrimeiroVencimento = proposta.dataPrimeiroVencimento
                propostaAtual.assessor = proposta.assessor
                propostaAtual.cliente = proposta.cliente
                propostaAtual.nomeAssessor = proposta.nomeAssessor
                propostaAtual.nomeCliente = proposta.nomeCliente
                propostaAtual.receitaOperacional = proposta.receitaOperacional
                propostaAtual.receitaNaoOperacional = proposta.receitaNaoOperacional
                propostaAtual.custoMercadoria = proposta.custoMercadoria
                propostaAtual.pagamentoPessoal = proposta.pagamentoPessoal
                propostaAtual.pagamentoFuncionarios = proposta.pagamentoFuncionarios
                propostaAtual.transporte = proposta.transporte
                propostaAtual.agua = proposta.agua
                propostaAtual.luz = proposta.luz
                propostaAtual.telefone = proposta.telefone
                propostaAtual.taxaAluguel = proposta.taxaAluguel
                propostaAtual.outrosCustos = proposta.outrosCustos
                propostaAtual.fluxoCaixa = proposta.fluxoCaixa
                propostaAtual.outrasDespesas = proposta.outrasDespesas
                propostaAtual.outrosPagamentos = proposta.outrosPagamentos
                propostaAtual.lote = proposta.lote
                propostaAtual.save(flush: true)
            }
        }
        lote.each { it ->
            it.status = LoteEnum.FINALIZADO.value()
            it.save(flush: true)
        }
        println("Lote Finalizado" + lote)
        render status: OK
    }
}
