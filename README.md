# 工程简介
访问：
  http://127.0.0.1:9090/api/index
  http://127.0.0.1:9090/api/doc.html
# 延伸阅读

# 1、代码
## 1.1、编写代码
### 1.1.1、基类
- 给所有的base创建一个基类SuperEntity
  1. 将所有pojo类共有属性(id、状态、创建时间、修改时间)放入基类中
  2. 在基类完成共同的操作
- 给所有的Mapper创建一个基类SuperMapper
- 给所有的service接口创建一个基类SuperService
- 给所有的service实现类创建一个基类SuperServiceImpl

### 1.1.2、共同返回类
- 用于向前端返回统一的结果对象
- 编写两个静态方法
  - 成功方法
  - 失败方法
- 在静态方法中创建该类的对象
- 编写返回数据的方法（实例方法）


# 2、后端技术
|   技术   |   说明   |   版本   |      |
| ---- | ---- | ---- | ---- |
|   log4j   |      |   1.2.17   |      |
|   mybatis   |      |   2.1.4   |      |
|   mybatis-plus   |      |   3.4.2   |      |
|   mybatis-plus-generator   |   mybatis-plus自动生成代码   |   3.5.1   |      |
|   freemarker   |   自动生成代码freemarker模板   |   2.3.28   |      |
|   knife4j   |   接口文档   |   2.0.7   |      |
|   redis   |   数据缓存   |      |      |
|   protostuff-core   |   序列化相关   |   1.1.5   |      |
|   protostuff-runtime   |   序列化相关   |   1.1.5   |      |
|   hutool-all   |   工具类   |   5.7.21   |   https://hutool.cn/docs/#/   |
|      |      |      |      |
|      |      |      |      |

# 3、 未来计划
- [ ] 添加参数配置表，例如添加用户时可以取默认密码
- 拉黑需将所有笔记隐藏



保存:window.sessionStorage.setItem('token', JSON.stringify(res))
删除:window.sessionStorage.removeItem('token')
获取:JSON.parse(window.sessionStorage.getItem('token'))

        layui-nav-tree:设置垂直导航
        layui-nav-item:设置单个导航项
        layui-nav-itemed:设置默认展开子菜单
        layui-nav-child：设置一级导航的二级导航菜单

