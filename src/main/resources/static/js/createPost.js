const queryParams = new URLSearchParams(window.location.search);
const dataId = queryParams.get("id");
const accessToken = window.localStorage.getItem('cuscomAccessToken');

const estimate = {
  _id: dataId,
  userName: "",
  posted: true,
  cpu: "",
  motherBoard: "",
  memory: "",
  dataStorage: "",
  graphicsCard: "",
  cpuCooler: "",
  powerSupply: "",
  desktopCase: ""
};

function initialSetup() {
  fetch(`/CusCom/API/open/getUserEstimate?estimateID=${dataId}`)
    .then(response => response.json())
    .then(data => {
      Object.assign(estimate, {
        userName: data.userName,
        cpu: data.cpu,
        motherBoard: data.motherBoard,
        memory: data.memory,
        dataStorage: data.dataStorage,
        graphicsCard: data.graphicsCard,
        cpuCooler: data.cpuCooler,
        powerSupply: data.powerSupply,
        desktopCase: data.desktopCase
      });

      updateH6Text("cpuH6", estimate.cpu);
      updateH6Text("motherBoardH6", estimate.motherBoard);
      updateH6Text("memoryH6", estimate.memory);
      updateH6Text("dataStorageH6", estimate.dataStorage);
      updateH6Text("graphicsCardH6", estimate.graphicsCard);
      updateH6Text("cpuCoolerH6", estimate.cpuCooler);
      updateH6Text("powerSupplyH6", estimate.powerSupply);
      updateH6Text("caseH6", estimate.desktopCase);
    })
    .catch(error => console.error("Failed to fetch estimate:", error));
}

function updateH6Text(id, text) {
  const h6 = document.getElementById(id);
  if (h6) h6.textContent = text;
}

function handleSubmit(event) {
  event.preventDefault();

  const selectedTags = Array.from(document.querySelectorAll('input[type="checkbox"][name="tag"]:checked'))
    .map(checkbox => checkbox.nextElementSibling.textContent);

  const postData = {
    estimateID: dataId,
    title: document.getElementById('title').value,
    tags: selectedTags
  };

  fetch("/CusCom/API/uploadPost", {
    method: "POST",
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${accessToken}`
    },
    body: JSON.stringify(postData)
  })
    .then(response => {
      if (response.ok) {
        window.location.href = '/CusCom/mainPage';
      } else {
        return response.json();
      }
    })
    .then(data => {
      if (data?.message) alert("Error: " + data.message);
    })
    .catch(error => {
      console.error("Upload failed:", error);
    });
}

function handleCancel() {
  history.go(-1);
}

window.addEventListener('load', initialSetup);
document.getElementById('submitPost').addEventListener('click', handleSubmit);
document.getElementById('cancel').addEventListener('click', handleCancel);
