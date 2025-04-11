document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('join_btn')?.addEventListener('click', function () {
        window.location.href = '/CusCom/joinPage';
    });

    document.getElementById('login_btn')?.addEventListener('click', function (event) {
        event.preventDefault();

        const logInData = {
            insertedID: document.getElementById('userid').value,
            insertedPassword: document.getElementById('pw').value
        };

        fetch("/CusCom/API/open/logIn", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(logInData)
        })
        .then(response => response.json())
        .then(data => {
            window.localStorage.removeItem('cuscomAccessToken');
            window.localStorage.removeItem('cuscomRefreshToken');
            window.localStorage.setItem('cuscomAccessToken', data.accessToken);
            window.localStorage.setItem('cuscomRefreshToken', data.refreshToken);
            window.location.href = '/CusCom/mainPage';
        })
        .catch(error => {
            console.error("LogIn failed:", error);
            alert("로그인 실패: " + error.message);
        });
    });
});
