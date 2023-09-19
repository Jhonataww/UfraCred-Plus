<template>
  <q-card class="my-card painel-detail">
    <q-item>
      <q-item-section avatar>
        <q-avatar size="150px">
          <img src="~assets/userLogo.png" />
        </q-avatar>
      </q-item-section>

      <q-item-section>
        <q-item-label>Nome: {{ this.userLogado.userName }}</q-item-label>
        <q-item-label caption>{{}}</q-item-label>

      </q-item-section>
    </q-item>
  </q-card>

  <!--
  <q-card class="my-card painel-detail">
    <div class="text-subtitle2 text-uppercase">Dados</div>
    <div class="q-pa-md">
      <div class="q-gutter-md row items-start">
        <q-input
          class="q-pa-md"
          outlined
          color="secondary"
          label-color="dark"
          v-model="pesquisa.nome"
          label="Nome do usuario"
        />

        <q-input
          class="q-pa-md"
          outlined
          color="secondary"
          label-color="dark"
          v-model="pesquisa.codigo"
          label="Código"
        />
      </div>
    </div>
  </q-card>
  -->
</template>

<script>
import { defineComponent } from "vue";
import usuarioService from "src/services/usuarioService.js";
import notificacaoService from "src/services/notificacaoService.js";
import assessorService from "src/services/assessorService";
import { useAuthStore } from "stores/auth";

export default defineComponent({
  components: {},
  name: "UsuarioListagem",

  methods: {
    async listarUsuarios() {
      this.$q.loading.show({ message: "Buscando Registro" });
      try {
        const { data } = await usuarioService.list(this.pesquisa);
        this.usuarios = data;
        this.usuarioLogado = data[0];
        console.log(this.usuarioLogado);
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
    },

    async listarAssessor() {
      this.$q.loading.show({ message: "Buscando Registro" });
      if (this.authStore.username != null && this.authStore.username != "") {
        try {
          this.$q.loading.show({
            message: "Buscando Registro de Assessor Logado"
          });
          const { data } = await assessorService.list(this.userLogado);
          this.assessor = data[0];
          console.log(this.assessor);
        } catch ({ response }) {
          notificacaoService.error(response.data);
        }
        this.$q.loading.hide();
      }
      this.$q.loading.hide();
    }
  },

  data() {
    return {
      modalExclusao: false,
      usuarioLogado: {},
      usuarios: [],
      assessor: null,

      userLogado: {
        userName: this.authStore.username
      },
      pesquisa: {
        nome: null,
        codigo: null,
        ispb: null
      }
    };
  },

  setup() {
    const authStore = useAuthStore();
    return {
      authStore
    };
  },

  created() {
    this.listarUsuarios();
    this.listarAssessor();
  }
});

</script>
