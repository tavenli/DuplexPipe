# DuplexPipe
DuplexPipe

```
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

```
	
	


----------


    一个双向管道。传统的管道只能从一端输入、一端输出。
    双向管道不仅可以让进程 A 的输出作为进程 B 的输入，
    也会让进程 B 的输出作为进程 A 的输入。
    这样就可以让两个进程实现交流。
    
    本程序主要是 TCP 转发工具。允许监听本地端口，也可以主动连接远程端口。
    如果和瑞士军刀“nc -e”配合使用， 就能实现本地进程和网络进程任意沟通。
    
    Linux下安装nc命令：
    yum install nc



