<template>
  <q-card>
    <q-card-section class="bg-secondary text-white">
      <div class="text-subtitle2 text-uppercase">{{ title }}</div>
    </q-card-section>

    <q-card-section>
      <slot></slot>
    </q-card-section>

    <q-card-actions align="right" class="q-mx-md q-my-sm">
      <slot name="actions"></slot>
    </q-card-actions>

    <q-separator />
  </q-card>
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({
  props: {
    title: {
      type: String,
      default: "Pesquisar",
    },
  },
});
</script>
