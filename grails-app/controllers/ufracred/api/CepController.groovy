package ufracred.api

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import groovyx.net.http.HTTPBuilder

import static org.springframework.http.HttpStatus.*

@ReadOnly
class CepController {

    CepService cepService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond cepService.list(params), model:[cepCount: cepService.count()]
    }

    def show(Long id) {
        respond cepService.get(id)
    }

    @Transactional
    def save(Cep cep) {
        if (cep == null) {
            render status: NOT_FOUND
            return
        }
        if (cep.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cep.errors
            return
        }

        try {
            cepService.save(cep)
        } catch (ValidationException e) {
            respond cep.errors
            return
        }

        respond cep, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Cep cep) {
        if (cep == null) {
            render status: NOT_FOUND
            return
        }
        if (cep.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cep.errors
            return
        }

        try {
            cepService.save(cep)
        } catch (ValidationException e) {
            respond cep.errors
            return
        }

        respond cep, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || cepService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }

    @Transactional
    @Secured(['permitAll'])
    def consultaCEP(String cep) {
        def endereco = Cep.findByCep(cep)
        if (endereco) {
            respond endereco, [status: OK, view:"show"]
            return
        }
        def http = new HTTPBuilder('http://viacep.com.br')
        def result = [:]

        http.get(path: "/ws/${cep}/json/") { resp, reader ->
            result = reader
        }

        if(result == null || result.erro) {
            respond result, [status: NOT_FOUND]
        }

        result = new Cep(cep: result.cep.replace("-", ""),
                complemento: result.complemento,
                logradouro: result.logradouro,
                bairro: result.bairro,
                localidade: result.localidade,
                uf: result.uf,
                ibge: result.ibge,
                gia: result.gia,
                ddd: result.ddd,
                siafi: result.siafi
        )
        save(result)
        respond result, [status: OK]
    }

}
