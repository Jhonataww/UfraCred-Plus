package ufracred.api.jobs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ufracred.api.IntegracaoService

@Service
class LoteJob {

    @Autowired
    IntegracaoService integracaoService

    @Scheduled(fixedRate = 1000L)
    void tarefaAgendada() {
        integracaoService.lote()
    }
}
