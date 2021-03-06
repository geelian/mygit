
起步 
## 运行Git前的配置 
1. /etc/gitconfig 每一个用户以及他们仓库的通用配置变量，  git config --system 会读 
2. ~/.gitconfig ~/.connfig/git/config 当前用户   git config --global  读
3. .git/config 当前仓库中的config文件 --file
下一级覆盖上级

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
>  暂存区有文件不能 git checkout branchB // 不能切换分支 
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
```
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

### 文件diff  查看要提交的文件
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
4. 本地仓库回滚 
git reset --hard origin/master 
5. 远程仓库回滚 
git reset --hard HEAD^  
git push -f 
```
git log
git reset --soft ${commit-id}
git stash 
git push -f
```
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
    
#### 拉取远程分支 

git checkout -b zhuanzhuan_scf_zzpaycore-feature-367-49  origin/zhuanzhuan_scf_zzpaycore-feature-367-49

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
git branch -a 查看所有分支  

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

### 拉取 
git pull = git fetch + git merge    
建议后者    

### 删除远程分支 
git push origin --delete  marster2  
删除的是服务上的指针，数据会在垃圾回收运行前存在    

## 变基 少用
整合分支 merge rebase

### 变基的基本操作 
变基：提取c4中的引入的补丁和修改，然后在c3的基础上再运用一次   

git checkout c3 // 切换到c3     
git rebase c4   // c3 多出来的加到 c4 并且c3指向末尾    
git checkout c4 // 切换到c4     
git merge c3 // 合并c3 c4的指针后移
优点： 提交历史整洁  
git rebase --onto master server client  
// 取出clinet分支，找出client和server共同祖先之后的修改，然后把它们在master分支上重演一遍。     
clinet - (server & client) + master 上      
git rebase master server // server -> master    

#### 变基的风险
1. 不要对在你的仓库外有副本的分支执行变基   
因为变基会删除提交，副本会再合并你变基后的结果
#### 变基解决变基
1. 手动拉取 再变基和服务器同步 
git fetch   
git rebase teamone/master 
2. git pull --rebase    

### 变基 vs 合并 
变基：会删除记录    
合并：记录过多 
原则：只对尚未推送或分享给别人的本地修改执行变基操作清理历史，不对已推送至别处的提交执行变基炒作


# 服务器上的Git

## 协议
本地协议Local HTTP协议 SSH(Secure Shell) Git   

### 本地协议
其中的远程版本库就是硬盘内的另一个目录,共享文件系统实现clone,push,pull  
```
git clone file:///opt/git/project.git
git remote add local_proj /opt/git/project.git
```

优点：  
1.  简单
缺点：  
1. 共享文件系统难配置 
2. 共享文件系统不到一定快 
3. 容灾

### HTTP
- 智能smart HTTP
使用用户名/密码的基础授权   
git clone https://exx.com/git.git   
- 哑dumb HTTP
只读

优点:   
1. 快捷高效     
缺点：
1. 服务器端配置麻烦 

### ssh 协议
git clone ssh://user@server/project.git     
优点：  
服务器配置简单 安全 
缺点：  
不能匿名访问    

### git 协议 
端口9418 
优点： 快   
缺点： 没有授权机制 

## 在服务器上搭建Git    
将裸库拷到服务器上  
cp -Rf my/.git my.git   
scp ... 
git clone user@uri:/opt/git/my.git      

## 生成SSH公钥  
ssh-keygen  id_rsa.pub 公钥

# 分布式 Git    

### 集成管理者工作流
1. 项目维护者推送到主仓库 
2. 贡献者克隆此窗口，做出修改 
3. 贡献者将数据推送到自己的公开仓库
4. 贡献者给维护者发送邮件，请求拉取自己的更新
5. 维护者在自己本地的仓库中，将贡献者的仓库加为远程仓库合并修改6. 维护者将合并后的修改推送到主仓库  
github gitlab 集线器式  

### 司令官与副司令官工作流 
100人开发 linux 
副司令官管理者负责项目中的特定部分  
1. 普通开发者在自己的特性分支上工作，并根据master分支进行变基。master是司令官的 
2. 副官将普通开发者的特性分支合并到自己的master分支中   
3. 司令官将所有副官master分支合并到自己的master分支中   
4. 司令官将集成后的master分支推送到参考参考中，以便所有其他开发者以此为基础进行变基 

## 向一个项目贡献 

### 提交准则
git diff --check 找出空白错误   


### 私有小团队
1. origin向本地master合并
git fetch origin    
git merge origin/master     
git push origin master  
2. 分支合并本地master origin合并本地master
git log --no-merges issue54..origin/master  
日志过滤只显示后面的分支 
git checkout master 
git merge issue54   
git merge origin/master 
git push origin master  

### 私有管理团队 
整合管理工作流程 -- 特定的工程师整合    

### 派生公共项目 
git clone (url)     
cd  
git checkout -b featureA    
work    
git commit  
// 去原项目 fork建立派生库  
git remote add myfork (url)     
git push -u myfork  featureA    

### 通过邮件的公开项目 
生成邮件 
git format-path -M origin/master    format-path打印补丁文件名-M 开光告诉Git查找重命名   
配置 imap 在 ~/.gitconfig
发送
 cat *.patch | git imap-send    

也可以配置 SMTP 
git send-email *.patch 


## 维护项目 


1. 在特性分支工作 
git checkout -b sc/ruby_clinet master   
2. 运用来着邮件的补丁 
- apply命令补丁
git apply /tmp/iiiii.patch  
将会修改工作目录中的文件    
git apply --check 检查 
- am命令补丁    
推荐 
git am -3 /tmp/iiiii.patch  
- 检出远程分支 
git remote add jessiac git://   
git fetch jessiac   
git checkout -b rubyclient jessiac/ruby-client  
git pull https://           


3. 确定引入了哪些东西 
git log contrib --not master -p     
contrib 这个分支 不包含master  -p 每次提交的差异    
git diff origin/master  和远程分支对吧  
git diff master...contrib 从master和contrib共同的祖先起到末尾分支的对比 

4. 将贡献的工作整合进来 
- 合并工作流    
master < develop < qt   
- 大项目合并工作流  
长期分支 master 安全特性分支next 用于新工作的pu(proposed updates) 维护性向后移植工作的maint分支 
- 变基与拣选工作流 
拣选：选取分支中的一段      
git cherry-pick e43a6fd3e94888d76779ad79fb568ed180e5fcdf        
- Rerere    
重用已记录的冲突解决方案 reuse recorded resolution 
启动rerere时，git会维护一些成功合并之前和之后的镜像，当git发现之前已经修复过类似的冲突时，便会使用之前的修复方案，不用人工干预  
git config --global rerere.enabled 

- 为发布打标签 
git tag -s v1.5 -m "my signed 1.5 tag"
gpg --list-keys 分发用来签名的PGP公钥生成   
gpg -a --export tagID | git hash-object -w --stdin //将key导入Git的数据库中     
git tag -a maintainer-pgp-pub key //  上面生成的key    通过hash-object命令给出的新sha-1值来创建一个直接指向它的标签     
git show maintainer-pgp-pub | gpg --import 从数据库拉取blob对象并导入到GPG中来导入PGP key

- 生成一个构建号
git describe  master // 只适应于有注解的标签-a -s  

- 准备一次发布 
git archive master --prefix='project/' | gzip > `git describe master`.tar.gz

- 制作提交简报  
git shortlog    
git shortlog --no-merges master --not v1.0.1    



# Git 工具

## 选择修订版本     

### 单个修订版本    

### 简短的SHA-1
git log   显示sha-1         
git show 1c002d  显示提交       
git log --abbrev-commit --pretty=oneline     
--abbrev-commit 输出简短的唯一值    
--pretty=oneline 一行输出   
出现相同的sha1会去前一个 但是概率小于亿亿亿     

### 分支引用 
git show sha-1(该分支最后的sha)    
git show 该分支名 
git rev-parse 该分支 // 查看该分支的sha1

### 引用日志 
git reflog  
记录了最近几个月你的HEAD和分支引用所指向的历史      
引用日志只存在于本地仓库    
git show HEAD@{5} //  第5次前提交记录   
git show HEAD@{2.months.age} 
git show master@{yesterday} // 昨天master提交 
git log -g // 格式输出引用日志  

### 祖先引用 
在引用末尾加一个^(同~) git会将其解析为该引用的上一个提交。       
git show HEAD^      
git show d921970~3  
git show HEAD^^^    


### 提交区间 
解决这个分支还有哪些提交尚未合并到主分支    

#### 双点  
git log master..experiment //在experiment分支中而不在master分支中的提交     
git log experiment..master //master分支不在experiment中的   
git log origin/master..HEAD 



#### 多点

不在refB 中 
```
git log  refA..refB 
git log ^refA refB
git log refB --not refA
```
```
git log refA refB ^refC
git log refA refB --not refC
```

#### 三点 
差集
git log  master...experiment 


## 交互式暂存 
git add -i // --interactive     

```
		staged     unstaged path
  1:    unchanged      +116/-0 git/ProGit.md

*** Commands ***
  1: status       2: update       3: /evert       4: add untracked
  5: patch        6: diff         7: quit         8: help
```

### 暂存于消暂存文件 
暂存 2：update	
取消暂存	3:revert	
已经暂存内容的区别 6:diff	// git diff --cached	

### 暂存补丁 
补丁 5:patch  	


## 储藏与清理

> 不想切换分支就提交一次 
git stash 储藏会处理工作目录的脏的状态-即，修改的跟踪文件与暂存改动-然后将未完成的修改保存到一个栈上，而你可以在任何时候重新应用这些改动。 

git stash /git stash save  // 注意没有跟踪的文件不会被储藏  
git stash list // 显示 
git stash apply  | git stash apply stash@{2}//默认最近的储藏区  
git stash apply --index //重新暂存修改  
git stash drop stash@{0} 移除储藏的 
git stash pop 出栈和使用    


### 创造性的储藏 
git stash --keep-index // 不要存储任何你通过git add 命令已暂存的东西   
git stash --include-untracked //-u  存储任何未跟踪文件  
git stash --patch // 命令行选择     

### 从储藏创建一个分支
储藏文件修改 需要从储藏中拉取分支合并 
git stash branch testchanges    

### 清理工作目录    
git stash --all 移除每一样东西存在栈中  
git clean  
    -f 强制清除 
    -d 目录 
    -n 预清除   
    -i 交互模式     
    -x 不忽略.gitiignore中的文件    
    
## 签署工作 
从其他人那里拿取工作，想验证提交是不是真正的来自可信来源，
GPG来签署和验证工作方式 
### GPG
gpg --gen-key 生成密匙  
gpg --list-keys     
git config --global uer.signingkey gpg_pub 来签署  

### 签署标签
git tag -s v1.5 -m 'my 1.5' 
git show v1.5 显示gpg签名附属在后面 

### 验证标签    
git tag -v [tag-name]   

### 签署提交
git 1.7.9 签署个人提交  
git commit -a -S -m "signed commit" // -S 直接签署到提交    
git log --show-signature 查看验证这些签名   
git log --pretty="format:%h %G? %aN %s" // 配置在%G?中  
git merge 与 pull 使用--verify-signatures选项来检查并拒绝没有携带可信GPG签名的提交  
git merge --verify-signatures non-verify    
## 搜索 
快速从它的数据库中浏览代码和提交    
### Git Grep    
git grep -n gmtime_r    
    -n 行号     
    --count 输出概述    
    -p 哪个方法函数 
    --and 复杂查询  
    ```
    git grep --break --head \
        -n -e '#define' --and \( -e LINK -e BUF_MAX \) v1.8.0
    ```
### git 日志搜索 
什么时候保存的 
git log -SZIB_BUF_MAX --oneline 
    -S ZIB_BUF_MAX 什么时新增和删除的   
    -G 正则搜索 
git diff  ip 查看不同   

#### 行日志搜索 
git log -L :git_deflate_bound:zlib.c    
    zlib.c 文件中 git_deflate_bound的每一次更变     


## 重写历史 

### 修改最后一次提交
git commit --amend  // 覆盖上一次提交的信息

### 修改多次提交信息
在任何想要修改的提交后停止，然后修改信息、添加文件或者然后想要的事情   
git rebase -i HEAD~3 // 交互式变基      
在 HEAD~3..HEAD 范围内的每一个提交都会被重写 
git commit --amend // 覆盖上一次提交的信息
git rebase --continue 

### 大量修改 filter-branch  
- 从每一个提交移除一个文件
git filter-branch --tree-filter 'rm -f passwords.txt' HEAD    
    tree-filter 检出项目的每一个提交后运行指定的命令然后重新提交结果。   

- 使一个子目录做为新的根目录 
git filter-branch --subditectory-filter trunk  HEAD
trunk变为根目录 

- 全局修改邮箱地址
```
git filter-branch --commit-filter '
    if [ "$GIT_AUTHOR_EMAIL" = "schacon@localhost" ];
    then    
            GIT_AUTHOR_NAME="XX";
            GIT_AUTHOR_EMAIL="XX@.COM";
            git commit-tree "$@";
    else    
            git commit-tree "$@";
    fi ' HEAD
```

## 重置揭密

### 三棵树 
树：文件集合 
|--树--|--用途--|
|HEAD |上一次提交的快照，下一次提交的父结点|
|index|预期的下一次提交的快照|
|Working Directory| 沙盘|

- HEAD 
head是当前分支引用的指针，总是指向该分支上的最后一次提交 。

显示HEAD快照实际的目录列表，以及其中每个文件的SHA-1校验和 
git cat-file -p HEAD    
git ls-tree -r HEAD         

- 索引 暂存区 
索引当前的样子 
git ls-files -s         
有更改不能随意切换分支      
- 工作目录 
有更改不能随意切换分支  

### 工作流程 

git init        
git add .       
git commit      
git status      

### 重置的作用  
reset       
1. 移动HEAD --soft
git reset id  // HEAD-> master -> id的快照 
> 本质是撤销了上一次git commit 命令  暂存区和工作区没有是提交时的样子 
git commit 提交 

2. 更新索引 --mixed
git reset --minxed HEAD~    
撤销上一次的提交，还会取消暂存区所有的东西  
回滚到git add 和git commit 之前     

3. 更新工作目录  --hard
git reset --hard HEAD~  // git add 和 git commit 已经工作目录中的所有工作都撤销了 
不能恢复

#### 通过路径来重置 
git reset file.txt // 同 git reset --mixed HEAD file.txt  
1. 移动HEAD分支到指向 
2. 让索引看起来像HEAD 
本质上只是将file.txt 从HEAD复制到索引（暂存区）中   
和 git add 对应     


### 压缩 
1. 退回到某个版本 git reset --soft  HEAD~2  // 暂存区还在 
2. git commit // 从新提交 这样中间2个版本就被不在链路上了   

### 检出 
checkout vs reset  

#### 不带路径 
1.  reset --hard vs checkout    
checkout确保不会将已经更改的文件吹走    
reset --hard 不会检查直接替换 

2. 更新HEAD  
reset 会移动HEAD分支的指向      
checkout 只会移动HEAD自身来指向另一个分支   



#### 带路径 
运行checkout不移动HEAD，提交中的那个文件来更新索引，但是也会覆盖工作目录中对应的文件。 


#### 总结
REF移动HEAD指向的分支引用   
HEAD只移动HEAD本身  

|-Commit Level-|-HEAD-|-Index-|-workdir- | WD Safe |
|:-|-|-|- |- |
|reset --soft [commit]| BEF|NO|NO| YES|
|reset [commit]| BEF|YES|NO| YES|
|reset --hard [commit]| BEF|YES|YES| NO|
|checkout [commit]| HEAD|YES|YES| YES|
|file level| ||| |
|reset (commit)[file]| NO|YES|NO| YES|
|checkout (commit)[file]| NO|YES|YES| NO|

## 高级合并 
哲学：聪明地决定无歧义的合并方案，有冲突不会尝试智能的自动解决它 。

### 合并冲突 
git merge xxxxx

1. 中断一次合并
git status -sb
```
## master
UU hello.rb
```
git merge --abort  // 退回合并

git status -sb  
```
## master
```
2. 忽略空白 
git merge --Xignore-space-change xxx // 忽略所有空白修改    
--Xignore-all-space 忽略任意数量的已有空白的修改    

3. 手动合并代码 
stage1 共同祖先     
stage2 你的版本     
stage3 MERGE_HEAD 要合并的版本  
git show 释放拷贝   
```
git show :1:wj > wj.common  
git show :2:wj > wj.outs        
git show :3:wj > wj.theirs      
```
查看上文件的SHA1    
git ls-files -u     
恢复合并   
git merge-file -p wj.outs wj.common wj.theirs > wj 
实际修改 
git diff --ours // 实际修改     
git diff --theirs -b // 合并后于他们的不同点 -b 去除空白        
git diff --base -b 查看2边是如何修改的      
清理产生的中间文件 
git clean -f 

4. 检出冲突 
git log --graph --oneline --decorate --all  // 查看分支状态     
git merge branch2 // 合并分支2 并产生冲突文件B  
git checkout --conflict=diff3 B // diff3 模式线上冲突 =merge默认模式    
git config --global merge.conflictstyle diff3 更改默认配置  
git checkout --ours // --theirs 选择那边的模式      


5. 合并日志 
每一个提交在不同开发路径  
git log --oneline --left-right HEAD...MERGE_HEAD    



### 撤销合并
合并到了master 
1. 想要修复引用 
git merge       
git reset --hard HEAD~      


2. 还原提交 $$$

生成一个新的提交选项，来撤销已存在提交的所有修改 
git revert -m 1 HEAD  // m 1 要被保留下来的父接点 本分支合并之前的一个  
缺点 分支会视为已经合并了   
只能在撤销还原的还原 
git revert 还原的id     

### 其他类型的合并 
git merge -Xours master // 以自己的为主 能合并上的合并 -Xtheirs  

#### 子树合并 
2个项目 其中一个映射到另一个项目的一个子目录 （（）分支2）分支1         
git init // 建立git仓库1     
git remote add rack_remote  **   // 加其他的远程仓库     
git fetch rack_remote  // 拉取其他分支的信息       
git checkout -b reck_brach rack_remote/master // 拉取远程分支到本地  
git checkout master  // 切换到 分支1    
git read-tree --prefix=rack/ -u rack_branch // 合并分支2   2合并到1中 
git checkout rack_branch // 2次合并    
git pull 
git checkout master 
git merge --squash -s recursive -Xsubtree=rack rack_branch // 才有递归合并策略  
对比    
git diff-tree -p rack_branch    
git diff-tree -p reck_remote/master     


## Rerere 不建议使用
reuse recorded resolution  重用记录决议     
允许你让git记住解决一个块冲突的方法，这样在下一次看到相同冲突时，git可以以你自动的解决它。

开启 
1. git config --global rerere.enabled true
2. mkdir .git/rr-cache  

git merge world  // 合并    
git rerere status // 查看使用状态   
git ls-files -u 查看前左右版本  
解决冲突    
git rerere diff // 查看解决方式 
git add *   
git commit    

git reset --hard HEAD^ 回滚 
git checkout work   
git rebase master  //根据rerere 自动变基      

## 使用Git调试 

### 文件注解 有用
每一行最后一次修改的提交    
git blame -L 12,22 test // test 12,22 之后修改  
git blame -C -L 141,153 test // -C 是从什么地方复制过来的   

### 二分查找 
同部署不同的版本找到，错误的提交次数    
```
git bisect start 启动
git bisect bad 提交有问题 
git bisect good v1.0 // v1.0 是正确的   git 检查中间的提交 
git bisect good 这个是对的 向上找   
git bisect bad 错的下找 
git bisect good // 最后找到 
git bisect reset 归位HEAD指针 
```
脚本判断
```
git bisect start HEAD v1.0
git bisect run test-error.sh 通过这个脚本判断   
```

## 子模块 
子模块允许你将一个git仓库作为另一个Git仓库的子目录，能让你将另一个仓库克隆到自己的项目中，同时还保持提交的独立  

### 开始使用子模块 
在git 目录下拉取模块    
git submodule add https://github.com/chaconinc/DbConnector  
会有模块文件和.gitmodules文件   

```
cat .gitmodules
[submodule "DbConnector"]
path = DbConnector
url = https://github.com/chaconinc/DbConnector``

```
更改子模块URL
git config submodule.DbConnector.url xxx    
git diff --cached DbConnector  // 比较
git diff --cached --submodule   


### 克隆含有子模块的项目 
git clone https://  含有子模块  
cd DbConnector  到子模块    
git submodule init  //在子模块下初始化  

=

git clone --recursive https:// 会同步所有的子模块       

### 在子模块上工作 
1. 更新子模块目录下 
git fetch       
git merge origin/master 
git diff --submodule // git config --global diff.submodule log默认打开 --submodule  
2. 更新 
git submodule update --remote DbConnector   
默认设置 DbConnector 更新   
git config -f .gitmodules submodule.DbConnector.branch stable 

3. 查看只模块日志
git log -p --submodule  



git submodule update --remote --merge // 更新合并   

git push --recurse-submodules=check 没有提交子模块 提交不上     
git push --recurse-submodules=on-demand //尝试性的合并  


```
git pull  // 拉取最新的 可能子模块冲突  
git diff 查看   
cd DbConnector // 到子模块  
git rev-parse HEAD // 显示HEAD指向的模块 有用   
git branch try-merge c771610    
解决冲突提交    
```

### 子模块技巧 

1. 子模块遍历- foreach 
git submodule foreach 'git stash'       
git submodule foreach 'git checkout -b featureA' // 拉取子模块 
git submodule foreach 'git diff'    

2. 有用的别名 
git config alias.sdiff '!'"git diff && git submodule foreach 'git diff'"    
git config alias.spush 'push --recurse-submodules=on-demand'    
git config alias.supdate 'submodule update --remote --merge'        

3. 问题 
- 切换分支会有麻烦 
- 子模块内切换会有麻烦  

## 打包 
git bundle  create repo.bundle HEAD master // 包含所有重建该仓库master分支所需的数据    
git clone repo.bundle // 解压   

git log --oneline master ^origin/master // 除了远程master的信息
git bundle create commits.bundle master ^9a466c5 // 除远程  部分打包    
git bundle verify commits.bundle //在该目录下检查是否合法 
git bundle list-heads commits.bundle // 查看导入哪些分支    
gti fetch ../commits.bundle master:other-master //导出拉取  




## 替换 
用其他对象假装替换数据库中的Git对象 

git for-each-ref 显示数据引用   

## 凭证储藏 
http 密码 
1. cahche 模式 15分钟 内存 
git config --global credential.helper cache  
2. store 磁盘 /home 下 明文 
git config --global credential.helper store --flie ~/.my-credentials  ??
3. 其他工具 

### 底层实现 
git credential  fill    

可用语言扩展    


# 自定义 Git    

## 配置Git 
git config 

### 客户端配置 
man git-config  

- core.editor   
git config --global core.editor emacs   

- commit.template 
默认提交信息    
git config --global commit.template ~/.gitmessage // .gitmessage 设置文件内容   
git commit  

- core.pager    
log diff 分页模式 默认more 
git config --global core.pager '' // 关闭   

- user.signingkey   
标签下设置密匙  
git config --global user.signingkey <gpg-key-id>    
git tag -s <tag-name>   

- core.excludesfile 
全局忽略配置    
```
/file .gitignore_global 
*~
.DS_Store
```

git config --global core.excludesfile ~/.gitignore_global

- help.autocorrect  
设置成1 模糊匹配后自动运行 
git committ  master  // 会指向git commit    

### Git 中的着色    
git config --global color.ui false // 关闭  默认auto

```
具体配色 true flase always  
color.branch 
color.diff
color.interactive   
color.status    
color.diff.meta     
```
### 格式化与多余的空白字符  
-  core.autocrlf   
提交时回车和换行 到换行 windows取出的是 换行转为换行回车    
git config  --global core.autocrlf true      
提交转为换行 检出不转换     
git config --global core.autocrlf input     
windows 保存回车    
git conifg --global core.autocrlf false

- core.whitespace  
探测和修正多余空白字符问题  
默认：
1. blank-at-eol 检查行尾空格    
2. blank-at-eof 盯住文件底部的空格  
3. space-before-tab 警惕行头的tab前面的空格 
关闭：
1. indent-with-non-tab 抛出以空格非tab开头的行  
2. tab-in-indent 监视在行头表示缩进的tab    
3. cr-at-eol 告诉Git忽略行尾的回车  

打开    
git config --global core.whitespace \   
    trailing-space,space-before-tab,indent-with-non-tab     

### 服务器端配置    

- receive.fsckObjects   
要求每一次提交一致性检查，默认false 
git config --system receive.fsckObjects true   //打开   

- receive.denyNonFastForwards   
禁用强制推送 push -f    
git config --system receive.denyNonFastForwards true    

- receive.denyDeletes
禁止通过推送删除分支和标签  


## Git属性 
基于路径的设置项 --对特定文件目录设置   
1. 目录下.gitattributes 文件    
2. .git/info/attributes 设置 

### 二进制文件 
```coding-shell
# file .gitattributes

# 设置为二进制识别 
*.pbxproj binary 

# 二进制文件对比    

# work 对比
#work转为txt在对比 
# 使用word 过滤器
*.docx diff=word 
# 1. 安装docx2txt
# 2. 在可执行路径中建立Git支持格式脚本
# #!/bin/bash 
# docx2txt.pl $1 -  
# $git config diff.word.textconv docx2txt配置git 
# 使用
# $ git diff 

# 图片对比 exiftool
*.png diff=exif 
# 配置exitftool 转原数据文本
# $git config diff.exif.textconv exiftool    
# $ git diff a.png b.png 
```


### 关键字展开 

*.txt ident 设置关键字过滤      
git config --global filter.indent.clean indent  
git config --global filter.indent.smudge cat    


### 导出版本库 

- export-ignore 
打包忽略 git archive
```
# file .gitattributes
# 忽略test目录
test/ export-ignore 

```

- exprot-subst 
gig log 格式化关键字被exprot-subst标记替换

eg  

echo 'Last commit date: $Format:%cd by %aN$' > LAST_COMMIT  
echo "LAST_COMMIT export-subst" >> .gitattributes   
git add .   
git commit  
git archive HEAD | tar xCf ../a -   
cat ../a/LAST_COMMIT // 将被替换   


### 合并策略    

```
# file .gitattributes
# 合并用本地的
database.xml merge=outs 
```

打开虚拟合并策略 
git config --global merge.ours.driver true  
database.xml 将将不变 

## Git钩子 
特定动作触发自定义脚本  
客户端:提交合并 
服务器端：接收推送 

### 安装钩子 
.git/hooks      
xx.sample 都是实例       

### 客户端钩子 
克隆时不被复制  
1. 提交工作流钩子   
- pre-commit 提交信息前运行     
- prepare-commit-msg 启动提交信息编辑器之前，默认信息被创建之后运行    
- commit-msg 提交验证状态   
- post-commit 在提交完成后运行 

2. 电子邮件钩子 
applypath-msg pre-applypach post-applypatch     

3.其他 
- pre-rebase 变基前 pre-rebase.sample 禁止已推送变基提交
- pre-rewrite 会替换记录的命令调用 
- post-checkout 在git checkout 后运行 
- post-merge 在git merge 成功运行后 
- pre-push 在git push 运行期间 

### 服务器端钩子
- pre-receive 推送操作是，先调用pre-receive     
- update 推送的每一个分支运行一次   
- post-receive 运行以后     


> 可以设置不能提交    

# Git 与其他系统  

1. git svn 桥接 svn 
2. git 与 Mercurial


# Git 内部原理
git是内容寻址文件系统   


## 底层命令和高层命令 

```
branches/
COMMIT_EDITMSG
config #项目特有的配置文件 
description # GitWeb使用
FETCH_HEAD
HEAD #文件指示目前被检出的分支
hooks/ # 钩子脚本
index # 文件保存暂存区信息
info/  # 目录包含一个全局性排除文件 
logs/
objects/ #目录存储所有数据内容 
ORIG_HEAD
packed-refs
refs/ # 目录存储指向数据的提交对象的指针

```
    
## Git 对象 
Git的核心是一个简单的键值对数据库   
向该数据库install返回sha1 使用sha1 返回该值     
hash-object     
储藏在 .git/objects     
使用find .git/objects -type f 查看 
1. 数据对象 
仅仅保存文件的内容，没有文件名 
// 储藏数据对象 
echo 'test content' | git hash-object -w --stdin    
// 取出值   
git cat-file -p sha1    

// 不断的储藏
git hash-object -w test.txt     
git hash-object -w test.txt     

// 回滚
git cat-file -p sha1 > test.txt     

注意每一个 文件提交 都是一个新的数据对象 

2. 树对象 
对应目录项 包含1-n条对象数据 
git cat-file -p master^{tree}
```
100644 blob a906cb2a4a904a152e80877d4088654daad0c859 README //数据对象
100644 blob 8f94139338f9404f26296befa88755fc2598c289 Rakefile
040000 tree 99f1a6d12cb4b6f19c8655fca46c3ecf317074e0 lib //树对象 
```

创建暂存区  
git update-index --add --cacheinfo 100644 sha1 test.txt 

add 加入跟踪  
cacheinfo 添加的文件位于git数据库中 
100644 普通文件 
100755 可执行文件 
120000 符号链接     
git write-tree 创建新的树对象   
git cat-file -t sha1 输出对象类型   
git reed-tree --prefix-bak sha1 树对象存入暂存区    

3. 提交对象 
// 第一个对象   
echo 'first commit ' | git commit-tree sha11        
// 第二个提交对象 父对象为 第一个   
echo 'second commit' | git commit-tree sha12 -p sha11   

git log --stat sha12    

## Git 引用 
需要一个文件来保存sha1值，起一个简单的名字，然后用这个名字来替代原始的sha1值--引用（reference refs) 在 .git/refs下   
```
$ find .git/refs
../.git/refs/
../.git/refs/heads
../.git/refs/heads/master
../.git/refs/remotes
../.git/refs/remotes/mygit
../.git/refs/remotes/mygit/master
../.git/refs/remotes/origin
../.git/refs/remotes/origin/HEAD
../.git/refs/remotes/origin/master
../.git/refs/stash
../.git/refs/tags
../.git/refs/tags/v1.1
../.git/refs/tags/v1.4
../.git/refs/tags/v1.4-1w
```
写入引用位置    
echo "提交对象sha1" > .git/refs/heads/master    
查看提交    
git log --pretty=oneline master     
建议使用写入
git update-ref refs/heads/master    
拉分支  
git update-ref refs/heads/test sha12        
查看    
git log --pretty=oneline test   

> git branch (branch)实际运行 update-ref  

### HEAD 引用 
git branch 时 最新的sha-1在HEAD文件中   
HEAD是符号引用，指向目前所有的分支  
```
# file .git/HEAD
ref: refs/heads/master
```
git checkout test 跟换HEAD指向  
安全查看    
git symbolic-ref HEAD   
设置    
git symbolic-ref HEAD refs/heads/test   

### 标签引用 

4. 标签对象 
包含标签创建信息，一个日期，一段注解信息，以及一个指针  
轻量标签：  
git  update-ref refs/tags/v1.0 sha1     
附注标签：
git tag -a v1.1 sha1 -m "test tag"  

### 远程引用
添加远程版本库并push,git会记录最近一次推送操作是每一个推送操作对应的每一个分支对应的值，并保存在refs/remotes目录下      
git remote add origin git@github.com:acc/simp.git   
git push origin master  
远程引用vs分支 远程引用是只读的     

## 包文件
git gc 进行打包压缩
打包对象是，会查找命名及大小相近的文件，并只保存文件不同版本直接的差异内容。    
查看打包的内容  
git verify-pack -v .git/objects/pack/pack-xxxx.idx  
提交也会gc      



## 引用规格

git remote add origin https://github.comxxx

```
file:.git/config    
[remote "origin"]   
url = https://github.comxxx 
fetch = +refs/head/*:refs/remotes/origin/* 
# 引用规则                      
# <src>:<dst> scr 远程版本 dst对应的远程引用在本地的对应 + 不能快进的情况下也要更新引用                 
```

引用规则由git remote add 自动生成   获取服务器refs/heads/下面的所有引用写入到本地的refs/remotes/origin/中 
git log refs/remotes/origin/master  

只拉取master    
fetch = +refs/heads/master:refs/remotes/origin/master   
指定拉取    
git fetch origin master:refs/remotes/origin/mymaster    

### 引用规则推送 
git push origin master:refs/heads/qa/master     
配置实现 
```
file:.git/config 
[remote "origin"]
push = refs/heads/master:refs/heads/qa/master   
```
强行推送 
git push origin localname:master -f  

### 删除引用
git push origin :topic 

## 传输协议 

### 哑协议 
服务器端不需要针对git特有代码 ，不能push

### 智能协议 
1. 上传数据 
客户端send-pack --- 服务器端receive-pack    

## 维护与数据恢复 
git gc -auto 
### 数据恢复
git reflog  
git log -g  
查看已经的提交情况  
git branch recover-branch sha1 上面的提交找的sha1   


## 环境变量 

1. 全局行为
GIT_EXEC_PATH 找到他的子程序 git-commit git-diff 使用git --exec-path 

GIT_PAGER 命令行输出多页的程序  
GIT_EDITOR 编辑文件启用的编辑器 
2. 版本库位置
GIT_DIR .git位置    
GIT_CEILING_DIRECTIORIES控制找.git行为  
GIT_WORK_TREE 非空版库设置 


