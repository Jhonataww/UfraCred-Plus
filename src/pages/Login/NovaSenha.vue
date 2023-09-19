<template>
  <div class="login-container">
    <q-form @submit="solicitarNovaSenha">
      <q-card class="login-box2">
        <q-card-section>
          <q-img src="~assets/logoUfra.svg" style="transform: scale(0.7)" />
        </q-card-section>

        <q-card-section>
          <div class="">
            <div
              class="text-h8 text-center justify-center"
              style="opacity: 0.8; padding-top: 16px"
              text-color="blue"
            >
              Nova Senha:
            </div>
            <q-input
              ref="passwordInput"
              v-model="senhaNova"
              :rules="[
                () => isValidPassword || 'Senha fora do padrão',
                (val) =>
                  (!!val && val == senhaConfirmada) ||
                  'As senhas não são iguais',
              ]"
              :type="isPwd ? 'password' : 'text'"
              color="secondary"
              label-color="dark"
              outlined
              @blur="this.$refs.passwordConfirmInput.validate()"
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

            <password-criteria
              :senha="senhaNova"
              @validate="(val) => (isValidPassword = val)"
            />

            <div
              class="text-h8 text-center justify-center"
              style="opacity: 0.8; padding-top: 16px"
              text-color="blue"
            >
              Confirmar nova senha:
            </div>
            <q-input
              ref="passwordConfirmInput"
              v-model="senhaConfirmada"
              :rules="[
                (val) =>
                  (!!val && val == senhaNova) || 'As senhas não são iguais',
              ]"
              :type="isPwd2 ? 'password' : 'text'"
              color="secondary"
              label-color="dark"
              outlined
              @blur="this.$refs.passwordInput.validate()"
            >
              <template v-slot:prepend>
                <q-icon color="primary" name="lock" square />
              </template>

              <template v-slot:append>
                <q-icon
                  :name="isPwd2 ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="isPwd2 = !isPwd2"
                />
              </template>
            </q-input>
          </div>
        </q-card-section>

        <q-card-actions align="center" vertical>
          <q-btn
            border-radius="20px"
            class="q-mt"
            label="Enviar"
            no-caps
            size="lg"
            style="background-color: #f72716"
            text-color="white"
            type="submit"
            unelevated
          />
        </q-card-actions>
      </q-card>
    </q-form>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import notificacaoService from "src/services/notificacaoService";
import esqueciMinhaSenhaService from "src/services/esqueciMinhaSenhaService";
import PasswordCriteria from "src/components/PasswordCriteria.vue";

export default defineComponent({
  components: {
    PasswordCriteria
  },
  name: "LoginEsqueci",

  data() {
    return {
      senhaNova: "",
      senhaConfirmada: "",
      isValidPassword: false,
      isPwd: true,
      isPwd2: true
    };
  },

  methods: {
    async solicitarNovaSenha() {
      this.$q.loading.show({ message: "Realizando operação" });
      try {
        const { data } = await esqueciMinhaSenhaService.alterarSenha({
          senhaNova: this.senhaNova,
          codigo: this.$route.query.codigo
        });
        notificacaoService.success(data);
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },

    validatePassword() {
      this.passwordValidation.length = this.senhaNova.length > 13;
      this.passwordValidation.capitalLetter = /^(?=.*[A-Z])/.test(
        this.senhaNova
      );
      this.passwordValidation.number = /^(?=.*[0-9])/.test(this.senhaNova);
      this.passwordValidation.symbol = /^(?=.*[^a-zA-Z0-9])/.test(
        this.senhaNova
      );
    }
  }
});
</script>

<style>
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

.login-box2 {
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  box-shadow: 0px 0px 10px #ccc;
  width: 400px;
}
</style>
