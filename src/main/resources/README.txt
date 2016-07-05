#tutorial from thrift.apache.org / thrift's github

thrift -r --gen java tutorial.thrift
#-r recursive?:tutorial.thrift中使用了include包含了其他的thrift idl,指定-r会把shared.thrift中的描述也生成对应语言的代码

#另外如果执行thrift命令出现像 generator dart not found等错误,把idl中对应的 namespace dart注释掉即可 估计是windows上的thrift编译器不支持吧!