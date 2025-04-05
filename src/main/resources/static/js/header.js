async function checkAccessTokenStatus() {
    try {
        const accessToken = window.localStorage.getItem('cuscomAccessToken');
        const response = await fetch("/CusCom/API/checkAccessToken", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${accessToken}`
            }
        });
        if (response.ok) {
            console.log("Access Token OK");
            return true;
        } else {
            console.error("Access token invalid or expired");
            const refreshToken = window.localStorage.getItem('cuscomRefreshToken');
            return await reissueAccessToken(refreshToken);
        }
    } catch (err) {
        console.error("Token check error:", err);
        return false;
    }
}

async function reissueAccessToken(refreshToken) {
    try {
        const response = await fetch("/CusCom/API/open/reissueAccessToken", {
            method: "POST",
            headers: {
                 "Content-Type": "application/json"
            },
            body: JSON.stringify({ refreshToken })
        });
        if (response.ok) {
            const newTokens = await response.json();
            localStorage.setItem('cuscomAccessToken', newTokens.accessToken);
            localStorage.setItem('cuscomRefreshToken', newTokens.refreshToken);
            console.log("New tokens issued and stored.");
            return true;
        } else {
            console.error("Failed to reissue tokens");
            return false;
        }
    } catch (err) {
        console.error("Reissue tokens error:", err);
        return false;
    }
}

window.addEventListener('load', async function() {
    const loginStatus = document.getElementById("loginCheck");
    const isValid = await checkAccessTokenStatus();
    if (isValid) {
        loginStatus.innerHTML = `
            <a class="nav-link" href="/CusCom/myPage">마이페이지</a>
            <a class="nav-link" id="logout">로그아웃</a>
        `;
        const logOutData = {
            accessToken: window.localStorage.getItem('cuscomAccessToken'),
            refreshToken: window.localStorage.getItem('cuscomRefreshToken')
        };

        document.getElementById('logout').addEventListener('click', () => {
            fetch("/CusCom/API/logOut", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                    'Authorization': `Bearer ${logOutData.accessToken}`
                },
                body: JSON.stringify(logOutData)
            })
            .then(response => {
                if (response.ok) {
                    location.reload();
                } else {
                    console.error("Logout failed:", response.status);
                }
            })
            .catch(error => {
                console.error("Logout error:", error);
            });
        });
    } else {
        loginStatus.innerHTML = '<a class="nav-link" href="/CusCom/loginPage">로그인</a>';
    }
});