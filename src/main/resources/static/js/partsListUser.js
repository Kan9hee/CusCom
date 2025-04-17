    const queryParams = new URLSearchParams(window.location.search);
    const dataId = queryParams.get("id");
    const currentPageMap = {};
    let chart = null;

    var estimate = {
        estimateId:null,
        cpu:null,
        motherBoard:null,
        memory:null,
        dataStorage:null,
        graphicsCard:null,
        cpuCooler:null,
        powerSupply:null,
        desktopCase:null
    }

    const usageCapacity=[0,0,0,0];
    const spareCapacity=[0,0,0,0];
    const totalTdp=[0,0,0];
    var ctxData = {
      labels: ["전력","쿨러 높이","그래픽카드 길이","파워 사이즈"],
      datasets: [
        {
          label: '사용 용량',
          data: usageCapacity,
          borderColor: 'rgba(255, 99, 132, 1)',
          backgroundColor: 'rgba(255, 99, 132, 1)'
        },
        {
          label: '여유 용량',
          data: spareCapacity,
          borderColor: 'rgba(75, 192, 192, 1)',
          backgroundColor: 'rgba(75, 192, 192, 1)'
        }
      ]
    }

    function fetchEstimate(){
        fetch(`/CusCom/API/open/getUserEstimate?estimateID=${dataId}`)
        .then(response => response.json())
        .then(data => {
            estimate.userName=data.userName;
            estimate.cpu=data.cpu;
            estimate.motherBoard=data.motherBoard;
            estimate.memory=data.memory;
            estimate.dataStorage=data.dataStorage;
            estimate.graphicsCard=data.graphicsCard;
            estimate.cpuCooler=data.cpuCooler;
            estimate.powerSupply=data.powerSupply;
            estimate.desktopCase=data.desktopCase;
            updateH6Text("cpuH6",estimate.cpu);
            updateH6Text("motherBoardH6",estimate.motherBoard);
            updateH6Text("memoryH6",estimate.memory);
            updateH6Text("dataStorageH6",estimate.dataStorage);
            updateH6Text("graphicsCardH6",estimate.graphicsCard);
            updateH6Text("cpuCoolerH6",estimate.cpuCooler);
            updateH6Text("powerSupplyH6",estimate.powerSupply);
            updateH6Text("caseH6",estimate.desktopCase);
        })
        .catch(error => console.error(error));
    }

    function getPartsList(partType, size, page) {
      return fetch(`/CusCom/API/open/${partType}?maxContent=${size}&page=${page}`)
        .then(res => res.json());
    }

    function generateItemDetails(partType, item) {
      switch (partType) {
        case "caseList":
          return `
            <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
            <small class="text-body-secondary">케이스 타입: ${item.caseType}</small>
            <small class="text-body-secondary">높이: ${item.height} mm</small>
            <small class="text-body-secondary">길이: ${item.length} mm</small>
            <small class="text-body-secondary">너비: ${item.width} mm</small>
            <small class="text-body-secondary sparePowerSize">최대 파워 크기: ${item.powerLength} mm</small>
            <small class="text-body-secondary spareCoolerSize">최대 CPU 쿨러 크기: ${item.cpuCoolerHeight} mm</small>
            <small class="text-body-secondary spareGpuSize">최대 그래픽카드 크기: ${item.graphicsCardLength} mm</small>
          `;
        case "cpuList":
          return `
           <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
           <small class="text-body-secondary">소켓: ${item.socket}</small>
           <small class="text-body-secondary">내장그래픽: ${item.isBuiltInGraphics}</small>
           <small class="text-body-secondary">코어 수: ${item.core}</small>
           <small class="text-body-secondary">쓰레드 수: ${item.thread}</small>
           <small class="text-body-secondary usageTdp">TDP: ${item.tdp}</small>
          `;
        case "cpuCoolerList":
          return `
            <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
            <small class="text-body-secondary">냉각방식: ${item.coolingType}</small>
            <small class="text-body-secondary usageCoolerSize">높이: ${item.height}</small>
            <small class="text-body-secondary">길이: ${item.length}</small>
            <small class="text-body-secondary">너비: ${item.width}</small>
            <small class="text-body-secondary usageTdp">TDP: ${item.tdp}</small>
          `;
        case "dataStorageList":
          return `
            <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
            <small class="text-body-secondary">인터페이스: ${item.storageInterface}</small>
            <small class="text-body-secondary">폼팩터: ${item.formFactor}</small>
            <small class="text-body-secondary">읽기 속도: ${item.readSpeed}</small>
            <small class="text-body-secondary">쓰기 속도: ${item.writeSpeed}</small>
          `;
        case "graphicsCardList":
          return `
            <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
            <small class="text-body-secondary">GPU: ${item.gpuType}</small>
            <small class="text-body-secondary usageGpuSize">길이: ${item.length}</small>
            <small class="text-body-secondary">기본 전력: ${item.basicPower}</small>
            <small class="text-body-secondary usageTdp">최대 전력: ${item.maxPower}</small>
          `;
        case "memoryList":
          return `
            <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
            <small class="text-body-secondary">타입: ${item.type}</small>
            <small class="text-body-secondary">용량: ${item.capacity}</small>
            <small class="text-body-secondary">높이: ${item.height}</small>
          `;
        case "motherBoardList":
          return `
            <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
            <small class="text-body-secondary">구분: ${item.cpuType}</small>
            <small class="text-body-secondary">소켓: {item.socket}</small>
            <small class="text-body-secondary">메모리 타입: ${item.memoryType}</small>
            <small class="text-body-secondary">메모리 슬롯: ${item.memorySlot}</small>
          `;
        case "powerSupplyList":
          return `
            <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
            <small class="text-body-secondary">모듈러: ${item.modular}</small>
            <small class="text-body-secondary usagePowerSize">크기: ${item.length}</small>
            <small class="text-body-secondary spareTdp">전력: ${item.power}</small>
            <small class="text-body-secondary">효율: ${item.efficiency}</small>
          `;
        default:
          return `<small class="text-body-secondary">응답 정보: ${JSON.stringify(item, null, 2)}</small>`;
      }
    }

    function loadPage(partType, page) {
      currentPageMap[partType] = page;
      getPartsList(partType, 10, page)
        .then(response => {
          renderPartsList(partType, response.data, response.totalPages, page);
        });
    }

    function renderPagination(partType, totalPages, currentPage, container) {
      const pagination = document.createElement('ul');
      pagination.className = "pagination justify-content-center";

      const prevButton = document.createElement('li');
      prevButton.className = `page-item ${currentPage === 1 ? "disabled" : ""}`;
      prevButton.innerHTML = `<a class="page-link" href="#" onclick="loadPage('${partType}', ${currentPage - 1})">&laquo;</a>`;

      const nextButton = document.createElement('li');
      nextButton.className = `page-item ${currentPage === totalPages ? "disabled" : ""}`;
      nextButton.innerHTML = `<a class="page-link" href="#" onclick="loadPage('${partType}', ${currentPage + 1})">&raquo;</a>`;

      pagination.appendChild(prevButton);
      pagination.appendChild(nextButton);

      container.appendChild(pagination);
    }

    function renderPartsList(partType, items, totalPages, currentPage) {
      const listContainer = document.getElementById(`${partType}`);
      listContainer.innerHTML = '';

      items.forEach(item => {
        const listItem = document.createElement('a');
        listItem.className = 'list-group-item py-3 lh-sm';
        listItem.setAttribute('data-item', JSON.stringify(item));
        listItem.innerHTML = `
          <div class="d-flex w-100 align-items-center justify-content-between">
            <strong class="mb-1">${item.name}</strong>
            <div class="button-container" style="display: flex; gap: 10px;">
              <button class="btn btn-primary btn-add" datatype="${partType}">담기</button>
            </div>
          </div>
          <div>
            ${generateItemDetails(partType, item)}
          </div>
        `;
        const button = listItem.querySelector('.btn-add');
        button.addEventListener('click', addParts);
        listContainer.appendChild(listItem);
      });
      renderPagination(partType, totalPages, currentPage, listContainer);
    }

    function fetchData() {
      const parts = [
        "caseList",
        "cpuList",
        "cpuCoolerList",
        "dataStorageList",
        "graphicsCardList",
        "memoryList",
        "motherBoardList",
        "powerSupplyList"
      ];

      parts.forEach(partType => {
        loadPage(partType, 1);
      });
    }

    window.addEventListener('load', function(){
        const ctx = document.getElementById('estimateChart');
        chart = new Chart(ctx,{
          type: 'bar',
          data: ctxData,
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'top',
              }
            }
          }
        });
        fetchData();
        if(dataId !== null){
            fetchEstimate();
        }
    });

    function updateH6Text(id, text) {
        const h6Element = document.getElementById(id);
        if (h6Element) {
            h6Element.textContent = text;
        }
    }

    function addParts(event){
        const listItem = this.closest('.list-group-item');
        const name = listItem.querySelector('.mb-1').textContent;
        const datatype = this.getAttribute('datatype');
        console.log("상품명:"+name+", 부품유형: "+datatype);
        const itemData = JSON.parse(listItem.getAttribute('data-item'));
        const extractValue = (className) => {
            const element = listItem.querySelector(`.${className}`);
            if (element) {
                const match = element.textContent.match(/\d+/);
                return match ? parseInt(match[0]) : 0;
            }
            return 0;
        };

        switch(datatype){
            case 'cpuList':
                estimate.cpu=itemData;
                updateH6Text("cpuH6",name);
                totalTdp[0] = extractValue("usageTdp");
                usageCapacity[0] = totalTdp.reduce((x, y) => x + y, 0);
                if (chart) {
                    chart.update();
                }
                break;
            case 'motherBoardList':
                estimate.motherBoard=itemData;
                updateH6Text("motherBoardH6",name);
                break;
            case 'memoryList':
                estimate.memory=itemData;
                updateH6Text("memoryH6",name);
                break;
            case 'dataStorageList':
                estimate.dataStorage=itemData;
                updateH6Text("dataStorageH6",name);
                break;
            case 'graphicsCardList':
                estimate.graphicsCard=itemData;
                updateH6Text("graphicsCardH6",name);
                totalTdp[2] = extractValue("usageTdp");
                usageCapacity[0] = totalTdp.reduce((x, y) => x + y, 0);
                usageCapacity[2] = extractValue("usageGpuSize");
                if (chart) {
                    chart.update();
                }
                break;
            case 'cpuCoolerList':
                estimate.cpuCooler=itemData;
                updateH6Text("cpuCoolerH6",name);
                totalTdp[1] = extractValue("usageTdp");
                usageCapacity[0] = totalTdp.reduce((x, y) => x + y, 0);
                usageCapacity[1] = extractValue("usageCoolerSize");
                if (chart) {
                    chart.update();
                }
                break;
            case 'powerSupplyList':
                estimate.powerSupply=itemData;
                updateH6Text("powerSupplyH6",name);
                spareCapacity[0] = extractValue("spareTdp");
                usageCapacity[3] = extractValue("usagePowerSize");
                if (chart) {
                    chart.update();
                }
                break;
            case 'caseList':
                estimate.desktopCase=itemData;
                updateH6Text("caseH6",name);
                spareCapacity[3] = extractValue("sparePowerSize");
                spareCapacity[1] = extractValue("spareCoolerSize");
                spareCapacity[2] = extractValue("spareGpuSize");
                if (chart) {
                    chart.update();
                }
                break;
            default:
                console.log("분류되지 않은 데이터");
                break;
        }
    }

    document.getElementById('submitEstimate')
    .addEventListener('click', function(event) {
        let token = window.localStorage.getItem('cuscomAccessToken');
        if (token === null) {
            alert("회원가입된 사용자만 견적을 저장할 수 있습니다.");
            return;
        }

        if (dataId != null) {
            estimate.estimateId = dataId;
        }

        const url = dataId == null ? "/CusCom/API/createEstimate" : "/CusCom/API/updateEstimate";

        fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(estimate)
        })
        .then(response => {
            if (response.ok) {
                window.location.href = '/CusCom/mainPage';
            } else {
                return response.json();
            }
        })
        .then(data => {
            if (data && data.message) {
                alert("Error: " + data.message);
            }
        })
        .catch(error => {
            console.error("Error:", error);
        });
    });

    document.getElementById('cancel')
            .addEventListener('click',function(event){
                history.go(-1);
            });