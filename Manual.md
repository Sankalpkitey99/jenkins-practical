
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
  - check it on localhost:9090/
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
### Step 5
 *On jenkins*
 - New item > "flask-app<name>" > freestyle project > add build > Execute shell
 - In General > Select Github project > add Git Url > add Git Repo link > add */main
 *In the shell add*
 - ##### Step 5.1: Build the Docker image
 - docker build -t <username>/myweb .

 - ##### Step 5.2: Login to Docker Hub using a personal access token
 - echo "<dockertoken>" | docker login -u <dockerusername> --password-stdin

 - ##### Step 5.3: Push the image to Docker Hub
 - docker push <username>/myweb

 - #### Step 5.4: Remove existing container (if it exists) - Fix: 'containerr' → 'container'
 - docker container rm --force flask-app

 - ##### Step 5.5: Run the new container
 - docker container run -d --name flask-app -p 4000:4000 <username>/myweb

### Step 6
 - Now, edit the index file version it v.0.2
 - push it
 - start build
 - you should now check it on localhost again
### Step 7
 - Do it periodically with Build SCM
 - - In General > Select Github project > add Git Url > add Git Repo link > add */main>Build Periodically
 - add * * * * *
 - Version it v 0.3
 - Push it to github
 - check local host
 - It will build periodacally after 1 min each
### Step 8
 - Do it when you verion it or push it
 - In General > Select Github project > add Git Url > add Git Repo link > add */main>Poll SCM
 - add * * * * *
 - Push it to github
 - Every time you push it will update the build

# With Pipeline

### Step 1 
 - New Item> Pipeline > add this pipeline file , you can take help with pipleline syntax
 - pipeline file
pipeline {
    agent any

    stages {
        stage('Pull data from git') {
            steps {
                git branch: 'main', url: 'https://github.com/<usernamegit>/flask-app-jenkins-.git'
            }
        }
        stage('Build Image') {
            steps {
                sh 'ls -l'
                sh 'docker build -t <gitusername>/myweb .'
            }
        }
        stage('push Image') {
            steps {
                sh 'docker push <gitusername>/myweb'
            }
        }
        stage('remove exisiting') {
            steps {
                sh 'docker service rm myservice'
            }
        }
        stage('create service') {
            steps {
                sh 'docker service create  --name myservice -p 4000:4000 --replicas 2 <gitusername>/myweb'
            }
        }
    }
}
 

### Step 10
 - You can here add periodically too. explore it
