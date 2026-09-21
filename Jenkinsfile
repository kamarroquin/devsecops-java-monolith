pipeline {

    agent any

    environment {
        APP_NAME = 'devsecops-java-monolith'

        SONAR_HOST_URL = 'http://sonarqube:9000'

        ARTIFACTORY_URL = 'http://artifactory:8082/artifactory'

        ARTIFACTORY_REPOSITORY = 'devsecops-local'
    }

    stages {

        stage('Build') {
            steps {
                echo '=============================='
                echo ' STAGE: BUILD'
                echo '=============================='

                sh 'chmod +x mvnw'

                sh './mvnw clean compile'
            }
        }

        stage('Testing - JUnit + JaCoCo') {
            steps {
                echo '=============================='
                echo ' STAGE: TESTING'
                echo '=============================='

                sh './mvnw test'
            }

            post {
                always {
                    junit allowEmptyResults: true,
                          testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo '=============================='
                echo ' STAGE: SONARQUBE'
                echo '=============================='

                withCredentials([
                    string(
                        credentialsId: 'sonarqube-token',
                        variable: 'SONAR_TOKEN'
                    )
                ]) {

                    sh '''
                        ./mvnw sonar:sonar \
                        -Dsonar.projectKey=devsecops-java-monolith \
                        -Dsonar.projectName=devsecops-java-monolith \
                        -Dsonar.host.url=${SONAR_HOST_URL} \
                        -Dsonar.token=${SONAR_TOKEN}
                    '''
                }
            }
        }

        stage('Package') {
            steps {
                echo '=============================='
                echo ' STAGE: PACKAGE'
                echo '=============================='

                sh './mvnw package -DskipTests'
            }
        }

        stage('Verify Artifact') {
            steps {
                echo '=============================='
                echo ' VERIFY ARTIFACT'
                echo '=============================='

                sh 'ls -lah target/'

                sh '''
                    test -f target/devsecops-java-monolith-0.0.1-SNAPSHOT.jar
                '''
            }
        }

        stage('Upload to Artifactory') {
            steps {
                echo '=============================='
                echo ' STAGE: ARTIFACTORY'
                echo '=============================='

                withCredentials([
                    usernamePassword(
                        credentialsId: 'artifactory-credentials',
                        usernameVariable: 'ARTIFACTORY_USER',
                        passwordVariable: 'ARTIFACTORY_PASSWORD'
                    )
                ]) {

                    sh '''
                        curl --fail \
                        -u "${ARTIFACTORY_USER}:${ARTIFACTORY_PASSWORD}" \
                        -T target/devsecops-java-monolith-0.0.1-SNAPSHOT.jar \
                        "${ARTIFACTORY_URL}/${ARTIFACTORY_REPOSITORY}/${APP_NAME}/0.0.1-SNAPSHOT/devsecops-java-monolith-0.0.1-SNAPSHOT.jar"
                    '''
                }
            }
        }

    }

    post {

        success {
            echo '=========================================='
            echo ' PIPELINE EJECUTADO CORRECTAMENTE'
            echo '=========================================='
        }

        failure {
            echo '=========================================='
            echo ' EL PIPELINE HA FALLADO'
            echo '=========================================='
        }

        always {
            archiveArtifacts artifacts: 'target/*.jar',
                             fingerprint: true,
                             allowEmptyArchive: true
        }
    }
}