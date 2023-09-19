package ufracred.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class LoteServiceSpec extends Specification {

    LoteService loteService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Lote(...).save(flush: true, failOnError: true)
        //new Lote(...).save(flush: true, failOnError: true)
        //Lote lote = new Lote(...).save(flush: true, failOnError: true)
        //new Lote(...).save(flush: true, failOnError: true)
        //new Lote(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //lote.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        loteService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Lote> loteList = loteService.list(max: 2, offset: 2)

        then:
        loteList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        loteService.count() == 5
    }

    void "test delete"() {
        Long loteId = setupData()

        expect:
        loteService.count() == 5

        when:
        loteService.delete(loteId)
        datastore.currentSession.flush()

        then:
        loteService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Lote lote = new Lote()
        loteService.save(lote)

        then:
        lote.id != null
    }
}
