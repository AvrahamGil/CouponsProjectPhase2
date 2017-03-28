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
       controller: "customerProfile"
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
customerController.$inject = ['$http', '$scope', 'userService'];
function customerController($http, $scope, userService) {

    (
               $scope.name = function () {
                   $scope.name = userService.name;
               })();


  
    $scope.logOut = function () {
        $http.post('/CouponsProjectPhase2/rest/Login/logOut').then(function successCall(data) {
            bootbox.alert("<html><body><center>Logout Success</center></body></html>");
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        })
    }


}

angular.module("myApp").controller('couponList', ['$http', '$scope', function ($http, $scope) {



    (
    $scope.getListOfAllCoupons = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons').then(function successCall(response) {
            $scope.coupons = [];
            $scope.listOfCoupons = $scope.coupons.concat(response.data.coupon);
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });

    })();

    $scope.type = function (couponTypeByNumber) {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/CouonType/' + couponTypeByNumber).then(function successCall(response) {
            $scope.coupons = [];
            $scope.listOfCouponsType = $scope.coupons.concat(response.data.coupon);
            
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        })
    }
    $scope.price = function (couponPrice) {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/CouonPrice/' + couponPrice).then(function successCall(response) {
            $scope.coupons = [];
            $scope.listOfCouponsPrice = $scope.coupons.concat(response.data.coupon);
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        })
    }

    $scope.couponPic = function (couponTypeByNumber) {
        if (couponTypeByNumber == 1) {
            bootbox.alert("<html><body><img src='http://www.restaurantfanatix.com/wp-content/uploads/2015/10/luquin.jpg' alt='Holiday'</body></html>")
        } else if (couponTypeByNumber == 2) {
            bootbox.alert("<html><body><img src='http://www.aviationexplorer.com/airline_coupon_code_promos.png' alt='Holiday'</body></html>")
        } else if (couponTypeByNumber == 3) {
            bootbox.alert("<html><body><img src='http://hunt4freebies.com/coupons/wp-content/uploads/2013/12/Chilis-Holiday.png' alt='Holiday'</body></html>")
        }

    }


    var rangeSliderForPriceToMakeItMoreEasy = function () {
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

    rangeSliderForPriceToMakeItMoreEasy();

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
                    $http.put('/CouponsProjectPhase2/rest/api/Customers/' + couponID + '/' + endDate).then(function successCall(data) {
                        bootbox.alert("Success, You just buy a new Coupon");
                    }, function errorCall(response) {
                        bootbox.alert(response.data.message);
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
            $http.get('/CouponsProjectPhase2/rest/api/Coupons/CustomerCoupons').then(function successCall(response) {
                $scope.coupons = [];
                $scope.couponscustomerDetails = $scope.coupons.concat(response.data.coupon);
            }, function errorCall(response) {
                bootbox.alert(response.data.message);
            });

        })();


    $scope.couponPic = function (couponTypeByNumber) {
        if (couponTypeByNumber == 1) {
            bootbox.alert("<html><body><img src='http://www.restaurantfanatix.com/wp-content/uploads/2015/10/luquin.jpg' alt='Holiday'</body></html>")
        } else if (couponTypeByNumber == 2) {
            bootbox.alert("<html><body><img src='http://www.aviationexplorer.com/airline_coupon_code_promos.png' alt='Holiday'</body></html>")
        } else if (couponTypeByNumber == 3) {
            bootbox.alert("<html><body><img src='http://hunt4freebies.com/coupons/wp-content/uploads/2013/12/Chilis-Holiday.png' alt='Holiday'</body></html>")
        }

    }

    $scope.deleteCustomerCoupon = function (customerID) {
        bootbox.confirm({
            message: "Delete Coupon ? ",
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
                    $http.delete('/CouponsProjectPhase2/rest/api/Customers/customerCoupon/' + customerID).then(function successCall(data) {
                        bootbox.alert("Remove Done");
                    }, function errorCall(response) {
                        bootbox.alert(response.data.message);
                    })
                }
                return;

            }
        });
    }


}])

angular.module("myApp").controller('customerProfile', ['$http', '$scope', '$location', function ($http, $scope, $location) {
    
    (
    $scope.getCustomer = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Customers/Profile').then(function successCall(response) {
            $scope.customer = response.data;
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });
    })();


    $scope.updateCustomer = function () {
               var updateC = JSON.stringify($scope.customer);
               $http.put('/CouponsProjectPhase2/rest/api/Customers', updateC).then(function successCall (data) {
                   bootbox.alert("Change Password success!!");
               
               }, function errorCall(response) {
                   bootbox.alert(response.data.message);
               });
       
   
    }

}])
	
