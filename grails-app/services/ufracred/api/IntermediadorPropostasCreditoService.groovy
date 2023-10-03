package ufracred.api

import grails.gorm.services.Service

@Service(IntermediadorPropostasCredito)
interface IntermediadorPropostasCreditoService {

    IntermediadorPropostasCredito get(Serializable id)

    List<IntermediadorPropostasCredito> list(Map args)

    Long count()

    IntermediadorPropostasCredito delete(Serializable id)

    IntermediadorPropostasCredito save(IntermediadorPropostasCredito intermediadorPropostasCredito)

}
