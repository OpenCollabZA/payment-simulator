pipeline {
	agent any
	triggers {
		pollSCM('H/15 * * * *')
	}
	options {
		buildDiscarder(logRotator(numToKeepStr : '5'))
		ansiColor('xterm')
		timestamps()
		timeout(time: 2, unit: 'HOURS')
	}
	stages {
		stage ("checkout"){
			steps{
				checkout scm
			}
		}
		stage ("build"){
			steps{
				withMaven(
						// Maven installation declared in the Jenkins "Global Tool Configuration"
						maven: 'maven-3.5.4',
						jdk: 'jdk1.8.0u181',
						// Maven settings.xml file defined with the Jenkins Config File Provider Plugin
						// Maven settings and global settings can also be defined in Jenkins Global Tools Configuration
						mavenSettingsConfig: 'opencollab-maven-settings',
						mavenLocalRepo: '.repository') {

					sh "mvn clean deploy -B -U"
				}
			}
		}
		stage ("docker"){
			when {
				branch 'master'
			}
			steps {
				fileOperations([
						fileRenameOperation(
								source : "services/target/payment-simulator.jar",
								destination : "scripts/docker/payment-simulator.jar"),
				])

				withDockerRegistry([url: 'https://opencollab-docker.opencollab.co.za/', credentialsId: 'ci-nexus3']) {
					sh "docker build --no-cache --force-rm -t opencollab-docker.opencollab.co.za/opencollab/payment-simulator:latest ${WORKSPACE}/scripts/docker"
					sh "docker push opencollab-docker.opencollab.co.za/opencollab/payment-simulator:latest"
				}
			}
		}
	}
	post {
		always {
			cleanWs()
			// Cleanup docker images on server
			sh "docker rmi opencollab-docker.opencollab.co.za/opencollab/payment-simulator:latest"
		}
		changed {
			emailext([subject: '$DEFAULT_SUBJECT',
						body: '$DEFAULT_CONTENT',
						recipientProviders: [
							[$class: 'CulpritsRecipientProvider'],
							[$class: 'DevelopersRecipientProvider'],
							[$class: 'RequesterRecipientProvider']
						],
						replyTo: '$DEFAULT_REPLYTO',
						to: '$DEFAULT_RECIPIENTS'
				])
		}
	}
}
