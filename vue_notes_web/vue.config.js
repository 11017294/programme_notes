module.exports = {
    publicPath: '/',
    devServer: {
        port: 8888,
        proxy: {
            '/notes': {
                // target: 'http://192.168.32.128:9000', //这里填写项目真实的后台接口地址
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
