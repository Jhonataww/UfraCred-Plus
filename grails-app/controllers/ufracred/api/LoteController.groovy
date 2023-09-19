package ufracred.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class LoteController {

    LoteService loteService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond loteService.list(params), model:[loteCount: loteService.count()]
    }

    def show(Long id) {
        respond loteService.get(id)
    }

    @Transactional
    def save(Lote lote) {
        if (lote == null) {
            render status: NOT_FOUND
            return
        }
        if (lote.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lote.errors
            return
        }

        try {
            loteService.save(lote)
        } catch (ValidationException e) {
            respond lote.errors
            return
        }

        respond lote, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Lote lote) {
        if (lote == null) {
            render status: NOT_FOUND
            return
        }
        if (lote.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lote.errors
            return
        }

        try {
            loteService.save(lote)
        } catch (ValidationException e) {
            respond lote.errors
            return
        }

        respond lote, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || loteService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
