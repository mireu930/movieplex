document.getElementById("send").addEventListener("click", sendMessage);

function sendMessage() {
    
    const inputChatting = document.getElementById("inputChatting");

    if(inputChatting.value.trim().length === 0){
        alert("입력하세요.")

        inputChatting.value = "";
        inputChatting.focus();
    } else {
        const chatMessage = {
            message : inputChatting.value,
            chatRoomNo,
            userNum,
            userId
        };

        console.log(chatMessage);
        const jsonParsedMessage = JSON.stringify(chatMessage);

        chattingSocket.send(jsonParsedMessage);

        inputChatting.value ="";
    }
}

chattingSocket.onmessage = function(e){
	console.log(e);

	const chatMessage = JSON.parse(e.data);
	
	const li = document.createElement("li");
	
	const p = document.createElement("p"); 
	p.classList.add("chat");
	
	// p태그 내부에 글내용추가 및 개행처리
	p.innerHTML = chatMessage.message.replace(/\\n/gm, "<br>"); // <p class="chat">전달받은메세지</p>

    const span = document.createElement("span");
    span.classList.add("chatDate"); // <span class="chatDate">??</span>
    
    const now = new Date();
    span.innerText = now.toLocaleDateString(); // <span class="chatDate">2024-01-30</span>

    // 내가 쓴 채팅인지, 상대방이 쓴 채팅인지 확인
    if(chatMessage.userNum == userNum){
        // 내가쓴글
        li.classList.add("myChat"); // 내가쓴글에 해당하는 스타일 적용
        li.append(p, span);
    }else {
        // 남이 쓴 글
        li.innerHTML = `<b>${chatMessage.userId}</b>`;
        li.classList.add("otherChat");
        li.append(p, span)
    }

    // 채팅창 요소가져오기
    const display = document.querySelector(".display-chatting");

    // 채팅창에 채팅내용 추가하기
    display.append(li)

    // 채팅창 맨 아래로 내리기
    display.scrollTop = display.scrollHeight;
    // scrollTop : 스크롤의 위치
    // scrollHeight : 스크롤되는 요소(display)의 전체 높이
}