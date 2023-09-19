<template>
  <detail-page-component title="Base Única de Cadastros">
    <template v-slot:filter>
      <!-- componente filtrer -->
      <detail-filter-component title="Pesquisar">
        <div class="q-pa-md">
          <div class="q-gutter-md row items-start">
            <q-input
              v-model="pesquisa.nome"
              class="q-pa-md"
              color="secondary"
              label="Nome do usuario"
              label-color="dark"
              outlined
            />
          </div>
        </div>

        <template v-slot:actions>
          <q-btn
            color="secondary"
            icon="search"
            label="Pesquisar"
            @click="listarUsuarios"
          />
        </template>
      </detail-filter-component>
    </template>
    <!-- volta para componente pai-->

    <template v-slot:actions>
      <router-link to="/usuario/novo">
        <q-btn
          color="secondary"
          icon="add"
          label="Novo Usuario"
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
        :columns="colunasUsuario"
        :rows="usuarios"
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
              :to="'/usuario/' + props.row.id"
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
              @click="modalExclusao = true"
            >
              <q-tooltip content-class="text-body2">
                Remover Registro
              </q-tooltip>
            </q-btn>

            <q-dialog v-model="modalExclusao" persistent>
              <q-card>
                <q-card-section class="row items-center">
                  <q-avatar color="negative" icon="delete" text-color="white" />
                  <span class="q-ml-sm"
                  >Deseja confirmar a exclusão do registro?</span
                  >
                </q-card-section>

                <q-card-actions align="right">
                  <q-btn v-close-popup color="primary" flat label="Cancelar" />
                  <q-btn
                    v-close-popup
                    color="primary"
                    flat
                    label="Confirmar"
                    @click="excluirRegistro(props.row.id)"
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
import usuarioService from "src/services/usuarioService.js";
import notificacaoService from "src/services/notificacaoService.js";

export default defineComponent({
  components: {
    DetailPageComponent,
    DetailFilterComponent
  },
  name: "UsuarioListagem",

  methods: {
    async listarUsuarios() {
      this.$q.loading.show({ message: "Buscando Registro" });
      try {
        const { data } = await usuarioService.list(this.pesquisa);
        this.usuarios = data;
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },
    async excluirRegistro(id) {
      this.$q.loading.show({ message: "Apagando Registro" });
      try {
        await usuarioService.delete(id);
        notificacaoService.success("Usuario excluído com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
      window.location.reload();
    }
  },

  data() {
    return {
      modalExclusao: false,
      colunasUsuario,
      usuarios: [],
      pesquisa: {
        nome: null,
        codigo: null,
        ispb: null
      }
    };
  },

  created() {
    this.listarUsuarios();
  }
});

const colunasUsuario = [
  {
    label: "Opções",
    name: "opcoes",
    field: "opcoes",
    align: "center"
  },
  {
    label: "Username",
    name: "username",
    field: "username",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Perfis",
    name: "roles",
    field: "roles",
    align: "left",
    required: true,
    sortable: true
  },
  {
    label: "Status",
    name: "enabled",
    field: "enabled",
    format: (val) => (val ? "Ativo" : "Inativo"),
    align: "left",
    sortable: true
  }
];
</script>
