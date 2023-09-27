package ufracred.api

import enums.api.CheckListsEnum
import enums.api.PropostaEnum
import enums.api.StatusEnum
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import groovyx.net.http.HTTPBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.NOT_FOUND

@Service
class UtilsService {

    @Autowired
    HistoricoTransacaoService historicoTransacaoService

    @Autowired
    SpringSecurityService springSecurityService

    def assessorLogado(){
        Authentication authentication = springSecurityService.authentication
        def assessor = Assessor.findByUserName(authentication.getName())
        return assessor
    }

    def authentication(){
        return springSecurityService.authentication
    }

    def historicoTransacao(Proposta proposta, String statusTransacao) throws Exception {
        try{
            HistoricoTransacao historicoTransacao = new HistoricoTransacao(proposta,  authentication().getName(), statusTransacao)
            historicoTransacaoService.save(historicoTransacao)
        }catch (Exception e){
            println("Erro ao salvar historico de transacao   -  " + e)
            throw e
        }
    }

    def nomeProposta(Proposta proposta) throws Exception {

        Cliente cliente = Cliente.findById(proposta.cliente)
        Assessor assessor = assessorLogado()

        try{
            proposta.nomeCliente = Cliente.findById(proposta.cliente).nomeCompleto
            proposta.nomeAssessor = assessor.nome
            proposta.assessor = assessor.id.intValue()
        }catch (Exception e){
            println("Erro ao buscar nome do cliente ou assessor " + e)
        }
        if (proposta.nomeAssessor == null || proposta.nomeAssessor == ""){
            proposta.nomeAssessor = StatusEnum.ILEGITIMO.value()
            proposta.status = StatusEnum.ILEGITIMO.value()
            throw new Exception("Proposta sem assessor")
        }
        if(proposta.nomeCliente == null || proposta.nomeCliente == ""){
            proposta.nomeCliente = StatusEnum.ILEGITIMO.value()
            proposta.status = StatusEnum.ILEGITIMO.value()
            throw new Exception("Proposta sem Cliente")
        }
        if(!(assessor.carteira == cliente.carteira)){
            proposta.status = StatusEnum.ILEGITIMO.value()
            throw new Exception("Carteira do cliente não corresponde a carteira do assessor")
        }
    }
}
