pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo("Build the project")
            }
        }
        
        stage('Run Security Scan') {
            steps {
                echo("Security testing using burp suite")
            }
        }
        stage("Run Unit test") {
            steps {
                echo("run UTs")
            }
        }
        stage("Run Integration test") {
            steps {
                echo("run ITs")
            }
        }
        
        stage("Deploy to dev") {
            steps {
                echo("deploy to dev")
            }
        }
         stage("Deploy to QA") {
            steps {
                echo("deploy to QA")
            }
        }
         stage("Run Regression test cases on QA") {
            steps {
                echo("Run Regression test on QA")
            }
        }
        stage("Deploy to stage") {
            steps {
                echo("deploy to stage")
            }
        }
        stage("Run Sanity test cases on QA") {
            steps {
                echo("Run Sanity test cases on QA")
            }
        }
        stage("Deploy to PROD") {
            steps {
                echo("Deploy to PROD")
            }
        }
    }
}