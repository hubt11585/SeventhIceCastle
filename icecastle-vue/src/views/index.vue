<template>
  <div class="fillcontain">
    <el-row>
      <head-top></head-top>
    </el-row>
    <el-row>
      <div class="minder">
        <div class="menu_item_style">
          <el-menu mode="horizontal">
            <el-menu-item index="1">主页</el-menu-item>
            <el-menu-item index="2">推荐</el-menu-item>
            <el-submenu index="3">
              <template slot="title">作品展示</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
              <el-menu-item index="3-3">选项3</el-menu-item>
              <el-submenu index="3-4">
                <template slot="title">选项4</template>
                <el-menu-item index="3-4-1">选项1</el-menu-item>
                <el-menu-item index="3-4-2">选项2</el-menu-item>
                <el-menu-item index="3-4-3">选项3</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-menu-item index="4">资料</el-menu-item>
            <el-menu-item index="5" disabled>电影</el-menu-item>
            <el-menu-item index="6">壁纸</el-menu-item>
          </el-menu>
        </div>
        <div style="margin-right: 20px">
          <el-button @click="login" v-if="name == null || name == ''" >登 录</el-button>
          <el-button @click="register" v-if="name == null || name == ''">注 册</el-button>
          <el-button @click="userSet" v-if="name != null && name != ''">{{name}}</el-button>
          <el-button @click="userExit" v-if="name != null && name != ''">退 出</el-button>
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
    import headTop from '../components/headTop'
    import Cookies from 'js-cookie'

    export default {
        data(){
            return {
                name :'',
            }
        },
        components: {
            headTop,
        },
        mounted(){
            this.name = Cookies.get('name');
        },
        activated(){

        },
        computed: {
        },
        methods: {
            register(){
                this.$router.push('register');
            },
            login(){
                this.$router.push('login');
            },
            userSet(){
                this.$router.push('userset');
            },
            userExit(){
                Cookies.set("name",'');
                Cookies.set("uuid",'');
                Cookies.set("token",'');
                location.reload();
            }
        },
        watch: {

        }
    }
</script>

<style>
  .minder {
    display: flex;
    justify-content: space-between;
  }
  .menu_item_style{
    width: 70%;
    margin: 0 auto; /*水平居中*/
  }
</style>
