package ufracred.api

import enums.api.CheckListsEnum
import enums.api.PropostaEnum
import enums.api.StatusEnum
import grails.gorm.services.Service
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.beans.factory.annotation.Autowired

@Service
class IntegracaoService {

    @Autowired
    IPropostaService propostaService

    def esteiraProposta1 (Proposta proposta){
        proposta.tipoProposta = PropostaEnum.NOVA.value()
        proposta.status = StatusEnum.EM_ANALISE.value()
        proposta.checkLists = CheckListsEnum.SIMULACAO.value()
        proposta.numeroAditivo = 0
    }

    def esteiraProposta2 (Proposta proposta){
        proposta.tipoProposta = "Nova"
        proposta.status = "Analisada"
        proposta.checkLists = "Comite"
        proposta.numeroAditivo = 0
    }

    def esteiraPropostaComite (Comite comite) {
        Proposta proposta = Proposta.get(comite.proposta.id)
        proposta.tipoProposta = PropostaEnum.NOVA.value()
        proposta.status = StatusEnum.COMITE.value()
        proposta.checkLists = comite.excluido == 0 ? CheckListsEnum.APROVADO.value() : CheckListsEnum.NEGADO.value()
        proposta.numeroAditivo = 0
        propostaService.save(proposta)
    }

//    @Secured(['ROLE_ADMIN', 'ROLE_COORDENADOR'])
//    def integracaoGetBanco(){
//        params.integracao = "integracao"
//        respond propostaService.list(params)
//    }

//    @Secured(['ROLE_ADMIN', 'ROLE_COORDENADOR'])
//    def integracaoSaveBanco(ArrayList<Proposta> propostas){
//        propostas.each{ it ->
//            propostaService.save(it)
//        }
//    }
}
