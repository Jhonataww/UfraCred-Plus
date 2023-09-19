<template>
  <div class="login-container">
    <q-card class="login-box">
      <q-card-section>
        <q-img
          src="~assets/logoUfraCred.png"
          style="height: 50%; width: 50%; margin-left: 75px"
        />
      </q-card-section>

      <q-card-section>
        <q-input
          v-model="email"
          bottom-slots
          color="secondary"
          label="Email"
          label-color="dark"
          outlined
        >
          <template v-slot:prepend>
            <q-icon color="primary" name="email" square />
          </template>
        </q-input>
      </q-card-section>

      <q-card-actions align="center" vertical>
        <q-btn
          border-radius="20px"
          class="q-mt-md"
          label="Enviar"
          no-caps
          size="lg"
          style="background-color: #f72716"
          text-color="white"
          unelevated
          @click="solicitarNovaSenha"
        />
      </q-card-actions>
    </q-card>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import notificacaoService from "src/services/notificacaoService";
import esqueciMinhaSenhaService from "src/services/esqueciMinhaSenhaService";

export default defineComponent({
  name: "EsqueciMinhaSenha",

  data() {
    return {
      email: null
    };
  },

  methods: {
    async solicitarNovaSenha() {
      this.$q.loading.show({ message: "Realizando operação" });
      try {
        const { data } = await esqueciMinhaSenhaService.solicitarTokenAcesso(
          this.email
        );
        notificacaoService.success(data);
      } catch ({ response }) {
        notificacaoService.error(response.data);
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
  background-image: url(~assets/logoUfra.svg);
  background-repeat: no-repeat;
  background-size: 100%;
}

.login-box {
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #fff;
  padding: 20px;
  box-shadow: 0px 0px 3px #cccccc;
}
</style>
