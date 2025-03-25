
const nav_link = document.getElementsByClassName("nav-link");
const contents = document.getElementById("contents");
const mainContents = document.getElementById("mainContents");

let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();



function setActive(element) {
    // 1. 모든 nav-link 요소들을 찾아옴
    const links = document.querySelectorAll('.nav-link');

    // 2. 각 요소에서 'active' 클래스를 제거 (기존 active 제거)
    links.forEach(link => link.classList.remove('active'));

    // 3. 클릭된 요소에만 'active' 클래스 추가
    element.classList.add('active');

}

contents.addEventListener("click", (e) => {
    let target = e.target.closest("[data-kind]");
    let kind = target?.getAttribute("data-kind");
    //console.log(e.target.getAttribute("id"));
    if (kind == "user") {
        mainContents.innerHTML = "";
    } else if (kind == "movies") {
        mainContents.innerHTML = "";
    } else if (kind == "theater") {
        contents.classList.add("d-flex");
        fetch("/admin/getTheaterPage")
            .then(r => r.text())
            .then(r => {
                let e = document.createAttribute("style");
                e.value = "display: flex; gap: 20px; width: 100%;";
                mainContents.setAttributeNode(e);
                //console.log(r);
                mainContents.innerHTML = r;
                act();

            })
    }
})

let checkMovieId="";
let selectedDate="";

mainContents.addEventListener("click", (e) => {
    let clickitem = e.target.closest(".movie-list-group");

    // list-group-item이 아닌 곳 클릭 시 무시
    if (!clickitem || !mainContents.contains(clickitem)) return;

    mainContents.querySelectorAll(".movie-list-group.active").forEach(item => {
        item.classList.remove("active");
    })

    clickitem.classList.add("active");

    const movieId = clickitem.getAttribute("data-movie-id");
    checkMovieId= movieId;
})

mainContents.addEventListener("click", (e)=>{
    if(e.target.classList.contains("date")){
        //console.log(e.target.innerHTML.substring(0, e.target.innerText.indexOf("일")));
        mainContents.querySelectorAll(".date.selected").forEach(item => {
            item.classList.remove("selected");
        })
        e.target.classList.add("selected");

        const day = parseInt(e.target.innerText.substring(0, e.target.innerText.indexOf("일")));
        const selected = new Date(currentYear, currentMonth, day);
        const yyyy = selected.getFullYear();
        const mm = String(selected.getMonth() + 1).padStart(2,'0');
        const dd = String(selected.getDate()).padStart(2,'0');
        const formattedDate = `${yyyy}-${mm}-${dd}`;

        selectedDate = formattedDate;

        let url = `/theater/getList?movieId=${checkMovieId}&theaterDate=${formattedDate}`
        fetch(url)
        .then(res => res.text())
        .then(res => {
            document.getElementById("theaterArea").innerHTML=res          
        })
    }
})

mainContents.addEventListener("click", (e) => {
    if(e.target.getAttribute("id") == "add_btn"){
        let url = `/admin/addTheaterForm?movieId=${checkMovieId}&theaterDate=${selectedDate}`
        // contents.classList.remove("d-flex");
        fetch(url)
        .then(res => res.text())
        .then(res => {
            mainContents.innerHTML = res;
            console.log(res);
        })
    }

})



//달력 js
function act() {
    const monthYear = document.getElementById("monthYear");
    const calendarDays = document.getElementById("calendarDays");
    const prevBtnEl = document.getElementById("prevMonth");
    const nextBtnEl = document.getElementById("nextMonth");



    const renderCalendar = () => {
        calendarDays.innerHTML = "";
        const date = new Date(currentYear, currentMonth, 1);
        const lastDay = new Date(currentYear, currentMonth + 1, 0).getDate();

        monthYear.innerText = `${date.toLocaleString("default", { month: "long" })} ${currentYear}`;

        for (let i = 1; i <= lastDay; i++) {
            const thisDate = new Date(currentYear, currentMonth, i);
            const weekday = thisDate.toLocaleDateString("ko-KR", { weekday: "short" });

            const dayBtn = document.createElement("button");
            dayBtn.classList.add("date");
            dayBtn.innerText = `${i}일(${weekday})`;

            // if (
            //     i === today.getDate() &&
            //     currentMonth === today.getMonth() &&
            //     currentYear === today.getFullYear()
            // ) {
            //     dayBtn.classList.add("today");
            // }

            calendarDays.appendChild(dayBtn);
        }
    };

    prevBtnEl.addEventListener("click", () => {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        renderCalendar();
    });

    nextBtnEl.addEventListener("click", () => {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        renderCalendar();
    });

    renderCalendar();
}