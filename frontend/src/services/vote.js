import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/vote/';

class Vote {
    getAll(){
        return axios
        .get(API_URL + 'get-vote-present', config)
        .then(response => {
            // console.log(response);
            return response.data;
        })
    }
    accept(param){
        const url = 'http://localhost:8080/admin/restaurant/add-restaurant-from-vote-to-database';
        return axios
        .post(`${url}?id=${param}`, [], config)
        .then(response => {
            return response.data;
        })
        .catch(error => {
            return error;
        })
    }
    reject(param){
        const url = 'http://localhost:8080/admin/restaurant/delete-present-vote';
        return axios
        .post(`${url}?id=${param}`, [], config)
        .then(response => {
            return response.data;
        })
        .catch(error => {
            return error;
        })
    }
}

export default new Vote();