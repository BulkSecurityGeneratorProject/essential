'use strict';

angular.module('essentialApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


