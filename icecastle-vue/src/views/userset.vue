<template>
    <div class="fillcontain">
        <el-row>
            <head-top></head-top>
        </el-row>
        <div class="menu_item_style">
            <el-form ref="userDataForm">
                <el-form-item hidden label="">
                    <el-input v-model="userData.uuid"></el-input>
                </el-form-item>
                <el-form-item required label="昵称：">
                    <el-input v-model="userData.name"></el-input>
                </el-form-item>
                <el-form-item class="button_submit">
                    <el-button type="primary" @click="update()">修 改</el-button>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>
<script>
    import headTop from '../components/headTop'
    import Vue from "vue";
    import Cookies from 'js-cookie'
    export default {
        data(){
            return {
                userData: {
                    name: '', //名称
                    uuid: ''
                }
            }
        },
        components: {
            headTop,
        },
        mounted(){
            var uuid = Cookies.get("uuid");
            this.ajax.get("/user/data?uuid="+uuid).then(result => {
                if (result.result) {
                    this.userData.name = result.data.name;
                    this.userData.uuid = uuid;
                }
            });
        },
        computed: {
        },
        methods: {
            update(){
                this.ajax.post("/user/update",this.userData).then(result => {
                    if (result.result) {
                        alert(result.msg);
                        Cookies.set("name",this.userData.name);
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
