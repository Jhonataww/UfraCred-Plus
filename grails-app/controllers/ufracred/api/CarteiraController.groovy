package ufracred.api

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
@Secured(['ROLE_ADMIN'])
class CarteiraController {

    CarteiraService carteiraService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond carteiraService.list(params), model:[carteiraCount: carteiraService.count()]
    }

    def show(Long id) {
        respond carteiraService.get(id)
    }

    @Transactional
    def save(Carteira carteira) {
        if (carteira == null) {
            render status: NOT_FOUND
            return
        }
        if (carteira.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond carteira.errors
            return
        }

        try {
            carteiraService.save(carteira)
        } catch (ValidationException e) {
            respond carteira.errors
            return
        }

        respond carteira, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Carteira carteira) {
        if (carteira == null) {
            render status: NOT_FOUND
            return
        }
        if (carteira.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond carteira.errors
            return
        }

        try {
            carteiraService.save(carteira)
        } catch (ValidationException e) {
            respond carteira.errors
            return
        }

        respond carteira, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || carteiraService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
