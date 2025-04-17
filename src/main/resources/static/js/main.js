    const queryParams = new URLSearchParams(window.location.search);
    let searchOption = queryParams.get("option") || "title";
    let searchData = queryParams.get("keyword");
    let currentPage = queryParams.get("page") || 1;
    let searchJson = null;

    function fetchPage(){
        const url = new URL(window.location.href);
        const queryParams = new URLSearchParams();
        queryParams.append("option", searchOption);
        if (searchData != null) {
          queryParams.append("keyword", searchData);
        }
        queryParams.append("maxContent", 9);
        queryParams.append("page", currentPage);

        fetch(`/CusCom/API/open/searchPost?${queryParams.toString()}`)
        .then(response => response.json())
        .then(data => {
          const basicPath = url.pathname + (url.search === "" ? "?" : url.search+"&");
          const postList = document.getElementById('postList');
          const posts = data["postList"];
          const pageCount = data["pageCount"];
          currentPage = data["page"];
          const startAtList = ((pageCount-2) < currentPage) ? 1 : (pageCount-2);
          const endAtList = ((currentPage+2) > pageCount) ? pageCount : (currentPage+2);

          if(searchData!=null){
            const searchResultText = document.getElementById('searchResultText')
            searchResultText.innerText=searchData+" 검색 결과";
            searchResultText.style.textAlign = "center";
          }

          posts.forEach((post, postIndex) => {
            const postCard = document.createElement('div');
            postCard.classList.add('col');
            postCard.innerHTML = `
              <div class="card shadow-sm" id="postCard" data-post-id="${postIndex}">
                <img class="card-img-top" src="${post.thumbnail}" alt="Post Thumbnail" style="width: 100%; height: 225px;">
                <div class="card-body">
                  <h3>${post.title}</h3>
                  <p class="card-text">${post.userName}</p>
                  <small>${post.tags.length > 0 ? post.tags.map(tag=>`#${tag}`).join(' ') : '태그 없음'}</small>
                  <div class="d-flex justify-content-between align-items-center">
                    <div>
                      <small class="text-body-secondary">조회수: ${post.viewCount}</small>
                      <small class="text-body-secondary">추천수: ${post.likeCount}</small>
                    </div>
                  </div>
                </div>
              </div>
            `;
            postCard.addEventListener('click',()=>{
              window.location.href=`/CusCom/post?postID=${post._id}`;
            });
            postList.appendChild(postCard);
          });

          const pagination = document.querySelector('.pagination');
          const previousPageLink = document.createElement('li');
          if(currentPage == startAtList)
            previousPageLink.classList.add('page-item', 'disabled');
          else
            previousPageLink.classList.add('page-item');
          previousPageLink.innerHTML = `<a class="page-link" href=${basicPath}page=${startAtList}><<</a>`;
          pagination.appendChild(previousPageLink);

          for (let page = startAtList; page <= endAtList; page++) {
            const pageLink = document.createElement('li');
            pageLink.classList.add('page-item');
            if (page == currentPage) {
              console.log(page);
              pageLink.innerHTML = `
                <li class="page-item active" aria-current="page">
                  <a class="page-link" href=${basicPath}page=${page}>${page}</a>
                </li>
              `;
            }else{
              pageLink.innerHTML = `<a class="page-link" href=${basicPath}page=${page}>${page}</a>`;
            }
            pagination.appendChild(pageLink);
          }

          const nextPageLink = document.createElement('li');
          if(currentPage == endAtList)
            nextPageLink.classList.add('page-item', 'disabled');
          else
            nextPageLink.classList.add('page-item');
          nextPageLink.innerHTML = `<a class="page-link" href=${basicPath}page=${endAtList}>>></a>`;
          pagination.appendChild(nextPageLink);
        })
        .catch(error => console.error('Error fetching data:', error));
    }

    window.addEventListener('load', function(){ fetchPage(); });

    document.querySelectorAll('.dropdown-item').forEach(item => {
      item.addEventListener('click', function() {
        searchOption = this.getAttribute('value');
        document.querySelector('.dropdown-toggle').textContent=this.textContent;
      });
    });

    document.getElementById('search').addEventListener('click', function(event) {
      event.preventDefault();
      searchData = document.getElementById('keyword').value;
      const queryParams = new URLSearchParams();
      queryParams.set("option", searchOption);
      queryParams.set("keyword", searchData);
      window.location.href = `/CusCom/mainPage?${queryParams.toString()}`;
    });
