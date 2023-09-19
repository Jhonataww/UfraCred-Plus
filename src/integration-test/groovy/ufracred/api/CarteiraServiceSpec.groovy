package ufracred.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class CarteiraServiceSpec extends Specification {

    CarteiraService carteiraService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Carteira(...).save(flush: true, failOnError: true)
        //new Carteira(...).save(flush: true, failOnError: true)
        //Carteira carteira = new Carteira(...).save(flush: true, failOnError: true)
        //new Carteira(...).save(flush: true, failOnError: true)
        //new Carteira(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //carteira.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        carteiraService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Carteira> carteiraList = carteiraService.list(max: 2, offset: 2)

        then:
        carteiraList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        carteiraService.count() == 5
    }

    void "test delete"() {
        Long carteiraId = setupData()

        expect:
        carteiraService.count() == 5

        when:
        carteiraService.delete(carteiraId)
        datastore.currentSession.flush()

        then:
        carteiraService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Carteira carteira = new Carteira()
        carteiraService.save(carteira)

        then:
        carteira.id != null
    }
}
