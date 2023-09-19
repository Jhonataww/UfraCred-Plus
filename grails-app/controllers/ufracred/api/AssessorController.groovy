package ufracred.api

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
@Secured(['ROLE_ADMIN'])
class AssessorController {

    @Autowired
    IAssessorService assessorService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond assessorService.list(params), model:[assessorCount: assessorService.count(params)]
    }

    def show(Long id) {
        respond assessorService.get(id)
    }

    @Transactional
    def save(Assessor assessor) {
        if (assessor == null) {
            render status: NOT_FOUND
            return
        }
        if (assessor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond assessor.errors
            return
        }

        try {
            assessorService.save(assessor)
        } catch (ValidationException e) {
            respond assessor.errors
            return
        }

        respond assessor, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Assessor assessor) {
        if (assessor == null) {
            render status: NOT_FOUND
            return
        }
        if (assessor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond assessor.errors
            return
        }

        try {
            assessorService.save(assessor)
        } catch (ValidationException e) {
            respond assessor.errors
            return
        }

        respond assessor, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || assessorService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
