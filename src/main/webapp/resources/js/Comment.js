
function submitReview() {
    let commentContents = document.getElementById("reviewText").value.trim();

    if (commentContents === "") {
        alert("댓글 내용을 입력하세요!");
        return;
    }

    document.getElementById("reviewForm").submit();
}

