<template>
  <user-card-list :user-list = "userList"/>
  <van-empty v-if="!userList || userList.length < 1" description="搜索结果为空"/>
</template>

<script setup >
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import qs from 'qs';
import myAxios from "../plugins/myAxios.ts";
import {Toast} from "vant";
import UserCardList from "../components/UserCardList.vue";

const route = useRoute();
const {tags} = route.query;


const userList = ref([]);


onMounted(async () => {
  // 为给定 ID 的 user 创建请求
  const userListData = await myAxios.get('/user/search/tags',{
    withCredentials: false,
    params: {
      tagList: tags
    },

    //序列化
    paramsSerializer: {
      serialize: params => qs.stringify(params, { indices: false}),
    }

  })
      .then(function (response) {
        console.log('/user/search/tags succeed',response);
        Toast.success('请求成功');
        return response?.data
      })
      .catch(function (error) {
        console.log('/user/search/tags error',error);
        Toast.fail('请求失败');
      });
  if (userListData){
    userListData.forEach(user =>{
      if (user.tags){
        user.tags = JSON.parse(user.tags);
      }
    })
    userList.value = userListData;
  }
})

// const mockUser = ref({
//   id: 2222,
//   username: 'Willow',
//   userAccount: 'Willow',
//   profile: '大家好',
//   gender: 1,
//   phone: '4652212365',
//   email: 'aaa@qq.com',
//   webId: '232',
//   avatarUrl: 'https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/shayu931/shayu.png',
//   tags: ['Java', 'emo', '打工中', 'emo', '打工中'],
//   createTime: new Date(),
// })

// const userList = ref({mockUser});

</script>

<style scoped>

</style>