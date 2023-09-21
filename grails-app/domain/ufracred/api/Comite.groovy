package ufracred.api

class Comite {

    Date aprovacao
    Integer excluido
    String parecer

    static belongsTo = [assessor: Assessor, proposta: Proposta]

    static constraints = {
        aprovacao nullable: false
        excluido nullable: false
        parecer nullable: false, maxSize: 255
    }
}