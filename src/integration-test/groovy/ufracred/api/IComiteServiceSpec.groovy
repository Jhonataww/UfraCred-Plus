package ufracred.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class IComiteServiceSpec extends Specification {

    IComiteService comiteService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Comite(...).save(flush: true, failOnError: true)
        //new Comite(...).save(flush: true, failOnError: true)
        //Comite comite = new Comite(...).save(flush: true, failOnError: true)
        //new Comite(...).save(flush: true, failOnError: true)
        //new Comite(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //comite.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        comiteService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Comite> comiteList = comiteService.list(max: 2, offset: 2)

        then:
        comiteList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        comiteService.count() == 5
    }

    void "test delete"() {
        Long comiteId = setupData()

        expect:
        comiteService.count() == 5

        when:
        comiteService.delete(comiteId)
        datastore.currentSession.flush()

        then:
        comiteService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Comite comite = new Comite()
        comiteService.save(comite)

        then:
        comite.id != null
    }
}
