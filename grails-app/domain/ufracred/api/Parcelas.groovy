package ufracred.api;

class Parcelas {

    Double valor
    Double taxaJuros
    Double valorJuros
    String amortizada
    String pago

    static constraints = {
        valor nullable: true
        taxaJuros nullable: true
        valorJuros nullable: true
        amortizada nullable: true
        pago nullable: true
    }

    //criar relação onde cada proposta tem várias parcelas. Arraylist

}
