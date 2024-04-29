// When DOM is loaded this 
    // function will get executed
    $(() => {
        // function will get executed 
        // on click of submit button
        $("#submitButton").click(function(ev) {
            var form = $("#formId");
            var password = form.attr('floatingPassword');
            var email = form.attr('floatingPassword');
            let body = JSON.stringify({
                email: email,
                password: password,
            });
            $.ajax({
                type: "POST",
                url: "https://www.bookingapp.ie/api/v1/user/login",
                data: body,
                contentType: 'application/json',
                success: function(data) {
                      
                    // Ajax call completed successfully
                    alert("Form Submited Successfully");
                },
                error: function(data) {
                      
                    // Some error in ajax call
                    alert("some Error");
                }
            });
            ev.preventDefault();
        });
    });