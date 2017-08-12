node("") {
    stage("build") {
        git("https://github.com/applied-continuous-delivery-livelessons/simple-maven-boot.git")
        sh("./mvnw clean install")
    }
    stage("deploy") {
        echo("Deploying the artifact")
    }
    stage("results") {
        junit("**/target/surefire-reports/TEST-*.xml")
        archive("target/*.jar")
    }
}