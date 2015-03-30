'use strict';

angular.module('essentialApp').factory('Oils', function Oils($resource) {
    	
        return $resource('api/oils/:id', {}, {
        	
            'findAll': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        return {                    	
                        	body: response.data._embedded['essential:oilResourceList'],
                        	links: response.data._links,
                        	page: response.data.page
                        };
                    }
                }
            },
            
            'update': { method: 'PUT' },
            'remove': { method: 'DELETE' }            
            
        });
    });