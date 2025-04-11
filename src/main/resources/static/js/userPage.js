        window.addEventListener("load", () => {
          if (typeof window.adminCheck === "function") {
            window.adminCheck().then(isAdmin => {
              if (isAdmin) {
              const adminArea = document.getElementById("admin-area");
              const adminButton = document.createElement("a");
              adminButton.href = "/CusCom/admin/main";
              adminButton.textContent = "어드민 페이지";
              adminArea.appendChild(adminButton);
              }
            });
          } else {
            console.error("adminCheck 함수가 정의되지 않았습니다.");
            window.location.href = '/CusCom/mainPage'
          }
        });

        function fetchData() {
          fetch('/CusCom/API/getUserEstimateList', {
            method: "GET",
            headers: {
              'Authorization': `Bearer ${window.localStorage.getItem('cuscomAccessToken')}`
            }
          })
            .then(response => response.json())
            .then(data => {
              const estimateList = document.getElementById('estimate-list');
              estimateList.innerHTML = '';

              data.forEach(estimateItem => {
                const listItem = document.createElement('div');
                listItem.className = 'list-group-item py-3 lh-sm';

                const postButtonContent = estimateItem.posted ?
                  `<button class="btn btn-primary" style="background-color: red; border-color: red;" type="submit" disabled>게시됨</button>` :
                  `<button class="btn btn-primary" type="submit" onclick="postEstimate(this)">게시</button>`;

                const editButtonContent = estimateItem.posted ? '' :
                  `<button class="btn btn-primary" type="submit" onclick="callEstimateEditPage(this)">편집</button>`;

                const deleteButtonContent = `
                  <button class="btn btn-primary" style="background-color: red; border-color: red;" type="submit" onclick="deleteEstimate(this)">삭제</button>`;

                listItem.innerHTML = `
                  <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1">${estimateItem._id}</strong>
                    <div class="button-container">
                      ${postButtonContent}
                      ${editButtonContent}
                      ${deleteButtonContent}
                    </div>
                  </div>
                  <div>
                    <small class="text-body-secondary">${estimateItem.cpu}</small>
                    <small class="text-body-secondary">${estimateItem.motherBoard}</small>
                    <small class="text-body-secondary">${estimateItem.graphicsCard}</small>
                    <small class="text-body-secondary">${estimateItem.powerSupply}</small>
                  </div>
                `;

                estimateList.appendChild(listItem);
              });
            })
            .catch(error => {
              console.error(error);
              window.location.href = '/CusCom/mainPage';
            });
        }

        window.addEventListener('load', fetchData);

        function getEstimateID(button) {
          const mbElement = button.closest(".list-group-item").querySelector(".mb-1");
          return mbElement ? mbElement.textContent : null;
        }

        function callEstimateEditPage(button) {
          const dataId = getEstimateID(button);
          window.location.href = `/CusCom/estimatePage?id=${dataId}`;
        }

        function postEstimate(button) {
          const dataId = getEstimateID(button);
          window.location.href = `/CusCom/uploadPostPage?id=${dataId}`;
        }

        function deleteEstimate(button) {
          const dataId = getEstimateID(button);
          fetch("/CusCom/API/deleteEstimate", {
            method: "POST",
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${window.localStorage.getItem('cuscomAccessToken')}`
            },
            body: JSON.stringify(dataId)
          })
          .then(response => {
            if (response.ok) { location.reload(); }
            else { return response.json(); }
          })
          .then(data => {
            if (data) alert("Error: " + data.message);
          })
          .catch(error => {
            console.error("Error:", error);
          });
        }