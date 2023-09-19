package ufracred.api

interface IPropostaService {

    Proposta get(Serializable id)

    List<Proposta> list(Map args)

    Long count(Map args)

    Proposta delete(Serializable id)

    Proposta save(Proposta proposta)

}
