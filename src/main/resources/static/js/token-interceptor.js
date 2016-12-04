dicionarioApp.factory("tokenInterceptor",function ($q) {

    return {
        'request': function (config) {
            config.headers.Authorization = 'Bearer ' + localStorage.getItem("userToken");

            return config;
        },
        'responseError': function (rejection) {
            if (rejection.status == 401) {
                window.location.replace("/login");
            }
            return $q.reject(rejection);
        }
    }
});