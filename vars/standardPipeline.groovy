def call(body) {

  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

	node{
              stage('test'){

		sh "echo test"

              }
	      stage('push'){
			sh """
		           echo 'This is a test file' > test.txt
		           tar -zcf test.tar.gz test.txt
		"""
sleep(2)
		useNexus{
                         echo env.NexusUrl
		echo env.NexusUser
	
		}
sleep(2)
	
//			useNexus{
			  nexusArtifactUploader artifacts: [[artifactId: 'dfs',
			  classifier: 'debug',
			  file: 'test.tar.gz', type: 'gzip-compressed']],
			  credentialsId: 'nexus',
			  groupId: 'dfs',
			  nexusUrl: 'http://47.74.228.246:30002',
			  nexusVersion: 'nexus3',
			  protocol: 'http',
			  repository: 'tip-portal',
			  version: "1.0.7"
//			}

		}
	}
}
