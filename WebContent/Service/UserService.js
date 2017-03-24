(function () {

    var app = angular.module("myApp");

    app.service('userService', userService);
    userService.$inject = ['$location'];
    function userService($locaion) {
        var vm = this;
        vm.errorMessage = '';

       

        var name = function () {
            var name;
            return name;
        }
    }

})();
   








