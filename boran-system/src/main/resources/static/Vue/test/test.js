/*var Main = {

}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')*/
$(function(){
    $(".main_left li").on("click",function(){
        var address =$(this).attr("data-src");
        $("iframe").attr("src",address);
    });
});

