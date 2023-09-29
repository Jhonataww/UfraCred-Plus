package ufracred.api.jobs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ufracred.api.IntegracaoService

@Service
class LoteJob {

    @Autowired
    IntegracaoService integracaoService

//    @Scheduled(cron = "0 18 * * 1-5") // 18h de segunda a sexta
//    void tarefaAgendada() {
//        integracaoService.lote()
//    }

    @Scheduled(cron = "0 * * * * ?") // // a cada minuto
    void tarefaAgendada() {
        integracaoService.lote()
    }
}
