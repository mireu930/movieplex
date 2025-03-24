const userId = document.getElementById("joinuserId");
const userPw = document.getElementById("joinuserPw");
const userEmail = document.getElementById("userEmail");
const userPhone = document.getElementById("userPhone");
const userName = document.getElementById("userName");
const idbtn = document.getElementById("idbtn");
const userPwCheck = document.getElementById("userPwCheck");
const agree = document.getElementById("agree");

let idcheck = false;
userId.addEventListener('input', ()=>{
    let feedback = document.getElementById("userIdFeedback");
    
    if(isId(userId.value)){
        feedback.style.display = 'none';
        
    }else{
        feedback.style.display = 'block';
        userId.classList.add('is-invalid');
    };

    idcheck = false;
    checkIdMatch();
})

function checkIdMatch() {
    if(!idcheck){
        userId.classList.add('is-invalid');
    }
}

function isId(v) {
    let regex = /^[A-Za-z0-9]{6,12}$/
    return regex.test(v)
}

idbtn.addEventListener("click",()=>{

    fetch("./check?userId="+userId.value)
    .then(r=>r.text())
    .then(r=>{
        if(r.trim()=='0'){
            alert('중복된 아이디입니다.')
            userId.classList.add('is-invalid');
            idcheck=false;
        } else {
            alert('사용 가능한 아이디입니다.')
            userId.classList.remove('is-invalid');
            idcheck=true;
        }
    })
})


userPw.addEventListener('input', ()=>{
    
    let feedback = document.getElementById("userPwFeedback");
    
    if(isPw(userPw.value)){
        feedback.style.display = 'none';
        userPw.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userPw.classList.add('is-invalid');
    }
    checkPasswordMatch();
})

function isPw(v) {
    let regex = /^[A-Za-z0-9]{8,12}$/
    return regex.test(v)
}

userPwCheck.addEventListener('input',checkPasswordMatch);

function checkPasswordMatch() {
    let feedback = document.getElementById("userPwCheckFeedback");

    if (userPw.value === userPwCheck.value && userPw.value !== "") {
        feedback.style.display = 'none';
        userPwCheck.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userPwCheck.classList.add('is-invalid');
    }
}

userPhone.addEventListener('input', (e)=>{
    
    let feedback = document.getElementById("userPhoneFeedback");

    let v = e.target.value.replace(/[^0-9]/g, "")
    .replace(/^(\d{3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);

    console.log(v)

    e.target.value =v;

    if(v.length>10){
        feedback.style.display = 'none';
        userPhone.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userPhone.classList.add('is-invalid');
    }
})



let label = document.querySelector('label[for="checkbox"]');

agree.addEventListener("change",()=>{
    let feedback = document.getElementById("checkboxFeedback");

    if(agree.checked){
        feedback.style.display = 'none';
		label.style.color = 'blue';
    } else {
        feedback.style.display = 'block';
		label.style.color = '';
		agree.style.borderColor = ''; 
    }
})

document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form[action='/login/oauth2/code/kakao']");
    if (!form) return; // 해당 폼이 없을 경우 실행 방지

    const submitButton = form.querySelector("button[type='submit']");
    const inputs = form.querySelectorAll("input");

    function toggleSubmitButton() {
        let invalidInputs = form.querySelectorAll(".is-invalid");
        if(invalidInputs.length===0){
            submitButton.disabled = false;
        }else{
            submitButton.disabled = true;
        }
       
    }

    // 모든 입력 필드에서 변경 이벤트가 발생할 때마다 실행
    inputs.forEach(input => {
        input.addEventListener("input", toggleSubmitButton);
    });

    // 초기 실행 (페이지 로딩 시)
    toggleSubmitButton();
});
