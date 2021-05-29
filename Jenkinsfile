pipeline {
    agent any
    tools { 
        maven 'MAVEN_3_8_1' 
        jdk 'JDK_13_0_2' 
    }
    
    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'MAVEN_3_8_1') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                withMaven(maven : 'MAVEN_3_8_1') {
                    bat 'mvn test'
                }
            }
        }


        stage ('Package Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_8_1') {
                    bat 'mvn package'
                }
            }
        }

        // stage('Deploy Tomcat') {
        //     steps {
        //         echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL} direcion ${env.WORKSPACE}"  
        //         withMaven(maven : 'MAVEN_3_8_1') {
        //             bat '"C:\\Program Files\\Git\\mingw64\\bin\\curl.exe" -T ".\\target\\demo-1.0.jar" "http://tomcat:tomcat@localhost:9090/manager/text/deploy?path=/classes&update=true"'
        //         } 
        //     }
        // }
    }
}