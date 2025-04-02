function setActive(element) {
    // 1. 모든 nav-link 요소들을 찾아옴
    const links = document.querySelectorAll('.nav-link');

    // 2. 각 요소에서 'active' 클래스를 제거 (기존 active 제거)
    links.forEach(link => link.classList.remove('active'));

    // 3. 클릭된 요소에만 'active' 클래스 추가
    element.classList.add('active');
}

//내정보
function loadUserInfo() {
	fetch("/users/mypageData")
	.then(result=>result.json())
	.then(user=> {

        const loginType = user.sns === 0 ? "일반": "SNS";

		 document.getElementById('mainContents').innerHTML = `
         <div style="padding: 20px; background-color: #f9f9f9; border-radius: 8px; width: 600px; margin: 20px auto;">
                <h2>내 정보</h2>
                <p><strong>아이디:</strong> ${user.userId}</p>
                <p><strong>이름:</strong>${user.userName}</p>
                <p><strong>이메일:</strong>${user.userEmail}</p>
                <p><strong>폰번호:</strong>${user.userPhone}</p>
                <p><strong>등급:</strong> ${user.userGrade}</p>
                <p><strong>가입일:</strong> ${user.registDate}</p>
                <p><strong>로그인형태:</strong> ${loginType}</p>

                 <input type="button" id="editBtn" class="btn btn-primary" value="수정">
                  <input type="button" id="deleteBtn" class="btn btn-danger" value="탈퇴">
            </div>
                `;

                document.getElementById('editBtn').addEventListener('click', ()=> {
                    editUserInfo(user);
                });

                document.getElementById('deleteBtn').addEventListener('click', ()=> {
                    delteUserInfo(user.userId);
                });
	})
	.catch(error=>{
	alert(error.message);
	})
}

function editUserInfo(user){

        document.getElementById("mainContents").innerHTML=`
        <div style="padding: 20px; background-color: #f9f9f9; border-radius: 8px; width: 600px; margin: 20px auto;">
            <h2>정보 수정하기</h2>
            <form>

                <input type="hidden" id="userId" name ="userId" value=${user.userId}>
                <div class="mb-3">
                <label for="userName" class="form-label"><p><strong>이름:</strong></p></label>
                <input type="text" class="form-label" id="userName" name="userName" value=${user.userName}>
                </div>
                
                 <div class="mb-3">
                <label for="userEmail" class="form-label"><p><strong>이메일:</strong></p></label>
                <input type="text" class="form-label" id="userEmail" name="userEmail" value=${user.userEmail}>
                </div>

                 <div class="mb-3">
                <label for="userPhone" class="form-label"><p><strong>폰번호:</strong></p></label>
                <input type="text" class="form-label" id="userPhone" name="userPhone" value=${user.userPhone}>
                </div>

                 <div class="mb-3">
                <label for="userPw" class="form-label"><p><strong>비밀번호:</strong></p></label>
                <input type="password" class="form-label" id="userPw" name="userPw" value=${user.userPw}>
                </div>

                <input type="button" id="savebtn" class="btn btn-success" value="저장">
                <input type="button" id="cancelbtn" class="btn btn-secondary" value="취소">
            </form>
        </div>
        `
    

        document.getElementById("savebtn").addEventListener("click",update)

        document.getElementById("cancelbtn").addEventListener("click",loadUserInfo)
}

function update() {
    const userId = document.getElementById("userId");
    const userName = document.getElementById("userName");
    const userEmail = document.getElementById("userEmail");
    const userPhone = document.getElementById("userPhone");
    const userPw = document.getElementById("userPw");


    let param = new URLSearchParams();

    param.append("userId", userId.value);
    param.append("userName",userName.value);
    param.append("userEmail",userEmail.value);
    param.append("userPhone",userPhone.value);
    param.append("userPw",userPw.value);

     fetch(`/users/update`,{
        method: 'POST',
        headers:{
            "Content-type":"application/x-www-form-urlencoded"
        },
        body: param.toString()
    })
    .then(result => result.json())
    .then(user=>{
        console.log(user);
        if(!user){
            alert("수정실패.");
            return;
        } 
            alert("수정되었습니다")
            loadUserInfo();
       
    })
    .catch(error=>{
        alert(error.message);
    })

}

function delteUserInfo(userId) {
    let con = confirm("정말 탈퇴하시겠습니까?");

    if (!con) {
        return;
    }

    fetch(`/users/delete?userId=${userId}`, {
        method: 'POST',
        headers:{
            "Content-type":"application/x-www-form-urlencoded"
        }
    })
    .then(result=>result.text())
    .then(result=>{
            if(result==='1'){
                alert('탈퇴되었습니다.')
                location.reload();
            } else {
                alert("취소되었습니다.");
            }
    })
    .catch(error=>{
        alert(error.message);
    })
}

//결제내역
function loadPayInfo(page=1) {
    fetch(`/users/paymentList?page=${page}`)
    .then(result=>result.json())
    .then(p=>{
        console.log(p)
        let paymentHtml = '';

        paymentHtml += `
        <table style="width: 600px; border-collapse: collapse;">
                <thead>
                    <tr style="background-color: #ddd; text-align: left;">
                        <th style="padding: 10px; border-bottom: 2px solid #bbb;">결제금액</th>
                        <th style="padding: 10px; border-bottom: 2px solid #bbb;">결제승인여부</th>
                    </tr>
                </thead>`
        p.list.forEach(item=>{
            paymentHtml += `
   
                <tbody>
                        <tr style="background-color: #f9f9f9;">
                            <td style="padding: 10px; border-bottom: 1px solid #ddd;">${item.payAmounts}</td>
                            <td style="padding: 10px; border-bottom: 1px solid #ddd;">${item.payCheck == 0 ? "<span style='color: red;'>미승인</span>": item.payCheck == 1 ? "<span style='color: blue;'>승인</span>":"<span style='color: green;'>환불</span>"}</td>
                        </tr>
                </tbody>
            `
        })
        paymentHtml += `</table>`

        let pager = p.pager;
        paymentHtml += `
                <div class="pagination-wrapper" style="width: 600px; margin: 20px auto;">
            <nav aria-label="Page navigation example">
            <ul class="pagination">
                
                <li class="page-item"><button class="page-link paymentPages" data-page-num="${pager.start-1}">Previous</button></li>
        `
        for(let i = pager.start; i<=pager.end;i++){

            paymentHtml += `
            <li class="page-item"><button class="page-link paymentPages" data-page-num="${i}">${i}</button></li>
            `
        }

        paymentHtml += `
               <li class="page-item ${pager.endCheck?'disabled':''}"><button class="page-link paymentPages" data-page-num="${pager.end+1}">Next</button></li>
            </ul>
        </nav>
        </div>
        `
        
        document.getElementById('mainContents').innerHTML = paymentHtml;

    })
}

mainContents.addEventListener("click",(e)=>{
    let page = e.target.closest(".paymentPages");

    if(page){
        let pageNum = page.getAttribute("data-page-num");

       if(pageNum){
        loadPayInfo(pageNum);
       }
    }
})

//예매내역
function loadBookInfo(page=1) {
    fetch(`/users/bookList?page=${page}`)
    .then(result=>result.json())
    .then(b=>{
        console.log(b)
        let bookHtml = '';

        bookHtml += '<div style="display: flex; flex-wrap: wrap; gap: 20px;">'
        b.list.forEach(item=>{
            bookHtml += `
                <div style="width: 250px; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); background: #fff;">
                    <img src="${item.theaterDTO.movieDTO.shortPoster}" alt="${item.theaterDTO.movieDTO.movieTitle}" onclick="bookDetail(${item.bookId})" style="width: 100%; height: 150px; object-fit: cover;cursor: pointer;">
                    <div style="padding: 15px;">
                        <h3 style="font-size: 16px; margin: 0 0 10px;">${item.theaterDTO.movieDTO.movieTitle}</h3>
                        <p style="color: #555; font-size: 14px; margin: 0;">예매 날짜: ${item.bookDate}</p>
                    </div>
                </div>
            `
        })
        bookHtml += `</div>`

        let pager = b.pager;
        bookHtml += `
                <div class="pagination-wrapper" style="width: 600px; margin: 20px auto;">
            <nav aria-label="Page navigation example">
            <ul class="pagination">
                
                <li class="page-item"><button class="page-link bookPages" data-page-num="${pager.start-1}">Previous</button></li>
        `
        for(let i = pager.start; i<=pager.end;i++){

            bookHtml += `
            <li class="page-item"><button class="page-link bookPages" data-page-num="${i}">${i}</button></li>
            `
        }

        bookHtml += `
               <li class="page-item ${pager.endCheck?'disabled':''}"><button class="page-link bookPages" data-page-num="${pager.end+1}">Next</button></li>
            </ul>
        </nav>
        </div>
        `
        
        document.getElementById('mainContents').innerHTML = bookHtml;
    })
}

mainContents.addEventListener("click",(e)=>{
    let page = e.target.closest(".bookPages");

    if(page){
        let pageNum = page.getAttribute("data-page-num");

       if(pageNum){
        loadBookInfo(pageNum);
       }
    }
})

function bookDetail(bookId){
    fetch(`/users/bookDetail?bookId=${bookId}`)
    .then(r=>r.json())
    .then(b=>{
        console.log(b)
        document.getElementById('mainContents').innerHTML=`
            <div style="width: 600px; margin: auto; background: #fff; border-radius: 10px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); text-align: center;">
                <img src="${b.theaterDTO.movieDTO.longPoster}" alt="${b.theaterDTO.movieDTO.movieTitle}" style="width: 100%; height: 350px; object-fit: cover;">
                
                <div style="padding: 20px; text-align: left;">
                    <h2 style="margin: 0 0 10px; font-size: 1.5em; color: #333;">${b.theaterDTO.movieDTO.movieTitle}</h2>

                    <p style="margin: 5px 0;"><strong>영화관:</strong> ${b.theaterDTO.theaterName}</p>
                    <p style="margin: 5px 0;"><strong>좌석:</strong> ${b.theaterDTO.seatDTO.seat}</p>
                    <p style="margin: 5px 0;"><strong>날짜:</strong> ${b.bookDate}</p>

            </div>
        `;
    })
}

//포인트
function loadPoint() {

}

//관람평
function loadReview(page=1) {
    let kind = document.querySelector("select[name='kind']")?.value || "k1"; 
    let search = document.querySelector("input[name='search']")?.value || "";

    fetch(`/users/reviewList?page=${page}&kind=${kind}&search=${encodeURIComponent(search)}`)
    .then(result=>result.json())
    .then(r=>{
 
        let reviewHtml = '';
        let pager = r.pager || { search: "", start: 1, end: 1, endCheck: true };

        reviewHtml += `
        <form id="list_form" action="/user/reviewList" method="GET" style="width: 600px; margin: 20px auto;">
            <input type="hidden" name="page" id="pageNum">
            <div class="row mb-3">
            <div class="col-md-3">
                <label class="visually-hidden" for="inlineFormSelectPref">Preference</label>
                <select class="form-select" name="kind" id="inlineFormSelectPref">
                <option value="k1">리뷰내용</option>
                <option value="k2">등록날짜</option>
                </select>
            </div>
            <div class="col-md-6">
                <label class="visually-hidden" for="inlineFormInputGroupUsername"></label>
                <input type="text" name="search" value="${pager.search}" class="form-control" id="inlineFormInputGroupUsername" placeholder="검색어를 입력하세요">
            </div>
            
            <div class="col-md-3">
                <button type="submit" class="btn btn-primary">검색</button>
            </div>
            
            </div>
        </form>
        <table style="width: 600px; border-collapse: collapse;">
                <thead>
                    <tr style="background-color: #ddd; text-align: left;">
                        <th style="padding: 10px; border-bottom: 2px solid #bbb;">리뷰내용</th>
                        <th style="padding: 10px; border-bottom: 2px solid #bbb;">등록날짜</th>
                    </tr>
                </thead>`
        r.list.forEach(item=>{
            reviewHtml += `
   
                <tbody>
                        <tr style="background-color: #f9f9f9;">
                            <td style="padding: 10px; border-bottom: 1px solid #ddd; cursor: pointer;" onclick="reviewDetail(${item.reviewId})">${item.reviewContents}</td>
                            <td style="padding: 10px; border-bottom: 1px solid #ddd;">${item.reviewDate}</td>
                        </tr>
                </tbody>
            `
        })
        reviewHtml += `</table>`

        reviewHtml += `
                <div class="pagination-wrapper" style="width: 600px; margin: 20px auto;">
            <nav aria-label="Page navigation example">
            <ul class="pagination">
                
                <li class="page-item"><button class="page-link reviewPages" data-page-num="${pager.start-1}">Previous</button></li>
        `
        for(let i = pager.start; i<=pager.end;i++){

            reviewHtml += `
            <li class="page-item"><button class="page-link reviewPages" data-page-num="${i}">${i}</button></li>
            `
        }

        reviewHtml += `
               <li class="page-item ${pager.endCheck?'disabled':''}"><button class="page-link reviewPages" data-page-num="${pager.end+1}">Next</button></li>
            </ul>
        </nav>
        </div>
        `
        
        document.getElementById('mainContents').innerHTML = reviewHtml;
    })
}

mainContents.addEventListener("submit", function (e) {
    if (e.target && e.target.id === "list_form") {
        e.preventDefault(); 
        loadReview(); 
    }
});

mainContents.addEventListener("click",(e)=>{
    let page = e.target.closest(".reviewPages");

    if(page){
        let pageNum = page.getAttribute("data-page-num");

       if(pageNum){
        loadReview(pageNum);
       }
    }
})

function reviewDetail(reviewId) {
    fetch(`/users/reviewDetail?reviewId=${reviewId}`)
    .then(r=>r.json())
    .then(r=>{
        
        document.getElementById('mainContents').innerHTML=`
          
			    <table style="width: 600px; border-collapse: collapse;">
			      <thead>
			        <tr style="background-color: #ddd; text-align: left;">
			          <th scope="col">작성일</th>
			          <th scope="col">별점</th>
			          <th scope="col">컨텐츠id</th>
			          <th scope="col">종류</th>
			        </tr>
			        <tr style="background-color: #f9f9f9;">
			          <td>${r.reviewDate}</td>
			          <td>${r.reviewRate}</td>
			          <td>${r.contentId}</td>
			          <td>${r.kind}</td>
			        </tr>
			      </thead>
			    </table>
                <br>
			    <div class="form-group" style="width: 100%;">
				  <label for="reviewContents">내용</label>
				  <div id="reviewContents" class="form-control" style="width: 100%; font-size: 1.1em; min-height: 300px;">
				    ${r.reviewContents}
				  </div>
				</div>
        `
    })
}

//쿠폰
function loadCoupon() {
    fetch("/users/couponList")
    .then(result=>result.json())
    .then(c=>{

        let couponHtml = '';

        c.forEach(item =>{
            couponHtml+= `
            
            <div style="padding: 20px; background-color: #f9f9f9; border-radius: 8px; width: 600px; margin: 20px auto;">
                <input type="hidden" name="couponNum" value="${item.couponDTO.couponNum}"> 
                <p><strong>쿠폰이름:</strong>${item.couponDTO.couponName}</p>
                <p><strong>쿠폰금액:</strong>${item.couponDTO.couponCost}</p> 
            </div>
            `          
        })

        couponHtml += `<input type="button" id="couponbtn" class="btn btn-success" value="등록"></input>`

        document.getElementById('mainContents').innerHTML = couponHtml;

        document.getElementById('couponbtn').addEventListener('click', ()=> {
            couponRegister();
        });
    })
}


function couponRegister() {
    const couponCode = prompt("쿠폰 코드를 입력하세요:");

    if (!couponCode || couponCode.trim() === "") {
        return;
    }

    // encodeURIComponent->
    // 쿠폰 코드(couponCode) 같은 문자열을 URL 파라미터로 보낼 때, 
    // 공백이나 특수문자가 포함되어 있으면 올바르게 전달되지 않을 수 있기 때문
    fetch(`/users/getCouponByCode?couponCode=${encodeURIComponent(couponCode.trim())}`)
        .then(response => response.json())
        .then(coupon => {
            if (!coupon || !coupon.couponNum) {
                return;
            }

            const couponNum = coupon.couponNum;

            // 쿠폰 등록 요청
            fetch(`/users/couponAdd`, {
                method: "POST",
                headers: {
                    "Content-type": "application/x-www-form-urlencoded"
                },
                body: `couponNum=${couponNum}&couponCode=${encodeURIComponent(couponCode)}`
            })
            .then(response => response.json())
            .then(result => {
                if (result > 0) {
                    alert("쿠폰이 등록되었습니다");
                    location.reload();
                } else {
                    alert("쿠폰 등록 실패");
                }
            })
            .catch(error => {
                console.error("쿠폰 등록 실패:", error);
                alert("서버 오류가 발생했습니다.");
            });
        })
        .catch(error => {
            console.error("쿠폰 조회 실패:", error);
            alert("쿠폰 정보를 가져올 수 없습니다.");
        });
}