<template>
  <detail-page-component title="Propostas Ufra Cred">
    <template v-slot:filter>
      <!-- componente filtrer -->
      <detail-filter-component title="Nova Proposta">
        <q-list bordered class="rounded-borders q-pa-sm">
          <q-expansion-item expand-separator icon="contact_page" label="Dados Gerais" style="color: #43744e">
            <div class="q-pa-md">
              <div class="q-gutter-md row items-start">
                <q-input
                  v-if="this.acao != 'Criar'"
                  v-model="proposta.id"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="ID da Proposta"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-select
                  v-model="clienteSelecionado"
                  :disable="trava"
                  :options="clientes"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Clientes"
                  label-color="dark"
                  option-label="nomeCompleto"
                  option-value="id"
                  style="width: 14rem "
                />
                <q-select
                  v-model="proposta.modalidade"
                  :disable="trava"
                  :options="modalidades"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Modalidade"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-select
                  v-model="proposta.restritivos"
                  :disable="trava"
                  :options="tipoRestritivos"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Restritivos"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-input
                  v-model="proposta.localizacao"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Localização/Endereço"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-select
                  v-model="proposta.finalidade"
                  :disable="trava"
                  :options="tipoFinalidade"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Finalidade"
                  label-color="dark"
                  style="width: 14rem "
                />

              </div>
            </div>
          </q-expansion-item>
        </q-list>

        <q-list bordered class="rounded-borders q-pa-sm" icon="person" style="color: #43744e; margin-top: 20px">
          <q-expansion-item expand-separator icon="paid" label="Dados Financeiros">
            <div class="q-pa-md">
              <div class="q-gutter-md row items-start">
                <q-select
                  v-model="proposta.quantidadeParcelas"
                  :options="this.parcelas"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Quantidade Parcelas"
                  label-color="dark"
                  style="width: 14rem "
                  @focus="
                    novoValor != this.proposta.quantidadeParcelas
                      ? (simulacaoConfirma = false)
                      : simulacaoConfirma == true
                  "
                />

                <q-input
                  v-model="proposta.receitaOperacional"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Receita Operacional"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />
                <q-input
                  v-model="proposta.receitaNaoOperacional"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Receita Não Operacional"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />
                <q-input
                  v-model="proposta.custoMercadoria"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Custo de Mercadoria"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />

                <q-input
                  v-model="proposta.pagamentoPessoal"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Pagamento Pessoal"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />
                <q-input
                  v-model="proposta.pagamentoFuncionarios"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Salário de Funcionários"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />

                <q-input
                  v-model="proposta.transporte"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Transporte"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />
                <q-input
                  v-model="proposta.agua"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Água"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />
                <q-input
                  v-model="proposta.luz"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Luz"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />
                <q-input
                  v-model="proposta.telefone"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Telefone"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />

                <q-input
                  v-model="proposta.taxaAluguel"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Taxa de Aluguel"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />

                <q-input
                  v-model="proposta.outrosCustos"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Outros Custos"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />

                <q-input
                  v-model="proposta.outrasDespesas"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Outras Despesas"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />

                <q-input
                  v-model="proposta.outrosPagamentos"
                  :disable="trava"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Outros Pagamentos"
                  label-color="dark"
                  mask="##########"
                  style="width: 14rem "
                />
              </div>
            </div>
          </q-expansion-item>
        </q-list>

        <q-list v-if="simulacaoConfirma == true" bordered class="rounded-borders q-pa-sm" style="margin-top: 20px">
          <q-expansion-item expand-separator icon="check_circle_outline" label="Informações da Simulação"
                            style="color: #9d9300">
            <div class="q-pa-md">
              <div class="q-gutter-md row items-start">
                <q-input
                  v-model="proposta.tipoProposta"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Tipo de Proposta"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.fluxoCaixa"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Fluxo de Caixa"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.nomeAssessor"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Assessor"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.checkLists"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="CheckList"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.numeroContrato"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Numero do Contrato"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.numeroAditivo"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Numero do Aditivo"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.status"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Status da Proposta"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.taxaOperacao"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Taxa Operacao"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.valorParcela"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Valor Parcela"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.valorJuros"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Valor do Juros"
                  label-color="dark"
                />
                <q-input
                  v-model="proposta.valorPrincipal"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Valor Principal"
                  label-color="dark"
                />
                <div class="q-pa-md" style="max-width: 197px; margin-top: -5px">
                  <div>
                    <q-badge color="secondary">Data do Contrato</q-badge>
                  </div>
                  <q-input
                    v-model="proposta.dataContrato"
                    :locale="ptBr"
                    dense
                    disable
                    style="width: 168px "
                  >
                    <template v-slot:append>
                      <q-icon class="cursor-pointer" name="event">
                        <q-popup-proxy
                          cover
                          transition-hide="scale"
                          transition-show="scale"
                        >
                          <q-date
                            v-model="proposta.dataContrato"
                            :locale="ptBr"
                            mask="YYYY-MM-DD"
                          >
                            <div class="row items-center justify-end">
                              <q-btn
                                v-close-popup
                                color="secondary"
                                flat
                                label="Close"
                              />
                            </div>
                          </q-date>
                        </q-popup-proxy>
                      </q-icon>
                    </template>
                  </q-input>
                </div>
                <div class="q-pa-md" style="max-width: 197px; margin-top: -5px">
                  <div>
                    <q-badge color="secondary"
                    >Data do Primeiro Vencimento
                    </q-badge
                    >
                  </div>
                  <q-input
                    v-model="proposta.dataPrimeiroVencimento"
                    :locale="ptBr"
                    dense
                    disable
                    style="width: 168px "
                  >
                    <template v-slot:append>
                      <q-icon class="cursor-pointer" name="event">
                        <q-popup-proxy
                          cover
                          transition-hide="scale"
                          transition-show="scale"
                        >
                          <q-date
                            v-model="proposta.dataPrimeiroVencimento"
                            :locale="ptBr"
                            mask="YYYY-MM-DD"
                          >
                            <div class="row items-center justify-end">
                              <q-btn
                                v-close-popup
                                color="secondary"
                                flat
                                label="Close"
                              />
                            </div>
                          </q-date>
                        </q-popup-proxy>
                      </q-icon>
                    </template>
                  </q-input>
                </div>
                <q-input
                  v-model="proposta.valorReajuste"

                  :disable="simulacaoConfirma == false"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Reajuste de Credito"
                  label-color="dark"
                  mask="###########"
                  @blur="
                  novoValor != this.proposta.valorPrincipal
                    ? (simulacaoConfirma = false)
                    : simulacaoConfirma == true
                  "
                />
              </div>
            </div>
          </q-expansion-item>
        </q-list>

        <template v-slot:actions>
          <q-btn
            v-if="this.acao == 'Alterar' || this.simulacaoConfirma == true"
            :label="acao"
            :to="'/proposta/'"
            color="secondary"
            @click="submitForm"
          />

          <q-btn
            v-if="this.acao == 'Criar' && this.simulacaoConfirma == false"
            color="secondary"
            label="Simulação de Credito"
            @click="simulacaoCredito"
          />

          <q-btn
            color="secondary"
            label="Limpar"
            @click="limparP"
          />

        </template>
      </detail-filter-component>

    </template>
    <!-- volta para componente pai-->

    <template v-slot:actions>
      <router-link to="/proposta">
        <q-btn
          color="secondary"
          icon="list"
          label="Listagem de Propostas"
          style="margin-top: 5px"
          text-color="white"
          unelevated
        />
      </router-link
      >
    </template>
    <!-- slot de lista-->
    <template v-slot:list></template>
  </detail-page-component>
</template>

<script>
import DetailFilterComponent from "src/components/Page/DetailFilterComponent.vue";
import DetailPageComponent from "src/components/Page/DetailPageComponent.vue";
import { defineComponent } from "vue";
import notificacaoService from "src/services/notificacaoService.js";
import propostaService from "src/services/propostaService";
import simulacaoCreditoService from "src/services/simulacaoCreditoService";
import clienteService from "src/services/clienteService";

export default defineComponent({
  components: {
    DetailPageComponent,
    DetailFilterComponent
  },
  name: "PropostaForm",

  methods: {
    async submitForm() {
      if (this.acao == "Criar") {
        this.criar();
      } else if (this.acao == "Alterar") {
        this.atualizar();
      }
    },

    async criar() {
      this.formatarDataEnvio();
      this.$q.loading.show({ message: "Realizando operação" });
      try {
        await propostaService.save(this.proposta);
        this.limpar();
        notificacaoService.success("Registro salvo com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },

    async atualizar() {
      this.$q.loading.show({ message: "Realizando operação" });
      try {
        await propostaService.update(this.proposta);
        notificacaoService.success("Registro atualizado com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },

    limpar() {
      for (let chave in this.proposta) {
        this.proposta[chave] = null;
      }
    },

    limparP() {
      for (let chave in this.proposta) {
        if (chave !== "assessor") {
          this.proposta[chave] = null;
        }
      }
      this.trava = false;
      this.clienteSelecionado = null;
      this.simulacaoConfirma = false;
    },

    formatarData() {
      this.proposta.dataContrato = this.proposta.dataContrato.slice(0, 10).split("-").reverse().join("/");
      this.proposta.dataOperacao = this.proposta.dataOperacao.slice(0, 10).split("-").reverse().join("/");
      this.proposta.dataPrimeiroVencimento = this.proposta.dataPrimeiroVencimento.slice(0, 10).split("-").reverse().join("/");
    },

    formatarDataEnvio() {
      this.proposta.dataContrato = this.proposta.dataContrato.slice(0, 10).split("/").reverse().join("-");
      this.proposta.dataOperacao = this.proposta.dataOperacao.slice(0, 10).split("/").reverse().join("-");
      this.proposta.dataPrimeiroVencimento = this.proposta.dataPrimeiroVencimento.slice(0, 10).split("/").reverse().join("-");
    },

    async simulacaoCredito() {
      this.trava = true;
      this.$q.loading.show({ message: "Realizando operação" });
      try {
        const {data} = await simulacaoCreditoService.save(this.proposta);
        for (let chave in this.proposta) {
          this.proposta[chave] = data[chave];
        }
        this.formatarData();
        if (this.proposta.fluxoCaixa < 300) {
          notificacaoService.error("Proposta fora do limite disponível de credito");
          this.limparP();
          this.simulacaoConfirma = false;
        } else if(this.proposta.fluxoCaixa > 30000) {
          notificacaoService.error("Proposta fora do limite disponível de credito");
          this.limparP();
          this.simulacaoConfirma = false;
        } else {
          notificacaoService.success("Simulação Feita com sucesso.");
          this.simulacaoConfirma = true;
        }

      } catch ({ response }) {
        this.simulacaoConfirma = false;
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    }

  },

  data() {
    return {
      simulacaoConfirma: false,
      trava: false,
      acao: "Criar",
      clienteSelecionado: null,
      modalidades: modalidades,
      tipoProposta: tipoProposta,
      tipoRestritivos: tipoRestritivos,
      tipoStatus: tipoStatus,
      tipoFinalidade: tipoFinalidade,
      clientes: clientes,
      parcelas: parcelasSelect,

      proposta: {
        id: null,
        modalidade: null,
        tipoProposta: null,
        assessor: null,
        cliente: null,
        dataContrato: null,
        dataOperacao: null,
        valorParcela: null,
        valorJuros: null,
        valorPrincipal: null,
        valorReajuste: null,
        restritivos: null,
        numeroContrato: null,
        numeroAditivo: null,
        quantidadeParcelas: null,
        status: null,
        localizacao: null,
        taxaOperacao: null,
        checkLists: null,
        dataPrimeiroVencimento: null,
        finalidade: null,
        nomeAssessor: null,
        nomeCliente: null,
        receitaOperacional: null,
        receitaNaoOperacional: null,
        custoMercadoria: null,
        pagamentoPessoal: null,
        pagamentoFuncionarios: null,
        transporte: null,
        agua: null,
        luz: null,
        telefone: null,
        taxaAluguel: null,
        outrosCustos: null,
        outrasDespesas: null,
        outrosPagamentos: null,
        fluxoCaixa: null
      },

      ptBr: {
        days: "Domingo_Segunda_Terça_Quarta_Quinta_Sexta_Sábado".split("_"),
        daysShort: "Dom_Seg_Ter_Qua_Qui_Sex_Sáb".split("_"),
        months:
          "Janeiro_Fevereiro_Março_Abril_Maio_Junho_Julho_Agosto_Setembro_Outubro_Novembro_Dezembro".split(
            "_"
          ),
        monthsShort: "Jan_Fev_Mar_Abr_Mai_Jun_Jul_Ago_Set_Out_Nov_Dez".split(
          "_"
        ),
        firstDayOfWeek: 1, // 0-6, 0 - Sunday, 1 Monday, ...
        format24h: true,
        pluralDay: "dias"
      }
    };
  },


  async created() {
    this.$q.loading.show({ message: "Buscando Registro" });
    if (this.$route.params.propostaId !== undefined) {
      //Se existir caminho na URL para Edição de Proposta
      this.acao = "Alterar";
      this.$q.loading.show({ message: "Buscando Registro" });
      try {
        const { data } = await propostaService.show(this.$route.params.propostaId);
        for (let chave in this.proposta) {
          this.proposta[chave] = data[chave];
        }
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.formatarData();
    }

    if (this.$route.params.propostaId == undefined) {
      try {
        this.$q.loading.show({
          message: "Buscando Registro de assessor logado"
        });
        const { data } = await clienteService.list();
        this.clientes = data;
        this.$q.loading.hide();
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
    }
    this.$q.loading.hide();
  },

  watch: {
    clienteSelecionado(newCliente) {
      if (newCliente) {
        this.proposta.cliente = newCliente.id;
      } else {
        this.proposta.cliente = null;
      }
    }


  }
});

//Alguns "enuns" de referencia - DEVEM SER APAGADOS NA VERSÃO FINAL DO PROJETO
const modalidades = ["Micro Credito"];
const tipoProposta = [
  //para noção geral do status da proposta - será setado pela API
  "Nova",
  "Atualização de Dados",
  "Renovação"
];
const tipoRestritivos = [
  "Sem Restritivos",
  "Serasa",
  "SPC",
  "Ação Judicial",
  "Antecedentes Criminais",
  "Outros"
];
const tipoStatus = [
  //para noção geral do status da proposta - será setado pela API
  "Pendente CheckList",
  "Negado CheckList",
  "Pendente de Análise Oscip",
  "Negada Análise da Oscip",
  "Pendente de Análise Instituição Financeira",
  "Negada Análise Instituição Financeira",
  "Pendente de Formalização",
  "Formalização Cancelada",
  "Formalizada",
  "Cancelamento Automático"
];
const tipoFinalidade = [
  "Pagamento de Fornecedores",
  "Pagamento de Salarios",
  "Pagamento de Impostos",
  "Compra de Equipamentos",
  "Compra de Materiais",
  "Compra de Veiculos",
  "Compra de Imoveis",
  "Outros"
];
const parcelasSelect = [
  3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24
];
const clientes = [];
</script>
