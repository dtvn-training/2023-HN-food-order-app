<template>
    <div class="login">
        <div :class="{ 'right-panel-active': isRightPanelActive, 'container': true }" id="container">
            <div class="form-container sign-up-container">
                <form v-if="isVerify === false" :class="{ 'isVerify': true }" v-loading="loading">
                    <h1>Create Account</h1>
                    <div class="social-container">
                        <a href="#" class="social">
                            <Google />
                        </a>
                    </div>
                    <span>or use your email for registration</span>
                    <input type="text" placeholder="Nguyen Van A" v-model="fullname" />
                    <input type="email" placeholder="nguyenvana@gmail.com" v-model="email" />
                    <input type="password" placeholder="Mật khẩu" v-model="password" />
                    <button @click="handleSignUp">Sign Up</button>
                </form>
                <form v-if="isVerify === true">
                    <h1>Verify</h1>
                    <span style="font-size: 15px; margin-top: 50px;color: rgb(117, 117, 117);">Hệ thống đã gửi mã OTP về
                        địa chỉ email</span>
                    <span style="font-size: 14px; margin-bottom: 20px;">{{ email }}</span>
                    <input type="text" placeholder="OTP" v-model="otp">
                    <a class="back-register" @click="back">Quay lại trang đăng ký</a>
                    <button @click="verify">Verify</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form>
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a href="#" class="social">
                            <Google />
                        </a>
                    </div>
                    <span>or use your account</span>
                    <input type="email" placeholder="Email" v-model="email" />
                    <input type="password" placeholder="Password" v-model="password" />
                    <a href="#">Forgot your password?</a>
                    <button @click="handleSignIn">Sign In</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn" @click="signInToggle">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp" @click="signUpToggle">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Google from '../components/icons/Google.vue';

export default {
    components: {
        Google
    },
    data() {
        return {
            isRightPanelActive: false,
            isVerify: false,
            email: '',
            password: '',
            fullname: '',
            otp: '',
            loading: false,
        }
    },
    computed: {
        status() {
            return this.$store.state.auth.status;
        },
    },
    created() {
        if (this.status.loggedIn) {
            switch (this.status.role) {
                case 'admin':
                    this.$router.push("/admin");
                    break;
                case 'user':
                    break;
            }
        }
    },
    methods: {
        signUpToggle() {
            this.isRightPanelActive = true;
        },
        signInToggle() {
            this.isRightPanelActive = false;
        },
        handleSignIn() {
            this.loading = true;
            this.email = this.email.trim();
            this.password = this.password.trim();
            if (this.email == "" || this.password == "") {
                this.$message.warning('Không được bỏ trống!');
                return false;
            }
            const user = {
                'email': this.email,
                'password': this.password
            }
            this.$store.dispatch('auth/login', user).then(
                () => {
                    if (this.$store.state.auth.user.role == "ADMIN") this.$router.push("/admin/restaurants");
                },
                (error) => {
                    this.loading = false;
                    this.$message.error('Tài khoản hoặc mật khẩu không đúng!');
                }
            );
        },
        handleSignUp() {
            this.loading = true;
            this.email = this.email.trim();
            this.password = this.password.trim();
            this.fullname = this.fullname.trim();

            if (this.email == "" || this.password == "" || this.fullname == "") {
                this.$message.warning('Không được bỏ trống!');
                this.loading = false;
                return false;
            }

            const user = {
                'email': this.email,
                'password': this.password,
                'fullName': this.fullname,
            }
            // console.log(user);
            this.$store.dispatch('auth/register', user).then(
                () => {
                    this.loading = false;
                    this.isVerify = true;
                },
                (error) => {
                    this.loading = false;
                    this.$message.warning('Email đã tồn tại!');
                }
            )
        },
        verify() {
            this.otp = this.otp.trim();
            if (this.otp == "")this.$message.warning("Không được bỏ trống!");

            const user = {
                email: this.email,
                otp: this.otp,
            }

            this.$store.dispatch("auth/verifyRegister", user).then(
                () => {
                    this.$message.success('Đăng ký tài khoản thành công');
                    this.isVerify = false;
                    this.signInToggle();
                },
                (error) => {
                    this.$message.error('Sai mã OTP, vui lòng nhập lại!')
                }
            )
        },
        back() {
            this.isVerify = false;
        }
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

* {
    box-sizing: border-box;
}

body {
    background: #f6f5f7;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    font-family: 'Montserrat', sans-serif;
    height: 100vh;
    margin: -20px 0 50px;
}

.login {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

h1 {
    font-weight: bold;
    margin: 0;
}

h2 {
    text-align: center;
}

p {
    font-size: 14px;
    font-weight: 100;
    line-height: 20px;
    letter-spacing: 0.5px;
    margin: 20px 0 30px;
}

.back-register {
    margin: 0;
    text-decoration: underline;
    color: rgb(0, 81, 202);
}

.back-register:hover {
    cursor: pointer;
}

span {
    font-size: 12px;
}

a {
    color: #333;
    font-size: 14px;
    text-decoration: none;
    margin: 15px 0;
}

button {
    border-radius: 20px;
    border: 1px solid #D10A0A;
    background-color: #D10A0A;
    color: #FFFFFF;
    font-size: 12px;
    font-weight: bold;
    padding: 12px 30px;
    letter-spacing: 1px;
    text-transform: uppercase;
    transition: transform 80ms ease-in;
    margin-top: 30px;
    cursor: pointer;
}

button:active {
    transform: scale(0.95);
}

button:focus {
    outline: none;
}

button.ghost {
    background-color: transparent;
    border-color: #FFFFFF;
}

form {
    background-color: #FFFFFF;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 0 50px;
    height: 100%;
    text-align: center;
}

input {
    background-color: #eee;
    border: none;
    padding: 15px 10px 15px 12px;
    margin: 8px 0;
    width: 100%;
}

.container {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25),
        0 10px 10px rgba(0, 0, 0, 0.22);
    position: relative;
    overflow: hidden;
    width: 768px;
    max-width: 100%;
    min-height: 480px;
}

.form-container {
    position: absolute;
    top: 0;
    height: 100%;
    transition: all 0.6s ease-in-out;
}

.sign-in-container {
    left: 0;
    width: 50%;
    z-index: 2;
}

.container.right-panel-active .sign-in-container {
    transform: translateX(100%);
}

.sign-up-container {
    left: 0;
    width: 50%;
    opacity: 0;
    z-index: 1;
}

.container.right-panel-active .sign-up-container {
    transform: translateX(100%);
    opacity: 1;
    z-index: 5;
    animation: show 0.6s;
}

@keyframes show {

    0%,
    49.99% {
        opacity: 0;
        z-index: 1;
    }

    50%,
    100% {
        opacity: 1;
        z-index: 5;
    }
}

.overlay-container {
    position: absolute;
    top: 0;
    left: 50%;
    width: 50%;
    height: 100%;
    overflow: hidden;
    transition: transform 0.6s ease-in-out;
    z-index: 100;
}

.container.right-panel-active .overlay-container {
    transform: translateX(-100%);
}

.overlay {
    background: #D10A0A;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 0 0;
    color: #FFFFFF;
    position: relative;
    left: -100%;
    height: 100%;
    width: 200%;
    transform: translateX(0);
    transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
    transform: translateX(50%);
}

.overlay-panel {
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 0 40px;
    text-align: center;
    top: 0;
    height: 100%;
    width: 50%;
    transform: translateX(0);
    transition: transform 0.6s ease-in-out;
}

.overlay-left {
    transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
    transform: translateX(0);
}

.overlay-right {
    right: 0;
    transform: translateX(0);
}

.container.right-panel-active .overlay-right {
    transform: translateX(20%);
}

.social-container {
    margin: 20px 0;
}

.social-container a {
    border: 1px solid #DDDDDD;
    border-radius: 50%;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    margin: 0 5px;
    height: 40px;
    width: 40px;
}
</style>