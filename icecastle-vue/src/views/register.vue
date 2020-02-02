<template>
    <div class="fillcontain">
        <el-row>
            <head-top></head-top>
        </el-row>
        <div class="menu_item_style">
            <el-form ref="userDataForm">
                <el-form-item required label="昵称：">
                    <el-input v-model="userData.name"></el-input>
                </el-form-item>
                <el-form-item required label="邮箱：">
                    <el-input v-model="userData.mail"></el-input>
                </el-form-item>
                <el-form-item required label="密码：">
                    <el-input type="password" v-model="userData.passWord"></el-input>
                </el-form-item>
                <el-form-item required label="确认密码：">
                    <el-input type="password" v-model="userData.confirmPassWord"></el-input>
                </el-form-item>
                <el-form-item class="button_submit">
                    <el-button type="primary" @click="register()">注 册</el-button>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>
<script>
    import headTop from '../components/headTop'
    import Vue from "vue";
    export default {
        data(){
            return {
                userData: {
                    name: '', //名称
                    mail: '', //邮箱
                    passWord:'', //密码
                    confirmPassWord :'', //密码
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
            register(){
                this.ajax.post("/user/register",this.userData).then(result => {
                    if (result.result) {
                        alert(result.msg);
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
