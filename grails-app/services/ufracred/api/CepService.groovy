package ufracred.api

import grails.gorm.services.Service

@Service(Cep)
interface CepService {

    Cep get(Serializable id)

    List<Cep> list(Map args)

    Long count()

    Cep delete(Serializable id)

    Cep save(Cep cep)

}
