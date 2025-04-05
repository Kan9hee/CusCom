document.getElementById('login_btn').addEventListener('click',function(event){
    const logInData = {
        insertedID: document.getElementById('login_btn').value,
        insertedPassword: document.getElementById('login_btn').value
    };
    fetch("/CusCom/API/open/logIn", {
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(logInData)
    })
    .then(response => response.json())
    .then(data =>
        window.localStorage.removeItem('cuscomAccessToken');
        window.localStorage.removeItem('cuscomRefreshToken');
        window.localStorage.setItem('cuscomAccessToken',data.accessToken);
        window.localStorage.setItem('cuscomAccessToken',data.refreshToken);
        window.location.href = '/CusCom/mainPage';
    )
    .catch(error => {
        console.error("LogIn failed:", error);
        alert(error);
    });
});