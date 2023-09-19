<template>
  <div class="login-container">
    <q-card class="login-box">
      <q-card-section>
        <q-img
          src="~assets/logoUfraCred.png"
          style="height: 30%; width: 30%; margin-left: 115px"
        />
      </q-card-section>

      <q-card-section>
        <q-input
          v-model="login"
          bottom-slots
          color="secondary"
          label="Usuário"
          label-color="dark"
          outlined
        >
          <template v-slot:prepend>
            <q-icon color="primary" name="person" square />
          </template>
        </q-input>

        <q-input
          v-model="senha"
          :type="isPwd ? 'password' : 'text'"
          color="secondary"
          label-color="dark"
          outlined
        >
          <template v-slot:prepend>
            <q-icon color="primary" name="lock" square />
          </template>

          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>

        <div
          class="text-h8 text-center justify-center"
          style="opacity: 0.8; padding-top: 16px"
          text-color="blue"
        >
          <router-link to="/EsqueciMinhaSenha">Esqueci minha senha</router-link>
        </div>
      </q-card-section>

      <q-card-actions align="center" vertical>
        <q-btn
          border-radius="20px"
          class="q-mt-xl"
          label="Acessar"
          no-caps
          size="lg"
          style="background-color: #f72716"
          text-color="white"
          unelevated
          @click="realizarLogin"
        />
      </q-card-actions>
    </q-card>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import { useAuthStore } from "stores/auth";
import notificacaoService from "src/services/notificacaoService";

const authStore = useAuthStore();

export default defineComponent({
  name: "LoginPage",

  data() {
    return {
      login: "teste",
      senha: "teste",
      isPwd: true
    };
  },

  methods: {
    async realizarLogin() {
      this.$q.loading.show({ message: "Realizando operação" });
      try {
        await authStore.login(this.login, this.senha);
        this.$router.push(this.$route.query.to || "/");
      } catch (error) {
        notificacaoService.error("Usuário ou senha inválida");
      }
      this.$q.loading.hide();
    }
  }
});
</script>

<style escoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #0b6a1c;
  background-image: url(~assets/logoUfra.svg);
  background-repeat: no-repeat;
  background-size: 100%;
}

.login-box {
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  box-shadow: 0px 0px 10px #ccc;
  width: 400px;
  height: 500px;
}
</style>
