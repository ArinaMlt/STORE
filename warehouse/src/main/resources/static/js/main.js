import Vue from "vue"
// import '@/styles/main.scss';
import '@babel/polyfill'
// import VueResource from 'vue-resource'
import 'api/resource'
import router from "router/router"
import App from "pages/App.vue"
import store from 'store/store'


// Vue.use(VueResource)


new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})
