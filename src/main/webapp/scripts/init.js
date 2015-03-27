(function( $, angular ) {
	"use strict";

	angular.element(document).ready(function() {

		var $window = $( window ),
		    $body = $( 'body' );

		/**
		 * =======================================
		 * Function: Detect Mobile Device
		 * =======================================
		 */
		// source: http://www.abeautifulsite.net/detecting-mobile-devices-with-javascript/
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
		 * Function: Resize Background
		 * =======================================
		 */
		var resizeBackground = function() {

			$( '.section-background video, .section-background img, .two-cols-description-image img' ).each(function( i, el ) {
				var $el       = $( el ),
				    $section  = $el.parent(),
				    min_w     = 300,
				    el_w      = el.tagName == 'VIDEO' ? el.videoWidth : el.naturalWidth,
				    el_h      = el.tagName == 'VIDEO' ? el.videoHeight : el.naturalHeight,
				    section_w = $section.outerWidth(),
				    section_h = $section.outerHeight(),
				    scale_w   = section_w / el_w,
				    scale_h   = section_h / el_h,
				    scale     = scale_w > scale_h ? scale_w : scale_h,
				    new_el_w, new_el_h, offet_top, offet_left;

				if ( scale * el_w < min_w ) {
					scale = min_w / el_w;
				};

				new_el_w = scale * el_w;
				new_el_h = scale * el_h;
				offet_left = ( new_el_w - section_w ) / 2 * -1;
				offet_top  = ( new_el_h - section_h ) / 2 * -1;

				$el.css( 'width', new_el_w );
				$el.css( 'height', new_el_h );
				$el.css( 'marginTop', offet_top );
				$el.css( 'marginLeft', offet_left );
			});

		};

		/**
		 * =======================================
		 * Detect Mobile Device
		 * =======================================
		 */
		if ( isMobile.any() ) {
			// add identifier class to <body>
			$body.addClass( 'mobile-device' );
			// remove all element with class "remove-on-mobile-device"
			$( '.remove-on-mobile-device' ).remove();
		};

		/* =======================================
		 * Resize Background
		 * =======================================
		 */
		$window.on( 'resize', function() {
			resizeBackground();
		});
		
		/**
		 * =======================================
		 * Initiate Stellar JS
		 * =======================================
		 */
		if ( $.fn.stellar && ! isMobile.any() ) {
			$.stellar({
				responsive: true,
				horizontalScrolling: false,
				hideDistantElements: false,
				verticalOffset: 0,
				horizontalOffset: 0,
			});
		};

		/**
		 * =======================================
		 * Scroll Spy
		 * =======================================
		 */
		var toggleHeaderFloating = function() {
			// Floating Header
			if ( $window.scrollTop() > 80 ) {
				$( '.header-section' ).addClass( 'floating' );
			} else {
				$( '.header-section' ).removeClass( 'floating' );
			};
		};

		$window.on( 'scroll', toggleHeaderFloating );

		/**
		 * =======================================
		 * One Page Navigation
		 * =======================================
		 */
		if ( $.fn.onePageNav ) {
			$( '#header-nav' ).onePageNav({
				scrollSpeed : 1000,
				begin : function() {
					$( '#navigation' ).collapse( 'toggle' );
				},
			});
		};
		
		/**
		 * =======================================
		 * Anchor Link
		 * =======================================
		 */
        angular.element(document).ready(function() {
        	
        	var headerFloatingHeight = 60;
        	
    		$('body').on( 'click', 'a.anchor-link', function( e ) {
    			e.preventDefault();

    			var $a = $( this ),
    			    $target = $( '#hero' );

    			if ( $target.length < 1 ) return;

    			$( 'html, body' ).animate({ scrollTop: Math.max( 0, $target.offset().top - headerFloatingHeight ) }, 1000 );
    		});        	
        });		

		/**
		 * =======================================
		 * Animations
		 * =======================================
		 */
		if ( $body.hasClass( 'enable-animations' ) && ! isMobile.any() ) {
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

		/* =======================================
		 * Preloader
		 * =======================================
		 */
		if ( $.fn.jpreLoader && $body.hasClass( 'enable-preloader' ) ) {

			$body.jpreLoader({
				showSplash : false,
				// autoClose : false,
			}, function() {
				$body.trigger( 'pageStart' );
			});

			$body.on( 'pageStart', function() {
				$body.addClass( 'done-preloader' );
			});

		} else {
			$body.trigger( 'pageStart' );
		};

		$window.trigger( 'resize' );
		$window.trigger( 'scroll' );

	});

})( jQuery, angular );