angular.module("myApp").config(function ($routeProvider) {
    $routeProvider
   .when("/register/registerCompany", {
       templateUrl: "register/registerCompany.htm",
       controller: "register"
   })
   .when("/register/registerCustomer", {
       templateUrl: "register/registerCustomer.htm",
       controller: "register"

   })  .when("/register/success", {
       templateUrl: "register/success.htm",
       controller: "register"

   });


});


angular.module("myApp").controller('register', ['$http', '$scope', '$location', function ($http, $scope, $location) {


	
    $scope.createCustomer = function () {
        var createC = JSON.stringify($scope.customer);
        $http.post('http://localhost:8080/CouponsProjectPhase2/rest/register/customer', createC).then(function (successs) {
            $scope.PostDataResponse = createC;
        }).
      then(function () {
          $location.path("/register/success");
      });

    }

  

    $scope.createCompany = function () {
        var createC = JSON.stringify($scope.company);
        $http.post('http://localhost:8080/CouponsProjectPhase2/rest/register/company', createC).then(function (successs) {
            $scope.PostDataResponse = createC;
        }).
      then(function () {
    	  $location.path("/register/success");
      });

    }


}])