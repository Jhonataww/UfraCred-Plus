package ufracred.api

class Cliente {

    String nomeCompleto
    String nomeAbreviado
    String mae
    String pai
    String sexo
    String grauInstrucao
    String estadoCivil
    String capacidadeCivil
    String nacionalidade
    String estado
    String cidade
    String possuiRenda
    String servidorPublico
    String necessidadesEspeciais
    String situacaoEspecial
    String proposito
    String produtosServicos
    String atividade
    String grauInteresse
    String politicamenteExposta
    String titular
    String atualizacao
    String situacao
    String tipoPessoa
    String cpfCnpj
    String cpfConjuge
    String cep
    String endereco
    Integer excluido
    Date nascimento
    Date dataAtualizacao

    static belongsTo = [carteira: Carteira, agenda: Agenda]

    static constraints = {
        cpfCnpj nullable: false
        cpfConjuge nullable: true, blank: true
        nomeCompleto nullable: false
        nomeAbreviado nullable: false
        mae nullable: false
        pai nullable: false
        sexo nullable: false
        grauInstrucao nullable: false
        estadoCivil nullable: false
        capacidadeCivil nullable: false
        nacionalidade nullable: false
        estado nullable: false
        cidade nullable: false
        possuiRenda nullable: false
        servidorPublico nullable: false
        necessidadesEspeciais nullable: false
        situacaoEspecial nullable: false
        proposito nullable: false
        produtosServicos nullable: false
        atividade nullable: false
        grauInteresse nullable: false
        politicamenteExposta nullable: false
        titular nullable: false
        atualizacao nullable: true, blank: true
        situacao nullable: true, blank: true
        tipoPessoa nullable: false
        cpfCnpj nullable: false
        cpfConjuge nullable: true, blank: true
        excluido nullable: false
        nascimento nullable: false
        dataAtualizacao nullable: true , blank: true
        endereco nullable: false
        cep nullable: false
        carteira nullable: true, blank: true
        agenda nullable: true, blank: true
    }
}
