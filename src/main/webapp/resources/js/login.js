const btnPopup = document.querySelector('.btnlogin-popup');
const cover_box = document.querySelector('.cover_box');
const login_link = document.querySelector('.login-link');
const register_link = document.querySelector('.register-link');
const icon_close = document.querySelector('.icon_close');
// const label = document.querySelector('.label');



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