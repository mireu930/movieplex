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

function fetchSchedule(){
    if(!selectedDate) return;

    const baseUrl = `/theater/getTheaterList?theaterStart=${selectedDate} 00:00:00`;
    const url = selectedMovieId ? `${baseUrl}&movieId=${selectedMovieId}` : baseUrl;

    fetch(url)
    .then(r=>r.text())
    .then(r=>{
        document.getElementById("theater-list").innerHTML = r;
    })
}

const date_strip = document.getElementById("date-strip")

date_strip.addEventListener("click", (e)=>{
    const target = e.target.closest(".date-item");
    if(!target) return;

    const dates = document.querySelectorAll(".date-item");

    dates.forEach(date => date.classList.remove("active"));

    target.classList.add("active");

    selectedDate = target.dataset.date;

    fetchSchedule();
})

const movieList = document.getElementById("movie-list");

movieList.addEventListener("click", (e)=>{
    const target = e.target.closest(".movie-item");
    if(!target) return;

    const movies = document.querySelectorAll(".movie-item");

    movies.forEach(movie => movie.classList.remove("active"));

    target.classList.add("active");

    selectedMovieId = target.getAttribute("data-movie-id");

    fetchSchedule();
})

const theaterList = document.getElementById("theater-list");

theaterList.addEventListener("click", (e) => {
    const target = e.target.closest(".theater-item");
    if(!target) return;
    
    window.location.href = `/movieBooks/seatBook?movieId=${target.getAttribute("data-movie-id")}`
})
