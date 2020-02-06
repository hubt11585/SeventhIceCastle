import Vue from 'vue';

import VueRouter from 'vue-router';
import Routers from './router';
import Vuex from 'vuex';
import Util from './libs/util';
import App from './App.vue';
import store from './stores';

Vue.use(VueRouter);
Vue.use(Vuex);

// ---------------------------------------- element ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

Vue.config.lang = 'zh-cn'

// ---------------------------------------- charts
import VCharts from 'v-charts';
Vue.use(VCharts)

// ---------------------------------------- 提示信息工具类
Vue.prototype.info = function (msg) {
    //this.$message(msg);
    this.$message({
        type: "success",
        message: msg
    });
}

Vue.prototype.error = function (msg) {
    this.$message({ type: 'error', message: msg });
}

Vue.prototype.confirm = function () {
    return this.$confirm(...arguments);
}

// ---------------------------------------- 请求
Vue.prototype.ajax = Util.ajax;

// 定义全局filter
Vue.filter('filterKeyword', function (value, key) {
    if (!key) return value;
    return value.filter(e => Util.isMatch(e, key));
});

// ---------------------------------------- event bus
import VueBus from 'vue-bus';

import './plugins/element.js'
Vue.use(VueBus);


// ---------------------------------------- 自动设置语言
function switchLanguage(value){
    var lang = value;

    if(lang){
        setCookie('lang', value);
    }
    else{
        const navLang = navigator.language;
        const localLang = (navLang === 'zh-CN' || navLang === 'en-US') ? navLang : false;
        //const lang = window.localStorage.getItem('language') || localLang || 'zh-CN';
        lang = getCookie('lang') || localLang || 'zh-CN';        
    }

    Vue.config.lang = lang;

    console.log("language", lang);
}

switchLanguage();

// ---------------------------------------- 路由配置
const RouterConfig = {

    routes: Routers
};
const router = new VueRouter(RouterConfig);

let loading;

router.beforeEach((to, from, next) => {
    loading = ElementUI.Loading.service({ fullscreen: true });
    Util.title(to.meta.title);
    next();
});

router.afterEach(() => {
    loading.close();
    window.scrollTo(0, 0);
});

var instance = new Vue({
    el: '#app',
    router: router,
    store: store,
    render: h => h(App)
});


instance.$bus.on("lang-change", switchLanguage);

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return (arr[2]);
    else
        return null;
}

function setCookie(name, value) {
    document.cookie = name + '=' + value;
}