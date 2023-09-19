<template>
  <header class="teal lighten-2">
    <div>
      <router-link class="link" to="/user/services">доступные услуги</router-link>
      <router-link class="link" to="/user/appointments">мои записи</router-link>
    </div>
    <span>{{this.$store.state.user}}</span>
    <span @click="remove()" class="logout">LOGOUT</span>
  </header>
  <router-view></router-view>
</template>

<script>

import store from "../store";

export default {
  computed: {
    store() {
      return store
    }
  },
  methods: {
    remove() {
      localStorage.removeItem('login');
      localStorage.removeItem('role');
      localStorage.removeItem('user');
      this.$router.push('/')
    },
    check() {
      let role = localStorage.getItem('role')
      let login = localStorage.getItem('login')
      if (login) {
        this.$router.push('/')
      }
      if (role === 'admin') {
        this.$router.push('/')
      }
    }
  },
  mounted() {
    // this.check()
  }
}

</script>

<style>
header {
  width: 100%;
  height: 60px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

header div {
  margin-left: 40px;
}

header span {
  margin-right: 40px;
}

.link {
  margin-left: 20px;
  margin-right: 20px;
  color: #F1F1F1;
  font-size: 18px;
}

.logout {
  color: #F1F1F1;
  cursor: pointer;
}

</style>