
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

const btnreview = document.getElementById("btn-review");

btnreview.addEventListener("click",()=>{
    fetch("/reviewNest/checkUser")
    .then(r=>r.text())
    .then(r=>{
        if(r==0){
        	
            alert("로그인이 필요합니다")
            window.location.href = "/reviewNest/login"
            } else {
                    const modal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
                    modal.show();
        }
    })
})
