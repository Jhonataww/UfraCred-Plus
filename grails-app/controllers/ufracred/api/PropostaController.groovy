package ufracred.api

import enums.api.CheckListsEnum
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import groovyx.net.http.HTTPBuilder
import org.springframework.beans.factory.annotation.Autowired
import ufracred.api.impl.ComiteServiceImpl

import static org.springframework.http.HttpStatus.*

@ReadOnly
@Secured(['ROLE_ADMIN'])
class PropostaController {

    @Autowired
    UtilsService utilsService

    @Autowired
    IPropostaService propostaService

    @Autowired
    ComiteServiceImpl comiteService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_COORDENADOR'])
    def index(Integer max) {
        if(!utilsService.authentication().authorities.role.findAll{it == "ROLE_COORDENADOR"}){
            def assessor = utilsService.assessorLogado()
            params.nomeAssessor = assessor.nome
            params.comite = "assessor"
        }
        params.max = Math.min(max ?: 10, 100)
        respond propostaService.list(params), model:[propostaCount: propostaService.count(params)]
    }

    def show(Long id) {
        respond propostaService.get(id)
    }

    @Transactional
    def save(Proposta proposta) {
        if (proposta == null) {
            render status: NOT_FOUND
            return
        }
        if (proposta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond proposta.errors
            return
        }
        try {
            propostaService.save(proposta)

            //utilsService.historicoTransacao(proposta, "Save")
        } catch (ValidationException e) {
            respond proposta.errors
            return
        }
        respond proposta, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Proposta proposta) {
        if (proposta == null) {
            render status: NOT_FOUND
            return
        }
        if (proposta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond proposta.errors
            return
        }

        try {
            proposta.checkLists = CheckListsEnum.ATUALIZACAO.value()
            utilsService.nomeProposta(proposta)
            //utilsService.historicoTransacao(proposta, "update")
            propostaService.save(proposta)
        } catch (ValidationException e) {
            respond proposta.errors
            return
        }
        respond proposta, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        try{
            if(Comite?.findByProposta(Proposta?.findById(id))?.excluido == 1){
                comiteService.delete(Comite?.findByProposta(Proposta?.findById(id))?.id)
            }
            if (id == null || propostaService.delete(id) == null) {
                render status: NOT_FOUND
                return
            }
            //utilsService.historicoTransacao(Proposta.findById(id), "delete")
        }
        catch (Exception e){
            println("Erro ao buscar proposta para salvar o historico e deletar " + e)
        }
        redirect(action: "index")
        render status: NO_CONTENT
    }

    @Transactional
    @Secured(['permitAll'])
    def consultaCEP(String cep) {

        def http = new HTTPBuilder('http://viacep.com.br')
        def result = [:]

        http.get(path: "/ws/${cep}/json/") { resp, reader ->
            result = reader
        }

        if(result == null || result.erro) {
            respond result, [status: NOT_FOUND]
        }
        respond result
    }

    @Transactional
    def simulaProposta(Proposta proposta) throws Exception{
        try {
            calculoCredito(proposta)
            respond proposta
        }catch (Exception e){
           throw new Exception("Erro ao simular proposta " + e)
            respond proposta, [status: NOT_FOUND ]
        }
    }

    def calculoCredito(Proposta proposta) throws Exception{

        Double receitaOperacional = proposta.receitaOperacional
        Double receitaNaoOperacional = proposta.receitaNaoOperacional
        Double custoMercadoria = proposta.custoMercadoria
        Double pagamentoPessoal = proposta.pagamentoPessoal
        Double taxaAluguel = proposta.taxaAluguel
        Double pagamentoFuncionarios = proposta.pagamentoFuncionarios
        Double transporte = proposta.transporte
        Double agua = proposta.agua
        Double luz = proposta.luz
        Double telefone = proposta.telefone
        Double outrosCustos = proposta.outrosCustos
        Double outrasDespesas = proposta.outrasDespesas
        Double outrosPagamentos = proposta.outrosPagamentos
        Integer prazo = proposta.quantidadeParcelas
        Double fluxoCaixa = proposta.fluxoCaixa


        // Cálculo da Receita Líquida
        Double receitaLiquida = (receitaOperacional + receitaNaoOperacional) - (custoMercadoria + pagamentoPessoal + transporte + agua + luz + telefone + taxaAluguel + outrosCustos + outrosPagamentos + pagamentoFuncionarios + outrasDespesas)

        //até esse ponto não mudou nada, a partir daqui muda

        //Limite de crédito definido por tabelamento
        Double limiteCredito = 0.0

        if (receitaLiquida <= 1000)
            limiteCredito = 2500.0
        else if ((receitaLiquida > 1000) && (receitaLiquida <= 2000))
            limiteCredito = 5000.0
        else if ((receitaLiquida > 2000) && (receitaLiquida <= 3000))
            limiteCredito = 7500.0
        else if ((receitaLiquida > 3000) && (receitaLiquida <= 4000))
            limiteCredito = 10000.0
        else if ((receitaLiquida > 4000) && (receitaLiquida <= 5000))
            limiteCredito = 12500.0
        else if ((receitaLiquida > 5000) && (receitaLiquida <= 6000))
            limiteCredito = 15000.0
        else if ((receitaLiquida > 6000) && (receitaLiquida <= 7000))
            limiteCredito = 17500.0
        else if ((receitaLiquida > 7000) && (receitaLiquida <= 8000))
            limiteCredito = 20000.0
        else if (receitaLiquida >= 8000)
            limiteCredito = 21000.0

        //Taxa de juros é um valor fixo (se puder colocar como constante melhor ainda)
        Double taxaJuros = 0.04

        Double valorJuros = limiteCredito * taxaJuros

        //Cálculo das parcelas já incluso os juros
        Double valorParcelaMensal = limiteCredito * taxaJuros * Math.pow(1 + taxaJuros, prazo) / (Math.pow(1 + taxaJuros, prazo) - 1)

        //Valor total do empréstimo será o próprio limite, já que ele aparecerá como valor principal
        Double valorTotalEmprestimo = limiteCredito

        Double fatorMultiplicacao = 2.5 //essa variável não é mais necessária, só botei pra não quebrar o código em vigor pra fins de teste

        //até aqui foram as mudanças

        proposta.dataContrato = new Date()
        proposta.taxaOperacao = taxaJuros
        proposta.valorJuros = valorJuros
        proposta.valorParcela = Math.round(valorParcelaMensal * 100.0)/100.0
        proposta.valorPrincipal = valorTotalEmprestimo
        proposta.fluxoCaixa = receitaLiquida
        proposta.dataOperacao = new Date()
        proposta.dataPrimeiroVencimento = new Date()

        //criar o array list de parcelas

        utilsService.esteiraProposta1(proposta)
        utilsService.gerarContrato(proposta)
        utilsService.nomeProposta(proposta)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_COORDENADOR'])
    def integracaoGetBanco(){
        params.integracao = "integracao"
        respond propostaService.list(params)
    }

//    @Secured(['ROLE_ADMIN', 'ROLE_COORDENADOR'])
//    def integracaoSaveBanco(ArrayList<Proposta> propostas){
//        propostas.each{ it ->
//            propostaService.save(it)
//        }
//    }
}