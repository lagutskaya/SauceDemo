pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven 3.9.6"
    }

    environment {
        allureResults = 'target/allure-results'
        allureReportPolicy = 'ALWAYS'
        mailRecipients = 'polinalagutskaya@gmail.com'
    }

    parameters {
        choice(choices: ['chrome', 'firefox', 'edge'], name: 'BROWSER')
    }

    stages {
        stage('Run tests') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/lagutskaya/SauceDemo.git'

                // Run Maven on a Unix agent.
                sh "mvn clean test -Dbrowser=${params.BROWSER}"
            }
        }

        stage('Run Allure Reports') {
            steps {
                allure([
                    includeProperties: true,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: "${env.allureReportPolicy}",
                    results: [[path: "${env.allureResults}"]]
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline is complete'
            emailext (
                subject: "CMXQA.TESTS Отчет прогона тестов [${env.BUILD_NUMBER}]",
                body: """Подробный allure-отчет: <a href='${env.BUILD_URL}allure/'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>""",
                to: "${env.mailRecipients}"
            )
        }
    }
}