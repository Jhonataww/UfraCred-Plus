<template>
  <div class="password-criteria q-pa-sm">
    <div class="text-subtitle2 q-mb-sm">Requisitos da senha:</div>
    <div>
      <q-icon :name="this.passwordValidation.length ? 'check_circle' : 'cancel'"
        :color="this.passwordValidation.length ? 'positive' : 'negative'" />
      Deve conter no mínimo 14 caracteres.
    </div>
    <div>
      <q-icon :name="this.passwordValidation.capitalLetter ? 'check_circle' : 'cancel'"
        :color="this.passwordValidation.capitalLetter ? 'positive' : 'negative'" />
      Deve conter no mínimo 1 letra maiúscula.
    </div>
    <div>
      <q-icon :name="this.passwordValidation.number ? 'check_circle' : 'cancel'"
        :color="this.passwordValidation.number ? 'positive' : 'negative'" />
      Deve conter no mínimo 1 número.
    </div>
    <div>
      <q-icon :name="this.passwordValidation.symbol ? 'check_circle' : 'cancel'"
        :color="this.passwordValidation.symbol ? 'positive' : 'negative'" />
      Deve conter no mínimo 1 caractere especial.
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: "ResetSenha",
  props: {
    senha: {
      default: ''
    }
  },
  emits: ['validate'],
  data() {
    return {
      isValidPassword: false,
      passwordValidation: {
        length: false,
        capitalLetter: false,
        number: false,
        symbol: false
      }
    };
  },

  methods: {
    validatePassword() {
      this.passwordValidation.length = this.senha.length > 13
      this.passwordValidation.capitalLetter = /^(?=.*[A-Z])/.test(this.senha)
      this.passwordValidation.number = /^(?=.*[0-9])/.test(this.senha)
      this.passwordValidation.symbol = /^(?=.*[^a-zA-Z0-9])/.test(this.senha)
      this.isValidPassword = Object.values(this.passwordValidation).every(Boolean)
    }
  },

  watch: {
    senha() {
      this.validatePassword()
    },
    isValidPassword(val) {
      this.$emit('validate', val)
    }
  }

});
</script>
