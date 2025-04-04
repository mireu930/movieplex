<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>Album example · Bootstrap v5.3</title>

<style>
html, body {
		padding: 0 !important;
		margin: 0 !important;
		background-color: #FFF !important; 
		display: block;
		overflow: hidden;
	}
	
	body > div {
		margin: 0; 
		padding: 0; 
	}

	#main {
		width: 400px;
		height: 510px;
		margin: 3px;
		display: grid;
		grid-template-rows: repeat(12, 1fr);
	}
	#header > h2 {		
		margin: 0px;
		margin-bottom: 10px;
		padding: 5px;
	}

	#list {
		border: 1px solid var(--border-color);
		box-sizing: content-box;
		padding: .5rem;
		grid-row-start: 2;
		grid-row-end: 12;
		font-size: 14px;
		overflow: auto;
	}
	
	#msg {
		margin-top: 3px;
	}
	
	#list .item {
		font-size: 14px;
		margin: 15px 0;
	}
	
	#list .item > div:first-child {
		display: flex;
	}
	
	#list .item.me > div:first-child {
		justify-content: flex-end;
	}
	
	#list .item.other > div:first-child {
		justify-content: flex-end;
		flex-direction: row-reverse;
	}
	
	#list .item > div:first-child > div:first-child {
		font-size: 10px;
		color: black;
		margin: 3px 5px;
	}
	
	#list .item > div:first-child > div:nth-child(2) {
		border: 1px solid var(--border-color);
		display: inline-block;
		min-width: 100px;
		max-width: 250px;
		text-align: left;
		padding: 3px 7px;
	}
	
	#list .state.item > div:first-child > div:nth-child(2) {
		background-color: #EEE;
	}
	
	#list .item > div:last-child {
		font-size: 10px;
		color: #777;
		margin-top: 5px;
	}
	
	#list .me {
		text-align: right;
	}
	
	#list .other {
		text-align: left;
	}
	
	#list .msg.me.item > div:first-child > div:nth-child(2) {
		background-color: rgba(255, 99, 71, .2);
	}
	
	#list .msg.other.item > div:first-child > div:nth-child(2) {
		background-color: rgba(100, 149, 237, .2);
	}
	
	#list .msg img {
		width: 150px;
	}
</style>
</head>
<body>
	<main>
		<div id="main">
		<div id="header"><h2>WebSocket <small>닉네임</small></h2></div>
		<div id="list">
			
		</div>
		<input type="text" id="msg" placeholder = "대화 내용을 입력하세요.">
	</div>
	
	<script>
	let name;
	let ws;
	const url = '/chatServer';

	function connect(name, usergrade) {
	    console.log(name);
	    window.name = name;
		window.usergrade = usergrade;
	    document.querySelector('#header small').textContent = name;

	    ws = new WebSocket(url);

	    ws.onopen = function(evt) {
	        console.log('서버 연결 성공');
	        
	        let message = {
	            code: '1',
	            sender: window.name,
	            receiver: '',
	            content: '',
	            regdate: new Date().toLocaleString(),
				usergrade: window.usergrade
	        };
	        
	        console.log(message);
	        ws.send(JSON.stringify(message));
	        
	        printMessage(message.sender, '대화방에 참여했습니다.', 'me', 'state', message.regdate);
	        
	        document.getElementById('msg').focus();
	    };

	    ws.onmessage = function(evt) {
	        let message = JSON.parse(evt.data);
	        console.log(message);
	        
	        if (message.code === '1') {
	            printMessage('', `[\${message.sender}]님이 들어왔습니다.`, 'other', 'state', message.regdate);
	        } else if (message.code === '2') {
	            printMessage('', `[\${message.sender}]님이 나갔습니다.`, 'other', 'state', message.regdate);
	        } else if (message.code === '3') {
	            printMessage(message.sender, message.content, 'other', 'msg', message.regdate);
	        }
	    };
	}

	window.addEventListener('beforeunload', function() {
	    disconnect();
	});

	function disconnect() {
	    if (!ws) return;
	    
	    let message = {
	        code: '2',
	        sender: window.name,
	        receiver: '',
	        content: '',
	        regdate: new Date().toLocaleString()
	    };
	    
	    ws.send(JSON.stringify(message));
	    ws.close();
	}

	document.getElementById('msg').addEventListener('keydown', function(evt) {
	    if (evt.keyCode === 13) {
	        let message = {
	            code: '3',
	            sender: window.name,
	            receiver: '',
	            content: evt.target.value,
	            regdate: new Date().toLocaleString()
	        };
	        
	        ws.send(JSON.stringify(message));
	        evt.target.value = '';
	        evt.target.focus();
	        
	        printMessage(window.name, message.content, 'me', 'msg', message.regdate);
	    }
	});
	
	function printMessage(sender, message, senderType, messageType, regdate) {
	    let item = document.createElement('div');
	    item.className = `item \${senderType} \${messageType}`;
	    item.innerHTML = `
	        <div>
	            <div>\${message}</div>
	        </div>
	        <div>\${regdate}</div>
	    `;
	    
	    document.getElementById('list').appendChild(item);
	    scrollList();
	}

	function scrollList() {
	    let list = document.getElementById('list');
	    list.scrollTop = list.scrollHeight;
	}
	</script>
	</main>
</body>
</html>