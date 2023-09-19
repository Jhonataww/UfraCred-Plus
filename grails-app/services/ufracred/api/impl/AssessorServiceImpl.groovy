package ufracred.api.impl

import grails.gorm.services.Service
import ufracred.api.Assessor
import ufracred.api.IAssessorService


@Service(Assessor)
abstract class AssessorServiceImpl implements IAssessorService {

    private Closure buildCriteria(Map args) {

        def criteria = {
            if (args.id) {
                eq("id", args.id as Long)
            }
            if (args.userName) {
                ilike("userName", "%${args.userName}%")
            }
        }

        return criteria
    }

    List<Assessor> list(Map args) {

        def criteria = buildCriteria(args)

        return Assessor.createCriteria().list(args, criteria)
    }

    Long count(Map args) {

        def criteria = buildCriteria(args)

        return Assessor.createCriteria().count(criteria)
    }

}
