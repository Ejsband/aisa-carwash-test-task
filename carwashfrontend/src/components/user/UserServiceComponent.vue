<template>
  <div class="container">
    <ul class="collection with-header width-big">
      <li class="collection-header"><h4>Список доступных услуг</h4></li>
      <li class="collection-item" v-for="(item, index) in serviceData" :key="item">
        <div>{{ index + 1 + ' - ' + item.serviceName }}
          <router-link :to="{ path: '/user/specific', query: { serviceId: item.serviceId, serviceName: item.serviceName }}" class="waves-effect waves-light btn-small secondary-content margin-left">выбрать дату</router-link>
          <router-link to="/user/nearest" class="waves-effect waves-light btn-small secondary-content margin-left">создать ближайшую запись</router-link>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>

import http from "../../http-common";

export default {
  data() {
    return {
      serviceData: []
    }
  },
  methods: {
    getAllServices() {
      http.get("services")
          .then(response => this.serviceData = response.data)
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
    },
    createAppointment(serviceId) {
      http.post("appointments", {
        userName: this.$store.state.user,
        serviceId: serviceId
      })
          .then(() => this.$router.push('/user/appointments'))
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
    this.getAllServices()
  }
}

</script>

<style>

</style>