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


    $("#ong-or-not").change(function(){
        $(this).find("option:selected").each(function(){
            var optionValue = $(this).attr("value");
            if(optionValue != "ong"){
                $("#link-doacao").hide();
            } else{
                $("#link-doacao").show();
            }
        });
    }).change();
    
    function checkPassword(form) { 
        password1 = form.password1.value; 
        password2 = form.password2.value; 

        // If password not entered 
        if (password1 == '') 
            alert ("Please enter Password"); 
              
        // If confirm password not entered 
        else if (password2 == '') 
            alert ("Please enter confirm password"); 
              
        // If Not same return False.     
        else if (password1 != password2) { 
            alert ("\nPassword did not match: Please try again...") 
            return false; 
        } 

        // If same return True. 
        else{ 
            alert("Password Match: Welcome to GeeksforGeeks!") 
            return true; 
        } 
    } 

    $input.addEventListener('change', function () {
        $fileName.textContent = this.value;
    });
});

