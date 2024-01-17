import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/admin/order/';

class Order {
    getOrders(){
        return axios
        .get(API_URL+'get-list-order', config)
        .then(response => {
            return response.data;
        })
        .catch(error => {
            return error;
        })
    }

    async order(body){
        return axios
        .post(API_URL + 'order', body, config)
        .then(response => {
            return response.data;
        })
        .catch(error => {
            console.log(error);
            return 'fail';
        });
    }

    async delete(param){
        return await axios
        .delete(API_URL +'delete-order-item?order_id='+param, config)
        .then(response => {
            return response.data;
        })
    }

    async userOrder(body){
        return axios
        .post('http://localhost:8080/order/' + 'order', body, config)
        .then(response => {
            return response.data;
        })
        .catch(error => {
            console.log(error);
            return 'fail';
        });
    }
}

export default new Order();