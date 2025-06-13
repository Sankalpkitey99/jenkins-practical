
# Jenkins Practical 

## Steps to be performed
### Step 1
- Create a directory jenkins in which 
- Create a index.html file and
- Create Docker file
### Step 2
  *build image*
  - docker build -t <username>/myweb .
  *create container*
  - docker container run -d --name myapp -p 4000:4000 <username>/myweb
### Step 3
  *GIT creation*
  - git init
  - git add .
  - git commit -m 'flask-ap'
  - git remote -v
  - git remote add origin <repositorylink>
  - git branch -M main
  - git push -u origin main
### Step 4
 *Add jenkins user*
 - sudo usermod -aG docker jenkins
 - sudo systemctl restart jenkins
### Step 4
 *On jenkins*
 - New item > "flask-app<name>" > freestyle project > add build > Execute shell
 *In the shell add*
 - ##### Step 1: Build the Docker image
 - docker build -t <username>/myweb .

 - ##### Step 2: Login to Docker Hub using a personal access token
 - echo "<dockertoken>" | docker login -u <dockerusername> --password-stdin

 - ##### Step 3: Push the image to Docker Hub
 - docker push <username>/myweb

 - #### Step 4: Remove existing container (if it exists) - Fix: 'containerr' → 'container'
 - docker container rm --force flask-app

 - ##### Step 5: Run the new container
 - docker container run -d --name flask-app -p 4000:4000 <username>/myweb

### Step 1
### Step 1
### Step 1
### Step 1
### Step 1
### Step 1
