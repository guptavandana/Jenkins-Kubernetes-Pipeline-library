def call(Closure body) {
  withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'NexusPassword', usernameVariable: 'NexusUser')]) {
    withEnv([ 'NexusUrl=http://47.74.228.246:30002'
      ]) {
      body()
    }
  }
}
