<template>
  <div class="q-pa-md">
    <q-card class="my-card" style="margin: 2% 4%">
      <q-card-section>
        <div class="text-h6">Gráficos Operacionais</div>
        <div class="text-subtitle2">UFRACRED</div>
      </q-card-section>
      <div class="q-gutter-md row items-start">
        <grafico-apex></grafico-apex>
        <grafico-apex-pizza></grafico-apex-pizza>
      </div>
    </q-card>
  </div>
</template>
<script>
import { defineAsyncComponent, defineComponent } from "vue";

const graficoApex = defineAsyncComponent(() =>
  import("../components/GraficoApex.vue")
);

const graficoApexPizza = defineAsyncComponent(() =>
  import("../components/GraficoApexPizza.vue")
);

export default defineComponent({
  name: "IndexPage",

  components: {
    graficoApex,
    graficoApexPizza
  }
});
</script>
