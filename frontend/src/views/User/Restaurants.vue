<template>
    <div class="wrapper">
        <div v-for="restaurant in restaurants">
            <Card 
                :img="restaurant.img"
                :restaurantName="restaurant.name"
                :address="restaurant.address"
                :star="restaurant.star"
            />
        </div>
    </div>
</template>
<style scoped>
.wrapper {
    height: auto;
    width: 100%;
    /* background-color: rgb(162, 149, 196); */
    display: grid;
      grid-template-columns: repeat(5, 1fr);
}
</style>

<script>
import Card from "@/components/user/Card.vue"

export default {
    components: {
        Card,
    },
    data() {
        return {
            restaurants: [
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },

                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
            ]
        }
    }
}
</script>