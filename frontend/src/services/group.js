import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/admin/auth/';

class Group {
    getMembers(){
        return axios
        .get(API_URL + 'get-users', config)
        .then(response => {
            return response.data;
        })
    }

    getApprovals(){
        return axios
        .get(API_URL + 'get-user-not-approved', config)
        .then(response => {
            return response.data;
        })
    }

    accept(param){
        return axios
        .post(API_URL + 'approve-user' + "?email=" + param, [], config)
        .then(response => {
            return response.data;
        })
    }

    delete(param){
        return axios
        .post(API_URL + 'disapprove-user' + "?email=" + param, [], config)
        .then(response => {
            return response.data;
        })
    }
}

export default new Group();