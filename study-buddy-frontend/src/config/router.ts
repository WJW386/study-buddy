import IndexPage from '../pages/IndexPage.vue'
import TeamPage from '../pages/TeamPage.vue'
import UserPage from '../pages/UserPage.vue'
import SearchPage from "../pages/searchPage.vue";
import UserEditPage from "../pages/userEditPage.vue";
import searchResultPage from "../pages/searchResultPage.vue";
import userLoginPage from "../pages/userLoginPage.vue";
import teamAddPage from "../pages/TeamAddPage.vue";
import TeamUpdatePage from "../pages/TeamUpdatePage.vue";
import UserTeamJoinPage from "../pages/UserTeamJoinPage.vue";
import UserTeamCreatePage from "../pages/UserTeamCreatePage.vue";
import UserUpdatePage from "../pages/UserUpdatePage.vue";

const routes = [
    {path: '/', component:IndexPage},
    {path: '/team', component:TeamPage},
    {path: '/user', component:UserPage},
    {path: '/search', component:SearchPage},
    {path: '/user/edit', component:UserEditPage},
    {path: '/user/list', component:searchResultPage},
    {path: '/user/login', component: userLoginPage},
    {path: '/team/add', component: teamAddPage},
    { path: '/team/update', component: TeamUpdatePage },
    { path: '/user/team/join', component: UserTeamJoinPage },
    { path: '/user/team/create', component: UserTeamCreatePage },
    { path: '/user/update', component: UserUpdatePage },

]
export default routes