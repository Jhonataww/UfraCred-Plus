package ufracred.api

import enums.api.CheckListsEnum
import enums.api.LoteEnum
import enums.api.PropostaEnum
import enums.api.StatusEnum
import grails.gorm.services.Service
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.beans.factory.annotation.Autowired

import javax.xml.crypto.Data
import java.text.SimpleDateFormat

@Service
class IntegracaoService {

    @Autowired
    IPropostaService propostaService

    @Autowired
    LoteService loteService



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
        proposta.tipoProposta = PropostaEnum.NOVA.value()
        proposta.status = StatusEnum.EM_ANALISE.value()
        proposta.checkLists = CheckListsEnum.SIMULACAO.value()
        proposta.numeroAditivo = 0
    }

    def esteiraProposta2 (Proposta proposta){
        if(proposta.status ==  StatusEnum.COMITE.value() && proposta.checkLists == CheckListsEnum.NEGADO_COMITE.value()) {
            proposta.tipoProposta = PropostaEnum.NOVA.value()
            proposta.status = StatusEnum.COMITE.value()
            proposta.checkLists = CheckListsEnum.NEGADO_COMITE.value()
            return
        }
        proposta.tipoProposta = PropostaEnum.NOVA.value()
        proposta.status = StatusEnum.PENDENTE.value()
        proposta.checkLists = CheckListsEnum.PENDENTE_INTEGRACAO.value()
    }

    def esteiraPropostaComite (Comite comite) {
        Proposta proposta = Proposta.get(comite.proposta.id)
        proposta.tipoProposta = PropostaEnum.NOVA.value()
        proposta.status = StatusEnum.COMITE.value()
        proposta.checkLists = comite.comite == 0 ? CheckListsEnum.APROVADO_COMITE.value() : CheckListsEnum.NEGADO_COMITE.value()
        propostaService.save(proposta)
        esteiraProposta2(proposta)

    }

    def lote(){
        def proposta = listPropostaLote()
        if(!proposta){
            println("Não há propostas para serem processadas");
            return
        }
        def lote = new Lote(status : LoteEnum.CRIADO.value(), dataCriacao : new Date(), dataAtualizacao : new Date())
        def LoteIdCriado = loteService.save(lote).id
        proposta.each{ it ->
            it.lote = LoteIdCriado
            it.status = StatusEnum.LOTE.value()
            propostaService.save(it)
        }
        lote.status = LoteEnum.PROCESSANDO.value()
        loteService.save(lote)
        println("Lote ${LoteIdCriado} criado com sucesso, com ${proposta.size()} propostas");
    }

    private Closure buildCriteria() {

        def criteria = {
            eq("status", StatusEnum.PENDENTE.value())
            eq("tipoProposta", PropostaEnum.NOVA.value())
            eq("checkLists", CheckListsEnum.PENDENTE_INTEGRACAO.value())
        }

        return criteria
    }

    List<Proposta> listPropostaLote() {

        def criteria = buildCriteria()

        return Proposta.createCriteria().list(criteria)
    }
}
