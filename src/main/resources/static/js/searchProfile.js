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
   allResults+="<a href='http://localhost:8080/view/profile/"+res[i].id+"'>";
   allResults+=  res[i].username+"</a>";

   }
   result.innerHTML =allResults;
}
};

