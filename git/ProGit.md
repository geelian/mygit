# 起步 
## 运行Git前的配置 
1. /etc/gitconfig 每一个用户以及他们仓库的通用配置变量，  git config --system 会读 
2. ~/.gitconfig ~/.connfig/git/config 当前用户   git --global  读
3. .git/config 当前仓库中的config文件
没有一级覆盖上级

git config --list 列出      
git config user.name 某一项     

## 用户信息 
git config --global user.name "lg"   
git config --global user.email lg@qq.com    

// 写在 .gitconfig 中

## 文本编辑器 
默认vim 
git config --global core.editor emacs   


## 帮助 
git help <verb>     
git <verb> --help      
man git-<verb>  

git help cofig  

irc.freenode.net   :好像被墙了  

# git基础 

## 获取Git

1. 现有目录初始化 git init 
2. 克隆仓库 git clone  https://github.com/libgit2/libgit2   

## 更新到仓库
文件状态    
Untracked(无足迹的  -- Add the file --》 staged (已暂存 -- Commit --》 Unmodified (未更变 --Remove the file --》Untracked `

Unmodified-- Edit the file --> Modified(修改 ---stage the file ---> Staged      

### 检查当前文件状态

git 只是暂存运行git add命令时的版本     
git status 
```
On branch master 在主分支
Your branch is up-to-date with 'mygit/master'. 你的分支是最新的和mygit/master

nothing to commit, working directory clean 没有什么要提交的，工作区是干净的 
新建一个文件以后 
```
On branch master 在主分支
Your branch is up-to-date with 'mygit/master'.  你的分支和mygit/master一样

Untracked files: 没有跟踪的文件
  (use "git add <file>..." to include in what will be committed) 使用git add <file>...  使用git add <file>... 下面包含的将被提交

	README  README文件

nothing added to commit but untracked files present (use "git add" to track) 没有添加到提交，但是在跟踪的文件 
```
跟踪新文件 
git add README
```
Changes to be committed: 提交修改
  (use "git reset HEAD <file>..." to unstage)  使用git reset HEAD <file>... 到没有跟踪状态

	new file:   README  新文件 
```
暂存已修改文件

```
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

	new file:   README

Changes not staged for commit:  更改没有放在缓存区
  (use "git add <file>..." to update what will be committed)  使用  "git add <file>..." 将更新文件将被提交
  (use "git checkout -- <file>..." to discard changes in working directory)  使用“git checkout --<file>....”  将放弃在工作区的修改

	modified:   README      被更改的 
```


$ git status --short //-s
```
 M README // 修改不在暂存区
MM Rakefile // 暂存区有,不在暂存 
A lib/git.rb // 新加到暂存区
M lib/simplegit.rb //修改过的文件在暂存区
?? LICENSE.txt // 没有跟踪的文件
```

### 忽略文件
在文件目录下建立 .gitinore  *.swp// vim的临时文件将被忽略   
\\# 注解 
glob匹配模式
\\! 取反    
target/ 忽略目录下的所有    
参考https://github.com/github/gitignore 


### 文件diff 
git diff 工作区 vs 暂存区   
git diff --staged 暂存区 vs 本地仓库
git difftool 命令行工具 


### 提交更新 
git commit  -m ""// 暂存区 --》本地仓库 
git commit -a -m "" // 工作区 --》本地仓库
```
[master 88b5faa] add  // 当前是在master分支提交的 ，SHA-1 
 3 files changed, 165 insertions(+)
 create mode 100644 .gitignore
 create mode 100644 "git/\\"
```

### 移除文件 
rm 从工作区删除 
git rm 从暂存区移除 
git rm --cached 从本地仓库移除  
git rm log/\*.log  支持glob模式 

### 移动文件 
git mv file_from file_to    
==  
mv file_from file_to    
git rm file_from    
git add file_to 



## 查看提交历史 git  log
git log 选项
```
-p  每一次更新的差异
--stat 文件修改统计 
--shortstat 只显示添加移除的统计    
--name-only 仅在提交信息后显示已修改的文件清单  
--name-status 显示增加删除的清单    
--abbrev-commit 只显示sha-1前几个字符  
--relative-date 显示短时间格式 
--graph 显示分支合并历史 
--pretty 使用其他格式显示提交历史
```

长度控制
```
-2 最近2个 
--since="2008-10-01"
--until=
--author=作者 
--committer显示提交者相关的提交
--grep=搜索提交关键字
-S 显示添加或移除了某个关键字的提交 
```
## 撤销操作
1. 更改提交信息
git commit --amend 覆盖上一次的提交信息 
2. 取消暂存的文件 
git reset HEAD ***   
HEAD 危险命令 
3. 工作区；回滚
git checkout -- test  只会回滚改的部分 
> 新建文件 没有add 不会被工作区检查到   
- 改的内容会消失 -  

## 远程仓库的使用
git remote 列出远程仓库     
git remote -v 使用git和对应URL  
git remote show [remote-name] 仓库远程仓库 // 查看  
git remote rename pb paul  // 重命名    
git remote rm paul // 删除  
## 添加远程仓库 
git remote add <shortname> <url>   

### 从远程仓库中抓取与拉取 
git fetch [remote-name]  // 有那个远程仓库中所有分支的引用，可以随时合并或查看   只是来取，不会合并代码     
// 可以拉取最新的信息 和服务器同步 最后先拉取 再commit 
clone一个仓库，命令自动添加位远程仓库默认以origin为简写，   
git pull 自动的抓取后合并远程分支到当前分支 

### 推送到远程仓库 
git push origin master  // origin- remote-name master- branch-name 将master分支推送到origin     
> 有写权限 &没人推送 有人推送要拉取合并 



## 打标签
git tag // 显示标签     
git tag -l 'v1.8*' // grep 标签
git show v1.8.2 // 显示特定的标签   

### 创建标签 
轻量级标签lightweight--像不会改变的分支，一个特定提交的引用 
git tag v1.4-lw     
附注标签 annotated -- 存储在git数据库中的一个完整的对象，可被校验 有附加信息    
git tag -a v1.4 -m 'my version 1.4' 

追加标签 
1. 获取某次提交  的校验和   
git log --pretty=oneline    
2. 追加标签
git tag -a v1.2 9e57a23ff1c62a802b4eb4749b396fdfb9c96dad

### 共享标签 
git push 默认是不会把标签推送到服务器上  必须显示推送 git push origin tagname   
git push origin  --tags 可以一次推送 

### 检出标签 
没有标签没有分支概念 新拉出一个分支     
git checkout -b [branchename] [tagname]     

## Git别名 

```
git config --global alias.co checkout
git config --global alias.br branch
git config --global alias.ci commit //git commit  -> git ci
git config --global alias.st status
```
替代外部命令
git config --global alias.ll '!ll' // git ll --> ll

# Git 分支
git保持的是一系列不同时刻的文件快照     

在进行提交操作是，git 会保存一个提交对象 commit object ,该提交对象包含一个指向暂存内容快照的指针。还有作者信息，提交输入信息以及指向它的父对象的指针。 第一个没有指向父对象的 。普通提交有，多分钟合并产生的提交对象有多个付对象 

git 对象指向的是对象校验和的文件 
// 删除分支分支下对于的一部分 快照还在吗？      
git add a b c
> 1.计算每个文件sha-1，2.保存当前文件快照保存到git仓库中 
```
建立 blob a blob b  blob c
```

git commit -m "add initial"
1. 计算每个子目录的校验和，在git仓库中校验和保持为tree对象
2. 再建立一个提交commit对象，作者信息。。指向上面的树对象
```
commit 对象 ----> tree 对象 ---> blob a 
^                        |------> blob c
|
commit 对象 2 --->....
```
tree + blob 为快照 snapshot

### 创建分支
git branch tasting  
> 创建一个可以移动的指针
```
           commit1
             ^
           commit2
             ^
           commit3
             ^
master --> commit4 <---testing <---HEAD
```
在什么分支上 ？ HEAD 特殊指针 指向  
git status 
git log --oneline --decorate 仓库  
> --oneline 一次提交在一显示 
> --decorate 输出当前指向的对象  

### 分支切换 
git checkout testing //1.HEAD 指向 testing 2. 将目录恢复到master指向的快照内容 

```
           commit1
             ^
           commit2
             ^
           commit3
             ^
master --> commit4 
             ^
           commit5 <---testing <---HEAD
```
git log --oneline --decorate --graph --all 各分支提交情况   
    

## 分支的创建与合并

1工作区:1暂存区:n分支：n远程仓库
切换分支会检出该分支和工作区暂存区合并 ，可能会有冲突   
### 新建跟换到新分支 
git checkout -b iss53   
=   
git branch iss53    
git checkout iss53  

git merge iss53 // 合并代码     
1. 上游分支合并到下游 fast-forward 只是指针向前移动     
2. 分叉 分支1 2 和共同祖先三方合并 产生新的 提交 
2.1 解决冲突 
产生unmerged文件 <<<< HEAD ==== 要合并的版本>>>>    
git add .
或者使用 git mergetool 

git branch -d hotfix //删除该分支   

## 分支管理 
git branch //分支类别 
git branch -v //最后一次提交 
git branch --merged --no-merged 当前分支合的分支 未合并的分支       
git branch -d testing // 没有合并过来帮你不能删除  -D 可以 

## 分支开发工作流 
master ---- develop ----topic       

## 远程分支 
对远程仓库的引用,包括分支、标签等等。   
查看    
git ls-remote (remote)  
git remote show (remote)    

以(remote)/(branch)命名 如 origin/master 或 origin/iss53    
origin 远程分支在本地的命名 也可以是git clone -o mygit 的mygit  master 是默认的分支

git clone 。。。克隆远程分支后 

`````
远程服务器
        master
1 <  2 < 3

本地
            origin/master 远程分支 
            //git fetch origin 抓去同步远程分支
1   <   2  <   3    <   4   <   5
                              master 本地分支  

`````

多远程操控 fetch 
1. 如果 在同一线上 远程分支指向线上不同节点     

```
1<--- 2(远程1/master) <----3 (远程2/master)
```

### 推送 
git push (remote) (branch)
git push origin refs/heads/master:refs/heads/master1 推送本地的master更新到远程操控上的master1 
git config --global credential.helper cache 设置用户名密码 

git fetch origin 抓取是不可更改的origin/master1 指针 
使用 
1. git merge origin/master1 线合并再更改  
2. git checkout -b master1 origin/master1 设置远程分支+checkout 
### 跟踪分支 
从远程跟踪分支检出的一个本地分支会自动创建一个加做跟踪分支。git pull 会自动去识别是哪个服务器上抓取、被合并哪个分支 
clone时自动建立origin/master的master分支    
其他分支跟踪到远程分支  
1. git checkout --track origin/master1      
2. git checkout -b sf origin/master1    
更改正在跟踪的远程分支--set-upstream-to   
git  branch -u origin/master2

#### 查看跟踪分支和远程分支不同  
git fetch --all // 来源于最后拉取的数据 最好先fetch  
git branch -vv
```
 iss53 7e424c3 [origin/iss53: ahead 2] forgot the brackets
// 跟踪了 origin/iss53 有2个版本没有推送 ahead 2
  master 1ae2a45 [origin/master] deploying index fix
* serverfix f8674d9 [teamone/server-fix-good: ahead 3, behind 1] this should do it
// 落后1个版本 behind 1
  testing 5ea463a trying something new
```





