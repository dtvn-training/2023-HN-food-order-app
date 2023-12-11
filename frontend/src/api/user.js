import axios from 'axios'

export function login(data) {
  return axios.post('http://localhost:8080/api/login', data);
}

export function getInfo(token) {
  return axios.get('http://localhost:8080/api/infor', {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
}

export function logout(token) {
  const result = axios.post('http://localhost:8080/api/logout');
  console.log(result)
  return result
}
