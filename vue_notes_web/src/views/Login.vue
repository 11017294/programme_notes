<template>
    <div class="contain">
        <div class="contain-info">
            <div class="input-box">
                <div class="contain-item" v-if="isLoginPage">
                    <div class="title">用户登录</div>
                    <div class="register-user">
                        <input
                            type="email"
                            name="username"
                            id=""
                            placeholder="用户名"
                            autocomplete="off"
                            v-model="form.userName"
                        />
                        <input
                            type="password"
                            name="username"
                            id=""
                            placeholder="密码"
                            v-model="form.passWord"
                        />
                    </div>
                    <button class="bbutton" @click="login">登录</button>
                </div>
                <div class="contain-item" v-else>
                    <div class="title">注册账号</div>
                    <div class="register-user">
                        <input
                            type="text"
                            name="username"
                            id=""
                            placeholder="用户名"
                            autocomplete="off"
                            v-model="form.userName"
                        />
                        <input
                            type="email"
                            name="username"
                            id=""
                            placeholder="邮箱"
                            autocomplete="off"
                            v-model="form.email"
                        />
                        <input
                            type="password"
                            name="username"
                            id=""
                            placeholder="密码"
                            v-model="form.passWord"
                        />
                    </div>
                    <button class="bbbutton" @click="register">注册</button>
                </div>
            </div>
            <div class="text-box" :class="{ active: isLoginPage }">
                <div class="text-contain" v-if="isLoginPage">
                    <div class="stitle">你好，朋友!</div>
                    <div class="scontent">开始注册<br />和我们一起旅行</div>
                    <button class="sbutton" @click="changeType">注册</button>
                </div>
                <div class="text-contain" v-else>
                    <div class="stitle">欢迎回来!</div>
                    <div class="scontent">与我们保持联系<br />请登录你的账户</div>
                    <button class="sbutton" @click="changeType">登录</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {authVerify, getUserById, login, register} from "@/api"

export default {
    name: "login",
    data() {
        return {
            form: {
                userName: "",
                email: "",
                passWord: "",
            },
            isLoginPage: true, //决定显示登录界面还是注册界面
        };
    },
    methods: {
        changeType() {
            this.isLoginPage = !this.isLoginPage
            this.form.userName = ""
            this.form.email = ""
            this.form.passWord = ""
        },
        // 登录
        async login() {
            let that = this;
            if (that.form.userName != "" && that.form.passWord != "") {
                var params = new URLSearchParams();
                params.append("userName", that.form.userName)
                params.append("passWord", that.form.passWord)
                login(params).then(response => {
                    let token = response.data;
                    authVerify(token).then(res => {
                        that.$store.commit("SET_IS_LOGIN", true)
                        that.$store.commit("SET_TOKEN", token)
                        that.$store.commit("SET_USERINFO", res.data)
                        let userUid = res.data.userUid;
                        getUserById(userUid).then(res => {  // 根据用户id获取用户个人信息
                            localStorage.setItem('userInfo', JSON.stringify(res.data));
                            that.$store.commit("SET_AVATAR", res.data.avatar)
                        }).catch(err => {
                            that.$message.error(err);
                        })
                        that.$message({
                            message: '登录成功！',
                            type: 'success'
                        });
                        this.$router.push('/')
                    }).catch(err => {
                        that.$message.error(err);
                    })
                }).catch(err => {
                    that.$message.error(err);
                })
            } else {
                this.$message({
                    message: '登录内容不能留空！',
                    type: 'warning'
                });
            }
        },
        // 注册
        async register() {
            let that = this;
            if (this.form.userName != "" && this.form.email != "" && this.form.passWord != "") {
                var params = new URLSearchParams();
                params.append("userName", that.form.userName)
                params.append("passWord", that.form.passWord)
                params.append("email", that.form.email)
                register(params).then(res => {
                    that.$message({
                        message: '注册成功！快去登录吧',
                        type: 'success'
                    });
                    that.isLoginPage = true
                }).catch(err => {
                    that.$message.error(err);
                })
            } else {
                this.$message({
                    message: '注册内容不能留空！',
                    type: 'warning'
                });
            }
        },
    },
};
</script>
<style scoped>
.contain {
    width: 100vw;
    height: 100vh;
    box-sizing: border-box;
}
.contain-info {
    width: 50%;
    height: 60%;
    position: relative;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #ffffff;
    border-radius: 20px;
    box-shadow: 0 0 10px #f0f0f0, 0 0 10px #f0f0f0;
}
.input-box {
    width: 70%;
    height: 60%;
    position: absolute;
    top: 20%;
    right: 0;
    /* transform: translate(0, -50%); */
    transform: translateX(0%);
    transition: all 1s;
}
.contain-item {
    width: 80%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.contain-item .title {
    font-size: 30px;
    color: #39a7b0;
    font-weight: 700;
}

.register-user {
    width: 70%;
    height: 100%;
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}
.register-user input {
    width: 100%;
    height: 30px;
    margin-top: 10px;
    border-style: none;
    outline: none;
    background-color: #eeee;
    border-radius: 10px;
    padding: 2px 10px;
}
.errTips {
    position: absolute;
    top: 55px;
    left: 10px;
    display: block;
    text-align: left;
    color: red;
    font-size: 0.7em;
}
.bbutton,
.bbbutton {
    width: 30%;
    height: 40px;
    margin-top: 20px;
    border-style: none;
    border-radius: 10px;
    outline: none;
    background-color: #39a7b0;
}
.text-box {
    width: 30%;
    height: 100%;
    position: absolute;
    top: 50%;
    left: 0;
    transform: translate(0, -50%);
    /* transform: translateX(0%); */
    transition: all 1s;
    background-color: #38b399;
    border-radius: 20px;
}
.text-contain {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: #fff;
}
.stitle {
    font-size: 25px;
    font-weight: 700;
}
.scontent {
    margin: 20px 10px;
}
.sbutton {
    height: 30px;
    width: 100px;
    outline: none;
    border-style: none;
    border-radius: 20px;
}
</style>
