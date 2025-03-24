const monthYear = document.getElementById("monthYear");
const calendarDays = document.getElementById("calendarDays");
const prevBtn = document.getElementById("prevMonth");
const nextBtn = document.getElementById("nextMonth");

let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();
let selectedDate = null;

const renderCalendar = () => {
  calendarDays.innerHTML = "";
  const date = new Date(currentYear, currentMonth, 1);
  const lastDay = new Date(currentYear, currentMonth + 1, 0).getDate();

  monthYear.innerText = `${date.toLocaleString("default", { month: "long" })} ${currentYear}`;

  for (let i = 1; i <= lastDay; i++) {
    const thisDate = new Date(currentYear, currentMonth, i);
    const weekday = thisDate.toLocaleDateString("ko-KR", { weekday: "short" }); // "월", "화", ...

    const dayBtn = document.createElement("button");
    dayBtn.innerText = `${i}일(${weekday})`; // 👈 날짜 + 요일 표시

    // 오늘 날짜 표시
    if (
      i === today.getDate() &&
      currentMonth === today.getMonth() &&
      currentYear === today.getFullYear()
    ) {
      dayBtn.classList.add("today");
    }

    // 선택된 날짜 표시
    if (
      selectedDate &&
      selectedDate.getDate() === i &&
      selectedDate.getMonth() === currentMonth &&
      selectedDate.getFullYear() === currentYear
    ) {
      dayBtn.classList.add("selected");
    }

    // 클릭 이벤트로 선택
    dayBtn.addEventListener("click", () => {
      selectedDate = new Date(currentYear, currentMonth, i);
      renderCalendar();
    });

    calendarDays.appendChild(dayBtn);
  }
};

// prevBtn.addEventListener("click", () => {
//   currentMonth--;
//   if (currentMonth < 0) {
//     currentMonth = 11;
//     currentYear--;
//   }
//   renderCalendar();
// });

// nextBtn.addEventListener("click", () => {
//   currentMonth++;
//   if (currentMonth > 11) {
//     currentMonth = 0;
//     currentYear++;
//   }
//   renderCalendar();
// });

renderCalendar();
