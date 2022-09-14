module.exports = {
    devServer: {
        port: 8888,
        proxy: {
            '/notes': {
                target: 'http://127.0.0.1:9090/api', //这里填写项目真实的后台接口地址
                changOrigin: true, //设置允许跨域
                pathRewrite: {
                    '^/notes': ''
                }
            }
        }
    },
    lintOnSave: false
}
