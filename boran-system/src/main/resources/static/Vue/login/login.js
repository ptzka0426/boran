var Main = {
    data() {
        return {
            form: {
                uuser: '',
                upassword: ''
            }
        }
    }, methods: {
        //点击button提交
        onsubmit() {
            var $this = this;
            if (this.form.uuser.trim() == 0 || this.form.upassword.trim() == 0) {
                this.$alert('请输入用户名或密码！', '提示');
            } else {
                if (validate() == false) {
                    alert("输入正确的验证码！");
                } else {
                    $.post('./login', user = $this.form, function (data) {
                        if (data > 0) {
                            $this.$alert('登陆成功,欢迎:' + $this.form.uuser, '提示');
                        } else {
                            $this.$alert('登陆失败', '提示');
                        }
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
            }
            // this.$message('这是一条消息提示');
            //console.log("submit!" );
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')
