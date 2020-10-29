var Main = {
    methods: {
        handleClick(row) {
            console.log(row);
        }
    },
    data() {
        return {
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                province: '上海',
            }, {
                date: '2016-05-04',
                name: '王小虎',
                province: '上海'
            }, {
                date: '2016-05-01',
                name: '王小虎',
                province: '上海',
            }]
        }
    }
};
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')
