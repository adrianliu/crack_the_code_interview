# crack_the_code_interview
java version

Create the remote repository, and get the URL such as 

git@github.com:/youruser/somename.git or https://github.com/youruser/somename.git

If your local GIT repo is already set up, skips steps 2 and 3

Locally, at the root directory of your source, git init

Locally, add and commit what you want in your initial repo 
(for everything, 
git add . 
git commit -m 'initial commit comment'

to attach your remote repo with the name 'origin' (like cloning would do)
git remote add origin [URL From Step 1]

Execute git pull origin master to pull the remote branch so that they are in sync.
to push up your master branch (change master to something else for a different branch):
git push origin master

