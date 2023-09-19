<template>
  <q-card style="width: 20%;">
    <q-card-section class="bg-negative text-white">
      <div class="text-h6">Alterar Senha</div>
    </q-card-section>
    <q-form @submit="alterarSenha">
      <q-card-section>
        <div>
          <div class="text-h8 text-center justify-center" style="opacity: 0.8; padding-top: 16px" text-color="blue">
            Senha Atual:
          </div>

          <q-input v-model="senhaAntiga" outlined color="secondary" label-color="dark"
            :type="isPwd ? 'password' : 'text'">
            <template v-slot:prepend>
              <q-icon square :color="color" name="lock" />
            </template>
            <template v-slot:append>
              <q-icon :name="isPwd ? 'visibility_off' : 'visibility'" class="cursor-pointer" @click="isPwd = !isPwd" />
            </template>
          </q-input>
        </div>

        <div class="">
          <div class="text-h8 text-center justify-center" style="opacity: 0.8; padding-top: 16px" text-color="blue">
            Nova Senha:
          </div>
          <q-input v-model="senhaNova" outlined color="secondary" label-color="dark" :type="isPwd2 ? 'password' : 'text'"
            :rules="[
              () => isValidPassword || 'Senha fora do padrão',
              (val) => (!!val && val == senhaConfirmada) || 'As senhas não são iguais',
            ]" ref="passwordInput" @blur="this.$refs.passwordConfirmInput.validate()">
            <template v-slot:prepend>
              <q-icon square :color="color" name="lock" />
            </template>

            <template v-slot:append>
              <q-icon :name="isPwd2 ? 'visibility_off' : 'visibility'" class="cursor-pointer" @click="isPwd2 = !isPwd2" />
            </template>
          </q-input>

          <password-criteria :senha="senhaNova" @validate="(val) => isValidPassword = val" />

        </div>

        <div class="">
          <div class="text-h8 text-center justify-center" style="opacity: 0.8; padding-top: 16px" text-color="blue">
            Confirmar nova senha:
          </div>
          <q-input v-model="senhaConfirmada" outlined color="secondary" label-color="dark"
            :type="isPwd3 ? 'password' : 'text'" :rules="[
              () => isValidPassword || 'Senha fora do padrão',
              (val) => (!!val && val == senhaNova) || 'As senhas não são iguais',
            ]" ref="passwordConfirmInput" @blur="this.$refs.passwordInput.validate()">
            <template v-slot:prepend>
              <q-icon square color="negative" name="lock" />
            </template>

            <template v-slot:append>
              <q-icon :name="isPwd3 ? 'visibility_off' : 'visibility'" class="cursor-pointer" @click="isPwd3 = !isPwd3" />
            </template>
          </q-input>
        </div>
      </q-card-section>
      <q-card-actions align="right">
        <q-btn :color="color" label="Alterar" type="submit" />
      </q-card-actions>
    </q-form>
  </q-card>
</template>

<script>
import { defineComponent } from 'vue'
import usuarioService from 'src/services/usuarioService';
import notificacaoService from 'src/services/notificacaoService';
import PasswordCriteria from './PasswordCriteria.vue';

export default defineComponent({
  components: {
    PasswordCriteria
  },
  name: "ResetSenha",
  props: {
    color: {
      default: 'negative'
    }
  },
  data() {
    return {
      senhaAntiga: "",
      senhaNova: "",
      senhaConfirmada: "",
      isPwd: true,
      isPwd2: true,
      isPwd3: true,
      isValidPassword: false,
    };
  },

  methods: {
    async alterarSenha() {
      this.$q.loading.show({ message: "Apagando Registro" });
      try {
        await usuarioService.alterarSenha({ id: this.$route.params.usuarioId, senhaAntiga: this.senhaAntiga, senhaNova: this.senhaNova })
        this.$emit('onSuccess')
        notificacaoService.success("Senha alterada com sucesso.");
      } catch ({ response }) {
        notificacaoService.error(response.data);
      }
      this.$q.loading.hide();
    },
    validatePassword() {
      this.passwordValidation.length = this.senhaNova.length > 13
      this.passwordValidation.capitalLetter = /^(?=.*[A-Z])/.test(this.senhaNova)
      this.passwordValidation.number = /^(?=.*[0-9])/.test(this.senhaNova)
      this.passwordValidation.symbol = /^(?=.*[^a-zA-Z0-9])/.test(this.senhaNova)
    }
  },

});
</script>
