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
document.getElementById("ingAdd").addEventListener("click",()=>{
console.log($("#ingredientName").val().length)
  if($("#ingredientName").val().trim().length!==0){
        $.ajax({
                               type: "POST",
                               url: "/look/addItem",
                               data: {
                                  ingredientName:$("#ingredientName").val()
                               },
                               success:function(response){

                               document.getElementById("noResult").innerHTML=response;
                               setTimeout(function(){
                               document.getElementById("noResult").innerHTML="";
                               },2000);

                                // allResults+=`<p>{}</p>`


                               }
                               })
        }else{
        alert("ingredient name should atleast 3 letters ");
        }

})

 $("#instruction").markdown({
    autofocus: false,
    height: 270,
    iconlibrary: 'fa',
    onShow: function(e) {
      //e.hideButtons('cmdPreview');
      e.change(e);
    }});
$("#instruction").change(()=>{

});
document.getElementById("save").addEventListener("click",()=>{
    //$("#instruction").val(marked($("#instruction").val()));
})

function inputResults(res){
  let result = document.getElementById("result");
  let noResult = document.getElementById("noResult");
  let allResults = "";
  for(let i=0;i<res.length;i++){
  allResults+=`<div class='view overlay zoom' id="${res[i].id},${res[i].name}"><p id="${res[i].id},${res[i].name}">${res[i].name}
  </p></div>`

    }
    console.log(res.length);
     //result.innerHTML =allResults;
    if(res.length===0  ){
      if($("#ingName").val().trim()!=""){
      allResults = "";
    result.innerHTML="";
    allResults+="<P>no ingredients found</p>"
   allResults+= '<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">Add new Ingredient </button>';
    noResult.innerHTML = allResults;
    }
    }else{
    result.innerHTML = "";
    //allResults.innerHTML="";
       result.innerHTML =allResults;
    }

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