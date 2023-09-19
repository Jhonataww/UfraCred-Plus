package ufracred.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class IAssessorServiceSpec extends Specification {

    IAssessorService assessorService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Assessor(...).save(flush: true, failOnError: true)
        //new Assessor(...).save(flush: true, failOnError: true)
        //Assessor assessor = new Assessor(...).save(flush: true, failOnError: true)
        //new Assessor(...).save(flush: true, failOnError: true)
        //new Assessor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //assessor.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        assessorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Assessor> assessorList = assessorService.list(max: 2, offset: 2)

        then:
        assessorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        assessorService.count() == 5
    }

    void "test delete"() {
        Long assessorId = setupData()

        expect:
        assessorService.count() == 5

        when:
        assessorService.delete(assessorId)
        datastore.currentSession.flush()

        then:
        assessorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Assessor assessor = new Assessor()
        assessorService.save(assessor)

        then:
        assessor.id != null
    }
}
