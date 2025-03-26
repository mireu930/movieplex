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
        let url = `/admin/addTheaterForm`
        contents.classList.add("d-flex");
        mainContents.classList.add("flex-grow-1", "p-4");
        fetch(url)
        .then(res => res.text())
        .then(res => {
            mainContents.innerHTML = res;
        })
        
    }
})



//영화 조회
mainContents.addEventListener("click", (e) => {
    if(e.target.getAttribute("id") == "fetchScheduleBtn"){
        const selectedRadio = document.querySelector('input[name="theater"]:checked');
        let selectedName="";
        if(selectedRadio){
            selectedName = selectedRadio.id;
            console.log(selectedName);
        }
        let selectedDate = document.getElementById("theaterDate").value;
        console.log(selectedDate);
        
        let url = `/theater/getDayList?theaterDate=${selectedDate}&theaterName=${selectedName}`;
        fetch(url)
        .then(r => r.text())
        .then(r => {
            const scheduleList = document.getElementById("scheduleList");
            scheduleList.innerHTML = r;
        })
    }

})

let runTime = 0;

let startTime = "";
let endTime = "";
let selectedMovie = "";

//영화 러닝 타임
mainContents.addEventListener("change", (e)=>{
    if(e.target.getAttribute("id") == "selectOption"){
        console.log(e.target.value);
        selectedMovie = e.target.value;
        let url = `/movies/getRuntime?movieId=${e.target.value}`;
        fetch(url)
        .then(r=>r.text())
        .then(r => {
            runTime = r;

            const runningTimeDisplay = document.getElementById("runningTimeDisplay");
            runningTimeDisplay.innerText=runTime;
        })
    }
})

//러닝 타임 계산 후 종료시간 계산
mainContents.addEventListener("change", (e) => {
    if (e.target.getAttribute("id") === "startTime") {
        let sTime = document.getElementById("startTime");
        let eTime = document.getElementById("endTime");

        if (!sTime.value) return; // 입력값이 없으면 중단

        let [hours, minutes] = sTime.value.split(":").map(Number);
        const totalMinutes = hours * 60 + minutes;

        // // runTime이 존재하는지 확인
        // if (!runTime || isNaN(runTime)) {
        //     console.warn("⚠️ 러닝타임이 유효하지 않음:", runTime);
        //     return;
        // }

        const endTimeMin = totalMinutes + parseInt(runTime);

        console.log(endTimeMin);
        
        const resultHours = Math.floor(endTimeMin / 60);
        const resultMinutes = endTimeMin % 60;

        const resultTime = `${String(resultHours).padStart(2, '0')}:${String(resultMinutes).padStart(2, '0')}`;
        eTime.value = resultTime;

        startTime = sTime.value
        endTime = resultTime;
    }
});

mainContents.addEventListener("click", (e) => {
    if(e.target.getAttribute("id") == "add_btn"){
        const theaterDate = document.getElementById("theaterDate");
        const theaterName = document.getElementById("theaterName");
        const selectedRadio = document.querySelector('input[name="theater"]:checked');
        let selectedName="";
        if(selectedRadio){
            selectedName = selectedRadio.id;
            console.log(selectedName);
        }

        let selectedDate = document.getElementById("theaterDate").value;
        console.log(selectedDate);
        
        const fullDateStart = `${selectedDate} ${startTime}:00`;
        const fullDateEnd = `${selectedDate} ${endTime}:00`;
        //let url = `/theater/addTheater?movieId=${selectedMovie}&theaterName=${selectedName}&theaterDate=${selectedDate}`;
        //let url = `/theater/addTheater`;

        const params = new URLSearchParams({
            movieId: selectedMovie,
            theaterName: selectedName,
            theaterStart: fullDateStart,
            theaterEnd: fullDateEnd,
            theaterDate: selectedDate
          });
          
        const url = `/theater/addTheater?${params.toString()}`;
          
        fetch(url)
        .then(r => r.text())
        .then(r => {

        })
    }
})

mainContents.addEventListener("click", (e) => {
    if(e.target.getAttribute("id") == "reset_btn"){
        const resetOption = document.getElementById("selectOption");
        resetOption.value= "default";
        const resetStart = document.getElementById("startTime");
        resetStart.value = "";
        const resetEnd = document.getElementById("endTime");
        resetEnd.value = "";
        const resetRuntime = document.getElementById("runningTimeDisplay");
        resetRuntime.innerText = "";
        const resetName = document.getElementById("theaterName");
        resetName.value = "default";
    }
})





//예매하기 할 때 사용할 js admin에서는 필요 없음

//contents.classList.add("d-flex");
        // fetch("/admin/getTheaterPage")
        //     .then(r => r.text())
        //     .then(r => {
        //         let e = document.createAttribute("style");
        //         e.value = "display: flex; gap: 20px; width: 100%;";
        //         mainContents.setAttributeNode(e);
        //         //console.log(r);
        //         mainContents.innerHTML = r;
        //         act();

        //     })


// let checkMovieId="";
// let selectedDate="";

// mainContents.addEventListener("click", (e) => {
//     let clickitem = e.target.closest(".movie-list-group");

//     // list-group-item이 아닌 곳 클릭 시 무시
//     if (!clickitem || !mainContents.contains(clickitem)) return;

//     mainContents.querySelectorAll(".movie-list-group.active").forEach(item => {
//         item.classList.remove("active");
//     })

//     clickitem.classList.add("active");

//     const movieId = clickitem.getAttribute("data-movie-id");
//     checkMovieId= movieId;
// })

// mainContents.addEventListener("click", (e)=>{
//     if(e.target.classList.contains("date")){
//         //console.log(e.target.innerHTML.substring(0, e.target.innerText.indexOf("일")));
//         mainContents.querySelectorAll(".date.selected").forEach(item => {
//             item.classList.remove("selected");
//         })
//         e.target.classList.add("selected");

//         const day = parseInt(e.target.innerText.substring(0, e.target.innerText.indexOf("일")));
//         const selected = new Date(currentYear, currentMonth, day);
//         const yyyy = selected.getFullYear();
//         const mm = String(selected.getMonth() + 1).padStart(2,'0');
//         const dd = String(selected.getDate()).padStart(2,'0');
//         const formattedDate = `${yyyy}-${mm}-${dd}`;

//         selectedDate = formattedDate;

//         let url = `/theater/getList?movieId=${checkMovieId}&theaterDate=${formattedDate}`
//         fetch(url)
//         .then(res => res.text())
//         .then(res => {
//             document.getElementById("theaterArea").innerHTML=res          
//         })
//     }
// })


// //달력 js
// function act() {
//     const monthYear = document.getElementById("monthYear");
//     const calendarDays = document.getElementById("calendarDays");
//     const prevBtnEl = document.getElementById("prevMonth");
//     const nextBtnEl = document.getElementById("nextMonth");



//     const renderCalendar = () => {
//         calendarDays.innerHTML = "";
//         const date = new Date(currentYear, currentMonth, 1);
//         const lastDay = new Date(currentYear, currentMonth + 1, 0).getDate();

//         monthYear.innerText = `${date.toLocaleString("default", { month: "long" })} ${currentYear}`;

//         for (let i = 1; i <= lastDay; i++) {
//             const thisDate = new Date(currentYear, currentMonth, i);
//             const weekday = thisDate.toLocaleDateString("ko-KR", { weekday: "short" });

//             const dayBtn = document.createElement("button");
//             dayBtn.classList.add("date");
//             dayBtn.innerText = `${i}일(${weekday})`;

//             // if (
//             //     i === today.getDate() &&
//             //     currentMonth === today.getMonth() &&
//             //     currentYear === today.getFullYear()
//             // ) {
//             //     dayBtn.classList.add("today");
//             // }

//             calendarDays.appendChild(dayBtn);
//         }
//     };

//     prevBtnEl.addEventListener("click", () => {
//         currentMonth--;
//         if (currentMonth < 0) {
//             currentMonth = 11;
//             currentYear--;
//         }
//         renderCalendar();
//     });

//     nextBtnEl.addEventListener("click", () => {
//         currentMonth++;
//         if (currentMonth > 11) {
//             currentMonth = 0;
//             currentYear++;
//         }
//         renderCalendar();
//     });

//     renderCalendar();
// }








