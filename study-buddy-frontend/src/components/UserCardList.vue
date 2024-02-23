<template>
  <van-card
      v-for="user in props.userList"
      :desc="user.userProfile"
      :title="`${user.username} (${user.webId})`"
      :thumb="user.avatarUrl"
  >
    <template #tags>
      <van-tag plain type="danger" v-for="tag in tags" style="margin-right: 8px; margin-top: 8px" >
        {{tag}}
      </van-tag>
    </template>
    <template #footer>
      <van-button size="mini">联系方式</van-button>
    </template>
  </van-card>
</template>

<script setup lang="ts">
import {UserType} from "../models/user";
import UserCardList from "./UserCardList.vue";

interface UserCardListProps {
  userList : UserType[]
}

const props = withDefaults(defineProps<UserCardListProps>(), {
  userList: [] as UserType[],
})

</script>

<style scoped>

</style>