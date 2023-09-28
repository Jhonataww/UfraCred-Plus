package ufracred.api

class Lote {

    String status
    Date dataCriacao
    Date dataAtualizacao
    static constraints = {
        status nullable: true
        dataCriacao nullable: true
        dataAtualizacao nullable: true
    }
}
