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
import RestaurantInfo from '@/views/user/RestaurantInfo.vue'
import CompontTest from '@/components/ComponentTest.vue';
import Payment from '@/views/user/Payment.vue'
import Vote from '@/views/user/Vote.vue'
import Cart from '@/views/user/Cart'

import Login from '@/views/Login.vue'

Vue.use(Router)

export const router  = new Router({
  routes: [
    {
      path: '/test',
      component: CompontTest,
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/',
      component: Login
    },
    {
      path: '/admin',
      component: HomeAdmin,
      children: [
        {
          name: 'restaurants',
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
      path: '/',
      component: Home,
      children: [
        {
          name: 'userRestaurants',
          path: 'restaurants',
          component: RestaurantUser,
        },
        {
          name: 'userPayments',
          path: 'payments',
          component: Payment,
        },
        {
          name: 'userVotes',
          path: 'votes',
          component: Vote,
        },
        {
          name: 'restaurantInfo',
          path: 'restaurants/:id',
          component: RestaurantInfo,
        },
        {
          name: 'cart',
          path: 'cart',
          component: Cart,
        },
      ],
    },
  ]
});


router.beforeEach((to, from, next) => {
  // chuyển đến trang login nếu chưa được login
  const publicPages = ['/login', '/register'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  if (authRequired && !loggedIn) {
    return next('/login');
  }

  next();
})