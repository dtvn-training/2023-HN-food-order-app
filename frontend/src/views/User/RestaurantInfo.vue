<template>
    <div class="restaurantInfo">
        <div class="info">
            <div class="banner">
                <img :src="restaurant.image" alt="">
            </div>
            <div class="detail">
                <span class="shop">SHOP/CỬA HÀNG</span>
                <div class="restaurant-name">{{ restaurant.name }}</div>
                <div class="address">
                    <Location />
                    <span>{{ restaurant.address }}</span>
                </div>
                <div class="rating">
                    <Star />
                    <span>{{ restaurant.rating }}</span>
                </div>
                <div class="open-time">
                    <Online />
                    <span class="opening">Mở cửa</span>
                    <Clock class="clock" />
                    <span class="time">07:00 - 12:00 | 13:00 - 17:00 | 19:00 - 22:00</span>
                </div>
                <div class="price">
                    <Token />
                    <span>30,000 - 200,000 đ</span>
                </div>
                <hr>
                <div class="description">
                    <span>{{ restaurant.description }}</span>
                </div>
            </div>
        </div>
        <hr style="margin: 5px 0 15px;">
        <div class="menu">
            <div class="category">
                <span>THỰC ĐƠN</span>
                <div class="wrapper">
                    <ul>
                        <li v-for="category in restaurant.dishCategoryList">{{ category.name }}</li>
                    </ul>
                </div>
            </div>
            <div class="foods">
                <Search placeholder="Tên món ăn" width="100%" value="" />
                <div class="food-category">
                    <div v-for="category in restaurant.dishCategoryList">
                        <div class="category-name">{{ category.name }}</div>
                        <div class="food" v-for="food in category.dishList">
                            <div class="image-name">
                                <img :src="food.image" alt="">
                                <div class="food-name">{{ food.name }}</div>
                            </div>
                            <div class="food-price">{{ food.price }}</div>
                            <div @click="handleAdd(food.id)">
                                <Add />
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.restaurantInfo {
    padding-top: 20px;
}

.info {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
}

.banner img {
    width: 560px;
}

.banner {
    overflow: hidden;
}

.shop {
    font-size: 16px;
    color: rgb(134, 134, 134);
    font-weight: 400;

}

.restaurant-name {
    font-size: 24px;
    font-weight: 600;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    color: rgb(53, 53, 53);
}

.address span,
.rating span,
.open-time span,
.price span {
    margin-left: 4px;
    font-size: 14px;
}

.open-time {
    display: flex;
    flex-direction: row;
    align-items: center;
}

.open-time,
.rating,
.address,
.price {
    margin-top: 5px;
    color: rgb(92, 92, 92);
}

.address {
    margin-top: 10px;
}

.opening {
    color: #5feb3c;
}

.clock {
    margin-left: 20px;
}

.price {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}

.price span {
    margin-top: 2px;
}

.description {
    margin-top: 5px;
    color: rgb(92, 92, 92);
    font-size: 14px;
}

.menu {
    display: grid;
    grid-template-columns: 35% 65%;
    margin-bottom: 40px;
}

.category {
    padding-right: 50px;
}

.foods {
    padding: 0 0 10px 10px;
}

.category .wrapper {
    border: 1px rgb(114, 114, 114) solid;
    border-radius: 10px;
    background: #FAFAFA;
    padding: 10px 10px 10px 40px;
    margin-top: 20px;
}

.category span {
    color: #D10A0A;
    font-weight: 600;
}

.category ul {
    list-style: none;
    padding: 0;
}

.category li {
    margin: 18px 0 18px;
    cursor: pointer;
}

.food {
    display: grid;
    grid-template-columns: 75% 20% 5%;
    align-items: center;
    margin: 5px 0 5px;
}

.image-name {
    display: flex;
    flex-direction: row;
    align-items: center;
}

.food-name {
    padding: 0 10px 0 10px;
}

.food img {
    width: 60px;
}

.food-price {
    color: #066ECD;
    font-weight: 500;
    text-align: center;
}

.category-name {
    font-size: 14px;
    margin-top: 25px;
    margin-bottom: 8px;
    color: rgb(119, 119, 119);
}
</style>
<script>
import Location from "@/components/icons/Location"
import Star from "@/components/icons/Star"
import Token from "@/components/icons/Token"
import Clock from "@/components/icons/Clock"
import Online from "@/components/icons/Online"
import Search from "@/components/actions/Search1"
import Add from "@/components/icons/Add"
import Restaurant from '@/services/restaurant.js'
import Cart from '@/services/cart'

export default {
    components: {
        Location,
        Star,
        Token,
        Online,
        Clock,
        Search,
        Add
    },
    data() {
        return {
            restaurant: [],
        }
    },
    beforeMount() {
        this.getRestaurant();
    },
    methods: {
        async getRestaurant() {
            const id = this.$route.params.id;
            const restaurant = await Restaurant.getRestaurant(id)
                .then(response => {
                    return response;
                })
            this.restaurant = restaurant;
        },
        handleAdd(id) {
            console.log(id);
            const body = {
                dishId: id,
                quantity: 1,
            }

            Cart.addFood(body)
            .then(response => {
                console.log(response);
                this.$message({
                    message: response.message,
                    type: 'success'
                });
            })

        }
    }
}
</script>