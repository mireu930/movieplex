// active

const nav_link = document.getElementsByClassName("nav-link");


for (let nav of nav_link) {
    nav.addEventListener("click", (e) => {
        let kind = e.target.getAttribute("data-section");
        console.log(kind);
        if (kind == "theater") {
            fetch("/admin/getTheaterPage")
                .then(r => r.text())
                .then(r => {
                    const mainContents = document.getElementById("mainContents");
                    console.log(r);
                    mainContents.innerHTML = r;
                })
        }

    })
}