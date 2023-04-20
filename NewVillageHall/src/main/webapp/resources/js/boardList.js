$(function () {
  $(".FAQ-Ul1").on("click", function() {
    console.log($(this).next())
    if($(this).next().css("display") == 'none') {
      $(this).siblings("div").slideUp();
      $(this).next().slideDown();
    } else{
      $(this).next().slideUp();
    }
  })
})