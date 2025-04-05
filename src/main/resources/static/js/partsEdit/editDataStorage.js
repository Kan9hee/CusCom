const accessToken = window.localStorage.getItem('cuscomAccessToken');
const queryParams = new URLSearchParams(window.location.search);
const queryData = queryParams.get("DataStorage");
let firstCreate = false;
let beforePartsName = null;

function initialSetup() {
  fetch(`/CusCom/API/searchDataStorage${queryData !== null ? `?dataStorageData=${queryData}` : ''}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('storageInterface').value = data.storageInterface;
      document.getElementById('formFactor').value = data.formFactor;
      document.getElementById('capacity').value = data.capacity;
      document.getElementById('readSpeed').value = data.readSpeed;
      document.getElementById('writeSpeed').value = data.writeSpeed;
      firstCreate = (data.name === "");
      beforePartsName = data.name;
    });
}

window.onload = initialSetup;

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

  const requestFormat = {
    partsType: 'DataStorage',
    requestJSON: JSON.stringify(dataStorageData),
    partsImage: document.getElementById('imageFile').files[0],
    beforePartsName: beforePartsName
  };

  const url = firstCreate
    ? "/CusCom/admin/API/createParts"
    : "/CusCom/admin/API/updateParts";

  fetch(url, {
    method: "POST",
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${accessToken}`
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