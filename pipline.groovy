pipeline {
    agent any

    stages {
        stage('Pull data from git') {
            steps {
                git branch: 'main', url: 'https://github.com/Sankalpkitey99/flask-app-jenkins-.git'
            }
        }
        stage('Build Image') {
            steps {
                sh 'ls -l'
                sh 'docker build -t kiteysa/myweb .'
            }
        }
        stage('push Image') {
            steps {
                sh 'docker push kiteysa/myweb'
            }
        }
        stage('remove exisiting') {
            steps {
                sh 'docker service rm myservice'
            }
        }
        stage('create service') {
            steps {
                sh 'docker service create  --name myservice -p 4000:4000 --replicas 2 kiteysa/myweb'
            }
        }
    }
}
