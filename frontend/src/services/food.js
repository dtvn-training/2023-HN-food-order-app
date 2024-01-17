import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/admin/dishes/';

class Food {
    getAll(){
        return axios
        .get(API_URL, config)
        .then(response => {
            return response.data;
        })
    }
    async updatedActive(param, body){
        return await axios
        .put(API_URL + param, body, config)
        .then(response => {
            return response;
        })
    }
}

export default new Food();