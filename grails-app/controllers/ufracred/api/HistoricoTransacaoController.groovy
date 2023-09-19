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
class HistoricoTransacaoController {

    HistoricoTransacaoService historicoTransacaoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond historicoTransacaoService.list(params), model:[historicoTransacaoCount: historicoTransacaoService.count()]
    }

    def show(Long id) {
        respond historicoTransacaoService.get(id)
    }

    @Transactional
    def save(HistoricoTransacao historicoTransacao) {
        if (historicoTransacao == null) {
            render status: NOT_FOUND
            return
        }
        if (historicoTransacao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond historicoTransacao.errors
            return
        }

        try {
            historicoTransacaoService.save(historicoTransacao)
        } catch (ValidationException e) {
            respond historicoTransacao.errors
            return
        }

        respond historicoTransacao, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(HistoricoTransacao historicoTransacao) {
        if (historicoTransacao == null) {
            render status: NOT_FOUND
            return
        }
        if (historicoTransacao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond historicoTransacao.errors
            return
        }

        try {
            historicoTransacaoService.save(historicoTransacao)
        } catch (ValidationException e) {
            respond historicoTransacao.errors
            return
        }

        respond historicoTransacao, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || historicoTransacaoService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
