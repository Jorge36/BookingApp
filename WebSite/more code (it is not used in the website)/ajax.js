///////////////////// ANother option AJAX

function isMobile() {
  const regex = /Mobi|Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i;
  return regex.test(navigator.userAgent);
}

if (isMobile()) { // Mobile device

  const signInMobile = document.getElementById("signin");
  signInMobile.addEventListener("submit", handleFormSubmitMobile);

  function handleFormSubmitMobile(e) {

    const newXHRRequest = new XMLHttpRequest();
  
    newXHRRequest.open(
      "POST",
      "https://www.bookingapp.ie/api/v1/user/login",
      false
    );

    newXHRRequest.setRequestHeader("Content-type", "application/json; charset=UTF-8");
  
    var password = document.getElementById('floatingPassword').value;
    var email = document.getElementById('floatingEmail').value;
    document.getElementById('showError').innerText = password + " " + email;

    let body = JSON.stringify({
        email: email,
        password: password,
    });

    newXHRRequest.onreadystatechange = function () {

      if (newXHRRequest.readyState == 4 && newXHRRequest.status == 200) {

        const json = JSON.parse(newXHRRequest.responseText);
        localStorage.setItem("token", data.message)
        setTimeout(redirect1, 2000);

      } else {

        const json = JSON.parse(newXHRRequest.responseText);
        document.getElementById('showError').innerText = json.message;

      }
    };
  
    newXHRRequest.send(body);
    e.preventDefault();
    e.stopPropagation()

  }
  
  function redirect1() {

            window.location.href="booking.html";

  }

  } else { // Desktop Device
  
      const signIn = document.getElementById("signin");
      signIn.addEventListener("submit", handleFormSubmit);
      async function handleFormSubmit(e) {
        e.preventDefault();
        var password = document.getElementById('floatingPassword').value;
        var email = document.getElementById('floatingEmail').value;

        let response = await fetch('https://www.bookingapp.ie/api/v1/user/login', {
            method: 'POST',
            body: JSON.stringify({
              email: email,
              password: password,
            }),
            headers: {
              'Content-type': 'application/json; charset=UTF-8',
            }
        })

        if (response.ok) {
          let data = await response.json();
          successCallback(data);
        } else {
          failCallback(response);
        };
      }

      function successCallback(data) {
            localStorage.setItem("token", data.message)
            setTimeout(redirect, 2000);
      }

      function redirect() {

            window.location.href="booking.html";

      }

      function failCallback(error) {

         console.log(error.status);

         error.json().then((json) => {
            document.getElementById('showError').innerText = "Error: " + json.message;
            console.log(json.message);
         });

      }
      
  }