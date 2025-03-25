const btnPopup = document.querySelector('.btnlogin-popup');
const cover_box = document.querySelector('.cover_box');
const login_link = document.querySelector('.login-link');
const register_link = document.querySelector('.register-link');
const icon_close = document.querySelector('.icon_close');
// const label = document.querySelector('.label');
const forgetPw = document.getElementById("forgetPw");
const fogetUserEmail = document.getElementById("fogetUserEmail");
const emailTransport = document.getElementById("emailTransport");


function activeCoverbox(){
    cover_box.classList.add('active')
}

function deactiveCoverbox(){
    cover_box.classList.remove('active')
}

function activePopup(){
    cover_box.classList.add('active-popup')
}

function deactivePopup(){
    cover_box.classList.remove('active-popup')
}

register_link.addEventListener("click",activeCoverbox);
login_link.addEventListener("click",deactiveCoverbox);

forgetPw.addEventListener("click", ()=>{
    fogetUserEmail.addEventListener("input",()=>{
        if(isEmail(fogetUserEmail.value)){
            
            fogetUserEmail.classList.remove('is-invalid');
        } else {
            
            fogetUserEmail.classList.add('is-invalid');
        }
    })
})

function isEmail(v) {
    let regex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return regex.test(v)
}


function toggleTransButton() {
    let transbtn = document.querySelector(".is-invalid");

    if(transbtn != null){
        emailTransport.disabled = false;
    } else {
        emailTransport.disabled = true;
    }
}

emailTransport.addEventListener("click",input=>{
    input.addEventListener("click", toggleTransButton);
    let email = fogetUserEmail.value;
        console.log(email);

        fetch("/users/forgetPw?email="+email)
        .then(r=>r.text())
        .then(r=>{

        alert("비밀번호가 전송되었습니다.")
        
    })
})