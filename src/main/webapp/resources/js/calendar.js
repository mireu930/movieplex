
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
    const dayBtn = document.createElement("button");
    dayBtn.innerText = i;

    if (
      i === today.getDate() &&
      currentMonth === today.getMonth() &&
      currentYear === today.getFullYear()
    ) {
      dayBtn.classList.add("today");
    }

    if (
      selectedDate &&
      selectedDate.getDate() === i &&
      selectedDate.getMonth() === currentMonth &&
      selectedDate.getFullYear() === currentYear
    ) {
      dayBtn.classList.add("selected");
    }

    dayBtn.addEventListener("click", () => {
      selectedDate = new Date(currentYear, currentMonth, i);
      renderCalendar();
    });

    calendarDays.appendChild(dayBtn);
  }
};

prevBtn.addEventListener("click", () => {
  currentMonth--;
  if (currentMonth < 0) {
    currentMonth = 11;
    currentYear--;
  }
  renderCalendar();
});

nextBtn.addEventListener("click", () => {
  currentMonth++;
  if (currentMonth > 11) {
    currentMonth = 0;
    currentYear++;
  }
  renderCalendar();
});

renderCalendar();

