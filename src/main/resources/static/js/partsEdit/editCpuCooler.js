let firstCreate = true;
let beforePartsName = null;

function initialSetup() {
  const queryParams = new URLSearchParams(window.location.search);
  const queryData = queryParams.get("CPUCooler");
  if (queryData !== null && queryData !== "") {
    fetch(`/CusCom/API/searchCPUCooler?cpuCoolerData=${queryData}`)
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

  const formData = new FormData();
  formData.append('partsType', 'CPUCooler');
  formData.append('requestJSON', JSON.stringify(cpuCoolerData));
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