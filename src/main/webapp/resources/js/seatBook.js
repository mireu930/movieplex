const seats = document.getElementById("seat");
const adult = document.getElementById("adult");
const teen = document.getElementById("teen");

const audience = document.getElementById("audience");
let adultPeople = document.querySelector('input[name="adult"]:checked').value;
let teenPeople = document.querySelector('input[name="teen"]:checked').value;

const ticketPrice = document.getElementById("ticketPrice");
const adultPrice = 15000;
const teenPrice = 10000;

const finalBtn = document.getElementById("finalBtn");

const main = document.getElementById("main");

//좌석 확인
let url = `/movieBooks/getSeats${window.location.search}`
fetch(url)
.then(r => r.json())
.then(r => {
    for(let i = 0; i < 10; i++) {
        let div = document.createElement("div");
        seats.append(div);
        div.classList.add("seat-group-section")
        const row = document.createElement("button");
        row.classList.add("seatRow");
        row.innerText = String.fromCharCode(i + 65);
        div.append(row);
    
        const groupLeft = document.createElement("div");
        groupLeft.classList.add("seat-group", "left-group");
    
        const groupCenter = document.createElement("div");
        groupCenter.classList.add("seat-group", "center-group");
    
        const groupRight = document.createElement("div");
        groupRight.classList.add("seat-group", "right-group");
    
        // 왼쪽 3칸
        for(let j = 1; j <= 3; j++) {
            const seat = createSeat(i, j);
            groupLeft.append(seat);
        }
    
        // 가운데 7칸
        for(let j = 4; j <= 10; j++) {
            const seat = createSeat(i, j);
            groupCenter.append(seat);
        }
    
        // 오른쪽 3칸
        for(let j = 11; j <= 13; j++) {
            const seat = createSeat(i, j);
            groupRight.append(seat);
        }
    
        div.append(groupLeft, groupCenter, groupRight);
    }
    
    // 좌석 버튼 생성 함수
    function createSeat(rowIndex, colIndex) {
        const seat = document.createElement("button");
        seat.type = "button";
        seat.classList.add("seat", "btn", "btn-outline-dark", "btn-sm");
        seat.value = String.fromCharCode(rowIndex + 65) + colIndex;
        
        for(let l of r){
            // console.log(l);
            // console.log(seat.value);
            // console.log("=============")
            if(l === seat.value){
                seat.innerText = "X";
                seat.disabled = true;
                return seat;
            } 
            seat.innerText = colIndex;
        }
        //seat.innerText = colIndex;
        // 클릭 이벤트 추가
        // seat.addEventListener("click", function() {
        //     seat.classList.toggle("clicked");
        //     console.log(`클릭한 좌석: ${seat.value}`);
        // });
    
        return seat;
    }
})

//총 인원수 계산산
let total = 0;
let checkedAdult = 0;
audience.addEventListener("click", (e) => {
    // 라디오 버튼이 아닌 경우는 무시
    //라디오랑 라벨 둘다 눌려서 두번 실행이 됨됨
    if (e.target.tagName !== "INPUT") return;

    if (e.target.name == "adult") {
        adultPeople = e.target.value;
    } else if (e.target.name == "teen") {
        teenPeople = e.target.value;
    }

    console.log("일반: " + adultPeople);
    console.log("청소년: " + teenPeople);

    total = parseInt(adultPeople) + parseInt(teenPeople);

    console.log(total);
    let clickedSeat = document.querySelectorAll('.seat.clicked')
    if(total < clickedSeat.length){
        let result = confirm("자리 선택을 다시하겠습니까?")
        if(result){
            clickedSeat.forEach(button => {
                button.classList.remove("clicked");
            })
            document.querySelector('input[name="adult"][value="0"]').checked = true;
            document.querySelector('input[name="teen"][value="0"]').checked = true;
            adultPeople = 0;
            teenPeople = 0;
            total = 0;
            checked = 0;
            totalPrice = 0;
            ticketPrice.innerText = 0;
            countAdult = 0;
        }
    }
});

//좌석 선택 코드
let checked = 0;
let countAdult = 0;
let selectedSeat = [];
let totalPrice = 0;
seats.addEventListener("click", (e)=>{
    if(adultPeople == 0 && teenPeople == 0) {
        alert("인원을 먼저 선택하세요.");
        return;
    }
    console.log("총 인원수:" + total);
    if(checked < total || e.target.classList.contains("clicked")){
        if(e.target.classList.contains("seat")){
            console.log(e.target.classList.contains("clicked"));
            if(e.target.classList.contains("clicked")){
                e.target.classList.toggle("clicked");
                if(countAdult > 0){
                    totalPrice -= adultPrice
                    ticketPrice.innerText = totalPrice;
                    console.log("일반");
                    countAdult--;
                }else {
                    totalPrice -= teenPrice;
                    ticketPrice.innerText = totalPrice;
                    console.log("청소년");
                }
                checked--;
                selectedSeat.splice(selectedSeat.indexOf(e.target.value),1);
                console.log("취소:" + total);
                
            } else{
                e.target.classList.toggle("clicked");
                if(countAdult < adultPeople){
                    totalPrice += adultPrice
                    ticketPrice.innerText = totalPrice;
                    console.log("일반");
                    countAdult++;
                }else{
                    totalPrice += teenPrice;
                    ticketPrice.innerText = totalPrice;
                    console.log("청소년");

                }
                checked++;
                selectedSeat.push(e.target.value);
                console.log("선택:" + total);
                
            }

        }
    }else{
        alert("좌석을 이미 선택하였습니다.");
    }
    console.log(selectedSeat);
})

finalBtn.addEventListener("click", ()=>{
    if(selectedSeat.length == 0){
        alert("좌석을 선택하세요.");
        return;
    }

    fetch("/movieBooks/paymentPage")
    .then(r => r.text())
    .then(r => {
        main.innerHTML = r;
    })
})

