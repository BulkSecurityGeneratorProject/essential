'use strict';

angular.module('essentialApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('oils', {
                parent: 'site',
                url: '/oils',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/oils/oils.html',
                        controller: 'OilController'
                    }
                },
                resolve: {
                    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('oils');
                        return $translate.refresh();
                    }]
                }
            });
    });
