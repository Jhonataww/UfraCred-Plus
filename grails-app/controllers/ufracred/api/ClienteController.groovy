package ufracred.api

import enums.api.ClienteEnum
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
@Secured(['ROLE_ADMIN'])
class ClienteController {

    @Autowired
    IClienteService clienteService

    @Autowired
    SpringSecurityService springSecurityService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        Assessor assessor = assessorLogado()
        params.carteiraId = assessor.carteira.id
        params.max = Math.min(max ?: 10, 100)
        respond clienteService.list(params), model:[clienteCount: clienteService.count(params)]
    }

    def show(Long id) {
        respond clienteService.get(id)
    }

    @Transactional
    def save(Cliente cliente) {

        if(cliente != null){
            processamentoCliente(cliente)
        }

        if((cliente.cpfConjuge == null || cliente.cpfConjuge.equals("") || cliente.cpfConjuge.equals('')) && cliente.estadoCivil.equals("Casado")){
            respond cliente.errors
            return
        }

        if(cliente.cpfConjuge == null || cliente.cpfConjuge.equals("") || cliente.cpfConjuge.equals('')){
            cliente.cpfConjuge = ClienteEnum.NAO.value()
        }

        if (cliente == null) {
            render status: NOT_FOUND
            return
        }
        if (cliente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cliente.errors
            return
        }

        try {
            clienteService.save(cliente)
        } catch (ValidationException e) {
            respond cliente.errors
            return
        }

        respond cliente, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Cliente cliente) {
        if (cliente == null) {
            render status: NOT_FOUND
            return
        }
        if (cliente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cliente.errors
            return
        }

        try {
            clienteService.save(cliente)
        } catch (ValidationException e) {
            respond cliente.errors
            return
        }

        respond cliente, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || clienteService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }

    def processamentoCliente(Cliente cliente){
        cliente.situacao = ClienteEnum.CADASTRADO.value()
        cliente.atualizacao = ClienteEnum.CADASTRO.value()
        cliente.dataAtualizacao = new Date();
    }


    def assessorLogado(){
        Authentication authentication = springSecurityService.authentication
        def assessor = Assessor.findByUserName(authentication.getName())
        return assessor
    }
}
