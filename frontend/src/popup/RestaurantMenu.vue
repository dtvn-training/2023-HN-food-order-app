<template>
    <div class="wrapper" @click="cancel">
        <div class="restaurant-menu" @click.stop="">
            <div class="container">
                <div class="header">
                    <span>{{ header }}</span>
                </div>
                <hr>
                <div class="menu">
                    <div class="category" v-for="category in categoriesResult">
                        <div class="category-name">{{ category.name }}</div>
                        <div class="food" v-for="food in category.foods">
                            <div style="display: flex; align-item:center">
                                <img class="food-img" :src="food.image" alt="">
                                <span class="name">{{ food.name }}</span>
                            </div>
                            <div style="display: flex;align-item:center">
                                <span class="price">{{ food.price + 'đ' }}</span>
                                <div class="radio-btn" @click="selectedFood(food.id)">
                                    <RadioChecked v-if="food.selected === 1" />
                                    <RadioUncheck v-if="food.selected === 0" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="action">
                    <button class="save" @click="save">Save</button>
                    <button class="reset" @click="reset">Reset</button>
                </div>
            </div>
        </div>
    </div>
</div></template>

<script>
import Logo from "../components/icons/Logo.vue";
import RadioChecked from "../components/icons/RadioChecked.vue"
import RadioUncheck from "../components/icons/RadioUncheck.vue"

export default {
    components: {
        RadioChecked,
        RadioUncheck
    },
    props: [
        'categories',
    ],
    data() {
        return {
            header: 'No.12   Bún chả mẹ Nga',
            categoriesResult: [...this.categories],
        }
    },
    methods: {
        selectedFood(id) {
            this.categoriesResult.forEach(item => {
                item.foods.forEach(food => {
                    if (food.id == id){
                        food.selected = +!food.selected;
                        return;
                    }
                })
            });
            // this.categoriesResult.forEach(item => {
            //     item.foods.forEach(food => {
            //         console.log(food.selected);
            //     });
            // })
        },
        cancel() {
            this.categoriesResult = [];
            this.$emit('onClickCancel');
        },
        save() {
            this.$emit("onClickSave", this.categoriesResult);
        },
        reset() {
            this.categoriesResult = this.categories;
            // console.log(this.categoriesResult);
            // console.log(this.categories);
            // this.categoriesResult.forEach(item => {
            //     item.foods.forEach(food => {
            //         console.log(food.selected);
            //     });
            // })
            // this.categories.forEach(item => {
            //     item.foods.forEach(food => {
            //         console.log(food.selected);
            //     });
            // })
        }
    }

}
</script>


<style scoped>
.wrapper {
    background: #373737b0;
    width: 100%;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
}

.restaurant-menu {
    background: #ffffff;
    min-width: 550px;
    width: 60%;
    max-width: 730px;
    height: 780px;
    position: fixed;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.container {
    width: 90%;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.header {
    height: 55px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.header span {
    font-size: 22px;
    color: #474747;
    font-weight: 500;
}

.menu {
    height: 645px;
    overflow: auto;
    width: 100%;
    padding-left: 7px;
}

.category {
    height: auto;
    margin-bottom: 30px;
}

.category-name {
    color: #616161;
    margin-bottom: 5px;
    font-size: 16px;
}

.action {
    width: 70%;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    height: 50px;
    align-items: center;
    margin-top: 10px;
}

.action button {
    cursor: pointer;
    margin: 0 10px 0 10px;
    height: 30px;
    width: 100px;
    font-size: 14px;
    color: rgb(73, 73, 73);
    background: rgb(247, 247, 247);
    border-radius: 5px;
    border: 1px solid rgb(109, 109, 109);
}

.radio-btn {
    padding-top: 5px;
    width: 50px;
    height: 50px;
    display: flex;
    justify-content: right;
    cursor: pointer;
}

button.save {
    background: #06cb83;
    color: white;
    font-weight: 400;
    border: none;
}

button.save:hover {
    background: #23e49d;
}

button.reset:hover {
    background: #ffffff;
}

::-webkit-scrollbar-thumb {
    background: #ffffff;
    border-radius: 5px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #ffffff;
}

hr {
    width: 100%;
}

.food {
    display: flex;
    margin-bottom: 10px;
    font-size: 14px;
    justify-content: space-between;
}

.food-img {
    margin-top: 3px;
    width: 60px;
    height: 45px;
    margin-right: 10px;
}

.food .name {
    width: 72%;
    color: black;
}

.food .price {
    padding-top: 5px;
    width: 100px;
    font-weight: 500;
    color: #066ECD;
    align-content: center;
}
</style>

