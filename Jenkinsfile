pipeline {
  agent any

    stages {

      stage('Build') {
        steps {
          echo 'Building..'
          sh './runApp.sh test'
            sh './gradlew --version'
        }
      }

      stage('Test') {
        steps {
          echo 'Testing..'
        }
      }

      stage('Deploy') {
        steps {
          echo 'Deploying....'
        }
      }

    }
}