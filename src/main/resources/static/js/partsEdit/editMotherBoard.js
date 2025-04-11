let firstCreate = false;
let beforePartsName = null;

function initialSetup() {
  const queryParams = new URLSearchParams(window.location.search);
  const queryData = queryParams.get("MotherBoard");
  fetch(`/CusCom/API/searchMotherBoard${queryData !== null ? `?motherBoardData=${queryData}` : ''}`)
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

  const requestFormat = {
    partsType: 'MotherBoard',
    requestJSON: JSON.stringify(motherBoardData),
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
