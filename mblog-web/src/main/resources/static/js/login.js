import {baseUrl, url, redirectToLogin} from './base.js';

var loginForm = new Vue({
    el: '#login-form',
    data: {
        username: '',
        password: '',
        rememberMe: false
    },
    methods: {
        login: function () {
            var form = this.$data;
            $.ajax({
                url: baseUrl + url.system.login,
                data: form,
                type: 'post',
                success: function (response) {
                    if(response.code = 200) {
                        //登录成功后保存userId和token
                        var userId = response.userId;
                        var token = response.data.token;
                        localStorage.setItem("userId",userId);
                        localStorage.setItem("token",token);
                        //登录成功后跳转到首页
                        window.location.href = url.web.index;
                    } else {
                        redirectToLogin();
                    }
                },
                error: function() {
                    console.log("登录接口调用失败！")
                }
            })
        }
    }
})