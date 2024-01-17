import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/admin/restaurant/';

class Restaurant {
    getAll(params){
        let paramsResult = '';
        params.forEach((key, value) => {
            paramsResult = paramsResult + `${value}=${key}&`;
        })
        paramsResult = '?'+paramsResult;
        paramsResult = paramsResult.slice(0, -1);
        return axios
        .get(API_URL + 'get-all-restaurant' + paramsResult, config)
        .then(response => {
            // console.log(response);
            return response.data;
        })
    }

    async addFoodUserList(params){
        let paramsResult = '';
        params.forEach((key, value) => {
            paramsResult = paramsResult + `${value}=${key}&`;
        })
        paramsResult = '?'+paramsResult;
        paramsResult = paramsResult.slice(0, -1);
        // console.log(paramsResult);
        return await axios
        .post(API_URL + 'add-restaurant-from-database-to-user-list'+paramsResult, [], config)
        .then(response => {
            return response.data;
        })
    }
    async unCheckFoodUserList(params){
        let paramsResult = '';
        params.forEach((key, value) => {
            paramsResult = paramsResult + `${value}=${key}&`;
        })
        paramsResult = '?'+paramsResult;
        paramsResult = paramsResult.slice(0, -1);
        // console.log(paramsResult);
        return await axios
        .delete(API_URL + 'remove-restaurant-from-user-list'+paramsResult, config)
        .then(response => {
            return response.data;
        })
    }
    async removeFoodUserList(params){
        let paramsResult = '';
        params.forEach((key, value) => {
            paramsResult = paramsResult + `${value}=${key}&`;
        })
        paramsResult = '?'+paramsResult;
        paramsResult = paramsResult.slice(0, -1);
        return await axios
        .delete(API_URL + 'remove-restaurant-from-database'+ paramsResult, config)
        .then(response => {
            return response.data;
        })
    }

    getRestaurant(param){
        const url = 'http://localhost:8080/user/get-detail-restaurant?id='+param
        return axios
        .get(url, config)
        .then(response => {
            return response.data;
        })
    }

    getRestaurantToday(params){
        let paramsResult = '';
        params.forEach((key, value) => {
            paramsResult = paramsResult + `${value}=${key}&`;
        })
        paramsResult = '?'+paramsResult;
        paramsResult = paramsResult.slice(0, -1);
        return axios
        .get('http://localhost:8080/restaurants' + paramsResult, config)
        .then(response => {
            // console.log(response);
            return response.data;
        })
    }
}

export default new Restaurant();