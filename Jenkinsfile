pipeline {
    agent any
    
    tools{
         maven 'maven'
         }

    stages
    {
       stage('Build')
        {
           steps
            {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                bat "mvn -Dmaven.test.failure.ignore-true clean package"
            }
            post
            {
               success
               {
                  junit '**/target/surfire-reports/TEST-*.xml'
                  archiveArtifacts 'target/*.jar'
                }
             }     
        }
        
        stage("Deploy to QA") {
            steps {
                echo("deploy to QA")
            }
        }
        
        stage("Regression Automation test") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                git 'https://github.com/Prateek985/OpenCartProject.git'
                bat "mvn clean test -Dsurfire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
            }
        }
        
        stage('Publish Allure Reports')
        {
           steps{
                  script{
                      allure([
                            includeProperties: false,
                               jdk: '',
                               properties: [],
                               reportBuildPolicy: 'ALWAYS',
                               results: [[path: 'allure-results']]
                           ])
                        }
                     }
               }                    
        stage('Publish Extent Report')
        {
           steps{
                  publishHTML([ allowMissing: false,
                               alwaysLinkToLastBuild: false,
                               KeepAll: true,
                               reportDir: 'reports',
                               reportFiles: 'TestExecutionReport.html',
                               reportName: 'HTML Regression Extent Report',
                               reportTitles: ''])
                     }
               } 
        
        stage("Deploy to Stage") {
            steps {
                echo("deploy to Stage")
            }
        }
        
        stage("Sanity Automation test") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                git 'https://github.com/Prateek985/OpenCartProject.git'
                bat "mvn clean test -Dsurfire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml"
            }
        }
        stage('Publish Sanity Extent Report')
        {
           steps{
                  publishHTML([ allowMissing: false,
                               alwaysLinkToLastBuild: false,
                               KeepAll: true,
                               reportDir: 'reports',
                               reportFiles: 'TestExecutionReport.html',
                               reportName: 'HTML Sanity Extent Report',
                               reportTitles: ''])
                     }
               } 
         }
    }
}   