<template>
    <div class="fillcontain">
        <el-row>
            <head-top></head-top>
        </el-row>
        <div class="menu_item_style">
            <el-form ref ="userDataForm">
                <el-form-item label="用户名：" >
                    <el-input v-model="loginData.userName" placeholder="邮箱登录"></el-input>
                </el-form-item>
                <el-form-item label="密码：" >
                    <el-input v-model="loginData.passWord" type="password"></el-input>
                </el-form-item>
                <el-form-item class="button_submit">
                    <el-button type="primary" @click="doLogin">登 录</el-button>
                    <el-button type="primary" @click="activate()">激 活</el-button>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>
<script>
    import headTop from '../components/headTop'
    import Vue from "vue";
    import Cookies from 'js-cookie';

    export default {
        data(){
            return {
                loginData: {
                    userName: '', //名称
                    passWord:'', //密码
                }
            }
        },
        components: {
            headTop,
        },
        mounted(){
        },
        computed: {
        },
        methods: {
            activate(){
                this.ajax.post("/user/activate",this.loginData).then(result => {
                    if (result.result) {
                        alert(result.msg);
                        this.$router.push('/');
                        Cookies.set("uuid",result.data.uuid);
                        Cookies.set("sessionId",result.data.sessionId);
                    } else {
                        alert(result.code+":"+result.msg);
                    }
                });
            },
            doLogin(){
                this.ajax.post("/user/login",this.loginData).then(result => {
                    if (result.result) {
                        alert(result.msg);
                        Cookies.set("token",result.data.sessionId);
                        Cookies.set("uuid",result.data.uuid);
                        Cookies.set("name",result.data.name);
                        this.$router.push('/');
                    } else {
                        alert(result.code+":"+result.msg);
                    }
                });
            }
        },
        watch: {

        }
    }
</script>
<style>
    .menu_item_style{
        width: 30%;
        margin: 0 auto; /*水平居中*/
    }
</style>
