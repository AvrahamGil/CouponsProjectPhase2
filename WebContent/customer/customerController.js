var app = angular.module("myApp");


angular.module("myApp").config(function ($routeProvider) {
    $routeProvider
   .when("/customer/buyCoupon", {
       templateUrl: "customer/buyCoupon.htm",
       controller: "customerController"
   })
   .when("/customer/couponList", {
       templateUrl: "customer/couponList.htm",
       controller: "couponList"

   })
   .when("/customer/Profile", {
       templateUrl: "customer/Profile.htm",
       controller: "customerControllerList"
   })
    .when("/customer/customerCoupons", {
        templateUrl: "customer/customerCoupons.htm",
        controller: "mycouponList"
    })
        .when("/customer/ByType", {
        templateUrl: "customer/ByType.htm",
        controller: "couponList"

    }).when("/customer/ByPrice", {
        templateUrl: "customer/ByPrice.htm",
        controller: "couponList"

    })

    ;


});




angular.module("myApp").controller('customerController', customerController);
customerController.$inject = ['$http', '$scope' , 'serviceName'];
function customerController($http, $scope, serviceName) {

    (
               $scope.name = function () {
                   $scope.name = serviceName.name;
               })();


  

  

    $scope.logOut = function () {
        $http.post('/CouponsProjectPhase2/rest/Login/logOut').then(function (success) {
            alert("log out success");
        })
    }


}

angular.module("myApp").controller('couponList', ['$http', '$scope', function ($http, $scope) {



    (
    $scope.getListOfAllCoupons = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons').then(function (response) {
            $scope.coupons = [];
            $scope.listOfCoupons = $scope.coupons.concat(response.data.coupon);
        });

    })();

    $scope.type = function (couponTypeByNumber) {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/CouonType/' + couponTypeByNumber).then(function (response) {
            $scope.coupons = [];
            $scope.listOfCouponsType = $scope.coupons.concat(response.data.coupon);
            
        })
    }
    $scope.price = function (couponPrice) {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/CouonPrice/' + couponPrice).then(function (response) {
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

    $scope.buyCoupon = function (couponID,endDate, couponTitle) {
        bootbox.confirm({
            message: "Are you sure you want to buy " + couponTitle + " coupon?",
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
                    $http.put('/CouponsProjectPhase2/rest/api/Customers/' + couponID + '/' + endDate).then(function (success) {
                        bootbox.alert("Success, You just buy a new Coupon");
                    }).then(function (error) {
                        console.error("Failed");
                    })
                }
                return;

            }
        });
    }



}])

angular.module("myApp").controller('mycouponList', ['$http', '$scope', function ($http, $scope) {

    (
        $scope.getCustomerCoupons = function () {
            $http.get('/CouponsProjectPhase2/rest/api/Coupons/CustomerCoupons').then(function (response) {
                $scope.coupons = [];
                $scope.couponscustomerDetails = $scope.coupons.concat(response.data.coupon);
            });

        })();






}])

angular.module("myApp").controller('customerControllerList', ['$http', '$scope', '$location', function ($http, $scope, $location) {
    var vm = this;
    vm.message = null;
    vm.status = null;

    (
    $scope.getCustomer = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Customers/Profile').then(function (response) {
            $scope.customer = response.data;
        });
    })();


    $scope.updateCustomer = function () {
               var updateC = JSON.stringify($scope.customer);
               $http.put('/CouponsProjectPhase2/rest/api/Customers', updateC).then(function (success) {
                   bootbox.alert("Change Password success!!");
               
            });
       
   
    }

}])
	
