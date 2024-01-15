import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/admin/restaurant/';

class Restaurant {
    getAll(){
        return axios
        .get(API_URL + 'get_all_restaurant', config)
        .then(response => {
            // console.log(response);
            return response.data;
        })
    }
}

export default new Restaurant();