<template>
    <div class="wrapper" @click="cancel">
        <div class="vote-create" @click.stop="">
            <div class="container">
                <div class="header">Tạo Vote</div>
                <hr style="margin-bottom: 15px;">
                <div class="restaurant">
                    <div class="label">Tên quán</div>
                    <input type="text" v-model="restaurantName">
                </div>
                <div class="url">
                    <div class="label">URL</div>
                    <input type="text" v-model="url">
                </div>
                <div class="description">
                    <div class="label">Mô tả</div>
                    <textarea v-model="description"></textarea>
                </div>
                <hr>
                <button class="save" @click="save">Save</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.wrapper {
    background: #373737b0;
    width: 100%;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
}

.vote-create {
    background: #ffffff;
    min-width: 550px;
    width: 60%;
    max-width: 730px;
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
    padding: 10px;
}

.header {
    font-size: 24px;
    font-weight: 500;
    margin: 5px 0 5px;
}

.label {
    width: 120px;
}

hr {
    width: 100%;
}

.restaurant,
.url,
.description {
    display: flex;
    flex-direction: row;
    margin: 10px 0 10px;
}

.description textarea {
    height: 180px;
    padding: 5px 10px 5px 10px;
    margin-bottom: 20px;

}

textarea:focus {
    outline: none;
}

input,
textarea {
    width: 380px;
    font-size: 14px;
}

.save {
    width: 90px;
    height: 30px;
    background: #00bd78;
    border: none;
    border-radius: 5px;
    color: white;
    margin-top: 30px;
    margin-bottom: 20px;
}

.save:hover {
    cursor: pointer;
    background: #01d889;
}
</style>

<script>
export default {
    components: {
    },
    data() {
        return {
            restaurantName: '',
            url: '',
            description: '',
        }
    },
    methods: {
        save() {
            this.$emit('onClickCancel');
        },
        cancel(){
            this.$emit('onClickCancel');
        }
    }
}
</script>