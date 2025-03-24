

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
                <p><strong>이름:</strong> ${user.userName}</p>
                <p><strong>이메일:</strong> ${user.userEmail}</p>
                <p><strong>폰번호:</strong> ${user.userPhone}</p>
                <p><strong>등급:</strong> ${user.userGrade}</p>
                <p><strong>가입일:</strong> ${user.registDate}</p>
                <p><strong>로그인형태:</strong> ${loginType}</p>

                 <button id="editBtn" class="btn btn-primary">수정하기</button>
                  <button id="deleteBtn" class="btn btn-danger" onclick="deleteAccount(${user.userId})">탈퇴하기</button>
            </div>
                `;

                document.getElementById('editBtn').addEventListener('click', function() {
                    editUserInfo(user.userId);
                });

                document.getElementById('deleteBtn').addEventListener('click', function() {
                    delteUserInfo(user.userId);
                });
	})
	.catch(error=>{
	alert(error.message);
	})

}