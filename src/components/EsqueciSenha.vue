<template>
  <q-card-section style="width: 400px">
    <div class="">
      <div class="text-h8 text-center justify-center" style="opacity: 0.8; padding-top: 16px" text-color="blue">
        Senha Atual:
      </div>

      <q-input v-model="senhaAtual" outlined color="secondary" label-color="dark" :type="isPwd ? 'password' : 'text'">
        <template v-slot:prepend>
          <q-icon square color="primary" name="lock" />
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
      <q-input v-model="senhaNova" outlined color="secondary" label-color="dark" :type="isPwd2 ? 'password' : 'text'">
        <template v-slot:prepend>
          <q-icon square color="primary" name="lock" />
        </template>

        <template v-slot:append>
          <q-icon :name="isPwd2 ? 'visibility_off' : 'visibility'" class="cursor-pointer" @click="isPwd2 = !isPwd2" />
        </template>
      </q-input>
    </div>

    <div class="">
      <div class="text-h8 text-center justify-center" style="opacity: 0.8; padding-top: 16px" text-color="blue">
        Confirmar nova senha:
      </div>
      <q-input v-model="senhaConfirmada" outlined color="secondary" label-color="dark"
        :type="isPwd3 ? 'password' : 'text'">
        <template v-slot:prepend>
          <q-icon square color="primary" name="lock" />
        </template>

        <template v-slot:append>
          <q-icon :name="isPwd3 ? 'visibility_off' : 'visibility'" class="cursor-pointer" @click="isPwd3 = !isPwd3" />
        </template>
      </q-input>
    </div>
  </q-card-section>
  <q-card-actions vertical align="center">
    <q-btn class="q-mt" style="background-color: #f72716" text-color="white" border-radius="20px" unelevated
      label="Enviar" size="lg" @click="realizarLogin" no-caps />
  </q-card-actions>
</template>

<script>
import { defineComponent } from "vue";
export default defineComponent({
  name: "LoginEsqueci",

  data() {
    return {
      senhaAtual: "",
      senhaNova: "",
      senhaConfirmada: "",
      isPwd: true,
      isPwd2: true,
      isPwd3: true,
    };
  },

  methods: {},
});
</script>

<style>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-box2 {
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  box-shadow: 0px 0px 10px #ccc;
  width: 400px;
  height: 580px;
}
</style>
