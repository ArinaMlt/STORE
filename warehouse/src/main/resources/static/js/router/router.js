import Vue from 'vue'
import VueRouter from 'vue-router'
import MessageList from 'components/messages/MessageList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'
import Product from 'pages/Product.vue'
import Test from 'components/test/Test.vue'
import Report from 'pages/Report.vue'
import Contragent from "pages/Contragent.vue"
import Check from "../pages/Check.vue"
import KontragentReport from "../pages/report/KontragentReport.vue";
import Invoice from "../pages/Invoice.vue";
import PricesReport from "../pages/report/PricesReport.vue";
import Payment from "../pages/Payment.vue";

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
        { path: '/checks', component: Check},
        { path: '/product', component: Product},
        { path: '/contragents', component: Contragent},
        { path: '/prices', component: PricesReport},
        { path: '/invoices', component: Invoice},
        { path: '/payments', component: Payment},
        { path: '/report', component: Report},
        { path: '/kontragentReport', component: KontragentReport},
        { path: '*', component: MessageList},
    ]
})

