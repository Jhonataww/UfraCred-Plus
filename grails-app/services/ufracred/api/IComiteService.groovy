package ufracred.api


interface IComiteService {

    Comite get(Serializable id)

    List<Comite> list(Map args)

    Long count(Map args)

    Comite delete(Serializable id)

    Comite save(Comite comite)

}
