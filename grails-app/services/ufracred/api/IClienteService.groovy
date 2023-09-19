package ufracred.api

import grails.gorm.services.Service


interface IClienteService {

    Cliente get(Serializable id)

    List<Cliente> list(Map args)

    Long count(Map args)

    Cliente delete(Serializable id)

    Cliente save(Cliente cliente)

}
