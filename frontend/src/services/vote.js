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
}

export default new Vote();