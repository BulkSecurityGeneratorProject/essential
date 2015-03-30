'use strict';

angular.module('essentialApp')
    .controller('OilController', function ($scope, Principal, Oils) {
    	
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
        
        var params = {
        		
        };
        $scope.oils = Oils.findAll(params, function(response) {
        	console.log(response);
        });        
    	    	
    });
