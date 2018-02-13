def call(body) {

  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

	node{
	      def namespace = "${config.namespace}"
              stage('ask'){
             sh '''
                echo "${namespace}"
			''' 

		}
              stage('test'){

		sh "echo test"

              }
	      stage('push'){
			sh """
		           echo 'This is a test file' > test.txt
		           tar -zcf test.tar.gz test.txt
		"""
		useNexus{
                         echo env.NexusUrl
		        echo env.NexusUser
	
		}

		}
	}
}
