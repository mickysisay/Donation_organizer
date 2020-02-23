window.onload = function(){
let arr = [];
 document.getElementById("result").addEventListener("click",(e)=>{
  let ar = e.target.id.split(",");
  addItem(Number(ar[0]),ar[1]);
 })
$("#ingName").keyup(()=>{
     $.ajax({
                       type: "GET",
                       url: "/look/searchItem",
                       data: {
                          searchWord:$("#ingName").val()
                       },
                       success:function(response){
                       console.log(response);
                       inputResults(response);

                       }
                       });});


function inputResults(res){
  let result = document.getElementById("result");
  let allResults = "";
  for(let i=0;i<res.length;i++){
  allResults+=`<div class='view overlay zoom' id="${res[i].id},${res[i].name}"><p id="${res[i].id},${res[i].name}">${res[i].name}
  </p></div>`

    }
     result.innerHTML =allResults;
  }
  function addItem(id,name){


      if(!arr.includes(id)){
      $("#ingName").val("");
       document.getElementById("result").innerHTML="";
       arr.push(id);

       let chosenIng=document.getElementById("ing")
       let str = chosenIng.innerHTML;
       str+=`<input type='checkbox' name='ingredients' value=${id} checked>
                        <label>${name}</label>`;
       chosenIng.innerHTML=str;
       }
     }

}