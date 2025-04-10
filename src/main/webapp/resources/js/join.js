const userId = document.getElementById("joinuserId");
const userPw = document.getElementById("joinuserPw");
const userEmail = document.getElementById("userEmail");
const userPhone = document.getElementById("userPhone");
const userName = document.getElementById("userName");
const idbtn = document.getElementById("idbtn");
const userPwCheck = document.getElementById("userPwCheck");
const mailCheckBtn = document.getElementById("mailCheckBtn");
const mailInput = document.getElementById("mailInput");
const mailInputCheck = document.getElementById("mailInputCheck");
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

//test 메서드는 정규식과 일치하는지 확인하는메서드
function isId(v) {
    let regex = /^[A-Za-z0-9]{6,12}$/
    return regex.test(v)
}

idbtn.addEventListener("click",()=>{

    let feedback = document.getElementById("userIdFeedback");

    if(isId(userId.value)){
        feedback.style.display = 'none';
    } else {
        alert("아이디는 영문,숫자 6자 이상 12자이하이여야합니다다(아이디확인해주십시오)")
        feedback.style.display = 'block';
        userId.classList.add('is-invalid');
        return;
    }

    fetch("./check?userId="+userId.value)
    .then(r=>r.text())
    .then(r=>{
        console.log(r);
        if(r.trim()=='0'){
            alert('중복된 아이디입니다.')
            userId.classList.add('is-invalid');
            idcheck=false
        } else {
            
            alert('사용 가능한 아이디입니다.')
            userId.classList.remove('is-invalid');
            idcheck=true;
        }
    })
})

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


userEmail.addEventListener('input', ()=>{
   
    let feedback = document.getElementById("userEmailFeedback");
    
    if(isEmail(userEmail.value)){
        feedback.style.display = 'none';
        userEmail.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userEmail.classList.add('is-invalid');
    }
})

function isEmail(v) {
    let regex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regex.test(v)
}
let code = "";

mailCheckBtn.addEventListener("click", () => {
    let email = userEmail.value;
    console.log(email);

    fetch("/users/mailCheck?email="+email)
    .then(r=>r.text())
    .then(r=>{

        console.log(r)
        if(r==="?? ???? ??????."){
            alert("이미 있는 이메일입니다. 다른이메일 인증부탁드립니다.")
        } else {
            code = r;
            alert("인증번호가 전송되었습니다.")
        }

    })
})

mailInput.addEventListener("input",()=>{
 
    let inputNum = mailInput.value;
    console.log(inputNum)
    console.log("code:"+code)
    if(inputNum===code&&mailInput.value.trim()!==''){
        mailInput.classList.remove('is-invalid');
        mailInputCheck.innerText = "인증번호가 일치합니다."
    } else {
        mailInput.classList.add('is-invalid');
        mailInputCheck.innerText = "인증번호가 일치하지 않습니다."
    }
    
})

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

userName.addEventListener('input', ()=>{
    
    let feedback = document.getElementById("userNameFeedback");

    if(userName.value.trim() !==''){
        feedback.style.display = 'none';
        userName.classList.remove('is-invalid');
    } else {
        feedback.style.display = 'block';
        userName.classList.add('is-invalid');
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
    const form = document.querySelector("form[action='./join']");
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