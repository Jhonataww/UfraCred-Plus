package ufracred.api

import grails.gorm.transactions.Transactional

@Transactional
class IntegracaoService {

    //teste
    def validacaoIntegracao(Proposta proposta){

        proposta.each { e ->
            if(e.value == null){
               return new Exception("O campo ${e.key} não pode ser nulo")
            }
        }
    }

    String validar(){
        return "OK"
    }
}
