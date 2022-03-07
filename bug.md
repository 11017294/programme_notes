# 1.25(周二)
1. 无法识别或表示多个时区
    ```java
   The server time zone value ' й   ׼ʱ  ' is unrecognized or represents more than one time zone.  
   You must configure either the server or JDBC driver (via the 'serverTimezone' configuration property) 
   to use a more specific time zone value if you want to utilize time zone support.
    ```
    - 解决方法
      - 在配置文件中连接数据库的地址加上serverTimezone=GMT
    - 原因
      - 应该是mysql安装时默认时区，使用的美国时区，而本地是国内时区
2. 分页查询没查数据，
   原因：size和currentPage写反了，其中传入参数currentPage=0
3. 时区问题
   - 问题：
     - 存入mysql时时区不对
     - 返回前端时时区不对
   - 解决方法：
     - 在配置文件中数据库的连接地址加上：`serverTimezone=Asia/Shanghai`
     - 在配置文件中加上spring.jackson.time-zone: Asia/Shanghai
   - 原因：mvc和mysql使用的是默认时区
     GMT-8是东八区，北京时间和东八区一致。
   Asia/Shanghai是已地区命名的地区标准时，在中国叫CST。这个地区标准时会兼容历史各个时间节点。中国1986-1991年实行夏令时，夏天和冬天差1个小时，Asia/Shanghai会兼容这个时间段。
   结论：1992年以后，在中国，GMT-8和Asia/Shanghai是一样的时间，1986-1991之间，夏天会有一小时时差。
   1. and
      - 问题：资源文件类型没选择正确
        Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
        Reason: Failed to determine a suitable driver class
      - 