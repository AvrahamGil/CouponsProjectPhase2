
(function () {

    var app = angular.module("myApp", [ "ngRoute"]);
    
    
    app.config(function ($routeProvider, $locationProvider) {
        $locationProvider.hashPrefix('');
        $routeProvider
		.when("/", {
		    templateUrl: "Login.htm"
		})
	    .when("/company", {
	        templateUrl: "company/company.htm" ,
	        controller: "companyController"
	    })
	    .when("/admin", {
	        templateUrl: "admin/admin.htm",
	        controller:  "adminController"
	    })
	     .when("/customer", {
	         templateUrl: "customer/customer.htm" ,
	         controller:  "customerController"
	     })
	     .when("/PickRegister" , {
	    	 templateUrl : "register/PickRegister.htm" ,
	    	 controller : "register"
	     });
    });

   
  
    app.controller('LoginController', LoginController);
    LoginController.$inject = ['userService', '$http', '$scope', '$location', '$rootScope'];
    function LoginController(userService, $http, $scope, $location, $rootScope) {

        var vm = this;
        
        vm.user = { userName: "" };
        vm.user.userName = '';
        vm.loginStatus = null;
        

        $scope.submit = function () {
            var user = JSON.stringify($scope.user);
            $http.post('/CouponsProjectPhase2/rest/Login', $scope.user)
            .then(function success (response) {
                if ($scope.user !== null) {

                    if ($scope.user.type === 'ADMIN') {
                        $location.path('/admin');
                    }
                    else if ($scope.user.type === 'COMPANY') {
                        $location.path('/company');
                    }
                    else {
                        $location.path('/customer');
                    }

                    vm.user.userName = response.data.userName;
                    userService.name = vm.user.userName;
                    vm.loginStatus = "success";
                    

                } 
            }, function error(response) {
                bootbox.alert(response.data.message);
                vm.loginStatus = "fail";
               
            })

        }


    }



})();

