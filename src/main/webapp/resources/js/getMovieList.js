const pages = document.getElementsByClassName("pages")
const pageNum = document.getElementById("pageNum")

for(let p of pages){
    p.addEventListener("click", function(){
        //data-page-num
        //console.log(p.data-page-num);
        console.log(p.getAttribute("data-page-num"));
        pageNum.value=p.getAttribute("data-page-num")
        list_form.submit();
    })
}