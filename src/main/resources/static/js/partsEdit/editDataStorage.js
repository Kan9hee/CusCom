let firstCreate = true;
let beforePartsName = null;

function initialSetup() {
  const queryParams = new URLSearchParams(window.location.search);
  const queryData = queryParams.get("DataStorage");
  if (queryData !== null && queryData !== "") {
    fetch(`/CusCom/API/searchDataStorage?dataStorageData=${queryData}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('storageInterface').value = data.storageInterface;
      document.getElementById('formFactor').value = data.formFactor;
      document.getElementById('capacity').value = data.capacity;
      document.getElementById('readSpeed').value = data.readSpeed;
      document.getElementById('writeSpeed').value = data.writeSpeed;
      firstCreate = false;
      beforePartsName = data.name;
    });
  }
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
  const dataStorageData = {
    name: document.getElementById('name').value,
    manufacturer: document.getElementById('manufacturer').value,
    storageInterface: document.getElementById('storageInterface').value,
    formFactor: document.getElementById('formFactor').value,
    capacity: document.getElementById('capacity').value,
    readSpeed: document.getElementById('readSpeed').value,
    writeSpeed: document.getElementById('writeSpeed').value
  };

  const formData = new FormData();
  formData.append('partsType', 'DataStorage');
  formData.append('requestJSON', JSON.stringify(dataStorageData));
  formData.append('partsImage', document.getElementById('imageFile').files[0]);
  if (!firstCreate) {
    formData.append('beforePartsName', beforePartsName);
  }

  const url = firstCreate
    ? "/CusCom/API/admin/createParts"
    : "/CusCom/API/admin/updateParts";

  fetch(url, {
    method: "POST",
    headers: {
      'Authorization': `Bearer ${window.localStorage.getItem('cuscomAccessToken')}`
    },
    body: formData
  })
    .then(response => {
      if (response.ok) {
        alert("저장되었습니다.");
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