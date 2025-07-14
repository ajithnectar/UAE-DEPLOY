pipeline {
  agent any

  environment {
    TOMCAT_URL            = 'http://api-nec-prod-1587031398.me-central-1.elb.amazonaws.com:8080'
    TOMCAT_APP_NAME       = 'user_project'
    TOMCAT_CREDENTIALS_ID = 'ca5b05d8-fb7e-42c5-9e47-65a8fb119b82'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build WAR') {
      agent {
        docker {
          image 'maven:3.9.6-eclipse-temurin-11'
          args  '-u root'
          reuseNode true
        }
      }
      steps {
        // this will build everything from your root pom, including deployOne
        sh 'mvn clean install -Dmaven.test.skip=true -P docker'
      }
    }

    stage('Deploy to Tomcat') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: env.TOMCAT_CREDENTIALS_ID,
          usernameVariable: 'TOMCAT_USER',
          passwordVariable: 'TOMCAT_PASS'
        )]) {
          script {
        // capture the WAR filename via a shell glob
        def warPath = sh(
          script: "ls deployOne/target/*.war",
          returnStdout: true
        ).trim()

        echo "Deploying WAR: ${warPath}"

        def deployUrl = "${TOMCAT_URL}/manager/text/deploy?path=/${TOMCAT_APP_NAME}&update=true"
        sh """
          curl --fail -v --upload-file ${warPath} \\
               --user $TOMCAT_USER:$TOMCAT_PASS \\
               '${deployUrl}'
        """
      }
        }
      }
    }
  }

  post {
    always { cleanWs() }
    success { echo '✅ WAR deployed to Tomcat successfully!' }
    failure { echo '❌ Deployment failed.' }
  }
}

