import axios from 'axios'
import { getToken } from '@/utils/auth'

// const state = {
//   token: getToken()
// }

// ADMIN

export function fetchList(query) {
  console.log(getToken())
  const result = axios.post('http://localhost:8080/admin/listrestaurant', query, {
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}


export function createRestaurant(data) {
  const result = axios.post('http://localhost:8080/admin/addres',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function updateRestaurant(data) {

  const result =  axios.put('http://localhost:8080/admin/restaurant', data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function deleteRestaurant(data) {
  const result =  axios.post('http://localhost:8080/admin/delerestaurant',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    },
  })
}

export function makeListRestaurantToday(data){
  const result = axios.post('http://localhost:8080/admin/makelistrestoday',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
}

export function getListRestaurantToday(){
  const result = axios.get('http://localhost:8080/admin/listrestoday',{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}


export function getDishGroubBy(){
  const result = axios.get('http://localhost:8080/admin/get-dish-groupby', {
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function getAllDishOrderedByEmpls(){
  const result = axios.get('http://localhost:8080/admin/get-all-dish-ordered-by-employees',{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function setPrice(data){
console.log(data)
  const result = axios.post('http://localhost:8080/admin/set-price',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function confirmPay(data){
  const result = axios.post('http://localhost:8080/admin/confirm-pay',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function getAllInforOrder(data){
  const result = axios.post('http://localhost:8080/admin/get-all-infor-order',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

// USER

export function getDish() {
  const result = axios.get('http://localhost:8080/user/listdish', {
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function order(data){
  const result = axios.post('http://localhost:8080/user/order',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function getAllDishOrderedByEmpl(){
  const result = axios.get('http://localhost:8080/user/get-all-dish-ordered-by-employee',{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function paid(data){
  const result = axios.post('http://localhost:8080/user/paid',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}

export function getAllInforOrderByUser(data){
  console.log(getToken())
  const result = axios.post('http://localhost:8080/user/get-all-infor-order',data,{
    headers: {
      Authorization: 'Bearer ' + getToken()
    }
  })
  return result
}
