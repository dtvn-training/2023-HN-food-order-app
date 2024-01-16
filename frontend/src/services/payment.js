import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/admin/payment/';

class Payment {
    getBills(){
        return axios
        .get(API_URL + 'getBills', config)
        .then(response => {
            return response.data;
        })
    }

    getRecharge(){
        return axios
        .get(API_URL + 'getRecharge', config)
        .then(response => {
            return response.data;
        })
    }
}

export default new Payment();