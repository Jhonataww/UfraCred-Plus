package ufracred.api

import grails.gorm.services.Service

@Service(HistoricoTransacao)
interface HistoricoTransacaoService {

    HistoricoTransacao get(Serializable id)

    List<HistoricoTransacao> list(Map args)

    Long count()

    HistoricoTransacao delete(Serializable id)

    HistoricoTransacao save(HistoricoTransacao historicoTransacao)

}
