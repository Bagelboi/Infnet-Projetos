<template>
  <img id="logo" src="./assets/logo.png" height="200">
  <nav>
    <router-link to="/">Home</router-link>
    <router-link to="/office/*">Cartório</router-link>
    <router-link to="/info">Sobre</router-link>
  </nav>
  <router-view/>
</template>

<style>
body {
  background: rgb(74, 89, 102);
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: whitesmoke;
}

#logo {
  position: absolute;
  left:0px;
}

nav {
  padding: 30px;
}

nav a {
  color: rgb(221, 221, 221);
  text-decoration: none;
  font-size: 1.25em;
}

nav a.router-link-exact-active {
  font-weight: bold;
  color: rgb(255, 255, 255);
}

button, select, nav a  {
  background-color: rgb(30, 30, 53);
  border: 0px;
  border-radius: 5px;
  color: whitesmoke;
  padding: 8px;
  margin: 5px;
  text-transform: uppercase;
}
</style>
