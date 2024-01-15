import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/admin/food/';

class Food {
    getAll(){
        return axios
        .get(API_URL + 'getFood', config)
        .then(response => {
            return response.data;
        })
    }
}

export default new Food();