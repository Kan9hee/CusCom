  const currentPageMap = {};

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
            <small class="text-body-secondary">최대 파워 크기: ${item.powerLength} mm</small>
            <small class="text-body-secondary">최대 CPU 쿨러 크기: ${item.cpuCoolerHeight} mm</small>
            <small class="text-body-secondary">최대 그래픽카드 크기: ${item.graphicsCardLength} mm</small>
          `;
        case "cpuList":
          return `
           <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
           <small class="text-body-secondary">소켓: ${item.socket}</small>
           <small class="text-body-secondary">내장그래픽: ${item.isBuiltInGraphics}</small>
           <small class="text-body-secondary">코어 수: ${item.core}</small>
           <small class="text-body-secondary">쓰레드 수: ${item.thread}</small>
           <small class="text-body-secondary">TDP: ${item.tdp}</small>
          `;
        case "cpuCoolerList":
          return `
            <small class="text-body-secondary">제조사: ${item.manufacturer}</small>
            <small class="text-body-secondary">냉각방식: ${item.coolingType}</small>
            <small class="text-body-secondary">높이: ${item.height}</small>
            <small class="text-body-secondary">길이: ${item.length}</small>
            <small class="text-body-secondary">너비: ${item.width}</small>
            <small class="text-body-secondary">TDP: ${item.tdp}</small>
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
            <small class="text-body-secondary">길이: ${item.length}</small>
            <small class="text-body-secondary">기본 전력: ${item.basicPower}</small>
            <small class="text-body-secondary">최대 전력: ${item.maxPower}</small>
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
            <small class="text-body-secondary">크기: ${item.length}</small>
            <small class="text-body-secondary">전력: ${item.power}</small>
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

      const editActionMap = {
        caseList: "editCase",
        cpuList: "editCpu",
        gpuList: "editGpu",
        dataStorageList: "editDataStorage",
        graphicsCardList: "editGraphicsCard",
        memoryList: "editMemory",
        motherBoardList: "editMotherBoard",
        powerSupplyList: "editPowerSupply"
      };

      const editAction = editActionMap[partType];

      items.forEach(item => {
        const listItem = document.createElement('a');
        listItem.className = 'list-group-item py-3 lh-sm';
        listItem.innerHTML = `
          <div class="d-flex w-100 align-items-center justify-content-between">
            <strong class="mb-1">${item.name}</strong>
            <div class="button-container" style="display: flex; gap: 10px;">
              <form method="get" action="/CusCom/admin/${editAction}">
                <input type="hidden" name="${partType}" value="${item.id}">
                <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
              </form>
              <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
            </div>
          </div>
          <div>
            ${generateItemDetails(partType, item)}
          </div>
        `;
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


        window.addEventListener("load", () => {
          if (typeof window.adminCheck === "function") {
            window.adminCheck().then(isAdmin => {
              if (isAdmin) {
                fetchData();
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

        function deleteItem(button) {
          const listItem = button.closest('a.list-group-item');
          const targetName = listItem.querySelector('strong.mb-1')?.textContent.trim();
          const typeInput = listItem.querySelector('form input[type="hidden"]');
          const type = typeInput?.getAttribute('name');
          if (!targetName || !type) {
            alert("삭제 정보를 찾을 수 없습니다.");
            return;
          }

          const deleteData = {
            targetName: targetName,
            type: type
          };
          fetch("/CusCom/API/admin/deleteParts", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              'Authorization': `Bearer ${window.localStorage.getItem('cuscomAccessToken')}`
            },
            body: JSON.stringify(deleteData)
          })
          .then(response => {
            if (response.ok) {
              listItem.remove();
            } else {
              return response.json().then(data => {
                alert("삭제 실패: " + (data.message || "Unknown error"));
              });
            }
          })
          .catch(error => {
            console.error("Error:", error);
            alert("서버 오류로 삭제 실패");
          });
        }