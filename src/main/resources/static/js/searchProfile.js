window.onload=()=>{
const searchWord = document.getElementById("searchWord");
searchWord.addEventListener("keypress",(e)=>{
  $.ajax({
                      type: "GET",
                      url: "/searches/users",
                      data: {
                         searchWord:e.target.value
                      },
                      success:function(response){
                      console.log(response);
                      }
                    })
   console.log("hello");
})
};
