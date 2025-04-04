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