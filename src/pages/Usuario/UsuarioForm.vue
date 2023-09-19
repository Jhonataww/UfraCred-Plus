<template>
  <detail-page-component title="Base Única de Cadastros">
    <template v-slot:filter>
      <!-- componente filtrer -->
      <detail-filter-component title="Novo usuário">
        <div class="q-pa-md">
          <div class="q-gutter-md items-start">
            <q-input
              v-model="usuario.name"
              class="q-pa-md"
              color="secondary"
              label="Nome"
              label-color="dark"
              outlined
            />

            <q-input
              v-model="usuario.username"
              class="q-pa-md"
              color="secondary"
              label="Username"
              label-color="dark"
              outlined
            />

            <q-input
              v-model="usuario.email"
              class="q-pa-md"
              color="secondary"
              label="Email"
              label-color="dark"
              outlined
            />

            <div v-if="this.acao == 'Criar'">
              <q-input
                ref="passwordInput"
                v-model="usuario.password"
                :rules="[
                  () => isValidPassword || 'Senha fora do padrão',
                  (val) =>
                    (!!val && val == passwordConfirm) ||
                    'As senhas não são iguais',
                ]"
                class="q-pa-md"
                color="secondary"
                label="Senha"
                label-color="dark"
                outlined
                @blur="this.$refs.passwordConfirmInput.validate()"
              />

              <password-criteria
                :senha="usuario.password"
                class="q-pa-md"
                @validate="(val) => (isValidPassword = val)"
              />

              <q-input
                ref="passwordConfirmInput"
                v-model="passwordConfirm"
                :rules="[
                  () => isValidPassword || 'Senha fora do padrão',
                  (val) =>
                    (!!val && val == usuario.password) ||
                    'As senhas não são iguais',
                ]"
                class="q-pa-md"
                color="secondary"
                label="Confirmar Senha"
                label-color="dark"
                outlined
                @blur="this.$refs.passwordInput.validate()"
              />
            </div>

            <q-select
              v-model="usuario.roles"
              :options="rolesOptions"
              class="q-pa-md"
              color="secondary"
              emit-value
              label="Perfil"
              label-color="dark"
              map-options
              option-label="authority"
              option-value="authority"
              outlined
            />

            <q-toggle
              v-model="usuario.enabled"
              checked-icon="check"
              class="q-pa-md"
              color="secondary"
              label="Ativo"
              label-color="dark"
              outlined
              unchecked-icon="clear"
            />
          </div>
        </div>

        <template v-slot:actions>
          <q-btn
            v-if="acao == 'Alterar'"
            color="secondary"
            icon="lock"
            label="Alterar senha"
            @click="modalResetSenha = true"
          />
          <q-btn :label="acao" color="secondary" @click="submitForm" />
        </template>
      </detail-filter-component>
    </template>
    <!-- volta para componente pai-->

    <template v-slot:actions>
      <router-link to="/usuario">
        <q-btn
          color="secondary"
          icon="list"
          label="Listagem de usuarios"
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

  <q-dialog v-model="modalResetSenha">
    <reset-senha @on-success="modalResetSenha = false" />
  </q-dialog>
</template>

<script>
import DetailFilterComponent from "src/components/Page/DetailFilterComponent.vue";
import DetailPageComponent from "src/components/Page/DetailPageComponent.vue";
import ResetSenha from "src/components/ResetSenha.vue";
import { defineComponent } from "vue";
import usuarioService from "src/services/usuarioService.js";
import notificacaoService from "src/services/notificacaoService.js";
import roleService from "src/services/roleService";
import PasswordCriteria from "src/components/PasswordCriteria.vue";

export default defineComponent({
  components: {
    DetailPageComponent,
    DetailFilterComponent,
    ResetSenha,
    PasswordCriteria
  },
  name: "UsuarioForm",

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
        await usuarioService.save(this.usuario);
        notificacaoService.success("Registro salvo com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },
    async atualizar() {
      this.$q.loading.show({ message: "Realizando operação" });
      try {
        await usuarioService.update(this.usuario);
        notificacaoService.success("Registro atualizado com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },
    limpar() {
      for (let chave in this.usuario) {
        this.usuario[chave] = null;
      }
    }
  },

  data() {
    return {
      acao: "Criar",
      rolesOptions: [],
      modalResetSenha: false,
      isValidPassword: false,
      passwordConfirm: null,
      usuario: {
        id: null,
        name: null,
        username: null,
        email: null,
        password: null,
        enabled: true,
        accountExpired: false,
        accountLocked: false,
        roles: []
      }
    };
  },

  async created() {
    this.$q.loading.show({ message: "Buscando Registro" });
    try {
      const { data } = await roleService.list();
      this.rolesOptions = data;
    } catch ({ response }) {
      notificacaoService.error(response.data);
    }
    if (this.$route.params.usuarioId !== undefined) {
      this.acao = "Alterar";
      try {
        const { data } = await usuarioService.show(
          this.$route.params.usuarioId
        );
        for (let chave in this.usuario) {
          this.usuario[chave] = data[chave];
        }
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
    }
    this.$q.loading.hide();
  }
});
</script>
