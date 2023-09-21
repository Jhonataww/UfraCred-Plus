package ufracred.api

import grails.gorm.services.Service
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication

import java.text.SimpleDateFormat

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
            proposta.nomeAssessor = "Ilegitimo"
            proposta.status = "Ilegitimo - Proposta sem assessor"
            throw new Exception("Proposta sem assessor")
        }
        if(proposta.nomeCliente == null || proposta.nomeCliente == ""){
            proposta.nomeCliente = "Ilegitimo"
            proposta.status = "Ilegitimo - Proposta sem cliente"
            throw new Exception("Proposta sem assessor")
        }
        if(!(assessor.carteira == cliente.carteira)){
            proposta.status = "Ilegitimo - Proposta sem cliente"
            throw new Exception("Carteira do cliente não corresponde a carteira do assessor")
        }
    }

    def gerarContrato(Proposta proposta){

        Random random = new Random()
        Integer v = random.nextInt(10000)
        Date dataAtual = new Date()
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd")
        String dataFormatada = dateFormat.format(dataAtual)
        Integer vd = Integer.parseInt(dataFormatada)
        Integer vf = (vd + ( v %= 10000))

        proposta.numeroContrato = vf
    }

    def esteiraProposta1 (Proposta proposta){
        proposta.tipoProposta = "Nova"
        proposta.status = "Em Análise"
        proposta.checkLists = "Simulacao"
        proposta.numeroAditivo = 0
    }

    def esteiraProposta2 (Proposta proposta){
        proposta.tipoProposta = "Nova"
        proposta.status = "Analisada"
        proposta.checkLists = "Comite"
        proposta.numeroAditivo = 0
    }

}
