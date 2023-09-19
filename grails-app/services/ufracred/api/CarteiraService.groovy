package ufracred.api

import grails.gorm.services.Service

@Service(Carteira)
interface CarteiraService {

    Carteira get(Serializable id)

    List<Carteira> list(Map args)

    Long count()

    Carteira delete(Serializable id)

    Carteira save(Carteira carteira)

}
