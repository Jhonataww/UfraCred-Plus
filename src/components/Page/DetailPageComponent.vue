<template>
  <q-card class="painel-detail">
    <q-card-section class="bg-secondary text-white">
      <div class="text-h6 text-uppercase">{{ title }}</div>
    </q-card-section>

    <!-- botao -->
    <q-card-section>
      <slot name="actions"></slot>
    </q-card-section>

    <q-card-section>
      <slot name="filter"></slot>
    </q-card-section>

    <q-card-section>
      <slot name="list"></slot>
    </q-card-section>

    <q-separator />
  </q-card>
</template>

<script>
import { defineComponent } from "vue";
import DetailFilterComponent from "./DetailFilterComponent.vue";

export default defineComponent({
  components: {},
  props: {
    titleFilter: {
      type: String,
      default: "Pesquisar",
    },
    title: {
      type: String,
      default: "",
    },
  },
});
</script>
