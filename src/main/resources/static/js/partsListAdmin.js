        const accessToken = window.localStorage.getItem('cuscomAccessToken');

        function fetchData() {
          const fetchRequests = [
            fetch('/CusCom/API/open/caseList').then(response => response.json()),
            fetch('/CusCom/API/open/cpuList').then(response => response.json()),
            fetch('/CusCom/API/open/cpuCoolerList').then(response => response.json()),
            fetch('/CusCom/API/open/dataStorageList').then(response => response.json()),
            fetch('/CusCom/API/open/graphicsCardList').then(response => response.json()),
            fetch('/CusCom/API/open/memoryList').then(response => response.json()),
            fetch('/CusCom/API/open/motherBoardList').then(response => response.json()),
            fetch('/CusCom/API/open/powerSupplyList').then(response => response.json())
          ];

          Promise.all(fetchRequests)
            .then(responses => {
              const caseList = document.getElementById('case-list');
              responses[0].forEach(caseItem => {
                const listItem = document.createElement('a');
                listItem.className = 'list-group-item py-3 lh-sm';
                const listItemContent = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1">${caseItem.name}</strong>
                    <div class="button-container" style="display: flex; gap: 10px;">
                      <form method="get" action="/CusCom/admin/editCase">
                        <input type="hidden" name="Case" value="${caseItem.id}">
                        <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
                      </form>
                      <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
                     </div>
                  </div>
                  <div>
                    <small class="text-body-secondary">제조사:${caseItem.manufacturer}</small>
                    <small class="text-body-secondary">구분:${caseItem.caseType}</small>
                    <small class="text-body-secondary">높이:${caseItem.height}</small>
                    <small class="text-body-secondary">길이:${caseItem.length}</small>
                    <small class="text-body-secondary">너비:${caseItem.width}</small>
                    <small class="text-body-secondary">최대파워크기:${caseItem.powerLength}</small>
                    <small class="text-body-secondary">최대cpu쿨러크기:${caseItem.cpuCoolerHeight}</small>
                    <small class="text-body-secondary">최대그래픽카드크기:${caseItem.graphicsCardLength}</small>
                  </div>
                `;
                listItem.innerHTML = listItemContent;
                caseList.appendChild(listItem);
              });

              const cpuList = document.getElementById('cpu-list');
              responses[1].forEach(cpuItem => {
                const listItem = document.createElement('a');
                listItem.className = 'list-group-item py-3 lh-sm';
                const listItemContent = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1">${cpuItem.name}</strong>
                    <div class="button-container" style="display: flex; gap: 10px;">
                      <form method="get" action="/CusCom/admin/editCPU">
                        <input type="hidden" name="CPU" value="${cpuItem.id}">
                        <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
                      </form>
                      <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
                    </div>
                  </div>
                  <div>
                    <small class="text-body-secondary">제조사:${cpuItem.manufacturer}</small>
                    <small class="text-body-secondary">소켓:${cpuItem.socket}</small>
                    <small class="text-body-secondary">내장그래픽:${cpuItem.isBuiltInGraphics}</small>
                    <small class="text-body-secondary">코어:${cpuItem.core}</small>
                    <small class="text-body-secondary">스레드:${cpuItem.thread}</small>
                    <small class="text-body-secondary">TDP:${cpuItem.tdp}</small>
                  </div>
                `;
                listItem.innerHTML = listItemContent;
                cpuList.appendChild(listItem);
              });

              const cpuCoolerList = document.getElementById('cpu-cooler-list');
              responses[2].forEach(cpuCoolerItem => {
                const listItem = document.createElement('a');
                listItem.className = 'list-group-item py-3 lh-sm';
                const listItemContent = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1">${cpuCoolerItem.name}</strong>
                    <div class="button-container" style="display: flex; gap: 10px;">
                      <form method="get" action="/CusCom/admin/editCPUCooler">
                        <input type="hidden" name="CPUCooler" value="${cpuCoolerItem.id}">
                        <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
                      </form>
                      <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
                    </div>
                  </div>
                  <div>
                    <small class="text-body-secondary">제조사:${cpuCoolerItem.manufacturer}</small>
                    <small class="text-body-secondary">냉각방식:${cpuCoolerItem.coolingType}</small>
                    <small class="text-body-secondary">높이:${cpuCoolerItem.height}</small>
                    <small class="text-body-secondary">길이:${cpuCoolerItem.length}</small>
                    <small class="text-body-secondary">너비:${cpuCoolerItem.width}</small>
                    <small class="text-body-secondary">TDP:${cpuCoolerItem.tdp}</small>
                  </div>
                `;
                listItem.innerHTML = listItemContent;
                cpuCoolerList.appendChild(listItem);
              });

              const dataStorageList = document.getElementById('data-storage-list');
              responses[3].forEach(dataStorageItem => {
                const listItem = document.createElement('a');
                listItem.className = 'list-group-item py-3 lh-sm';
                const listItemContent = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1">${dataStorageItem.name}</strong>
                    <div class="button-container" style="display: flex; gap: 10px;">
                      <form method="get" action="/CusCom/admin/editDataStorage">
                        <input type="hidden" name="DataStorage" value="${dataStorageItem.id}">
                        <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
                      </form>
                      <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
                    </div>
                  </div>
                  <div>
                    <small class="text-body-secondary">제조사:${dataStorageItem.manufacturer}</small>
                    <small class="text-body-secondary">인터페이스:${dataStorageItem.storageInterface}</small>
                    <small class="text-body-secondary">폼팩터:${dataStorageItem.formFactor}</small>
                    <small class="text-body-secondary">읽기속도:${dataStorageItem.readSpeed}</small>
                    <small class="text-body-secondary">쓰기속도:${dataStorageItem.writeSpeed}</small>
                  </div>
                `;
                listItem.innerHTML = listItemContent;
                dataStorageList.appendChild(listItem);
              });

              const graphicsCardList = document.getElementById('graphics-card-list');
              responses[4].forEach(graphicsCardItem => {
                const listItem = document.createElement('a');
                listItem.className = 'list-group-item py-3 lh-sm';
                const listItemContent = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1">${graphicsCardItem.name}</strong>
                    <div class="button-container" style="display: flex; gap: 10px;">
                      <form method="get" action="/CusCom/admin/editGraphicsCard">
                        <input type="hidden" name="GraphicsCard" value="${graphicsCardItem.id}">
                        <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
                      </form>
                      <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
                    </div>
                  </div>
                  <div>
                    <small class="text-body-secondary">제조사:${graphicsCardItem.manufacturer}</small>
                    <small class="text-body-secondary">GPU:${graphicsCardItem.gpuType}</small>
                    <small class="text-body-secondary">길이:${graphicsCardItem.length}</small>
                    <small class="text-body-secondary">기본전력:${graphicsCardItem.basicPower}</small>
                    <small class="text-body-secondary">최대전력:${graphicsCardItem.maxPower}</small>
                  </div>
                `;
                listItem.innerHTML = listItemContent;
                graphicsCardList.appendChild(listItem);
              });

              const memoryList = document.getElementById('memory-list');
              responses[5].forEach(memoryItem => {
                const listItem = document.createElement('a');
                listItem.className = 'list-group-item py-3 lh-sm';
                const listItemContent = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                      <strong class="mb-1">${memoryItem.name}</strong>
                      <div class="button-container" style="display: flex; gap: 10px;">
                        <form method="get" action="/CusCom/admin/editMemory">
                          <input type="hidden" name="Memory" value="${memoryItem.id}">
                          <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
                        </form>
                        <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
                      </div>
                    </div>
                    <div>
                      <small class="text-body-secondary">제조사:${memoryItem.manufacturer}</small>
                      <small class="text-body-secondary">타입:${memoryItem.type}</small>
                      <small class="text-body-secondary">용량:${memoryItem.capacity}</small>
                      <small class="text-body-secondary">높이:${memoryItem.height}</small>
                    </div>
                `;
                listItem.innerHTML = listItemContent;
                memoryList.appendChild(listItem);
              });

              const motherBoardList = document.getElementById('mother-board-list');
              responses[6].forEach(motherBoardItem => {
                const listItem = document.createElement('a');
                listItem.className = 'list-group-item py-3 lh-sm';
                const listItemContent = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1">${motherBoardItem.name}</strong>
                    <div class="button-container" style="display: flex; gap: 10px;">
                      <form method="get" action="/CusCom/admin/editMotherBoard">
                        <input type="hidden" name="MotherBoard" value="${motherBoardItem.id}">
                        <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
                      </form>
                      <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
                    </div>
                  </div>
                  <div>
                    <small class="text-body-secondary">제조사:${motherBoardItem.manufacturer}</small>
                    <small class="text-body-secondary">구분:${motherBoardItem.cpuType}</small>
                    <small class="text-body-secondary">소켓:${motherBoardItem.socket}</small>
                    <small class="text-body-secondary">메모리타입:${motherBoardItem.memoryType}</small>
                    <small class="text-body-secondary">메모리슬롯:${motherBoardItem.memorySlot}</small>
                  </div>
                `;
                listItem.innerHTML = listItemContent;
                motherBoardList.appendChild(listItem);
              });

              const powerSupplyList = document.getElementById('power-supply-list');
              responses[7].forEach(powerSupplyItem => {
                const listItem = document.createElement('a');
                listItem.className = 'list-group-item py-3 lh-sm';
                const listItemContent = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1">${powerSupplyItem.name}</strong>
                    <div class="button-container" style="display: flex; gap: 10px;">
                      <form method="get" action="/CusCom/admin/editPowerSupply">
                        <input type="hidden" name="PowerSupply" value="${powerSupplyItem.id}">
                        <button class="btn btn-primary" type="submit" style="border: none;">edit</button>
                      </form>
                      <button class="btn btn-primary" style="background-color: red; color: white; border: none;" onclick="deleteItem(this)">delete</button>
                    </div>
                  </div>
                  <div>
                    <small class="text-body-secondary">제조사:${powerSupplyItem.manufacturer}</small>
                    <small class="text-body-secondary">모듈러:${powerSupplyItem.modular}</small>
                    <small class="text-body-secondary">크기:${powerSupplyItem.length}</small>
                    <small class="text-body-secondary">전력:${powerSupplyItem.power}</small>
                    <small class="text-body-secondary">효율:${powerSupplyItem.efficiency}</small>
                  </div>
                `;
                listItem.innerHTML = listItemContent;
                powerSupplyList.appendChild(listItem);
              });
            })
            .catch(error => console.error(error));
        }

        window.onload = fetchData;


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
          fetch("/CusCom/admin/API/deleteParts", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              'Authorization': `Bearer ${accessToken}`
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