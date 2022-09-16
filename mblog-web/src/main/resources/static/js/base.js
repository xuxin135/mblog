//后端接口地址，后期可改为配置
var baseUrl = "http://localhost:12000";

/**
 * 路径常量
 * @type
 */
var url = {
    web: {
        login: "/login",
        index: "/",
    },
    system: {
        login: "/system/login",
    },
    auth: {

    }
}

// var expireTime =

/**
 * 跳转到登录页面
 */
function redirectToLogin() {
    window.location.href = url.web.login;
}


export {baseUrl, url, redirectToLogin}