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
                    $scope.coupons = [];
                    $scope.couponsDetails = $scope.coupons.concat(response.data.coupon);
            });

            })();
    
    /*
    $scope.updateCoupon = function () {
        bootbox.prompt("Update Coupon", function (result) { });
        var updateC = JSON.stringify($scope.updateCoupons);
        $http.put('/CouponsProjectPhase2/rest/api/Coupons', updateC).then(function (success) {
            $scope.ServerResponse = updateC;
        });
    }
    */
    /*
    $scope.updateCoupon = function (coupon) {
        var messageC = $()
        bootbox.dialog({
            message: "<form method='Post' id='update'><br/>New End Date:  <input type='Date' name='coupon.endDate' ng-model=coupon.endDateString/><br/>\New Price : <input type='text'  class='form-control' name='couponPrice' ng-model='coupon.couponPrice'  />'</form>",
            title: "Coupon Update",
            buttons: {
                confirm : {
                    label: "Update",
                    className: 'btn-success'
                },
                cancel: {
                    label: 'Cancle',
                    className: 'btn-danger'
                }
            }, callback: function (result) {
                if (result == true) {
                    //    var updateC = JSON.stringify($scope.updateCoupons);
                     coupon =  $('#update').submit();
                    $http.put('/CouponsProjectPhase2/rest/api/Coupons', coupon).then(function (success) {
                        bootbox.alert("Update Success!!");
                    }).then(function (error) {
                        bootbox.alert("Failed");
                    })
                }
            }
        })
    }
    */

   

    /*
    $scope.updateCoupon = function () {
       var update =  bootbox.dialog({
           message: '<form method="Post"><label for="EndDate">End Date</label><input type="Date"  name="endDateString" placeholder="Date" ng-model="updateCoupons.endDateString" /><br/><label for="Price">Coupon Price :</label><input type="text"  name="couponPrice"placeholder="Price" ng-model="updateCoupons.couponPrice" /></form>',
            title: "Update Coupon",
            buttons: {
                confirm: {
                    label: "Update",
                    className: 'btn-success'
                },
                cancel: {
                    label: 'Cancle',
                    className: 'btn-danger',   
                }
            }, callback: function (result) {
                if (result == true) {
                        var updateC = JSON.stringify($scope.updateCoupons);
                    $http.put('/CouponsProjectPhase2/rest/api/Coupons', updateC).then(function (success) {
                        bootbox.alert("Update Success!!");
                    }).then(function (error) {
                        bootbox.alert("Failed");
                    })
                }
            }
        })
    }
    */

    $scope.updateCoupon = function (coupon) {
        bootbox.confirm({
            message: '<label for="EndDate">End Date</label><input type="Date"  name="endDateString" placeholder="Date" ng-model="updateCoupons.endDateString" /><br/><label for="Price">Coupon Price :</label><input type="text"  name="couponPrice"placeholder="Price" ng-model="updateCoupons.couponPrice" />',
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
                    var updateC = JSON.stringify($scope.updateCoupons);
                    $http.put('/CouponsProjectPhase2/rest/api/Coupons', updateC).then(function (success) {
                        bootbox.alert("Update Success!!");
                    }).then(function (error) {
                        bootbox.alert("Failed");
                    })
                }
                return;

            }
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







