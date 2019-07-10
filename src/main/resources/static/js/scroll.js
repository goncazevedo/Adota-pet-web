$(document).ready(function () {
    $(window).on('scroll', function () {
        if ($(this).scrollTop() > 200) {
            $(".icon-scroll").addClass("icon-scroll-gone");
        } else {
            $(".icon-scroll").removeClass("icon-scroll-gone");
        }
    });
    var $input = document.getElementById('input-file'),
        $fileName = document.getElementById('file-name');

    $input.addEventListener('change', function () {
        $fileName.textContent = this.value;
    });
});

