package ufracred.api

class Carteira {

    Integer excluido


    static hasMany = [clientes: Cliente]
    static belongsTo = [ assessor: Assessor]

    static constraints = {
        excluido nullable: false
    }
}
