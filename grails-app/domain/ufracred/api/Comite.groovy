package ufracred.api

class Comite {

    Date aprovacao
    Integer excluido
    String parecer
    Integer comite

    static belongsTo = [assessor: Assessor, proposta: Proposta]

    static constraints = {
        aprovacao nullable: true
        excluido nullable: true
        parecer nullable: true, maxSize: 255
        comite nullable: true
    }
}
