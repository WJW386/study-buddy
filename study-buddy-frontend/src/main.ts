import { createApp } from 'vue'
// import './style.css'
import App from './App.vue'
import {Button, Icon, NavBar} from 'vant';
import 'vant/es/toast/style'
import * as VueRouter from 'vue-router';
import routes from "./config/route";
import '../global.css'
import Vant from "vant";
import 'vant/lib/index.css'


const app = createApp(App)
app.use(Vant)

const router = VueRouter.createRouter({

    history: VueRouter.createWebHistory(),
    routes, // `routes: routes` 的缩写
})

app.use(router)


app.mount('#app')