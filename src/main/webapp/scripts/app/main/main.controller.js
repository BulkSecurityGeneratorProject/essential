'use strict';

angular.module('essentialApp')
    .controller('MainController', function ($scope, Principal) {
    	
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
        
		var isMobile = {
				Android: function() {
					return navigator.userAgent.match( /Android/i );
				},
				BlackBerry: function() {
					return navigator.userAgent.match( /BlackBerry/i );
				},
				iOS: function() {
					return navigator.userAgent.match( /iPhone|iPad|iPod/i );
				},
				Opera: function() {
					return navigator.userAgent.match( /Opera Mini/i );
				},
				Windows: function() {
					return navigator.userAgent.match( /IEMobile/i );
				},
				any: function() {
					return ( isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows() );
				},
			};        
        
		/**
		 * =======================================
		 * Animations
		 * =======================================
		 */		
    	angular.element(document).ready(function() {
    		if ( $('body').hasClass( 'enable-animations' ) && ! isMobile.any() ) {
    			var $elements = $( '*[data-animation]' );

    			$elements.each( function( i, el ) {

    				var $el = $( el ),
    				    animationClass = $el.data( 'animation' );

    				$el.addClass( animationClass );
    				$el.addClass( 'animated' );
    				$el.addClass( 'wait-animation' );

    				$el.one( 'inview', function() {
    					$el.removeClass( 'wait-animation' );
    					$el.addClass( 'done-animation' );
    				});
    			});
    		};
    	});
    	    	
    });
