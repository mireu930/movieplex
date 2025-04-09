const moviesSection = document.getElementById("moviesSection");

moviesSection.addEventListener("click", (e)=>{
    let target = e.target.closest(".movie-card")
    if(e.target.getAttribute("id") == "bookBtn"){
        window.location.href = "/movieBooks/booking"
        
    }else{
        console.log(target.getAttribute("data-movie-id"))
        let movieId = target.getAttribute("data-movie-id");
        let url = `/movies/detail?movieId=${movieId}`;
        window.location.href = url;
    }
})
