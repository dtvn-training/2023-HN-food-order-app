import Vue from 'vue'
import Router from 'vue-router'

//Admin
import HomeAdmin from '@/views/admin/Home.vue'
import RestaurantAdmin from '@/views/admin/RestaurantContent.vue'
import FoodAdmin from '@/views/admin/FoodContent.vue'
import PaymentAdmin from '@/views/admin/PaymentContent.vue'
import GroupAdmin from '@/views/admin/GroupContent.vue'
//User
import Home from '@/views/user/Home.vue'
import RestaurantUser from '@/views/user/Restaurants.vue'
import CompontTest from '@/components/ComponentTest.vue';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/test',
      component: CompontTest,
    },
    {
      path: '/admin',
      component: HomeAdmin,
      children: [
        {
          name: 'restautents',
          path: 'restaurants',
          component: RestaurantAdmin,
        },
        {
          name: 'foods',
          path: 'foods',
          component: FoodAdmin,
        },
        {
          name: 'payments',
          path: 'payments',
          component: PaymentAdmin,
        },
        {
          name: 'groups',
          path: 'groups',
          component: GroupAdmin,
        }
      ],
    },
    {
      path: '/user',
      component: Home,
      children: [
        {
          name: 'home',
          path: '/',
          component: RestaurantUser,
        },
        // {
        //   name: 'payments',
        //   path: 'payments',
        //   component: Payment,
        // },
        // {
        //   name: 'votes',
        //   path: 'votes',
        //   component: Vote,
        // }
      ],
    },
  ]
})
