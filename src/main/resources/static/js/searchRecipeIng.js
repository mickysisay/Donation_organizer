window.onload = function(){
 let arr = [];
 document.getElementById("resul").addEventListener("click",(e)=>{
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

                       inputResults(response);

                       }
                       });});
//  document.getElementById("search").addEventListener("click",()=>{
//            $.ajax({
//                                   type: "POST",
//                                   url: "/searches/recipeIng",
//                                   data: {
//                                      ingList:arr
//                                   },
//                                   traditional: true,
//                                   success:function(response){
//
//
//                                   }
//                                   })
//  })
    document.getElementById("form").addEventListener("submit",()=>{
    document.getElementById("ingList").value = arr.join(",");
    })
  function inputResults(res){
  let result = document.getElementById("resul");
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
     document.getElementById("resul").innerHTML="";
     arr.push(id);

     let chosenIng = document.getElementById("chosenIng");
     let str = chosenIng.innerHTML;
     str+="<p>"+name+"</p>";
     chosenIng.innerHTML=str;
     }
   }


}