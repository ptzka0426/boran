/* new Vue({
     el: '#app',
     data: function () {
         return {visible: false}
     }
 })*/
/*new Vue().$mount('#app')*/
var Main = {
    data() {
        return {
            form: {
                uuser: '',
                upassword: ''
            }
        }
    }, methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')
