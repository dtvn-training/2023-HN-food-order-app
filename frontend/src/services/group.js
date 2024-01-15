import axios from 'axios';
import {config} from "./config"

const API_URL = 'http://localhost:8080/admin/group/';

class Group {
    getMember(){
        return axios
        .get(API_URL + 'getMember', config)
        .then(response => {
            return response.data;
        })
    }
}

export default new Group();