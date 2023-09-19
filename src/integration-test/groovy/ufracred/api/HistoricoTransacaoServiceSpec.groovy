package ufracred.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class HistoricoTransacaoServiceSpec extends Specification {

    HistoricoTransacaoService historicoTransacaoService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new HistoricoTransacao(...).save(flush: true, failOnError: true)
        //new HistoricoTransacao(...).save(flush: true, failOnError: true)
        //HistoricoTransacao historicoTransacao = new HistoricoTransacao(...).save(flush: true, failOnError: true)
        //new HistoricoTransacao(...).save(flush: true, failOnError: true)
        //new HistoricoTransacao(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //historicoTransacao.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        historicoTransacaoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<HistoricoTransacao> historicoTransacaoList = historicoTransacaoService.list(max: 2, offset: 2)

        then:
        historicoTransacaoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        historicoTransacaoService.count() == 5
    }

    void "test delete"() {
        Long historicoTransacaoId = setupData()

        expect:
        historicoTransacaoService.count() == 5

        when:
        historicoTransacaoService.delete(historicoTransacaoId)
        datastore.currentSession.flush()

        then:
        historicoTransacaoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        HistoricoTransacao historicoTransacao = new HistoricoTransacao()
        historicoTransacaoService.save(historicoTransacao)

        then:
        historicoTransacao.id != null
    }
}
