let firstCreate = true;
let beforePartsName = null;

function initialSetup() {
  const queryParams = new URLSearchParams(window.location.search);
  const queryData = queryParams.get("CPU");
  if (queryData !== null && queryData !== "") {
    fetch(`/CusCom/API/searchCPU?cpuData=${queryData}`)
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

  const formData = new FormData();
  formData.append('partsType', 'CPU');
  formData.append('requestJSON', JSON.stringify(cpuData));
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