<template>
  <van-nav-bar
      title="标题"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
  >
    <template #right>
      <van-icon name="search" size="18" />
    </template>
  </van-nav-bar>

  <div id="content">
    <router-view />
  </div>

  <van-tabbar route @change="onChange">
    <van-tabbar-item to="/" icon="home-o" name="indexPage">主页</van-tabbar-item>
    <van-tabbar-item to="/team" icon="search" name="teamPage">队伍</van-tabbar-item>
    <van-tabbar-item to="/user" icon="friends-o" name="userPage">个人</van-tabbar-item>
  </van-tabbar>

</template>

<script setup>
import {ref} from "vue";
import {Toast} from "vant";

import 'vant/es/toast/style';
import { useRouter } from "vue-router";

const router = useRouter();


const onClickLeft = () => alert('左');
const onClickRight = () => {
  router.push('/search')
};

const active = ref("index");
const onChange = (index) => Toast(`标签 ${index}`);

</script>

<style scoped>

</style>


