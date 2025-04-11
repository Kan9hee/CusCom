let firstCreate = false;
let beforePartsName = null;

function initialSetup() {
  const queryParams = new URLSearchParams(window.location.search);
  const queryData = queryParams.get("Memory");
  fetch(`/CusCom/API/searchMemory${queryData !== null ? `?memoryData=${queryData}` : ''}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('type').value = data.type;
      document.getElementById('capacity').value = data.capacity;
      document.getElementById('height').value = data.height;
      firstCreate = (data.name === "");
      beforePartsName = data.name;
    });
}

window.addEventListener("load", () => {
  if (typeof window.adminCheck === "function") {
    window.adminCheck().then(isAdmin => {
      if (isAdmin) {
        initialSetup();
      }
      else {
        window.location.href = '/CusCom/mainPage'
      }
    });
  } else {
    console.error("adminCheck 함수가 정의되지 않았습니다.");
    window.location.href = '/CusCom/mainPage'
  }
});

document.getElementById('confirm').addEventListener('click', function(event) {
  const memoryData = {
    name: document.getElementById('name').value,
    manufacturer: document.getElementById('manufacturer').value,
    type: document.getElementById('type').value,
    capacity: document.getElementById('capacity').value,
    height: document.getElementById('height').value
  };

  const requestFormat = {
    partsType: 'Memory',
    requestJSON: JSON.stringify(memoryData),
    partsImage: document.getElementById('imageFile').files[0],
    beforePartsName: beforePartsName
  };

  const url = firstCreate
    ? "/CusCom/API/admin/createParts"
    : "/CusCom/API/admin/updateParts";

  fetch(url, {
    method: "POST",
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${window.localStorage.getItem('cuscomAccessToken')}`
    },
    body: JSON.stringify(requestFormat)
  })
    .then(response => {
      if (response.ok) {
        window.location.href = "/CusCom/admin/main";
      } else {
        return response.json();
      }
    })
    .then(data => {
      if (data) {
        alert("Error: " + data.message);
      }
    })
    .catch(error => {
      console.error("Error:", error);
    });
});

document.getElementById('cancel').addEventListener('click', () => {
  window.history.go(-1);
});
