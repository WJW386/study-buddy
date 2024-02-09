import { createApp } from 'vue'
// import './style.css'
import App from './App.vue'
import {Button, Icon, NavBar} from 'vant';
import 'vant/es/toast/style'
import * as VueRouter from 'vue-router';
import routes from "./config/router";

import Vant from "vant";
import 'vant/lib/index.css'


const app = createApp(App)
app.use(Vant)

const router = VueRouter.createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: VueRouter.createWebHashHistory(),
    routes, // `routes: routes` 的缩写
})

app.use(router)


app.mount('#app')