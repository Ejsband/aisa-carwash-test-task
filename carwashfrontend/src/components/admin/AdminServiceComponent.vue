<template>

  <div class="container">
    <ul class="collection with-header width-big">
      <li class="collection-header"><h4>Список доступных услуг</h4></li>
      <li class="collection-item" v-for="(item, index) in serviceData" :key="item">
        <div>{{ index + 1 + ' - ' + item.serviceName }}
          <router-link :to="{ path: '/admin/edit', query: { serviceId: item.serviceId, serviceName: item.serviceName }}" class="waves-effect waves-light btn-small secondary-content margin-left">редактировать</router-link>
          <button @click="deleteService(item)" class="waves-effect waves-light btn-small secondary-content red lighten-2">
            удалить
          </button>
        </div>
      </li>
    </ul>
    <router-link to="/admin/create" class="waves-effect waves-light btn-large">Создать новую услугу</router-link>
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
    deleteService(item) {
      http.delete(`services/${item.serviceId}`)
          .then(() => this.removeElement(this.serviceData, item))
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
    removeElement(array, value) {
      let index = array.indexOf(value)
      if (index > -1) {
        array.splice(index, 1)
      }
      this.serviceData = array
    }
  },
  mounted() {
    this.getAllServices()
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

.collection-item {
  height: 60px;
}

.btn-large {
  margin-top: 30px;
}

.margin-left {
  margin-left: 20px;
}

.width-big {
  width: 800px;
}

</style>