const movieList = document.getElementById("movieList");

movieList.addEventListener("click", (e) => {
    let target = e.target.closest(".card")
    console.log(target.getAttribute("data-movie-id"))
    let movieId = target.getAttribute("data-movie-id");
    let url = `/movies/detail?movieId=${movieId}`;
    window.location.href = url;
})