let firstCreate = true;
let beforePartsName = null;

function initialSetup() {
  const queryParams = new URLSearchParams(window.location.search);
  const queryData = queryParams.get("GraphicsCard");
  if (queryData !== null && queryData !== "") {
    fetch(`/CusCom/API/searchGraphicsCard?graphicsCardData=${queryData}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('chipsetManufacturer').value = data.chipsetManufacturer;
      document.getElementById('gpuType').value = data.gpuType;
      document.getElementById('length').value = data.length;
      document.getElementById('basicPower').value = data.basicPower;
      document.getElementById('maxPower').value = data.maxPower;
      document.getElementById('phase').value = data.phase;
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
  const graphicsCardData = {
    name: document.getElementById('name').value,
    manufacturer: document.getElementById('manufacturer').value,
    chipsetManufacturer: document.getElementById('chipsetManufacturer').value,
    gpuType: document.getElementById('gpuType').value,
    length: document.getElementById('length').value,
    basicPower: document.getElementById('basicPower').value,
    maxPower: document.getElementById('maxPower').value,
    phase: document.getElementById('phase').value
  };

  const formData = new FormData();
  formData.append('partsType', 'GraphicsCard');
  formData.append('requestJSON', JSON.stringify(graphicsCardData));
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