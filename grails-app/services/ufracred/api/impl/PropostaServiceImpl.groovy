package ufracred.api.impl

import grails.gorm.services.Service
import ufracred.api.Proposta
import ufracred.api.IPropostaService

@Service(Proposta)
abstract class PropostaServiceImpl implements IPropostaService {

    private Closure buildCriteria(Map args) {

        def criteria = {
            if (args.id) {
                eq("id", args.id as Long)
            }
            if (args.nomeAssessor) {
                ilike("nomeAssessor", "${args.nomeAssessor}")
            }
            if (args.nomeCliente) {
                ilike("nomeCliente", "%${args.nomeCliente}%")
            }
            if(args.comite == 'comite'){
                not {
                    eq("checkLists", "Aprovado")
                    eq("checkLists", "Negado" )
                    eq("status", "Comite")
                }
            }
            if(args.integracao){
                ilike("status", "integacao")
            }

        }

        return criteria
    }

    List<Proposta> list(Map args) {

        def criteria = buildCriteria(args)

        return Proposta.createCriteria().list(args, criteria)
    }

    Long count(Map args) {

        def criteria = buildCriteria(args)

        return Proposta.createCriteria().count(criteria)
    }

}
