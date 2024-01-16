<template>
    <div class="footer">
        <div class="container">
            <div class="wrapper">
                <div class="logo">
                    <span>
                        <Logo />
                    </span>
                </div>
                <div class="desc">
                    <span>
                        Convenient food delivery app with a user-friendly interface and smart search functionality. Easily
                        customize orders, make secure payments, and track order status.
                    </span>
                </div>
                <div class="reserved">
                    <span>
                        © 2023 Foodify for company. All rights reserved.
                    </span>
                </div>
                <div class="contact">
                    <ul>
                        <li>
                            <img class="fb" src="../assets/fb.png" alt="">
                        </li>
                        <li>
                            <img src="../assets/twitter.png" alt="">
                        </li>
                        <li>
                            <img src="../assets/insta.png" alt="">
                        </li>
                        <li>
                            <img src="../assets/tiktok.png" alt="">
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.footer {
    /* bottom: 0; */
    width: 100%;
    background: #D6D6D6;
}

.container {
    width: 60%;
    margin: 0 auto;
}

.wrapper {
    widows: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.footer .logo {
    margin-top: 30px;
    width: 120px;
}

.footer .desc {
    margin-top: 15px;
    text-align: center;
    font-size: 16px;
    font-weight: 100;
    color: rgb(66, 66, 66);
}

.footer .reserved {
    margin-top: 60px;
    font-size: 12px;
    font-weight: 400;
    text-align: center;
}

.footer .contact {
    margin-top: 20px;
    margin-bottom: 20px;
}

.footer .contact ul {
    padding-left: 3px;
    list-style-type: none;
    display: inline-block;
}

.footer .contact li {
    float: left;
    list-style: none;
    cursor: pointer;
}

.footer .contact li img {
    height: 24px;
    margin-left: 12px;
    margin-right: 12px;
}
</style>

<script>
import Logo from './icons/Logo.vue'
export default {
    components: {
        Logo,
    }
}
</script>