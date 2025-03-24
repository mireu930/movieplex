// active

const nav_link = document.getElementsByClassName("nav-link")

// for(let nav of nav_link){
//     nav.addEventListener("click",(e)=>{
//         console.log(e);
//         e.target.classList.add("active");
//     })
// }

function setActive(element) {
    // 1. 모든 nav-link 요소들을 찾아옴
    const links = document.querySelectorAll('.nav-link');
  
    // 2. 각 요소에서 'active' 클래스를 제거 (기존 active 제거)
    links.forEach(link => link.classList.remove('active'));
  
    // 3. 클릭된 요소에만 'active' 클래스 추가
    element.classList.add('active');
  }
  