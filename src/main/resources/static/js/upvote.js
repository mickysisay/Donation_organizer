window.onload = function(){

//$(".recipes").each(()=>{
// let markedOne = marked($(this).html());
// console.log(markedOne);
//   //$(this).html();
//});

let recipess = document.getElementsByClassName("recipes");
for(let i=0;i<recipess.length;i++){
   let recipe = recipess[i].innerHTML;
   recipess[i].innerHTML = marked(recipe);

}
$(".delete").click((e)=>{
    let res = confirm("are you sure you want to delete recipe?");
    if(res === true){
    }else{
    e.preventDefault();
    }
})
//fdfff
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


//fdfff
  $(".fa-arrow-up").click((e)=>{

     $.ajax({
                         type: "GET",
                         url: "/upvote",
                         data: {
                            recipeId:Number(e.currentTarget.id)
                         },
                         success:function(response){
                        // console.log(Response);

                         }
                         })

   if($("#"+e.currentTarget.id).css("color")=="rgb(0, 0, 255)"){
   let num=Number( $("#count"+e.currentTarget.id).html())

       $("#count"+e.currentTarget.id).html(num-1);
       $("#"+e.currentTarget.id).css("color","black");
   }else{
     $("#"+e.currentTarget.id).css("color","blue");
     let num=Number( $("#count"+e.currentTarget.id).html());

            $("#count"+e.currentTarget.id).html(num+1);
   };});
   //start
   let touchmoved;
    let tapped=false;
   $('.recipess').on('touchend', function(e){

       if(touchmoved != true){
           // button click action

                if(!tapped){ //if tap is not set, set up single tap
                     tapped=setTimeout(function(){
                         tapped=null
                         e.preventDefault()

                     },300);   //wait 300ms then run single click code
                   } else {    //tapped within 300ms of last tap. double tap
                     clearTimeout(tapped); //stop single tap callback
                     tapped=null

                     //insert things you want to do when double tapped
                        $.ajax({
                                                 type: "GET",
                                                 url: "/upvote",
                                                 data: {
                                                    recipeId:Number(e.currentTarget.id.split(",")[1])
                                                 },
                                                 success:function(response){
                                                // console.log(Response);

                                                 }
                                                 })

                           if($("#"+e.currentTarget.id.split(",")[1]).css("color")=="rgb(0, 0, 255)"){
                           let num=Number( $("#count"+e.currentTarget.id.split(",")[1]).html())

                               $("#count"+e.currentTarget.id.split(",")[1]).html(num-1);
                               $("#"+e.currentTarget.id.split(",")[1]).css("color","black");
                           }else{
                             $("#"+e.currentTarget.id.split(",")[1]).css("color","blue");
                             let num=Number( $("#count"+e.currentTarget.id.split(",")[1]).html());

                                    $("#count"+e.currentTarget.id.split(",")[1]).html(num+1);
                           }
                   }
                   e.preventDefault()
           //
       }
   }).on('touchmove', function(e){
       touchmoved = true;
   }).on('touchstart', function(){
       touchmoved = false;
   });
   //


  $("#subBut").click((e)=>{
     console.log($("#subBut"));
  $.ajax({
                           type: "GET",
                           url: "/look/subscribe",
                           data: {
                              userId:Number($("#usersId").val())
                           },
                           success:function(response){
                           console.log(Response);

                        }
                           })
     $("#subBut").html($("#subBut").html() === "subscribe" ? "unsubscribe" : "subscribe");
     $()
  })
  $(".saveRecipe").click((e)=>{


    $.ajax({
                             type: "POST",
                             url: "/look/save",
                             data: {
                                recipeId:Number(e.currentTarget.id.split(",")[1])
                             },
                             success:function(response){

                          }
                             })
      document.getElementById(e.currentTarget.id).innerHTML = document.getElementById(e.currentTarget.id).innerHTML === "save" ?"unsave" : "save";

    })
}