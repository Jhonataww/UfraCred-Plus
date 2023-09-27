package ufracred.api

class Comite {

    Date aprovacao
    Integer excluido
    String parecer
    Integer comite

    static belongsTo = [assessor: Assessor, proposta: Proposta]

    static constraints = {
        aprovacao nullable: false
        excluido nullable: true
        parecer nullable: false, maxSize: 255
        comite nullable: false
    }
}
