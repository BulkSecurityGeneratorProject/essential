'use strict';

angular.module('essentialApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
