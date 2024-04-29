const signIn = document.getElementById("signin");
signIn.addEventListener("submit", handleFormSubmit);
async function handleFormSubmit(e) {
    var password = document.getElementById('floatingPassword').value;
    var email = document.getElementById('floatingEmail').value;

    let response = await fetch('https://bookingapp.ie/api/v1/user/login', {
        method: 'POST',
        body: JSON.stringify({
            email: email,
            password: password,
        }),
        headers: {
                'Content-type': 'application/json; charset=UTF-8',

        }
    }).then(function(response){
        
        if (response.ok) {
            return response.json();
        } else {
            return Promise.reject(response);
        };

    }).then(function(data) {

        localStorage.setItem("token", data.message)
        setTimeout(redirect, 2000);

    }).catch(function(error) {
  
        error.json().then((json) => {
          document.getElementById('showError').innerText = "Error: " + json.message;
          console.log(json.message);
  
        })
    })
    return false;    
}


function redirect() {

    window.location.href="booking.html";
    
}


