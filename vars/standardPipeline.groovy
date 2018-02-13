def call(body) {

  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

   def namespace
   stage('inpuat'){
   	namespace = input id: 'namespace', message: 'Enter Namespace', ok: 'Proceed?', parameters: [string(defaultValue: '', description: '', name: 'namespace')]
   }


	node{
              stage('ask'){
             sh '''
                echo ${namespace}
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
