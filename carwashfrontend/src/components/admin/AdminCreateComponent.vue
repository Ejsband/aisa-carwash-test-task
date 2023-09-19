<template>
  <div class="container">
    <h1>Введите название новой услуги</h1>
    <div class="row">
      <div class="col s12">
        <div class="row">
          <div class="input-field col s6">
            <input ref="input" autofocus id="input_text" type="text" data-length="10">
          </div>
        </div>
      </div>
    </div>
    <button @click="saveService()" class="waves-effect waves-light btn-large">
      создать услугу
    </button>
  </div>

</template>

<script>

import http from "@/http-common";

export default {
  methods: {

    saveService() {
      http.post("services", {
        serviceName: this.$refs.input.value
      })
          .then(() => this.$router.push('/admin/services'))
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

  }
}

</script>

<style>

.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.col {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}

.row {
  display: flex;
}

.input-field col s6 {
  display: inline-block;
  justify-content: center;
  width: 100%;
}

input {
  width: 100%;
}

</style>