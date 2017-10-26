# Normal 模式
ctrl+[ 也可以进入正常模式 

## 删除一列
1. ctrl-v进入bisul block 模式
2. x删除不迁移 d删除前移

## 粘贴
p

## 帮助
:help \<command\>

## 多行同时向移动
1. ：10,20s/^//
2. esc -- v -- >/shitf+./ 移动

## 撤销dd u
ctrl + r 

## 光标到第中心行
z

## 上一页 下一页
ctrl+f ctrl+b

## 允许shell 命令

1. 格式json
:%! jq .

## 回到上一个光标的地方 和下一个
ctrl + o  ctrl+i 

## 指定行号
vim +n nrfile

