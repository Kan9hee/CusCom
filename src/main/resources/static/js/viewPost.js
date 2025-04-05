        const queryParams = new URLSearchParams(window.location.search);
        const postId = queryParams.get("estimateID");

        function initialSetup(){
            fetch(`/CusCom/API/open/loadPost?id=${postId}`)
            .then(response => response.json())
            .then(data => {
                const postData=data.post;
                const commentList=data.commentList;
                const postEstimate=data.postEstimate;

                const postTitle = document.getElementById('postTitle');
                const postUserName = document.getElementById('postUserName');
                const postTags = document.getElementById('postTags');
                const postViewCount = document.getElementById('postViewCount');
                const postLikeCount = document.getElementById('postLikeCount');
                postTitle.textContent = postData.title;
                postUserName.textContent = postData.userName;
                const tagElements = postData.tags.map(tag => {
                  const tagElement = document.createElement('small');
                  tagElement.className = 'text-body-secondary';
                  tagElement.textContent = `#${tag} ` ;
                  return tagElement;
                });
                postTags.innerHTML = '';
                tagElements.forEach(tagElement => {
                  postTags.appendChild(tagElement);
                });
                postViewCount.textContent = `조회수: ${postData.viewCount}`;
                postLikeCount.textContent = `추천수: ${postData.likeCount}`;

                updateH6Text("cpuH6",postEstimate.cpu);
                updateH6Text("motherBoardH6",postEstimate.motherBoard);
                updateH6Text("memoryH6",postEstimate.memory);
                updateH6Text("dataStorageH6",postEstimate.dataStorage);
                updateH6Text("graphicsCardH6",postEstimate.graphicsCard);
                updateH6Text("cpuCoolerH6",postEstimate.cpuCooler);
                updateH6Text("powerSupplyH6",postEstimate.powerSupply);
                updateH6Text("caseH6",postEstimate.desktopCase);

                const commentsContainer = document.getElementById('comments');
                commentList.forEach(comment => {
                  const listItem = document.createElement('a');
                  listItem.className = 'list-group-item list-group-item-action py-3 lh-sm disabled';
                  const listItemContent = `
                    <div class="d-flex w-100 align-items-center justify-content-between">
                      <strong class="mb-1" style="color: white;">${comment.userName}</strong>
                    </div>
                    <div class="col-10 mb-1 small">${comment.content}</div>
                  `;
                  listItem.innerHTML = listItemContent;
                  commentsContainer.appendChild(listItem);
                });
            })
            .catch(error => console.error(error));
        }

        window.addEventListener('load', function(){ initialSetup(); });

        function updateH6Text(id,text) {
            const h6Element = document.getElementById(id);
            if (h6Element) {
                h6Element.textContent = text;
            }
        }

        document.getElementById('confirmComment')
            .addEventListener('click',function(event){
                const accessToken = window.localStorage.getItem('cuscomAccessToken');
                const commentData={
                    _id:"",
                    postID:postId,
                    userName: "",
                    content: document.getElementById('commentInputContent').value
                };

                fetch("/CusCom/API/uploadComment", {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${accessToken}`
                    },
                    body: JSON.stringify(commentData)
                })
                .then(response => {
                    if(response.ok){ location.reload(); }
                    else{ return response.json(); }
                })
                .then(data => {
                    alert("Error: " + data.message);
                })
                .catch(error => {
                  console.error("Error:", error);
                });
            });