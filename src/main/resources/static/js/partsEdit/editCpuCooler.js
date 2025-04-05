const accessToken = window.localStorage.getItem('cuscomAccessToken');
const queryParams = new URLSearchParams(window.location.search);
const queryData = queryParams.get("CPUCooler");
let firstCreate = false;
let beforePartsName = null;

function initialSetup() {
  fetch(`/CusCom/API/searchCPUCooler${queryData !== null ? `?cpuCoolerData=${queryData}` : ''}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('coolingType').value = data.coolingType;
      document.getElementById('coolerForm').value = data.coolerForm;
      document.getElementById('height').value = data.height;
      document.getElementById('length').value = data.length;
      document.getElementById('width').value = data.width;
      document.getElementById('TDP').value = data.tdp;
      firstCreate = (data.name === "");
      beforePartsName = data.name;
    });
}

window.addEventListener('load', initialSetup);

document.getElementById('confirm').addEventListener('click', function () {
  const cpuCoolerData = {
    name: document.getElementById('name').value,
    manufacturer: document.getElementById('manufacturer').value,
    coolingType: document.getElementById('coolingType').value,
    coolerForm: document.getElementById('coolerForm').value,
    height: document.getElementById('height').value,
    length: document.getElementById('length').value,
    width: document.getElementById('width').value,
    TDP: document.getElementById('TDP').value
  };

  const requestFormat = {
    partsType: 'CPUCooler',
    requestJSON: JSON.stringify(cpuCoolerData),
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