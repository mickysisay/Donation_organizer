window.onload=()=>{
const searchWord = document.getElementById("searchWord");
$("#searchWord").keyup(()=>{
    $.ajax({
                      type: "GET",
                      url: "/searches/users",
                      data: {
                         searchWord:$("#searchWord").val()
                      },
                      success:function(response){
                      inputResults(response);

                      }
                      });})
function inputResults(res){
   let result = document.getElementById("results");
   let allResults = "";
   for(let i=0;i<res.length;i++){
   allResults+="<div><hr><a href='http://localhost:8080/view/profile/"+res[i].id+"'";
   allResults+= "<h2>"+ res[i].username+"<h2></a>";
   allResults+="<h4>number of recipes: "+res[i].recipe.length+"</h4><hr></div>";
   }
   result.innerHTML =allResults;
}
};

