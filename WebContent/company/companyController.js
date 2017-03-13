var app = angular.module("myApp");


angular.module("myApp").config(function ($routeProvider) {
    $routeProvider
   .when("/company/createCoupon", {
       templateUrl: "company/createCoupon.htm",
       controller: "companyController"
   })
   .when("/company/createCustomer", {
       templateUrl: "company/createCustomer.htm",
       controller: "companyController"

   })
   .when("/company/removeCoupon", {
       templateUrl: "company/removeCoupon.htm",
       controller: "companyController"
   })
    .when("/company/removeCustomer", {
        templateUrl: "company/removeCustomer.htm",
        controller: "companyController"

    }).when("/company/updateCoupon", {
        templateUrl: "company/updateCoupon.htm",
        controller: "companyController"

    }).when("/company/updateCustomer", {
        templateUrl: "company/updateCustomer.htm",
        controller: "companyController"

    }).when("/company/myCoupons", {
        templateUrl: "company/myCoupons.htm",
        controller: "couponController"

    }).when("/company/Profile", {
        templateUrl: "company/Profile.htm",
        controller: "companyProfile"

    }).when("/company/updateCustomer", {
        templateUrl: "company/updateCustomer.htm",
        controller: "companyController"

    });


});


angular.module("myApp").controller('companyController', companyController);
companyController.$inject = ['$http', '$scope', '$location', '$rootScope' , 'serviceName'];
function companyController($http, $scope, $location, $rootScope, serviceName) {

  
   
	  (
			     $scope.name = function () {
			    	 $scope.name = serviceName.name;
			     })();
 

    $scope.companyName = function () {
        var getC = JSON.stringify($scope.companyID);
        $http.get('/CouponsProjectPhase2/rest/api/Companies/' + getC).then(function (response) {
            $scope.companyDetail = response.data.company;
        });
    }



    $scope.createCoupon = function () {
        var createC = JSON.stringify($scope.coupon);
        $http.post('/CouponsProjectPhase2/rest/api/Coupons', createC).then(function (successs) {
            $scope.PostDataResponse = createC;
        }).
      then(function () {
          alert('success!!');
      });

    }

  
    $scope.logOut = function () {
        $http.post('/CouponsProjectPhase2/rest/Login/logOut').then(function (success) {
            alert("logout success");
        })
    }

}


angular.module("myApp").controller('couponController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

    ( 
            $scope.getCompanyCoupon = function () {
            $http.get('/CouponsProjectPhase2/rest/api/Coupons/CompanyCoupons').then(function (response) {
                $scope.couponsDetails = response.data.coupon;
            });

            })();
    
    $scope.updateCoupon = function () {
        bootbox.prompt("Update Coupon", function (result) { });
        var updateC = JSON.stringify($scope.updateCoupons);
        $http.put('/CouponsProjectPhase2/rest/api/Coupons', updateC).then(function (success) {
            $scope.ServerResponse = updateC;
        });
    }


    $scope.deleteCoupon = function (couponID , couponTitle) {
        bootbox.confirm({
            message: "Are you sure you want to delete " + couponTitle +  " coupon?" ,
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success',
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            }, callback: function (result) {
                if (result == true) {
                    //       var deleteCo = JSON.stringify($scope.couponID);
                    $http.delete('/CouponsProjectPhase2/rest/api/Coupons/' + couponID).then(function (success) {
                        bootbox.alert("Remove Done");
                    }).then(function (error) {
                        console.error("Remove Failed");
                    })
                }
                return;

            }
        });
    }

}])


angular.module("myApp").controller('companyProfile', ['$http', '$scope', '$location', function ($http, $scope, $location) {

   (
    $scope.getCompany = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Companies/Profile').then(function (response) {
            $scope.companyDetail = response.data;
        });
    }
    )();

   $scope.updateCoupon = function () {
       bootbox.prompt("Update Coupon Price", function (result) { });
       var updateC = JSON.stringify($scope.updateCoupons);
       $http.put('/CouponsProjectPhase2/rest/api/Coupons', updateC).then(function (success) {
           $scope.ServerResponse = updateC;
       });
   }
  
}])







