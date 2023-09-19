<template>
  <detail-page-component v-if="this.aut" title="Listagem de Clientes">
    <template v-slot:filter>
      <!-- componente filtrer -->
      <detail-filter-component title="Pesquisar">
        <div class="q-pa-md">
          <div class="q-gutter-md row items-start">
            <q-input
              v-model="pesquisa.id"
              class="q-pa-md"
              color="secondary"
              dense
              label="ID do Cliente"
              label-color="dark"
            />

            <q-input
              v-model="pesquisa.nomeCliente"
              class="q-pa-md"
              color="secondary"
              dense
              label="Nome do cliente"
              label-color="dark"
            ></q-input>
          </div>
        </div>

        <template v-slot:actions>
          <q-btn
            color="secondary"
            icon="search"
            label="Pesquisar"
            @click="listarCliente"
          />
        </template>
      </detail-filter-component>
    </template>
    <!-- volta para componente pai-->

    <template v-slot:actions>
      <router-link to="/cliente/novo">
        <q-btn
          color="secondary"
          icon="add"
          label="Novo Cliente"
          style="margin-top: 5px"
          text-color="white"
          unelevated
        />
      </router-link
      >
    </template>
    <!-- slot de lista-->
    <template v-slot:list>
      <q-table
        :columns="colunasCliente"
        :rows="cliente"
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
              :to="'/cliente/' + props.row.id"
              color="primary"
              flat
              icon="edit"
              round
              size="12px"
            >
              <q-tooltip content-class="text-body2">
                Editar Registro
              </q-tooltip>
            </q-btn>

            <q-btn
              color="negative"
              dense
              flat
              icon="delete"
              round
              size="12px"
              @click="confirmarExclusao(props.row)"
            >
              <q-tooltip content-class="text-body2">
                Remover Registro {{ props.row.id }}
              </q-tooltip>
            </q-btn>

            <q-dialog v-model="modalExclusao" persistent>
              <q-card>
                <q-card-section class="row items-center">
                  <q-avatar color="negative" icon="delete" text-color="white" />
                  <span class="q-ml-sm"
                  >Deseja confirmar a exclusão do registro (
                    {{ linhaExclusao.id }} )?
                  </span>
                </q-card-section>

                <q-card-actions align="right">
                  <q-btn v-close-popup color="primary" flat label="Cancelar" />
                  <q-btn
                    v-close-popup
                    color="primary"
                    flat
                    label="Confirmar"
                    @click="excluirRegistro(linhaExclusao.id)"
                  />
                </q-card-actions>
              </q-card>
            </q-dialog>
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
import clienteService from "src/services/clienteService";
import { useAuthStore } from "src/stores/auth";

const authStore = useAuthStore();

export default defineComponent({
  components: {
    DetailPageComponent,
    DetailFilterComponent
  },
  name: "ClienteListagem",

  data() {
    return {
      aut: null,
      linhaExclusao: null,
      modalExclusao: false,
      colunasCliente,
      cliente: [],
      pesquisa: {
        id: null,
        nomeCliente: null
      }
    };
  },

  methods: {
    async listarCliente() {
      this.$q.loading.show({ message: "Buscando Registro" });
      try {
        const { data } = await clienteService.list(this.pesquisa);
        this.cliente = data;
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },

    confirmarExclusao(row) {
      this.linhaExclusao = row; // Armazena a linha atual  --- testar e verificar pq a lista ta pegando ultimo valor
      this.modalExclusao = true;
    },

    async excluirRegistro(id) {
      this.$q.loading.show({ message: "Apagando Registro" });
      try {
        await clienteService.delete(id);
        notificacaoService.success("Cliente excluído com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
      this.listarCliente();
    },
    async authLogin() {
      this.aut = authStore.getRoles.includes("ROLE_ADMIN");
      console.log(aut);
    }
  },

  created() {
    this.listarCliente();
    this.authLogin();
  }

});

const colunasCliente = [
  {
    label: "Opções",
    name: "opcoes",
    field: "opcoes",
    align: "center"
  },
  {
    label: "ID",
    name: "id",
    field: "id",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Nome Completo",
    name: "nomeCompleto",
    field: "nomeCompleto",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Nome Abreviado",
    name: "nomeAbreviado",
    field: "nomeAbreviado",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Mãe",
    name: "mae",
    field: "mae",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Pai",
    name: "pai",
    field: "pai",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Sexo",
    name: "sexo",
    field: "sexo",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Grau Instrucao",
    name: "grauInstrucao",
    field: "grauInstrucao",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Estado Civil",
    name: "estadoCivil",
    field: "estadoCivil",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Capacidade Civil",
    name: "capacidadeCivil",
    field: "capacidadeCivil",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Nacionalidade",
    name: "nacionalidade",
    field: "nacionalidade",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Estado",
    name: "estado",
    field: "estado",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Cidade",
    name: "cidade",
    field: "cidade",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Possui Renda",
    name: "possuiRenda",
    field: "possuiRenda",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Servidor Público",
    name: "servidorPublico",
    field: "servidorPublico",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Necessidades Especiais",
    name: "necessidadesEspeciais",
    field: "necessidadesEspeciais",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Situacao Especial",
    name: "situacaoEspecial",
    field: "situacaoEspecial",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Proposito",
    name: "proposito",
    field: "proposito",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Produtos e Servicos",
    name: "produtosServicos",
    field: "produtosServicos",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Atividade",
    name: "atividade",
    field: "atividade",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Grau Interesse",
    name: "grauInteresse",
    field: "grauInteresse",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Politicamente Exposta",
    name: "politicamenteExposta",
    field: "politicamenteExposta",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Titular",
    name: "titular",
    field: "titular",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Atualização",
    name: "atualizacao",
    field: "atualizacao",

    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Situação",
    name: "situacao",
    field: "situacao",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Tipo Pessoa",
    name: "tipoPessoa",
    field: "tipoPessoa",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "CPF/CNPJ",
    name: "cpfCnpj",
    field: "cpfCnpj",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "CPF do Conjuge",
    name: "cpfConjuge",
    field: "cpfConjuge",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "CEP",
    name: "cep",
    field: "cep",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Endereço",
    name: "endereco",
    field: "endereco",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Nascimento",
    name: "nascimento",
    field: "nascimento",
    format: (val) => val.slice(0, 10).split("-").reverse().join("/"),
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Data Atualizacao",
    name: "dataAtualizacao",
    field: "dataAtualizacao",
    format: (val) => val.slice(0, 10).split("-").reverse().join("/"),
    align: "left",
    required: true,
    sortable: true
  }
];
</script>
