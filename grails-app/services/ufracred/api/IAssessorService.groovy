package ufracred.api

interface IAssessorService {

    Assessor get(Serializable id)

    List<Assessor> list(Map args)

    Long count(Map args)

    Assessor delete(Serializable id)

    Assessor save(Assessor assessor)

}
