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
        onsubmit() {
            var $this = this;
            if (this.form.uuser.trim() == 0 || this.form.upassword.trim() == 0) {
                this.$alert('请输入用户名或密码！', '提示');
            } else {
                $.post('./index', user=$this.form, function (data) {
                    $this.$alert('登陆成功', '提示');
                    /*jquery中的$().each和$.each的区别，前者只能遍历数组，后者可以遍历数组和对象 */
                    /*$.each(data, function (key, values) {
                        $.each(values, function (s) {
                            $.each(values[s], function (key, value) {
                                alert(key + "\t" + value);
                            })
                        })
                    });*/
                });

            }
            // this.$message('这是一条消息提示');
            //console.log("submit!" );
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')