const accessToken = window.localStorage.getItem('cuscomAccessToken');
const queryParams = new URLSearchParams(window.location.search);
const queryData = queryParams.get("Memory");
let firstCreate = false;
let beforePartsName = null;

function initialSetup() {
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

window.onload = initialSetup;

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
