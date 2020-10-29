var Main = {
    data() {
        var checkName = (rule, value, callback) => {
            /*input为空时的提示*/
            if (!value) {
                return callback(new Error('公司名称不能为空'));
            }
            //
            setTimeout(() => {
                callback();//无值则正确
            }, 1000);
        };
        var checkPhone = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('电话号码不能为空'));
            }
            setTimeout(() => {
                /*if 未做判断*/
                callback();
            }, 1000)
        }

        var checkEmali = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('邮箱不能为空'));
            }
            setTimeout(() => {
                /*if 未做判断*/
                callback();
            }, 1000)
        }
        var checkAddress = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('地址不能为空'));
            }
            setTimeout(() => {
                /*if 未做判断*/
                callback();
            }, 1000)
        }
        var checkScompany = (rule, value, callback) => {
            if (value == "是") {
                $(".el-form-item ").eq(6).show();
            } else {
                $(".el-form-item ").eq(6).hide();
            }
            setTimeout(() => {
                /*if 未做判断*/
                callback();
            }, 1000)
        }
        return {
            form: {
                name: '',
                phone: '',
                emali: '',
                imageUrl: '',
                address: '',
                scompany: '否',
                items: [
                    {
                        value: 'no-1',
                        label: "博冉科技创新"
                    },
                    {
                        value: 'no-2',
                        label: "腾讯"
                    }, {
                        value: 'no-3',
                        label: "阿里巴巴"
                    }, {
                        value: 'no-4',
                        label: "字节跳动"
                    }
                ], sscompany: ''
            }, rules: {
                name: [
                    {validator: checkName, trigger: 'blur'}
                ],
                phone: [
                    {validator: checkPhone, trigger: 'blur'}
                ],
                emali: [
                    {validator: checkEmali, trigger: 'blur'}
                ],
                address: [
                    {validator: checkAddress, triger: 'blur'}
                ],
                scompany: [
                    {validator: checkScompany, triger: 'blur'}
                ]
            }
        };
    }, methods: {
        submitForm(form) {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(form) {
            this.$refs[form].resetFields();
        },
        handleAvatarSuccess(res, file) {
            this.form.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')
