package ufracred.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class IntermediadorPropostasCreditoServiceSpec extends Specification {

    IntermediadorPropostasCreditoService intermediadorPropostasCreditoService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new IntermediadorPropostasCredito(...).save(flush: true, failOnError: true)
        //new IntermediadorPropostasCredito(...).save(flush: true, failOnError: true)
        //IntermediadorPropostasCredito intermediadorPropostasCredito = new IntermediadorPropostasCredito(...).save(flush: true, failOnError: true)
        //new IntermediadorPropostasCredito(...).save(flush: true, failOnError: true)
        //new IntermediadorPropostasCredito(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //intermediadorPropostasCredito.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        intermediadorPropostasCreditoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<IntermediadorPropostasCredito> intermediadorPropostasCreditoList = intermediadorPropostasCreditoService.list(max: 2, offset: 2)

        then:
        intermediadorPropostasCreditoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        intermediadorPropostasCreditoService.count() == 5
    }

    void "test delete"() {
        Long intermediadorPropostasCreditoId = setupData()

        expect:
        intermediadorPropostasCreditoService.count() == 5

        when:
        intermediadorPropostasCreditoService.delete(intermediadorPropostasCreditoId)
        datastore.currentSession.flush()

        then:
        intermediadorPropostasCreditoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        IntermediadorPropostasCredito intermediadorPropostasCredito = new IntermediadorPropostasCredito()
        intermediadorPropostasCreditoService.save(intermediadorPropostasCredito)

        then:
        intermediadorPropostasCredito.id != null
    }
}
