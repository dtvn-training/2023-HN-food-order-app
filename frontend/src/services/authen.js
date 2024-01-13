import axios from 'axios';

const API_URL = 'http://localhost:8080/api/v1/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'login', {
        email: user.email,
        password: user.password
      })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify(response.data.token));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }
  register(user) {
    return axios.post(API_URL + 'register', {
      fullName: user.fullName,
      email: user.email,
      password: user.password
    })
  }

  verifyRegister(user) {
    return axios.post(API_URL + 'verify_register', {
      email: user.email,
      otp: user.otp,
    })
  }
}

export default new AuthService();

