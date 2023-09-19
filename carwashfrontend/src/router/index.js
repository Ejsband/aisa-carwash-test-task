import { createRouter, createWebHashHistory } from 'vue-router'
import LoginView from "@/views/LoginView.vue";
import AdminServiceComponent from "@/components/admin/AdminServiceComponent.vue";
import AdminCreateComponent from "@/components/admin/AdminCreateComponent.vue";
import AdminEditComponent from "@/components/admin/AdminEditComponent.vue";
import AdminAppointmentComponent from "@/components/admin/AdminAppointmentComponent.vue";
import UserServiceComponent from "@/components/user/UserServiceComponent.vue";
import UserAppointmentComponent from "@/components/user/UserAppointmentComponent.vue";
import UserSpecificComponent from "@/components/user/UserSpecificComponent.vue";
import UserNearestComponent from "@/components/user/UserNearestComponent.vue";


const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/admin',
    name: 'admin',
    component: function () {
      return import('../views/AdminView.vue')
    },
    children: [{
      path: 'services',
      component: AdminServiceComponent
    }, {
      path: 'create',
      component: AdminCreateComponent
    },
      {
      path: 'edit',
      component: AdminEditComponent
    },
      {
        path: 'appointments',
        component: AdminAppointmentComponent
      }]
  },
  {
    path: '/user',
    name: 'user',
    component: function () {
      return import('../views/UserView.vue')
    },
    children: [{
      path: 'services',
      component: UserServiceComponent
    }, {
      path: 'nearest',
      component: UserNearestComponent
    }, {
      path: 'appointments',
      component: UserAppointmentComponent
    }, {
      path: 'specific',
      component: UserSpecificComponent
    }]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
