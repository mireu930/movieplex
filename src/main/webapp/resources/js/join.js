const userId = document.getElementById("userId");
const userPw = document.getElementById("userPw");
const userEmail = document.getElementById("userEmail");
const userPhone = document.getElementById("userPhone");
const userName = document.getElementById("userName");
const idbtn = document.getElementById("idbtn");

userId.addEventListener('input', ()=>{
    console.log("input");
    let feedback = document.getElementById("userIdFeedback");

    if(userId.value.trim() !==''){
        feedback.style.display = 'none';
        userId.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userId.classList.add('is-invalid');
    }
})

idbtn.addEventListener("click",()=>{
    console.log("click")

    fetch("./check?userId="+userId.value)
    .then(r=>r.text())
    .then(r=>{
        if(r.trim()=='0'){
            alert('중복된 아이디입니다.')
            userId.classList.add('is-invalid');
        } else {
            alert('사용 가능한 아이디입니다.')
        }
    })
})


userPw.addEventListener('input', ()=>{
    console.log("input");
    let feedback = document.getElementById("userPwFeedback");

    if(userPw.value.trim() !==''){
        feedback.style.display = 'none';
        userPw.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userPw.classList.add('is-invalid');
    }
})

userEmail.addEventListener('input', ()=>{
    console.log("input");
    let feedback = document.getElementById("userEmailFeedback");

    if(userEmail.value.trim() !==''){
        feedback.style.display = 'none';
        userEmail.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userEmail.classList.add('is-invalid');
    }
})

userPhone.addEventListener('input', ()=>{
    console.log("input");
    let feedback = document.getElementById("userPhoneFeedback");

    if(userPhone.value.trim() !==''){
        feedback.style.display = 'none';
        userPhone.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userPhone.classList.add('is-invalid');
    }
})

userName.addEventListener('input', ()=>{
    console.log("input");
    let feedback = document.getElementById("userNameFeedback");

    if(userName.value.trim() !==''){
        feedback.style.display = 'none';
        userName.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userName.classList.add('is-invalid');
    }
})