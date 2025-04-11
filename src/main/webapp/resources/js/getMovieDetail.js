document.querySelectorAll(".star-rating span").forEach(star => {
    star.addEventListener("click", function() {
        let value = this.getAttribute("data-value");
        document.getElementById("selected-rating").innerText = "선택한 평점: " + value;
        
        // 선택한 별색 변경
        document.querySelectorAll(".star-rating span").forEach(s => s.classList.remove("selected"));
        this.classList.add("selected");
        this.previousElementSibling?.classList.add("selected"); // 왼쪽 별들 선택
    });
});
