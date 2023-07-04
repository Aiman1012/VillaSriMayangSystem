$(window).scroll(function() {
    var heroHeight = $('header').height();
    var yPosition = $(document).scrollTop();

    /**
     * Sticky nav-bar
     */
    if (yPosition <= heroHeight) {
        $('nav').removeClass('fixed');
    } else {
        $('nav').addClass('fixed');
    }

});
