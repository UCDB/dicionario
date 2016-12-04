dicionarioApp.factory('dataShare',function($rootScope,$timeout){

    var service = {};

    service.data = false;

    service.setData = function(data){
        this.data = data;

        $timeout(function(){
            $rootScope.$broadcast('data_shared');
        },100);
    };

    service.getData = function(){
        return this.data;
    };

    return service;
});