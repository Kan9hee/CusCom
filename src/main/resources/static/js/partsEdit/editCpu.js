const accessToken = window.localStorage.getItem('cuscomAccessToken');
const queryParams = new URLSearchParams(window.location.search);
const queryData = queryParams.get("CPU");
let firstCreate = false;
let beforePartsName = null;

function initialSetup() {
  fetch(`/CusCom/API/searchCPU${queryData !== null ? `?cpuData=${queryData}` : ''}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('socket').value = data.socket;
      document.getElementById('memoryType').value = data.memoryType;
      document.getElementById('core').value = data.core;
      document.getElementById('thread').value = data.thread;
      document.getElementById('isBuiltInGraphics').value = data.isBuiltInGraphics;
      document.getElementById('builtInGraphicName').value = data.builtInGraphicName;
      document.getElementById('TDP').value = data.tdp;
      firstCreate = (data.name === "");
      beforePartsName = data.name;
    });
}

window.addEventListener('load', initialSetup);

document.getElementById('confirm').addEventListener('click', function(event) {
  const cpuData = {
    name: document.getElementById('name').value,
    manufacturer: document.getElementById('manufacturer').value,
    socket: document.getElementById('socket').value,
    memoryType: document.getElementById('memoryType').value,
    core: document.getElementById('core').value,
    thread: document.getElementById('thread').value,
    isBuiltInGraphics: document.getElementById('isBuiltInGraphics').checked,
    builtInGraphicName: document.getElementById('builtInGraphicName').value,
    TDP: document.getElementById('TDP').value
  };

  const requestFormat = {
    partsType: 'CPU',
    requestJSON: JSON.stringify(cpuData),
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