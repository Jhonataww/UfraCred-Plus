package ufracred.api

class Cep {

     String cep
     String logradouro
     String complemento
     String bairro
     String localidade
     String uf
     String ibge
     String gia
     String ddd
     String siafi

    static constraints = {

        cep nullable: true
        logradouro nullable: true
        complemento nullable: true
        bairro nullable: true
        localidade nullable: true
        uf nullable: true
        ibge nullable: true
        gia nullable: true
        ddd nullable: true
        siafi nullable: true

    }
}
