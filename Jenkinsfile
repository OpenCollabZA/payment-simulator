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
						maven: 'maven-3.5.2',
						jdk: 'jdk1.8.0u162',
						// Maven settings.xml file defined with the Jenkins Config File Provider Plugin
						// Maven settings and global settings can also be defined in Jenkins Global Tools Configuration
						mavenSettingsConfig: 'assemble-maven-settings',
						mavenLocalRepo: '.repository') {

					sh "mvn clean deploy -B -U"
				}
			}
		}
	}
	post {
		always {
			cleanWs()
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
