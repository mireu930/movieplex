const files = document.getElementById("files");
const btn1 = document.getElementById("btn1");
const del = document.getElementsByClassName("del");
let count = files.getAttribute("data-files-size");

// import fileDelete from "/resources/JS/files/fileDelete.js";

// fileDelete(count);
const fileDelete = document.getElementsByClassName("files-delete");
  
  for( let c of fileDelete) {
    c.addEventListener("click", () => {
      console.log("delete");
  
      let confirm1 = confirm("정말 삭제하시겠습니까?");
  
      if(confirm1){
        let num = c.getAttribute("data-file-num")
        let kind = c.getAttribute("data-kind")
  
        console.log(num);
        console.log(kind);
  
        let url = `/${kind}/fileDelete`;
  
  
        fetch(url,{
          method: 'POST',
          headers: {
            "Content-type":"application/x-www-form-urlencoded"
          },
          body: 'fileNum='+num
        })
        .then(r=>r.text())
        .then(r => {
          console.log(r.trim());
          if(r.trim()*1>0){
            c.parentElement.remove();
            count--;
 
          }else {
            alert('파일삭제실패')
          }
        })
        .catch(e => {
          alert(e)
        })
        .finally();
      }
    })
  
  }


files.addEventListener("click", function(e) {
  if(e.target.classList.contains('del')){
    console.log("click");
    console.log(e.target.parentElement)
    e.target.parentNode.remove();
    count--;
  }
})

// for(let i of del){
//   i.addEventListener("click", function(){
//     console.log("del");

//   })
// }

btn1.addEventListener("click", function(){
  if(count>4){
    alert('최대5개만 가능');
    return;
  }
  
  let div = document.createElement("div");

  let col = document.createAttribute('class');
  col.value = 'col-md-3';

  div.setAttributeNode(col);

  
  let label = document.createElement('label');
  let formLabel = document.createAttribute('class');
  formLabel.value = 'form-label';
  label.innerHTML = '첨부파일';
  
  label.setAttributeNode(formLabel);
  
  let f = document.createAttribute('for');
  f.value = "attach"+count;
  
  label.setAttributeNode(f);
  
  div.append(label);
  
  let child = document.createElement('input');
  let tp = document.createAttribute('type');
  tp.value='file';
  let na = document.createAttribute('name');
  na.value='attaches';
  let c = document.createAttribute('class');
  c.value = 'form-control';
  let d = document.createAttribute('id');
  d.value = "attach"+count;
  
  child.setAttributeNode(tp);
  child.setAttributeNode(na);
  child.setAttributeNode(c);
  child.setAttributeNode(d);

  div.append(child);

  let x = document.createElement('button');
  x.type="button";
  
  x.innerHTML='X'
 
  x.classList.add("btn", "btn-danger", "del");
  div.append(x);
  
  files.append(div);
  count++;
})