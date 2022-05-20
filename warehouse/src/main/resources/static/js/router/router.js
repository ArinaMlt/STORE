import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'components/messages/MessageList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'
import Product from 'pages/Product.vue'
import Test from 'components/test/Test.vue'
import Report from 'pages/Report.vue'
import Contragent from "pages/Contragent.vue"

Vue.use(VueRouter)

// const routes = [
//     { path: '/', component: MessageList },
//     { path: '/auth', component: Auth},
//     { path: '/profile', component: Profile},
//     { path: '*', component: MessageList},
// ]

export default new VueRouter({
    mode: 'history',
    // base: process.env.BASE_URL,
    routes: [
        { path: '/',  component: MessageList },
        { path: '/auth', component: Auth},
        { path: '/profile', component: Profile},
        { path: '/product', component: Product},
        { path: '/contragentDemo', component: Contragent},
        { path: '/test', component: Test},
        { path: '/report', component: Report},
        { path: '*', component: MessageList},
    ]
})

