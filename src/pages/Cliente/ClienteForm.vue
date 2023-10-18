<template>
  <detail-page-component title="Cadastro de Cliente">
    <template v-slot:filter>
      <!-- componente filtrer -->
      <detail-filter-component title="Novo Cliente">
        <q-list bordered class="rounded-borders q-pa-sm">
          <q-expansion-item expand-separator icon="person" label="Dados Pessoais" style="color: #43744e">
            <div class="q-pa-md">
              <div class="q-gutter-md row items-start">
                <q-input
                  v-model="cliente.nomeCompleto"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Nome Completo"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-model="cliente.nomeAbreviado"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Nome Abreviado"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-model="cliente.mae"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Nome da Mãe"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-model="cliente.pai"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Nome do Pai"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-select
                  v-model="cliente.tipoPessoa"
                  :options="tipoPessoaSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Tipo de Pessoa"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-if="cliente.tipoPessoa == 'Física' || cliente.tipoPessoa == null"
                  v-model="cliente.cpfCnpj"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="CPF"
                  label-color="dark"
                  mask="###########"
                  style="width: 14rem "
                />
                <q-input
                  v-if="cliente.tipoPessoa == 'Jurídica'"
                  v-model="cliente.cpfCnpj"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="CNPJ"
                  label-color="dark"
                  mask="##############"
                  style="width: 14rem "
                />
                <q-select
                  v-model="cliente.estadoCivil"
                  :options="estadoCivilSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Estado Civil "
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-if="cliente.estadoCivil == 'Casado'"
                  v-model="cliente.cpfConjuge"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="CPF do Conjuge"
                  label-color="dark"
                  mask="###########"
                  style="width: 14rem "
                />

                <q-select
                  v-model="cliente.sexo"
                  :options="sexoSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Sexo"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-select
                  v-model="cliente.situacaoEspecial"
                  :options="SimNaoSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Situação Especial"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-select
                  v-model="cliente.necessidadesEspeciais"
                  :options="SimNaoSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Pe. com Deficiência"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-select

                  v-model="cliente.capacidadeCivil"
                  :options="SimNaoSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Capacidade Civil"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-select

                  v-model="cliente.grauInstrucao"
                  :options="grauInstrucaoSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Grau de Instrução"
                  label-color="dark"
                  style="width: 14rem "
                />
                <div class="q-pa-md" style="max-width: 225px; margin-top: -5px">
                  <div>
                    <q-badge color="secondary">Data de Nascimento</q-badge>
                  </div>
                  <q-input

                    v-model="cliente.nascimento"
                    :locale="ptBr"
                    :rules="[val => !!val || 'Campo Obrigatório']"
                    dense
                    mask="##/##/####"
                    style="width: 12rem "
                  >
                    <template v-slot:append>
                      <q-icon class="cursor-pointer" name="event">
                        <q-popup-proxy
                          cover
                          transition-hide="scale"
                          transition-show="scale"
                        >
                          <q-date
                            v-model="cliente.nascimento"
                            :locale="ptBr"
                            default-view="Years"
                            mask="DD-MM-YYYY"
                            minimal
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
              </div>
            </div>
          </q-expansion-item>
        </q-list>

        <q-list bordered class="rounded-borders q-pa-sm" style="color: #43744e; margin-top: 20px">
          <q-expansion-item expand-separator icon="source" label="Dados Gerais">
            <div class="q-pa-md">
              <div class="q-gutter-md row items-start">

                <q-input
                  v-model="cliente.cep"

                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="CEP"
                  label-color="dark"
                  mask="########"
                  @blur="buscaCep()"
                  style="width: 14rem ">
                </q-input>
                <q-input
                  v-model="cliente.endereco"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Endereço"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-model="cliente.cidade"

                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Cidade"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-select

                  v-model="cliente.servidorPublico"
                  :options="SimNaoSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Servidor Público"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-select

                  v-model="cliente.politicamenteExposta"
                  :options="SimNaoSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Politicamente Exposta"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-model="cliente.nacionalidade"

                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Nacionalidade"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-model="cliente.produtosServicos"

                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Produtos Servicos"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-input
                  v-model="cliente.proposito"

                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Proposito"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-model="cliente.atividade"

                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Atividade"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-select

                  v-model="cliente.estado"
                  :options="estadosSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="UF"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-select

                  v-model="cliente.titular"
                  :options="titularSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Titular"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-select

                  v-model="cliente.grauInteresse"
                  :options="grauInteresseSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Grau de Interesse"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-select

                  v-model="cliente.possuiRenda"
                  :options="SimNaoSelect"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  label="Possui Renda"
                  label-color="dark"
                  style="width: 14rem "
                />

                <q-input
                  v-if="this.acao != 'Criar'"

                  v-model="cliente.id"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="ID da Cliente"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-if="this.acao != 'Criar'"

                  v-model="cliente.carteira.id"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="ID da Carteira"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-if="this.acao != 'Criar'"

                  v-model="cliente.atualizacao"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Atualização"
                  label-color="dark"
                  style="width: 14rem "
                />
                <q-input
                  v-if="this.acao != 'Criar'"

                  v-model="cliente.situacao"
                  :rules="[val => !!val || 'Campo Obrigatório']"
                  class="q-pa-md"
                  color="secondary"
                  dense
                  disable
                  label="Situação"
                  label-color="dark"
                  style="width: 14rem "
                />

                <div v-if="this.acao != 'Criar'" class="q-pa-md" style="max-width: 225px; margin-top: -5px">
                  <div>
                    <q-badge color="secondary">Data de Atualização</q-badge>
                  </div>
                  <q-input

                    v-if="this.acao != 'Criar'"
                    v-model="cliente.dataAtualizacao"
                    :locale="ptBr"
                    dense
                    disable

                    style="width: 12rem "
                  >
                    <template v-slot:append>
                      <q-icon class="cursor-pointer" name="event">
                        <q-popup-proxy
                          cover
                          transition-hide="scale"
                          transition-show="scale"
                        >
                          <q-date
                            v-model="cliente.dataAtualizacao"
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
              </div>
            </div>
          </q-expansion-item>
        </q-list>

        <template v-slot:actions>
          <q-btn
            :label="acao"
            :to="'/cliente/'"
            color="secondary"
            @click="submitForm"
          />

        </template>
      </detail-filter-component>
    </template>
    <!-- volta para componente pai-->

    <template v-slot:actions>
      <router-link to="/cliente">
        <q-btn
          color="secondary"
          icon="list"
          label="Listagem de Cliente"
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
import assessorService from "src/services/assessorService";
import clienteService from "src/services/clienteService";
import { useAuthStore } from "src/stores/auth";
import cepService from "src/services/cepService";

export default defineComponent({
  components: {
    DetailPageComponent,
    DetailFilterComponent
  },
  name: "ClienteForm",

  data() {
    return {

      acao: "Criar",

      estadoCivilSelect: estadoCivilSelect,
      estadosSelect: estadosSelect,
      SimNaoSelect: SimNaoSelect,
      grauInteresseSelect: grauInteresseSelect,
      grauInstrucaoSelect: grauInstrucaoSelect,
      sexoSelect: sexoSelect,
      titularSelect: titularSelect,
      tipoPessoaSelect: tipoPessoaSelect,

      assessorLogado: null,
      carteiraLogada: {
        carteiraId: null
      },
      userLogado: {
        userName: this.authStore.username
      },


      cliente: {
        id: null,
        estadoCivil: null,
        cidade: null,
        nomeCompleto: null,
        estado: null,
        dataAtualizacao: null,
        proposito: null,
        sexo: null,
        titular: null,
        mae: null,
        politicamenteExposta: null,
        excluido: 0,
        pai: null,
        situacaoEspecial: null,
        carteira: {
          id: null
        },
        servidorPublico: null,
        cep: null,
        atualizacao: null,
        nacionalidade: null,
        capacidadeCivil: null,
        cpfConjuge: null,
        situacao: null,
        grauInteresse: null,
        endereco: null,
        cpfCnpj: null,
        produtosServicos: null,
        grauInstrucao: null,
        possuiRenda: null,
        atividade: null,
        nomeAbreviado: null,
        tipoPessoa: null,
        necessidadesEspeciais: null,
        nascimento: null

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

  methods: {
    async submitForm() {
      if (this.acao == "Criar") {
        this.criar();
      } else if (this.acao == "Alterar") {
        this.atualizar();
      }
    },

    async criar() {
      this.$q.loading.show({ message: "Realizando operação" });
      try {
        this.formatarData();
        await clienteService.save(this.cliente);
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
        this.formatarData();
        await clienteService.update(this.cliente);
        notificacaoService.success("Registro atualizado com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },

    async buscaCep() {
      this.$q.loading.show({ message: "Buscando CEP" });
      try {
        const response = await cepService.list(this.cliente.cep);
        let complemento = response.data.complemento !== undefined ? response.data.complemento : "";
        this.cliente.endereco = response.data.logradouro + " " + complemento;
        this.cliente.cidade = response.data.localidade;
      } catch (error) {
        console.error(error);
      }
      this.$q.loading.hide();
    },

    limpar() {
      for (let chave in this.cliente) {
        this.cliente[chave] = null;
      }
    },

    formatarData() {
      const data = this.cliente.nascimento;
      const partesData = data.split("/"); // Divide a string da data em partes separadas
      this.cliente.nascimento = `${partesData[2]}-${partesData[1]}-${partesData[0]}`;
    },

    formatarDataEdit() {
      const data = this.cliente.nascimento.slice(0, 10);
      const partesData = data.split("-"); // Divide a string da data em partes separadas
      this.cliente.nascimento = `${partesData[2]}/${partesData[1]}/${partesData[0]}`;
    }
  },

  setup() {
    const authStore = useAuthStore();
    return {
      authStore
    };
  },

  async created() {
    this.$q.loading.show({ message: "Buscando Registro" });
    if (this.$route.params.clienteId !== undefined) {
      //Se existir caminho na URL para Edição de cliente
      this.acao = "Alterar";
      this.$q.loading.show({ message: "Buscando Registro" });
      try {
        const { data } = await clienteService.show(
          this.$route.params.clienteId
        );
        for (let chave in this.cliente) {
          this.cliente[chave] = data[chave];
        }
        this.formatarDataEdit();
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    }

    if (this.$route.params.propostaId == undefined) {
      //Se não existir caminho na URL para Edição de Proposta
      if (this.authStore.username != null && this.authStore.username != "") {
        try {
          this.$q.loading.show({
            message: "Buscando Registro de assessor logado"
          });
          const { data } = await assessorService.list(this.userLogado);
          this.assessorLogado = data[0];
          this.cliente.carteira.id = data[0].carteira.id;
          this.$q.loading.hide();
        } catch ({ response }) {
          notificacaoService.error(response.data);
        }
      }

      try {
        this.$q.loading.show({ message: "Buscando Registro de clientes" });
        const { data } = await clienteService.list(this.carteiraLogada);
        this.clientes = data;
        this.$q.loading.hide();
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
    }

    this.$q.loading.hide();
  }
});

const estadoCivilSelect = [
  "Solteiro",
  "Casado",
  "Separado",
  "Divorciado",
  "Viúvo"
];

const estadosSelect = [
  "Acre",
  "Alagoas",
  "Amazonas",
  "Amapá",
  "Bahia",
  "Ceará",
  "Distrito Federal",
  "Espírito Santo",
  "Goiás",
  "Maranhão",
  "Minas Gerais",
  "Mato Grosso do Sul",
  "Mato Grosso",
  "Pará",
  "Paraíba",
  "Pernambuco",
  "Piauí",
  "Paraná",
  "Rio de Janeiro",
  "Rio Grande do Norte",
  "Rondônia",
  "Roraima",
  "Rio Grande do Sul",
  "Santa Catarina",
  "Sergipe",
  "São Paulo",
  "Tocantins"
];

const SimNaoSelect = [
  "Sim",
  "Não"
];

const grauInteresseSelect = [
  "Alto",
  "Medio",
  "Baixo",
  "Nenhum"
];

const grauInstrucaoSelect = [
  "Analfabeto",
  "Ensino fundamental incompleto",
  "Ensino fundamental completo",
  "Ensino médio incompleto",
  "Ensino médio completo",
  "Superior completo (ou graduação)",
  "Pós-graduação",
  "Mestrado",
  "Doutorado",
  "Pós-Doutorado"
];

const sexoSelect = [
  "Masculino",
  "Feminino",
  "Outro"
];

const titularSelect = [
  "Mãe",
  "Pai",
  "Maioridade civil"
];

const tipoPessoaSelect = [
  "Física",
  "Jurídica"
];

</script>
