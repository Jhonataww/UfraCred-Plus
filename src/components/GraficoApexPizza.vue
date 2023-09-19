<template>
  <div>
    <apexchart
      width="380"
      type="pie"
      :options="options"
      :series="series"
    ></apexchart>
  </div>
</template>

<script>
export default {
  name: "graficoApexPizza",

  data() {
    return {
      options: {}, // https://apexcharts.com/docs/options/
      series: [44, 55, 41, 17, 15], // https://apexcharts.com/docs/series/
    };
  },

  methods: {},
};
</script>
