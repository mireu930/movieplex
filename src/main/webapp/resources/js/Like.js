
$(document).ready(function(){
    $(".like-button").click(function(){
        const button = $(this);
        const userNum = button.data("usernum");
        const contentId = button.data("contentid");
        const kind = button.data("kind");

        $.ajax({
            type: "POST",
            url: "/reviewNest/like",
            contentType: "application/json",
            data: JSON.stringify({
                userNum: userNum,
                contentId: contentId,
                kind: kind
            }),
            success: function(result) {
                // 하트 변경 (토글)
                button.find(".heart-icon").text(result.liked ? "❤️" : "🤍");
            },
            error: function() {
                alert("좋아요 처리 중 오류가 발생했습니다.");
            }
        });
    });
});



$(document).ready(function(){
    $(".like-button").click(function(){
        const button = $(this);
        const reviewId = button.data("review-id");
        const kind = button.data("kind");

        $.ajax({
            type: "POST",
            url: "/reviewlike/toggle",
            data: {
                reviewId: reviewId,
                kind: kind
            },
            success: function(result) {
                button.find(".heart-icon").text(result.status === "liked" ? "❤️" : "🤍");
            },
            error: function() {
                alert("좋아요 처리 중 오류가 발생했습니다.");
            }
        });
    });
});

