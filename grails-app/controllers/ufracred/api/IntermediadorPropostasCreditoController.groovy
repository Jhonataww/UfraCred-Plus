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
class IntermediadorPropostasCreditoController {

    IntermediadorPropostasCreditoService intermediadorPropostasCreditoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond intermediadorPropostasCreditoService.list(params), model:[intermediadorPropostasCreditoCount: intermediadorPropostasCreditoService.count()]
    }

    def show(Long id) {
        respond intermediadorPropostasCreditoService.get(id)
    }

    @Transactional
    def save(IntermediadorPropostasCredito intermediadorPropostasCredito) {
        if (intermediadorPropostasCredito == null) {
            render status: NOT_FOUND
            return
        }
        if (intermediadorPropostasCredito.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond intermediadorPropostasCredito.errors
            return
        }

        try {
            intermediadorPropostasCreditoService.save(intermediadorPropostasCredito)
        } catch (ValidationException e) {
            respond intermediadorPropostasCredito.errors
            return
        }

        respond intermediadorPropostasCredito, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(IntermediadorPropostasCredito intermediadorPropostasCredito) {
        if (intermediadorPropostasCredito == null) {
            render status: NOT_FOUND
            return
        }
        if (intermediadorPropostasCredito.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond intermediadorPropostasCredito.errors
            return
        }

        try {
            intermediadorPropostasCreditoService.save(intermediadorPropostasCredito)
        } catch (ValidationException e) {
            respond intermediadorPropostasCredito.errors
            return
        }

        respond intermediadorPropostasCredito, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || intermediadorPropostasCreditoService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def envioLoteProposta(){
        println("envioLoteProposta")
        render status: OK
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def recebimentoLoteProposta(){
        println("recebimentoLoteProposta")
        render status: OK
    }
}
