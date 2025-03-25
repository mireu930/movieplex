

function setActive(element) {
    // 1. 모든 nav-link 요소들을 찾아옴
    const links = document.querySelectorAll('.nav-link');

    // 2. 각 요소에서 'active' 클래스를 제거 (기존 active 제거)
    links.forEach(link => link.classList.remove('active'));

    // 3. 클릭된 요소에만 'active' 클래스 추가
    element.classList.add('active');
}

function loadUserInfo() {
	fetch("/users/mypageData")
	.then(result=>result.json())
	.then(user=> {
        console.log(user);

        const loginType = user.sns === 0 ? "일반": "SNS";

		 document.getElementById('mainContents').innerHTML = `
         <div style="padding: 20px; background-color: #f9f9f9; border-radius: 8px; width: 600px; margin: 20px auto;">
                <h2>내 정보</h2>
                <p><strong>아이디:</strong> ${user.userId}</p>
                <p><strong>이름:</strong><span id="displayUserName">${user.userName}</span></p>
                <p><strong>이메일:</strong><span id="displayUserEmail">${user.userEmail}</span></p>
                <p><strong>폰번호:</strong><span id="displayUserPhone">${user.userPhone}</span></p>
                <p><strong>등급:</strong> ${user.userGrade}</p>
                <p><strong>가입일:</strong> ${user.registDate}</p>
                <p><strong>로그인형태:</strong> ${loginType}</p>

                 <input type="button" id="editBtn" class="btn btn-primary" value="수정">
                  <input type="button" id="deleteBtn" class="btn btn-danger" value="탈퇴">
            </div>
                `;

                document.getElementById('editBtn').addEventListener('click', ()=> {
                    editUserInfo(user.userId);
                });

                document.getElementById('deleteBtn').addEventListener('click', ()=> {
                    delteUserInfo(user.userId);
                });
	})
	.catch(error=>{
	alert(error.message);
	})
}

function editUserInfo(userId){
    fetch(`/users/mypageData?userId=${userId}`)
    .then(result => result.json())
    .then(user=> {
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
        document.getElementById("userName").innerText = user.userName;
        document.getElementById("userEmail").innerText = user.userEmail;
        document.getElementById("userPhone").innerText = user.userPhone;

        document.getElementById("savebtn").addEventListener("click",()=>{
            update()
        })
        document.getElementById("cancelbtn").addEventListener("click",()=>{
            loadUserInfo();
        })
    })
    .catch(error=>{
        alert(error.message);
    })
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
        body: param
    })
    .then(result => result.json())
    .then(result=>{
        if(result=='0'){
            alert("수정실패.")
        } else {
            alert("수정되었습니다")
            document.getElementById("displayUserName").innerText = userName;
            document.getElementById("displayUserEmail").innerText = userEmail;
            document.getElementById("displayUserPhone").innerText = userPhone;
            loadUserInfo();
        }
       
    })
    .catch(error=>{
        alert(error.message);
    })
}

function delteUserInfo(userId) {

    fetch(`/users/delete?userId=${userId}`, {
        method: 'POST',
        headers:{
            "Content-type":"application/x-www-form-urlencoded"
        }
    })
    .then(result=>result.text())
    .then(result=>{
        let con = confirm("정말 탈퇴하시겠습니까?");

        if(confirm){
            if(result==='1'){
                alert('탈퇴되었습니다.')
                location.reload();
            }
        }
    })
    .catch(error=>{
        alert(error.message);
    })
}

function loadPayInfo() {

}

function loadPoint() {

}

function loadReview() {

}

function loadCoupon() {
    fetch("/coupon/list")
    .then(result=>result.json)
    .then(c=>{
        document.getElementById('mainContents').innerHTML = ``
    })
}