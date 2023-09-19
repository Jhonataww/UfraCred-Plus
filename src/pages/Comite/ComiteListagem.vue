<template>
  <detail-page-component v-if="this.aut" title="Cômite de propostas">
    <template v-slot:filter>
      <!-- componente filtrer -->
      <detail-filter-component title="Pesquisar">
        <div class="q-pa-md">
          <div class="q-gutter-md row items-start">
            <q-input
              v-model="pesquisa.id"
              class="q-pa-md"
              color="secondary"
              label="ID da Proposta"
              label-color="dark"
            />
          </div>
        </div>

        <template v-slot:actions>
          <q-btn
            color="secondary"
            icon="search"
            label="Pesquisar"
            @click="listarProposta"
          />
        </template>
      </detail-filter-component>
    </template>
    <!-- volta para componente pai-->

    <template v-slot:actions>

    </template>
    <!-- slot de lista-->
    <template v-slot:list>
      <q-table
        :columns="colunasProposta"
        :rows="proposta"
        bordered
        flat
        no-data-label="Não há registros."
        row-key="name"
        rows-per-page-label="Registros por página:"
      >
        <template v-slot:body-cell-opcoes="props">
          <q-td :props="props">
            <q-btn
              :dense="true"
              :to="'/comite/' + props.row.id"
              color="primary"
              flat
              icon="content_paste_search"
              round
              size="12px"
            >
              <q-tooltip content-class="text-body2">
                Editar Registro
              </q-tooltip>
            </q-btn>

          </q-td>
        </template>
      </q-table>
    </template>
  </detail-page-component>
</template>

<script>
import DetailFilterComponent from "src/components/Page/DetailFilterComponent.vue";
import DetailPageComponent from "src/components/Page/DetailPageComponent.vue";
import { defineComponent } from "vue";
import notificacaoService from "src/services/notificacaoService.js";
import propostaService from "src/services/propostaService";
import { useAuthStore } from "src/stores/auth";

const authStore = useAuthStore();

export default defineComponent({
  components: {
    DetailPageComponent,
    DetailFilterComponent
  },
  name: "ComiteListagem",

  methods: {
    async listarProposta() {

      this.$q.loading.show({ message: "Buscando Registro" });
      try {
        const { data } = await propostaService.list(this.pesquisa);
        this.proposta = data;
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },

    async excluirRegistro(id) {
      this.$q.loading.show({ message: "Apagando Registro" });
      try {
        await propostaService.delete(id);
        notificacaoService.success("Proposta excluída com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      //this.$router.push("/proposta"); não funcionou, eu acho kkk
      this.$q.loading.hide();
      this.listarProposta();
    },

    async autLogin() {
      this.aut = authStore.getRoles.includes("ROLE_COORDENADOR");
      console.log(aut);
    }
  },

  data() {
    return {
      aut: null,
      modalExclusao: false,
      proposta: [],
      colunasProposta,
      pesquisa: {
        nome: null,
        comite: "comite"
      }
    };
  },

  created() {
    this.listarProposta();
    this.autLogin();
  }
});

const colunasProposta = [
  {
    label: "Opções",
    name: "opcoes",
    field: "opcoes",
    align: "center"
  },
  {
    label: "id",
    name: "id",
    field: "id",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Modalidade",
    name: "modalidade",
    field: "modalidade",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Tipo Proposta",
    name: "tipoProposta",
    field: "tipoProposta",
    align: "left",
    sortable: true
  },
  {
    label: "Assessor",
    name: "nomeAssessor",
    field: "nomeAssessor",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Cliente",
    name: "nomeCliente",
    field: "nomeCliente",
    align: "left",
    sortable: true
  },
  {
    label: "Data Contrato",
    name: "dataContrato",
    field: "dataContrato",
    align: "left",
    format: (val) => val.slice(0, 10).split("-").reverse().join("/"),
    sortable: true
  },
  {
    label: "Data Operacao",
    name: "dataOperacao",
    field: "dataOperacao",
    align: "left",
    format: (val) => val.slice(0, 10).split("-").reverse().join("/"),
    sortable: true
  },
  {
    label: "Valor Parcela",
    name: "valorParcela",
    field: "valorParcela",
    align: "left",
    sortable: true
  },
  {
    label: "Valor Juros",
    name: "valorJuros",
    field: "valorJuros",
    align: "left",
    sortable: true
  },
  {
    label: "Valor Principal",
    name: "valorPrincipal",
    field: "valorPrincipal",
    align: "left",
    sortable: true
  },
  {
    label: "Restritivos",
    name: "restritivos",
    field: "restritivos",
    align: "left",
    sortable: true
  },
  {
    label: "Numero Contrato",
    name: "numeroContrato",
    field: "numeroContrato",
    align: "left",
    sortable: true
  },
  {
    label: "Numero Aditivo",
    name: "numeroAditivo",
    field: "numeroAditivo",
    align: "left",
    sortable: true
  },
  {
    label: "Quantidade de Parcelas",
    name: "quantidadeParcelas",
    field: "quantidadeParcelas",
    align: "left",
    sortable: true
  },
  {
    label: "Status",
    name: "status",
    field: "status",
    align: "left",
    sortable: true
  },
  {
    label: "Localizacao",
    name: "localizacao",
    field: "localizacao",
    align: "left",
    sortable: true
  },
  {
    label: "Taxa Operacao",
    name: "taxaOperacao",
    field: "taxaOperacao",
    align: "left",
    sortable: true
  },
  {
    label: "CheckLists",
    name: "checkLists",
    field: "checkLists",
    align: "left",
    sortable: true
  },
  {
    label: "Data do Primeiro Vencimento",
    name: "dataPrimeiroVencimento",
    field: "dataPrimeiroVencimento",
    align: "left",
    format: (val) => val.slice(0, 10).split("-").reverse().join("/"),
    sortable: true
  },
  {
    label: "Finalidade",
    name: "finalidade",
    field: "finalidade",
    align: "left",
    sortable: true
  }
];
</script>
