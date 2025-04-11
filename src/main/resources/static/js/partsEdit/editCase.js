let firstCreate = false;
let beforePartsName = null;

function initialSetup() {
  const queryParams = new URLSearchParams(window.location.search);
  const queryData = queryParams.get("Case");
  fetch(`/CusCom/API/searchCase${queryData !== null ? `?caseData=${queryData}` : ''}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('caseType').value = data.caseType;
      document.getElementById('motherBoardFormFactor').value = data.motherBoardFormFactor;
      document.getElementById('maxCoolingFan').value = data.maxCoolingFan;
      document.getElementById('builtInCoolingFan').value = data.builtInCoolingFan;
      document.getElementById('height').value = data.height;
      document.getElementById('length').value = data.length;
      document.getElementById('width').value = data.width;
      document.getElementById('powerLength').value = data.powerLength;
      document.getElementById('cpuCoolerHeight').value = data.cpuCoolerHeight;
      document.getElementById('graphicsCardLength').value = data.graphicsCardLength;
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

document.getElementById('confirm').addEventListener('click', function () {
  const caseData = {
    name: document.getElementById('name').value,
    manufacturer: document.getElementById('manufacturer').value,
    caseType: document.getElementById('caseType').value,
    motherBoardFormFactor: document.getElementById('motherBoardFormFactor').value,
    maxCoolingFan: document.getElementById('maxCoolingFan').value,
    builtInCoolingFan: document.getElementById('builtInCoolingFan').value,
    height: document.getElementById('height').value,
    length: document.getElementById('length').value,
    width: document.getElementById('width').value,
    powerLength: document.getElementById('powerLength').value,
    cpuCoolerHeight: document.getElementById('cpuCoolerHeight').value,
    graphicsCardLength: document.getElementById('graphicsCardLength').value
  };

  const requestFormat = {
    partsType: 'Case',
    requestJSON: JSON.stringify(caseData),
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