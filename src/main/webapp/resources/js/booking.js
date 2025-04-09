const main = document.getElementById("main");

let selectedDate = null;
let selectedMovieId = null;

document.addEventListener("DOMContentLoaded", function () {
  generateDates(); // 날짜 뿌리기
});

// 날짜 14개 만들기
function generateDates(count = 14) {
  const strip = document.getElementById("date-strip");
  const today = new Date();
  const todayStr = today.toISOString().slice(0, 10);

  for (let i = 0; i < count; i++) {
    const d = new Date(today);
    d.setDate(d.getDate() + i);
    const date = d.toISOString().slice(0, 10);
    const day = d.toLocaleDateString('ko-KR', { weekday: 'short' });

    const div = document.createElement("div");
    div.className = "date-item";
    div.dataset.date = date;

    if (day === "토") {
      div.innerHTML = `<div>${d.getDate()}일･${day}</div>`;
      div.style.color = "blue";
    } else if (day === "일") {
      div.innerHTML = `<div>${d.getDate()}일･${day}</div>`;
      div.style.color = "red";
    } else {
      div.innerHTML = `<div>${d.getDate()}일･${day}</div>`;
    }

    if (date === todayStr) {
      div.classList.add("active");
      selectedDate = date;
      fetchSchedule(); // 오늘 날짜로 자동 fetch
    }

    strip.appendChild(div);
  }
}

function fetchSchedule() {
  if (!selectedDate) return;

  const baseUrl = `/theater/getTheaterList?theaterStart=${selectedDate} 00:00:00`;
  const url = selectedMovieId ? `${baseUrl}&movieId=${selectedMovieId}` : baseUrl;

  fetch(url)
    .then(r => r.text())
    .then(r => {
      document.getElementById("theater-list").innerHTML = r;
    })
}

const date_strip = document.getElementById("date-strip")

date_strip.addEventListener("click", (e) => {
  const target = e.target.closest(".date-item");
  if (!target) return;

  const dates = document.querySelectorAll(".date-item");

  dates.forEach(date => date.classList.remove("active"));

  target.classList.add("active");

  selectedDate = target.dataset.date;

  fetchSchedule();
})

const movieList = document.getElementById("movie-list");

movieList.addEventListener("click", (e) => {
  const target = e.target.closest(".movie-item");
  if (!target) return;

  if (target.classList.contains("active")) {
    target.classList.remove("active");
    selectedMovieId = null; // 선택 해제
    fetchSchedule();
    return;
  }

  const movies = document.querySelectorAll(".movie-item");

  movies.forEach(movie => movie.classList.remove("active"));

  target.classList.add("active");

  selectedMovieId = target.getAttribute("data-movie-id");

  fetchSchedule();
})

const theaterList = document.getElementById("theater-list");
let selectedTheaterId = "";

theaterList.addEventListener("click", (e) => {
  const target = e.target.closest(".theater-item");
  if (!target) return;

  selectedTheaterId = target.getAttribute("data-theater-id")
  fetch("/movieBooks/seatBook", {
    method: 'POST',
    headers: {
      "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
    },
    body: `theaterId=${target.getAttribute("data-theater-id")}`
  })
    .then(r => r.text())
    .then(r => {
      if (r * 1 == 0) {
        alert("로그인이 필요합니다.");
        window.location.replace("/users/login")
        return;
      }
      //console.log(r);
      main.innerHTML = r;
      seatBook();
    })

  // window.location.href = `/movieBooks/seatBook?theaterId=${target.getAttribute("data-theater-id")}`
})

//========================================================================================
//========================================================================================
//========================================================================================

//payments에서 사용할 변수들!
let adults = "";
let teens = "";

let sendingSeat = [];
let paymentPrice = 0;
let usedCoupon = "";


//좌석 선택 창 JS
function seatBook() {
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

  //좌석 확인
  fetch("/movieBooks/getSeats", {
    method: "POST",
    headers: {
      "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
    },
    body: `theaterId=${selectedTheaterId}`
  })
    .then(r => r.json())
    .then(r => {
      for (let i = 0; i < 10; i++) {
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
        for (let j = 1; j <= 3; j++) {
          const seat = createSeat(i, j);
          groupLeft.append(seat);
        }

        // 가운데 7칸
        for (let j = 4; j <= 10; j++) {
          const seat = createSeat(i, j);
          groupCenter.append(seat);
        }

        // 오른쪽 3칸
        for (let j = 11; j <= 13; j++) {
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
        if (r.length == 0) {
          seat.innerText = colIndex;
        }
        for (let l of r) {
          // console.log(l);
          // console.log(seat.value);
          // console.log("=============")
          if (l === seat.value) {
            seat.innerText = "X";
            seat.disabled = true;
            return seat;
          }
          seat.innerText = colIndex;
        }

        return seat;
      }
    })

  //총 인원수 계산
  let total = 0;
  let checkedAdult = 0;
  audience.addEventListener("click", (e) => {
    // 라디오 버튼이 아닌 경우는 무시
    //라디오랑 라벨 둘다 눌려서 두번 실행이 됨
    if (e.target.tagName !== "INPUT") return;

    if (e.target.name == "adult") {
      adultPeople = e.target.value;
      adults = adultPeople;
    } else if (e.target.name == "teen") {
      teenPeople = e.target.value;
      teens = teenPeople;
    }

    console.log("일반: " + adultPeople);
    console.log("청소년: " + teenPeople);

    total = parseInt(adultPeople) + parseInt(teenPeople);

    console.log(total);
    let clickedSeat = document.querySelectorAll('.seat.clicked')
    if (total < clickedSeat.length) {
      let result = confirm("자리 선택을 다시하겠습니까?")
      if (result) {
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
  seats.addEventListener("click", (e) => {
    if (adultPeople == 0 && teenPeople == 0) {
      alert("인원을 먼저 선택하세요.");
      return;
    }
    console.log("총 인원수:" + total);
    if (checked < total || e.target.classList.contains("clicked")) {
      if (e.target.classList.contains("seat")) {
        console.log(e.target.classList.contains("clicked"));
        if (e.target.classList.contains("clicked")) {
          e.target.classList.toggle("clicked");
          if (countAdult > 0) {
            totalPrice -= adultPrice
            ticketPrice.innerText = totalPrice;
            console.log("일반");
            countAdult--;
          } else {
            totalPrice -= teenPrice;
            ticketPrice.innerText = totalPrice;
            console.log("청소년");
          }
          checked--;
          selectedSeat.splice(selectedSeat.indexOf(e.target.value), 1);
          console.log("취소:" + total);

        } else {
          e.target.classList.toggle("clicked");
          if (countAdult < adultPeople) {
            totalPrice += adultPrice
            ticketPrice.innerText = totalPrice;
            console.log("일반");
            countAdult++;
          } else {
            totalPrice += teenPrice;
            ticketPrice.innerText = totalPrice;
            console.log("청소년");

          }
          checked++;
          selectedSeat.push(e.target.value);
          console.log("선택:" + total);

        }

      }
    } else {
      alert("좌석을 이미 선택하였습니다.");
    }
    console.log(selectedSeat);
    sendingSeat = selectedSeat;
    paymentPrice = totalPrice
  })

  finalBtn.addEventListener("click", () => {
    if (selectedSeat.length == 0 || total != checked) {
      alert("좌석을 선택하세요.");
      return;
    }
    fetch("/movieBooks/paymentPage", {
      method: "POST",
      headers: {
        "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
      },
      body: `theaterId=${selectedTheaterId}`
    })
      .then(r => r.text())
      .then(r => {
        main.innerHTML = r;
        //console.log(r);
        paymentPage();
      })
  })
}

//======================================================================================================
//======================================================================================================
//======================================================================================================
//결제

function paymentPage() {
  const totalPeople = document.getElementById("totalPeople");
  const payment_item_adult = document.getElementById("payment-item-adult");
  const payment_item_teen = document.getElementById("payment-item-teen");
  const adult_price = document.getElementById("adult-price");
  const teen_price = document.getElementById("teen-price");
  const total_amount = document.getElementById("total-amount");
  const discount_amount = document.getElementById("discount-amount");
  const final_amount = document.getElementById("final-amount");

  usedCoupon = "";
  // console.log(adults);
  // console.log(`일반 ${adults}`);

  if (adults != "" && teens == "") {
    totalPeople.innerText = `일반 ${adults}`
    payment_item_adult.innerText = `일반 ${adults}`
    adult_price.innerText = adults * 15000 + "원";
  } else if (adults == "" && teens != "") {
    totalPeople.innerText = `청소년 ${teens}`
    payment_item_teen.innerText = `청소년 ${teens}`
    teen_price.innerText = teens * 10000 + "원";
  } else {
    totalPeople.innerText = `일반 ${adults} 청소년 ${teens}`
    payment_item_teen.innerText = `청소년 ${teens}`
    payment_item_adult.innerText = `일반 ${adults}`
    adult_price.innerText = adults * 15000 + "원";
    payment_item_teen.innerText = `청소년 ${teens}`
    teen_price.innerText = teens * 10000 + "원";
  }
  total_amount.innerText = paymentPrice + "원";
  final_amount.innerText = paymentPrice + "원";

  const selectedCoupon = document.getElementById("selectedCoupon")
  const couponName = document.getElementById("couponName");

  const couponDelete = document.getElementById("couponDelete");

  selectedCoupon.addEventListener("change", (e) => {
    const coupon = e.target.options[e.target.selectedIndex];
    if (coupon.innerText == couponName.innerText) {
      alert("이미 선택한 쿠폰입니다.");
      return;
    }
    couponName.innerText = coupon.innerText;
    let discount = coupon.getAttribute("data-coupon-cost");
    console.log(discount);
    discount_amount.innerText = discount + "원";
    final_amount.innerText = (parseInt(paymentPrice) - parseInt(discount)) + "원";
    usedCoupon = coupon.value;
  })

  couponDelete.addEventListener("click", () => {
    couponName.innerText = "";
    selectedCoupon.value = "default";
  })

  const paymentMethod = document.getElementById("payment-radio");
  const bank = document.getElementById("bank");

  paymentMethod.addEventListener("change", (e) => {
    const selectedMethod = document.querySelector('input[name="method"]:checked');
    console.log(selectedMethod.value);
    if (selectedMethod.value == 0) {
      bank.disabled = false;
    } else {
      bank.disabled = true;
      bank.value = "default";
    }
  })

  const payBtn = document.getElementById("payBtn");
  const preBtn = document.getElementById("preBtn");

  preBtn.addEventListener("click", ()=>{
    let param = new URLSearchParams();
    param.append("theaterId", selectedTheaterId);
    console.log(selectedTheaterId);
    fetch("/movieBooks/seatBook", {
      method: 'POST',
      headers: {
        "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
      },
      body: param
    })
      .then(r => r.text())
      .then(r => {
        if (r * 1 == 0) {
          alert("로그인이 필요합니다.");
          window.location.replace("/users/login")
          return;
        }
        //console.log(r);
        main.innerHTML = r;
        seatBook();
      })

  })


  payBtn.addEventListener("click", () => {
    const selectedMethod = document.querySelector('input[name="method"]:checked');
    if (!selectedMethod) {
      alert("결제 수단을 선택하세요");
      return;
    }

    if (selectedMethod.value == 1) {
      IMP.init("imp54880348");
      fetch("/moviePayment/getUserInfo", {
        method: "POST",
        headers: {
          "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
        },
      })
        .then(r => r.json())
        .then(r => {
          console.log(r);
          requestPay(r);
        })


    } else if (selectedMethod.value == 0) {
      let param = new URLSearchParams();

      //여러개 보낼때는 반복문을 사용해야 함
      for (let s of sendingSeat) {
        param.append("seat", s);
      }
      param.append("theaterId", selectedTheaterId);
      param.append("totalPrice", paymentPrice);
      param.append("usedCoupon", usedCoupon);
      const selectedBank = document.getElementById("bank");
      fetch("/moviePayment/movieBookBankBook", {
        method: "POST",
        headers: {
          "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
        },
        body: param
      })
        .then(r => r.text())
        .then(r => {
          console.log(r);
          main.innerHTML = r
          const numPeople = document.getElementById("numPeople");
          const bankName = document.getElementById("bankName");
          bankName.innerText = selectedBank.value;
          if (adults != "" && teens == "") {
            numPeople.innerText = `일반 ${adults}`
          } else if (adults == "" && teens != "") {
            numPeople.innerText = `청소년 ${teens}`
          } else {
            numPeople.innerText = `일반 ${adults} 청소년 ${teens}`
          }

        })
    }
  })


  function requestPay(r) {
    const merchant_uid = new Date().getTime();

    IMP.request_pay(
      {
        channelKey: r.importChannel,
        pay_method: "card",
        merchant_uid: merchant_uid, // 주문 고유 번호
        name: "movieplex",
        amount: 100,
        buyer_email: r.userEmail,
        buyer_name: r.userName,
        buyer_tel: r.userPhone,
      },
      function (response) {
        if (response.success) {
          let param = new URLSearchParams();
          param.append("imp_uid", response.imp_uid);
          param.append("merchant_uid", response.merchant_uid);
          //param.append("bookId", r.bookId)

          for (let s of sendingSeat) {
            param.append("seat", s);
          }
          param.append("theaterId", selectedTheaterId);
          //param.append("totalPrice", paymentPrice);
          param.append("totalPrice", 100); //테스트용 금액 => 나중에 원래 금액으로 바꿀 예정
          param.append("usedCoupon", usedCoupon);


          fetch("/moviePayment/movieBookCard", {
            method: "POST",
            headers: {
              "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
            },
            body: param
          })
            .then(res => res.text())
            .then(res => {
              console.log(res);

              fetch("/movieBooks/bookSuccessPage", {
                method: "POST",
                headers: {
                  "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
                },
                body: `bookId=${res}`
              })
                .then(r => r.text())
                .then(r => {
                  console.log(r);
                  main.innerHTML = r;

                  const numPeople = document.getElementById("numPeople");
                  if (adults != "" && teens == "") {
                    numPeople.innerText = `일반 ${adults}`
                  } else if (adults == "" && teens != "") {
                    numPeople.innerText = `청소년 ${teens}`
                  } else {
                    numPeople.innerText = `일반 ${adults} 청소년 ${teens}`
                  }

                })

            })
          console.log(response);
        } else {
          console.log(response);
        }
        // 결제 종료 시 호출되는 콜백 함수
        // response.imp_uid 값으로 결제 단건조회 API를 호출하여 결제 결과를 확인하고,
        // 결제 결과를 처리하는 로직을 작성합니다.
      },
    );
  }
}
