package ufracred.api

import grails.plugin.springsecurity.SpringSecurityService
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
@Secured(['ROLE_COORDENADOR'])
class ComiteController {

    @Autowired
    IComiteService comiteService

    @Autowired
    IntegracaoService integracaoService

    @Autowired
    SpringSecurityService springSecurityService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond comiteService.list(params), model:[comiteCount: comiteService.count(params)]
    }

    def show(Long id) {
        respond comiteService.get(id)
    }

    @Transactional
    def save(Comite comite) {
        if (comite == null) {
            render status: NOT_FOUND
            return
        }
        if (comite.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond comite.errors
            return
        }

        try {
            if(Comite.findByProposta(comite.proposta)){
                throw new ValidationException("Proposta já cadastrada no comite")
            }
            integracaoService.esteiraPropostaComite(comite)
            comiteService.save(comite)
        } catch (ValidationException e) {
            respond comite.errors
            return
        }

        respond comite, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Comite comite) {
        if (comite == null) {
            render status: NOT_FOUND
            return
        }
        if (comite.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond comite.errors
            return
        }

        try {
            comiteService.save(comite)
        } catch (ValidationException e) {
            respond comite.errors
            return
        }

        respond comite, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || comiteService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
