package ufracred.api.impl

import grails.gorm.services.Service
import ufracred.api.Comite
import ufracred.api.IComiteService



@Service(Comite)
abstract class ComiteServiceImpl implements IComiteService {

    private Closure buildCriteria(Map args) {

        def criteria = {
            if (args.id) {
                eq("id", args.id as Long)
            }
            if (args.nomeAssessor) {
                ilike("nomeAssessor", "%${args.nomeAssessor}%")
            }
            if (args.nomeCliente) {
                ilike("nomeCliente", "%${args.nomeCliente}%")
            }

        }

        return criteria
    }

    List<Comite> list(Map args) {

        def criteria = buildCriteria(args)

        return Comite.createCriteria().list(args, criteria)
    }

    Long count(Map args) {

        def criteria = buildCriteria(args)

        return Comite.createCriteria().count(criteria)
    }

}
