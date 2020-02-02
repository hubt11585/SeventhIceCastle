const routers = [{
    path: '/',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/index.vue'], resolve)
},
{
    path: '/register',
    meta: {
        title: ''
    },
    component:(resolve) => require(['./views/register.vue'], resolve)
},
{
    path: '/login',
    meta: {
        title: ''
    },
    component:(resolve) => require(['./views/login.vue'], resolve)
},
{
    path: '/userset',
        meta: {
    title: ''
    },
    component:(resolve) => require(['./views/userset.vue'], resolve)
}
];
export default routers;