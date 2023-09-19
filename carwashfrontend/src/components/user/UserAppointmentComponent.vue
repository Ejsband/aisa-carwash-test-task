<template>
  <div class="container">
    <ul class="collection with-header width-big">
      <li class="collection-header"><h4>Запись для пользователя {{this.$store.state.user}}</h4></li>
      <li class="collection-item" v-for="(item, index) in appointmentData" :key="item">
        <div>
          {{ index + 1 + ' - ' + item.service.serviceName + " ||| " + item.date}}
        </div>
      </li>
    </ul>
  </div>
</template>

<script>

import http from "@/http-common";

export default {
  data() {
    return {
      appointmentData: []
    }
  },
  methods: {
    getAllAppointments(name) {
      http.get(`appointments/${name}`)
          .then(response => this.appointmentData = response.data)
          .catch(function (error) {
            if (error.response) {
              console.log(error.response.data);
              console.log(error.response.status);
              console.log(error.response.headers);
            } else if (error.request) {
              console.log(error.request);
            } else {
              console.log('Error', error.message);
            }
          })
    }
  },
  mounted() {
    this.getAllAppointments(this.$store.state.user)
  }
}


</script>

<style>

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

</style>