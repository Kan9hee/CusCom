const accessToken = window.localStorage.getItem('cuscomAccessToken');
const queryParams = new URLSearchParams(window.location.search);
const queryData = queryParams.get("PowerSupply");
let firstCreate = false;
let beforePartsName = null;

function initialSetup() {
  fetch(`/CusCom/API/searchPowerSupply${queryData !== null ? `?powerSupplyData=${queryData}` : ''}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('power').value = data.power;
      document.getElementById('efficiency').value = data.efficiency;
      document.getElementById('modular').value = data.modular;
      document.getElementById('length').value = data.length;
      firstCreate = (data.name === "");
      beforePartsName = data.name;
    });
}

window.onload = initialSetup;

document.getElementById('confirm').addEventListener('click', function(event) {
  const powerSupplyData = {
    name: document.getElementById('name').value,
    manufacturer: document.getElementById('manufacturer').value,
    power: document.getElementById('power').value,
    efficiency: document.getElementById('efficiency').value,
    modular: document.getElementById('modular').value,
    length: document.getElementById('length').value
  };

  const requestFormat = {
    partsType: 'PowerSupply',
    requestJSON: JSON.stringify(powerSupplyData),
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
