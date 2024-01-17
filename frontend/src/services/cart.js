import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/cart';

class Cart {
    async addFood(body){
        return await axios
        .post(API_URL+'/add-to-cart', body, config)
        .then(response => {
            return response.data;
        })
        .catch(error => {
            return error;
        })
    }
    list(){
        return axios
        .get(API_URL + '/get-cart', config)
        .then(response => {
            return response.data;
        })
        .catch(error => {
            return error;
        })
    }

    async delete(param){
        return await axios
        .delete(API_URL + `/delete-cart?cart_id=${param}`, config)
        .then(response => {
            return response.data;
        })
    }

    async updateQuantity(body){
        return await axios
        .put(API_URL+'/change-quantity', body, config)
        .then(response => {
            return response.data;
        })
    }
}

export default new Cart();