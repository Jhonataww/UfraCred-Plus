package ufracred.api

import grails.gorm.services.Service

@Service(Agenda)
interface AgendaService {

    Agenda get(Serializable id)

    List<Agenda> list(Map args)

    Long count()

    Agenda delete(Serializable id)

    Agenda save(Agenda agenda)

}
