function toggleMovieLike(userNum, contentId, kind) {
    var url = "/reviewNest/toggleMovieLike";

    var data = {
        userNum: userNum,
        contentId: contentId,
        kind: kind
    };

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) { // 요청 완료
            if (xhr.status === 200) { // 응답 성공
                var result = JSON.parse(xhr.responseText);
                alert(result.message);

                var likeButton = document.getElementById("likeButton");
                if (result.movieliked) {
                    likeButton.innerText = "❤️ 좋아요 취소";
                } else {
                    likeButton.innerText = "🤍 좋아요";
                }
            } else {
                alert("오류 발생: " + xhr.responseText);
            }
        }
    };

    xhr.send(JSON.stringify(data));
}

window.onload = function () {
    var likeButton = document.getElementById("likeButton");
    if (likeButton) {
        likeButton.onclick = function () {
            var userNum = likeButton.getAttribute("data-usernum");
            var contentId = likeButton.getAttribute("data-contentid");
            var kind = likeButton.getAttribute("data-kind");

            toggleMovieLike(userNum, contentId, kind);
        };
    }
};

document.getElementById("likeButton").addEventListener("click", function() {
    var userNum = sessionStorage.getItem("userNum");  // 세션에서 userNum 가져오기

    if (!userNum) {
        alert("로그인 후 사용 가능합니다.");
        return;  // 로그인되지 않았으면 함수 종료 (좋아요 기능 실행 안 됨)
    }

    // 로그인된 상태에서만 좋아요 기능 실행
    var contentId = this.getAttribute("data-contentid");
    var kind = this.getAttribute("data-kind");

    // 좋아요 기능 호출
    toggleMovieLike(userNum, contentId, kind);
});

