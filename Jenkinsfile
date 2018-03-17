pipeline {
  agent any

    stages {

        stage('Unit Test') {
            steps {
                echo 'Running gradle clear and test...'
                sh 'ls -al'
                sh 'pwd'
                sh './gradlew --version'
                sh './gradlew clean'
                sh './runApp.sh test'
            }
        }

        stage('Build') {
            steps {
                echo 'Building now'
                sh './gradlew --version'
                sh './gradlew clean bootRepackage'
                sh 'ls -al'
            }
        }

      stage('Deploy') {
        steps {
          echo 'Deploying....'
        }
      }

    }
}