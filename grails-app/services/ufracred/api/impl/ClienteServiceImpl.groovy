package ufracred.api.impl

import grails.gorm.services.Service
import ufracred.api.Assessor
import ufracred.api.Cliente
import ufracred.api.IAssessorService
import ufracred.api.IClienteService


@Service(Cliente)
abstract class ClienteServiceImpl implements IClienteService {

    private Closure buildCriteria(Map args) {

        def criteria = {
            if (args.carteiraId) {
                eq("carteira.id", args.carteiraId as Long)
            }
            if (args.id) {
                eq("id", args.id as Long)
            }
            if (args.nomeCliente) {
                ilike("nomeCompleto", "%${args.nomeCliente}%")
            }
        }


        return criteria
    }

    List<Cliente> list(Map args) {

        def criteria = buildCriteria(args)

        return Cliente.createCriteria().list(args, criteria)
    }

    Long count(Map args) {

        def criteria = buildCriteria(args)

        return Cliente.createCriteria().count(criteria)
    }

}
