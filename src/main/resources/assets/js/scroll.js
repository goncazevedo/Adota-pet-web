$(document).ready(function () {
    $(window).on('scroll', function () {
        if ($(this).scrollTop() > 200) {
            $(".icon-scroll").addClass("icon-scroll-gone");
        } else {
            $(".icon-scroll").removeClass("icon-scroll-gone");
        }
    });
});
