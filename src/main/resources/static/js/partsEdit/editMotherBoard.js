let firstCreate = true;
let beforePartsName = null;

function initialSetup() {
  const queryParams = new URLSearchParams(window.location.search);
  const queryData = queryParams.get("MotherBoard");
  if (queryData !== null && queryData !== "") {
    fetch(`/CusCom/API/searchMotherBoard?motherBoardData=${queryData}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('name').value = data.name;
      document.getElementById('manufacturer').value = data.manufacturer;
      document.getElementById('cpuType').value = data.cpuType;
      document.getElementById('socket').value = data.socket;
      document.getElementById('chipset').value = data.chipset;
      document.getElementById('motherBoardFormFactor').value = data.motherBoardFormFactor;
      document.getElementById('memoryType').value = data.memoryType;
      document.getElementById('memorySlot').value = data.memorySlot;
      document.getElementById('ssdM2Slot').value = data.ssdM2Slot;
      document.getElementById('ssdSATASlot').value = data.ssdSATASlot;
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
  const motherBoardData = {
    name: document.getElementById('name').value,
    manufacturer: document.getElementById('manufacturer').value,
    cpuType: document.getElementById('cpuType').value,
    socket: document.getElementById('socket').value,
    chipset: document.getElementById('chipset').value,
    motherBoardFormFactor: document.getElementById('motherBoardFormFactor').value,
    memoryType: document.getElementById('memoryType').value,
    memorySlot: document.getElementById('memorySlot').value,
    ssdM2Slot: document.getElementById('ssdM2Slot').value,
    ssdSATASlot: document.getElementById('ssdSATASlot').value,
  };

  const formData = new FormData();
  formData.append('partsType', 'MotherBoard');
  formData.append('requestJSON', JSON.stringify(motherBoardData));
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
