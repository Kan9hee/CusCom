document.getElementById('confirmJoin').addEventListener('click', handleJoin);

function handleJoin(event) {
  event.preventDefault();

  const joinData = {
    insertedID: document.getElementById('userName').value,
    insertedPassword: document.getElementById('userPassword').value,
    insertedNickname: document.getElementById('nickName').value
  };

  fetch("/CusCom/API/open/join", {
    method: "POST",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(joinData)
  })
    .then(response => {
      if (response.ok) {
        window.location.href = '/CusCom/loginPage';
      } else {
        return response.json();
      }
    })
    .then(data => {
      if (data?.message) {
        alert("Error: " + data.message);
      }
    })
    .catch(error => {
      console.error("Join request failed:", error);
    });
}

document.getElementById('cancel').addEventListener('click', () => {
  window.history.go(-1);
});