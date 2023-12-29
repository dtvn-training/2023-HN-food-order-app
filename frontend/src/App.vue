<template>
  <div id="app" style="all:unset; font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;">
    <!-- <img src="./assets/logo.png"> -->
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');

* {
  font-family: 'Poppins', sans-serif;
  margin: 0;
  box-sizing: border-box;
}
input{
  border: 1px rgb(99, 99, 99) solid;
  border-radius: 5px;
  height: 30px;
  font-size: 16px;
  padding-left: 10px;
  color: rgb(41, 41, 41);
}
input:focus {
  outline: none;
}

/* width */
::-webkit-scrollbar {
  width: 7px;
}

/* Track */
::-webkit-scrollbar-track {
  background: #ffffff; 
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background: #c0c0c0; 
  border-radius: 5px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #a7a7a7; 
}
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background: rgb(255, 255, 255);
}
</style>

