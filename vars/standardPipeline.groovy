def call(body) {

  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

	node{
		stage('push'){
			sh '''
		           echo 'This is a test file' > test.txt
		           tar -zcf test.tar.gz test.txt
			'''
			useNexus{
			  nexusArtifactUploader artifacts: [[artifactId: 'nexus-dfs',
			  classifier: 'debug',
			  file: 'test.tar.gz', type: 'gzip-compressed']],
			  credentialsId: ${credentialsId},
			  groupId: 'dfs',
			  nexusUrl: ${NexusUrl},
			  nexusVersion: 'nexus3',
			  protocol: 'http',
			  repository: 'tip-portal',
			  version: "1.0.7"
			}
		}
	}
}
