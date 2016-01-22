# DuplexPipe
DuplexPipe


用法: java -jar DuplexPipe-0.3.0-SNAPSHOT.jar [-vh] model model
选项:
        -v              输出一些提示信息
        -h              显示本帮助文档
模式:
        -u              UDP 模式
        -l port         监听本地端口
        -c host port    连接远程主机的端口
示例:
        java -jar DuplexPipe-0.3.0-SNAPSHOT.jar -c 192.168.1.100 3389 -l 1234
        将本地 1234 号 UDP 端口上的信息转发给 192.168.1.100 的 3389 TCP 端口

		
		
		