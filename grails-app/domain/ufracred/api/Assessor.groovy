package ufracred.api

class Assessor {

    String nome
    String cpf
    String rg
    String tituloEleitor
    String ctps
    String naturalidade
    String telefoneFixo
    String telefoneMovel
    String cep
    String endereco
    String numero
    String cidade
    String estado
    String userName
    Date inicioContrato
    Date fimContrato
    Double rendimentoBruto
    Date dataNascimento
    Integer carteiraAtual

    static hasMany = [agendas: Agenda]
    static hasOne = [carteira: Carteira]

    static constraints = {
        nome nullable: false
        cpf nullable: false
        rg nullable: false
        tituloEleitor nullable: false
        ctps nullable: false
        naturalidade nullable: false
        telefoneFixo nullable: false
        telefoneMovel nullable: false
        cep nullable: false
        endereco nullable: false
        numero nullable: false
        cidade nullable: false
        estado nullable: false
        userName nullable: false
        inicioContrato nullable: false
        fimContrato nullable: false
        rendimentoBruto nullable: false
        dataNascimento nullable: false
        carteiraAtual nullable: false
    }
}
