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
        let url = '/admin/userList'

        contents.classList.add("d-flex");
        mainContents.classList.add("flex-grow-1", "p-4");

        fetch(url)
        .then(r => r.text())
        .then(r=>{
            mainContents.innerHTML = r;
        })
 
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

//회원 조회


//회원 상세정보
mainContents.addEventListener("click", (e) => {
    let target = e.target.closest("td[id='userId']");

    if (target) {
        let userId = target.textContent.trim();

        fetch(`/admin/userDetail?userId=${userId}`)
            .then((res) => res.text()) 
            .then((data) => {
                mainContents.innerHTML = data;
            })
            .catch((error) => console.error(error));
    }
});

//회원정보 페이징 처리
mainContents.addEventListener("click",(e)=>{
    let page = e.target.closest(".pages");

    if(page){
        let pageNum = page.getAttribute("data-page-num");

        fetch(`/admin/userList?page=${pageNum}`)
        .then(r=>r.text())
        .then(r=>{
            mainContents.innerHTML = r;
        })
    }
})

//회원정보 검색처리
mainContents.addEventListener("click",()=>{
    let list_form = document.getElementById("list_form");

    if(list_form){
    list_form.addEventListener("submit",(e)=>{
        e.preventDefault(); //페이지 이동막기
       
        let formData = new FormData(e.target);
        let list = new URLSearchParams(formData).toString();
        
        fetch(`/admin/userList?${list}`)
        .then(r=>r.text())
        .then(r=>{
            console.log(r)
            mainContents.innerHTML = r;

            })
        })
    }
})

//관리자등록
mainContents.addEventListener("click", (e) => {
    if (e.target && e.target.id === "adminbtn") {
        
        let selectedUserId = document.getElementById("userId")?.textContent.trim(); 

            console.log(selectedUserId);
            if (!selectedUserId) {
                alert("회원을 선택하세요!");
                return;
            }

            let con = confirm("관리자로 등록하시겠습니까?");
            if (!con) {
                return; 
            }
    
            fetch(`/admin/adminUpdate?userId=${selectedUserId}`)
                .then(r => r.text())
                .then(r => {
                        alert(r.trim() == '0' ? "이미 관리자입니다." : "관리자로 변경되었습니다.");
                        location.reload();
                });
    }
});

//영구탈퇴
mainContents.addEventListener("click",(e)=>{
    if(e.target.id === "withdrawbtn") {
        let selectedUserId = document.getElementById("userId")?.textContent.trim()

        let con = confirm("영구히 탈퇴하시겠습니까?")

        if (!con) {
            return; 
        }

        fetch(`/admin/withdraw?userId=${selectedUserId}`)
        .then(r => r.text())
        .then(r => {
                alert(r.trim()=='0'?"실패":"영구탈퇴되었습니다.");
                location.reload();
        })
    }
})

//입금승인
mainContents.addEventListener("click",(e)=>{
    if(e.target.id === "paymentbtn"){
        let selectedUserId = document.getElementById("userId")?.textContent.trim()

        console.log(selectedUserId);
            if (!selectedUserId) {
                alert("회원을 선택하세요!");
                return;
            }

            let con = confirm("입금 승인인하시겠습니까?");

            if (!con) {
                return; 
            }

        fetch(`/admin/paymentUpdate?userId=${selectedUserId}`)
        .then(r=>r.text())
        .then(r=>{
            alert(r.trim()=='0'?"실패":"입금승인되었습니다.");
            location.reload();
        })
    }
})
    




//영화 조회
// mainContents.addEventListener("click", (e) => {
//     if (e.target.getAttribute("id") == "fetchScheduleBtn") {
//         const selectedRadio = document.querySelector('input[name="theater"]:checked');
//         let selectedName = selectedRadio ? selectedRadio.id : "";
//         let selectedDate = document.getElementById("theaterDate").value;
//         if (selectedName == "" || selectedDate == "") {
//             alert("모든 정보를 입력하세요!");
//             return;
//         }
//         const date = `${selectedDate} 00:00:00`;
//         let url = `/theater/getDayList?theaterStart=${date}&theaterName=${selectedName}`;
//         fetch(url)
//             .then(r => r.text())
//             .then(r => {
//                 const scheduleList = document.getElementById("scheduleList");
//                 scheduleList.innerHTML = r;
//             })
//     }
// })
let selectedDate = "";
mainContents.addEventListener("change", (e) => {
    if (e.target.getAttribute("id") == "theaterDate") {
        selectedDate = document.getElementById("theaterDate").value;
        const selectedRadio = document.querySelector('input[name="theater"]:checked');
        let selectedName = selectedRadio ? selectedRadio.id : "";

        const date = `${selectedDate} 00:00:00`;
        let url = `/theater/getDayList?theaterStart=${date}`;
        fetch(url)
            .then(r => r.text())
            .then(r => {
                const scheduleList = document.getElementById("scheduleList");
                scheduleList.innerHTML = r;
            })
    }
})

mainContents.addEventListener("change", (e) => {
    if (e.target.name == "theater") {
        if (selectedDate == "") {
            alert("날짜를 선택해주세요");
             // 현재 클릭한 라디오 버튼 선택 해제
            const selectedRadio = document.querySelector('input[name="theater"]:checked');
            if (selectedRadio) {
                selectedRadio.checked = false;
            }
            return;
        }
        const selectedRadio = document.querySelector('input[name="theater"]:checked');
        let selectedName = selectedRadio ? selectedRadio.id : "";

        const date = `${selectedDate} 00:00:00`;
        let url = `/theater/getDayList?theaterStart=${date}&theaterName=${selectedName}`;
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

//영화 러닝 타임 불러오기
mainContents.addEventListener("change", (e) => {
    if (e.target.getAttribute("id") == "selectOption") {
        console.log(e.target.value);
        selectedMovie = e.target.value;
        let url = `/movies/getRuntime?movieId=${e.target.value}`;
        fetch(url)
            .then(r => r.text())
            .then(r => {
                runTime = parseInt(r);

                const runningTimeDisplay = document.getElementById("runningTimeDisplay");
                runningTimeDisplay.innerText = runTime;

                //영화가 변경되었을 때 다시 러닝타임으로 끝나는 시간 계산하기
                calculationEndTime();
            })
    }
})

//러닝타임으로 끝나는 시간 계산하기
mainContents.addEventListener("change", (e) => {
    if (e.target.getAttribute("id") === "startTime") {

        calculationEndTime();
    }
});
let startDate = ""
let endDate = ""
//시작시간으로 종료시간 계산하는 함수(러닝타임 포함)
function calculationEndTime() {
    let sTime = document.getElementById("startTime");
    let eTime = document.getElementById("endTime");
    if (!sTime.value || isNaN(runTime)) return;

    let [hours, minutes] = sTime.value.split(":").map(Number);

    let selectedDate = document.getElementById("theaterDate").value;
    let [year, month, day] = selectedDate.split("-").map(Number);

    startDate = new Date(year, month - 1, day, hours, minutes);
    endDate = new Date(startDate.getTime() + parseInt(runTime) * 60000);

    //24시간 넘어갈 시 처리해주는 코드
    let endhours = endDate.getHours();
    let endMinutes = endDate.getMinutes();

    if (endhours >= 24) {
        endhours = endhours % 24;
    }

    startTime = sTime.value;

    //intput type=date 의 value 값의 넣어주기 위한 형변환
    endTime = `${String(endhours).padStart(2, '0')}:${String(endMinutes).padStart(2, '0')}`;
    eTime.value = endTime;

    if (endDate.getDate() !== startDate.getDate()) {
        alert("⚠️ 종료 시간이 다음 날로 넘어갑니다!");
    }
}

//상영정보추가
mainContents.addEventListener("click", (e) => {
    if (e.target.getAttribute("id") == "add_btn") {
        const theaterDate = document.getElementById("theaterDate");
        const theaterName = document.getElementById("theaterName");
        const selectedRadio = document.querySelector('input[name="theater"]:checked');
        let selectedName = selectedRadio ? selectedRadio.id : "";

        let selectedDate = document.getElementById("theaterDate").value;

        if (selectedDate == "" || selectedMovie == "" || selectedName == "" || startTime == "" || endTime == "") {
            alert("모든 정보를 입력하세요");
            return;
        }

        startDate = formatToTimeStamp(startDate);
        endDate = formatToTimeStamp(endDate);



        const params = new URLSearchParams({
            movieId: selectedMovie,
            theaterName: selectedName,
            theaterStart: startDate,
            theaterEnd: endDate,
        });

        const url = `/theater/addTheater?${params.toString()}`;

        fetch(url)
            .then(r => r.text())
            .then(r => {
                if (r * 1 == 1) {
                    alert("상영 정보가 추가 되었습니다.");

                    // document.getElementById("fetchScheduleBtn").click();
                    const date = `${selectedDate} 00:00:00`;
                    let url = `/theater/getDayList?theaterStart=${date}&theaterName=${selectedName}`;
                    fetch(url)
                        .then(r => r.text())
                        .then(r => {
                            const scheduleList = document.getElementById("scheduleList");
                            scheduleList.innerHTML = r;
                        })


                    const resetStart = document.getElementById("startTime");
                    resetStart.value = "";
                    const resetEnd = document.getElementById("endTime");
                    resetEnd.value = "";
                    const resetRuntime = document.getElementById("runningTimeDisplay");
                    resetRuntime.innerText = "";
                    const resetMovie = document.getElementById("selectOption")
                    resetMovie.value = "default";

                    runTime = 0;
                    startTime = "";
                    endTime = "";
                } else {
                    alert("상영시간이 겹칩니다. 시간을 변경하세요.");
                }

            })
            .catch((e) => {
                console.log(e);
                alert("관리자에게 문의하세요");
            })
    }
})

//초기화 버튼
mainContents.addEventListener("click", (e) => {
    if (e.target.getAttribute("id") == "reset_btn") {
        const resetStart = document.getElementById("startTime");
        resetStart.value = "";
        const resetEnd = document.getElementById("endTime");
        resetEnd.value = "";
        const resetRuntime = document.getElementById("runningTimeDisplay");
        resetRuntime.innerText = "";
        const resetMovie = document.getElementById("selectOption")
        resetMovie.value = "default";
        const selectedRadio = document.querySelector('input[name="theater"]:checked');
        if (selectedRadio) selectedRadio.checked = false;

        selectedMovie = "";
        runTime = 0;
        startTime = "";
        endTime = "";
    }
})

//자바 스크립트의 Date 객체를 자바 TimeStamp 형식으로 받을 수 있게
//변경하는 함수
function formatToTimeStamp(dateObj) {
    const yyyy = dateObj.getFullYear();
    const MM = String(dateObj.getMonth() + 1).padStart(2, "0");
    const dd = String(dateObj.getDate()).padStart(2, "0");
    const hh = String(dateObj.getHours()).padStart(2, "0");
    const mm = String(dateObj.getMinutes()).padStart(2, '0');
    const ss = "00";

    return `${yyyy}-${MM}-${dd} ${hh}:${mm}:${ss}`;
}


//---------------------------------------삭제하기-----------------------------------------------

mainContents.addEventListener("click", (e) => {
    if (e.target.getAttribute("id") == "del_btn") {
        const theaterId = e.target.getAttribute("data-theater-id");
        let param = new URLSearchParams();
        param.append("theaterId", theaterId);
        let url = `/theater/deleteTheater`;
        fetch(url, {
            method: 'POST',
            headers: {
                "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
            },
            body: param
        })
            .then(r => r.text())
            .then(r => {
                console.log(r);
                if (parseInt(r) == 1) {
                    alert("삭제가 완료 되었습니다.");
                    e.target.parentElement.parentElement.parentElement.remove();
                } else {
                    //이건 나중에 좀 생각해봐야함..... 삭제가 가능하게 할 건지..말건지...
                    alert("이미 예약한 고객이 있어 삭제가 불가합니다.");
                }
            })
            .catch(e => {
                alert("관리자에게 문의하세요");
            })
    }
})
