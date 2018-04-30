wokepace --add ---> 暂存区 --commit --> 本地仓库 -- push --> 远程仓库  -- pull --> wokepace     
远程仓库 -- fach --> 本地仓库   

# 图形化工具 source tree 

# 将本地文件变成Git仓库
git init 

# 添加文件到仓库 
加到本地  缓冲区
## git add
git add readme.txt 
-A // 添加所以跟改 
\* . //添加新建&更改 不包括删除
-u //修改&删除 非新建
## git commit 告诉Git 加到本地仓库
git commit -m "wrote a readme file"


# 版本控制
-

## 查看状态 status
git status
## 对比 diff
git diff
## 在commit前撤销add:reset,回滚commit前  
git reset <file> 
git reset 
git reset --hard HEAD^ | HEAD^^ | HEAD~100 回滚的版本 // git log
git reset --hard id //返回add&commit 滚回版本
## 在add和commit前撤销修改,回滚分支 checkout
git checkout -- REAME.md

## 查看提交日志 log
git log 
再一行--pretty=oneline  

### 查看分支上的指针
--decorate  

## 记录命令 reflog
git reflog

# 工作区&版本库(本地)

## 工作区
工作目录
## 版本库
.git = stage暂存区 +  master分支
第一步 git add 把文件放到暂存区 
第二步 git commit 把暂存区提交到当前分支

# 删除
rm file
git rm file 
git commit -m

# 远程仓库 GitHub

1. 记录ssh-key
$ ssh-keygen -t rsa -C "youremail@example.com"
$ ls .ssh id_rsa 私钥 is_rsa.pub 公钥
2. 登入到GitHub - Account settings - add SSH key - key里粘贴id_rsa.pub匹配成功
3. github建立库 链接到github    
git remote -v 列出远程仓库的URL     
git remote add mygit git@github.com:geelian/mygit.git 关联  
git remote set-url origint <url>  改url     
4. 第一次推送到github上
git push -u mygit master
5. 推送到github上
git push mygit master

6. 同步远程文件到本地 工作区
git pull--rebase orgin master 
git pull --all

## 克隆
git clone git@github.com:geelian/mygit.git

# 分支&合并
分支管理
## 建立分支
git checkout <name> 切换分支    
git checkout -b dev // 建立dev分支 切换到dev
= git branch dev + git checkout dev 
 建立分支		切换分支
## 查看当前分支
git branch
## 合并分支dev
出现问题手动合并
git merge dev
git merge --no-ff -m "merge with no-ff" dev
禁止Fast forward
## 删除分支
git branch -d dev
## 查看分支合并图
git log --graph

# stash
保存现场

# 跟换远程仓库url
git remote set-url origin https://


# 强制push远程分支 push 

git clone  repo

git checkout -b localname origin/xxxxxx-develop

vim README.md

git add

git commit

git push origin localname:master  -f


# 代码合并 
git pull    
复制    
git checkout file 回滚本代码    
git pull
粘贴    
git push 

