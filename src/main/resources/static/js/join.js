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
      if (!response.ok) {
        return response.json().then(errorData => {
          throw new Error(errorData.message || "알 수 없는 오류");
      });
    }
    return response.text();
  })
  .then(result => {
    alert("가입되었습니다.");
    window.location.href = '/CusCom/loginPage';
  })
  .catch(error => {
    alert(error.message);
  });
}

document.getElementById('cancel').addEventListener('click', () => {
  window.history.go(-1);
});