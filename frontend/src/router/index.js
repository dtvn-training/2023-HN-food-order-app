import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/admin/Home.vue'
import Restaurant from '@/views/admin/RestaurantContent.vue'
import Food from '@/views/admin/FoodContent.vue'
import Payment from '@/views/admin/PaymentContent.vue'
import Group from '@/views/admin/GroupContent.vue'
import Table from '@/components/Table.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/test',
      component: Table,
    },
    {
      path: '/admin',
      component: Home,
      children: [
        {
          name: 'restautents',
          path: 'restaurants',
          component: Restaurant,
        },
        {
          name: 'foods',
          path: 'foods',
          component: Food,
        },
        {
          name: 'payments',
          path: 'payments',
          component: Payment,
        },
        {
          name: 'groups',
          path: 'groups',
          component: Group,
        }
      ],
    },
  ]
})
