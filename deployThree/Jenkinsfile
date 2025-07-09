pipeline {
    agent any
 
    environment {
        AWS_REGION     = 'me-central-1'
        AWS_ACCOUNT_ID = '017691937205'
        ECR_REPO       = 'api'
        IMAGE_TAG      = "${env.BUILD_NUMBER}"
        // Jenkins credentials ID for AWS (IAM user with permissions to ECR & ECS)
        AWS_CREDENTIALS_ID = 'c1b403c0-d505-448f-abd8-88d650c97b36'
    }
 
    stages {
        stage('Checkout') {
            steps {
                // Checkout source code from SCM
                checkout scm
            }
        }
 
        stage('Login to Amazon ECR') {
            steps {
                withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY',
                    credentialsId: env.AWS_CREDENTIALS_ID
                ]]) {
                    // Authenticate Docker to the ECR registry
                    sh "aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
                }
            }
        }
      // stage('build WAR'){
        //  steps{
          //       sh "./mvnw clean install -Dmaven.test.skip=true  -P docker"
//	}	}
       stage('Build WAR') {
   	 agent {
         docker {
            image 'maven:3.9.6-eclipse-temurin-11'   // Java 11 + Maven 3.9
            args  '-u root'           // cache Maven repo
            reuseNode true                           // runs on same worker node
        }
    }
    steps {
        sh './mvnw clean install -Dmaven.test.skip=true -P docker'
   	 }
	}
 
        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${ECR_REPO}:${IMAGE_TAG} ."
            }
        }
 
        stage('Tag Image') {
            steps {
                sh "docker tag ${ECR_REPO}:${IMAGE_TAG} ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO}:${IMAGE_TAG}"
            }
        }
 
        stage('Push to ECR') {
            steps {
                sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO}:${IMAGE_TAG}"
            }
        }
 
        stage('Deploy to ECS') {
            steps {
                withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY',
                    credentialsId: env.AWS_CREDENTIALS_ID
                ]]) {
                    // Force new deployment in ECS service to pick up new image
                    sh "aws ecs update-service --cluster nectar-cluster --service nec-api-latest-service-gkajp0a1 --force-new-deployment --region ${AWS_REGION}"
                }
            }
        }
    }
 
    post {
        always {
            // Cleanup workspace after build
            cleanWs()
        }
        success {
            echo 'Deployment succeeded!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}