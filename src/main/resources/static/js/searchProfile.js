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
   allResults+="<a href='"+document.location.origin+"/view/profile/"+res[i].id+"'>";
   allResults+=  res[i].username+"</a>";

   }
   if(res.length===0 && $("#searchWord").val().trim()!=="" ){
   allResults+="<P>no users found</p>"
   }
   result.innerHTML =allResults;
}
};

