
function submitReview() {
    let reviewContents = document.getElementById("reviewText").value.trim();
    let reviewRate = document.getElementById("reviewRate").value;

    if (reviewContents === "") {
        alert("리뷰 내용을 입력하세요!");
        return;
    }

    if (reviewRate === "") {
        alert("별점을 선택하세요!");
        return;
    }

    document.getElementById("reviewForm").submit();
}


