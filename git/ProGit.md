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
Untracked(无足迹的  -- Add the file --》 staged (已暂存 -- Commit --》 Unmodified (未更变 --Remove the file --》Untracked 

Unmodified-- Edit the file --> Modified(修改 ---stage the file ---> Staged      

### 检查当前文件状态
git status 

