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

    }).when("/company/ByPrice", {
        templateUrl: "company/ByPrice.htm",
        controller: "couponController"

    }).when("/company/ByType", {
        templateUrl: "company/ByType.htm",
        controller: "couponController"

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
                    $scope.coupons = [];
                    $scope.couponsDetails = $scope.coupons.concat(response.data.coupon);
            });

            })();
    
 
   
    $scope.updateCoupons = function (coupon ) {
     //   var updateCo = JSON.stringify($scope.coupon);
        $http.put('/CouponsProjectPhase2/rest/api/Coupons', coupon).then(function (success) {
            bootbox.alert('Update Done');
        })
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

    
    $scope.type = function (couponTypeByNumber) {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/CompanyCouponsType/' + couponTypeByNumber).then(function (response) {
            $scope.coupons = [];
            $scope.listOfCouponsType = $scope.coupons.concat(response.data.coupon);
            
        })
    }
    $scope.price = function (couponPrice) {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/CompanyCouponsPrice/' + couponPrice).then(function (response) {
            $scope.coupons = [];
            $scope.listOfCouponsPrice = $scope.coupons.concat(response.data.coupon);
        })
    }

    var rangeSlider = function () {
        var slider = $('.range-slider'),
            range = $('.range-slider__range'),
            value = $('.range-slider__value');

        slider.each(function () {

            value.each(function () {
                var value = $(this).prev().attr('value');
                $(this).html(value);
            });

            range.on('input', function () {
                $(this).next(value).html(this.value);
            });
        });
    };

    rangeSlider();
    
}])


angular.module("myApp").controller('companyProfile', ['$http', '$scope', '$location', function ($http, $scope, $location) {

   (
    $scope.getCompany = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Companies/Profile').then(function (response) {
            $scope.company = response.data;
        });
    }
    )();

   $scope.updateCompany = function () {
       var createC = JSON.stringify($scope.company);
       $http.put('/CouponsProjectPhase2/rest/api/Companies', createC ).then(function (success) {
           bootbox.alert("Update Done");
       });
   }
  
}])







