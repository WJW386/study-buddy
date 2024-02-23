import IndexPage from '../pages/IndexPage.vue'
import TeamPage from '../pages/TeamPage.vue'
import UserPage from '../pages/UserPage.vue'
import SearchPage from "../pages/searchPage.vue";
import UserEditPage from "../pages/userEditPage.vue";
import searchResultPage from "../pages/searchResultPage.vue";
import userLoginPage from "../pages/userLoginPage.vue";

const routes = [
    {path: '/', component:IndexPage},
    {path: '/team', component:TeamPage},
    {path: '/user', component:UserPage},
    {path: '/search', component:SearchPage},
    {path: '/user/edit', component:UserEditPage},
    {path: '/user/list', component:searchResultPage},
    {path: '/user/login', component: userLoginPage}
]
export default routes