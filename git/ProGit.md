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





