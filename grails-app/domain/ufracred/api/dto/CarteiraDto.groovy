package ufracred.api.dto

import ufracred.api.Assessor
import ufracred.api.Cliente

class CarteiraDto {

    Integer excluido


    static hasMany = [clientes: Cliente]
    static belongsTo = [ assessor: Assessor]

    static constraints = {
        excluido nullable: false
    }

    static mapWith = 'none'
}
