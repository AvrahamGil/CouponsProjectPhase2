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
companyController.$inject = ['$http', '$scope', '$location', '$rootScope', 'userService'];
function companyController($http, $scope, $location, $rootScope, userService) {

  
   
	  (
			     $scope.name = function () {
			         $scope.name = userService.name;
			     })();
 

    $scope.companyName = function () {
        var getC = JSON.stringify($scope.companyID);
        $http.get('/CouponsProjectPhase2/rest/api/Companies/' + getC).then(function successCall(response) {
            userService.errorMessage;
            $scope.companyDetail = response.data.company;
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });
    }



    $scope.createCoupon = function () {
        var createC = JSON.stringify($scope.coupon);
        $http.post('/CouponsProjectPhase2/rest/api/Coupons', createC).then(function successCall (data) {
            bootbox.alert("Create Done");
            $location.path('company/company.htm')
        },function errorCall(response) {
            bootbox.alert(response.data.message);
        })

    }

  
    $scope.logOut = function () {
        $http.post('/CouponsProjectPhase2/rest/Login/logOut').then(function successCall(data) {
            bootbox.alert("LogOut Success");
        })
    }

}


angular.module("myApp").controller('couponController', ['$http', '$scope', '$location', function ($http, $scope, $location) {

    

    ( 
            $scope.getCompanyCoupon = function () {
                $http.get('/CouponsProjectPhase2/rest/api/Coupons/CompanyCoupons').then(function successCall(response) {
                    $scope.coupons = [];
                    $scope.couponsDetails = $scope.coupons.concat(response.data.coupon);
                }, function errorCall(response) {
                    bootbox.alert(response.data.message)
                });

            })();
    
 
    /*
    $scope.showWeb = function (event) {
        $mdDialog.show({
            controller: DialogController,
            templateUrl: 'updateCoupon.htm',
            parent: angular.element(document.body),
            targetEvent: event,
            clickOutsideToClose: true
        })
        .then(function (answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function () {
            $scope.status = 'You cancelled the dialog.';
        });
    };

    function DialogController($scope, $mdDialog) {
        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

        $scope.answer = function (answer) {
            $mdDialog.hide(answer);
        };
    }

    */









   /*
    $scope.updateCoupons = function (couponID) {
        bootbox.confirm({
            message: "Are you sure you want to update " + couponID + " coupon?",
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
                    $http.put('/CouponsProjectPhase2/rest/api/Coupons/uCoupon/' + couponID ).then(function (success) {
                        bootbox.alert('Update Done');
                    }, function errorCall(error) {
                        bootbox.alert("Update Failed" + response.data.message);
                    })
                }
                return;

            }
        });
    }
    */
    
    $scope.updateCoupons = function () {
        var createC = JSON.stringify($scope.currentCouponNew);
        $http.put('/CouponsProjectPhase2/rest/api/Coupons/uCoupon/', createC).then(function successCall(data) {
            bootbox.alert('Update Done');
       },function errorCall(response) {
      //     bootbox.alert(response.data.message);
       })
    }

   $scope.setCurrentCoupon = function (coupon) {
       $scope.currentCouponNew = angular.copy(coupon);

       $scope.currentCouponNew.couponPrice = Math.floor($scope.currentCouponNew.couponPrice);
       $scope.currentCouponNew.couponID = Math.floor($scope.currentCouponNew.couponID);
       $scope.currentCouponNew.endDate = Math.floor($scope.currentCouponNew.endDate);
   //    var st = $scope.currentCouponNew.endDate;
   //    var pattern = /(\d{4})(\d{2})(\d{2})/;
   //    var date = new Date(st.replace(pattern, '$1-$2-$3'));

   //    $scope.currentCouponNew.endDate = date;
   }

    /*
   function setCurrentCoupon(coupon) {
       vm.currentCoupon = coupon;
       vm.currentCouponNew = angular.copy(coupon);

       var price = Math.floor(vm.currentCouponNew.couponPrice);
       vm.currentCouponNew.couponPrice = price;

       var st = vm.currentCouponNew.couponEndDateYyyyMmDd;
       var pattern = /(\d{4})(\d{2})(\d{2})/;
       var date = new Date(st.replace(pattern, '$1-$2-$3'));

       vm.currentCouponNew.couponEndDate = date;

       vm.updateCouponStatus = null;
   }
   */
    $scope.couponPic = function (couponTypeByNumber) {
        if (couponTypeByNumber == 1) {
            bootbox.alert("<html><body><img src='http://www.restaurantfanatix.com/wp-content/uploads/2015/10/luquin.jpg' alt='Holiday'</body></html>")
        } else if (couponTypeByNumber == 2) {
            bootbox.alert("<html><body><img src='http://www.aviationexplorer.com/airline_coupon_code_promos.png' alt='Holiday'</body></html>")
        } else if (couponTypeByNumber == 3) {
            bootbox.alert("<html><body><img src='http://hunt4freebies.com/coupons/wp-content/uploads/2013/12/Chilis-Holiday.png' alt='Holiday'</body></html>")
        }
      
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
                    $http.delete('/CouponsProjectPhase2/rest/api/Coupons/' + couponID).then(function successCall (data) {
                        bootbox.alert("Remove Done");
                    },function errorCall(response) {
                        bootbox.alert("Remove Failed" + response.data.message);
                    })
                }
                return;

            }
        });
    }

    
    $scope.type = function (couponTypeByNumber) {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/CompanyCouponsType/' + couponTypeByNumber).then(function successCall(response) {
            $scope.coupons = [];
            $scope.listOfCouponsType = $scope.coupons.concat(response.data.coupon);
            
        }, function errorCall(response) {
            bootbox.alert(response.data.message)
        })
    }
    $scope.price = function (couponPrice) {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/CompanyCouponsPrice/' + couponPrice).then(function successCall(response) {
            $scope.coupons = [];
            $scope.listOfCouponsPrice = $scope.coupons.concat(response.data.coupon);
        }, function errorCall(response) {
            bootbox.alert(response.data.message)
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
        $http.get('/CouponsProjectPhase2/rest/api/Companies/Profile').then(function successCall(response) {
            $scope.company = response.data;
        }, function errorCall(response) {
            bootbox.alert(response.data.message)
        });
    }
    )();

   $scope.updateCompany = function () {
       var createC = JSON.stringify($scope.company);
       $http.put('/CouponsProjectPhase2/rest/api/Companies', createC).then(function successCall(data) {
           bootbox.alert("Update Done");
       }, function errorCall(response) {
           bootbox.alert(response.data.message)
       });
   }
  
}])







