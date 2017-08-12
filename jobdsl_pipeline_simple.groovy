import javaposse.jobdsl.dsl.DslFactory

DslFactory factory = this

factory.job("simple-maven-boot-build") {
    deliveryPipelineConfiguration("Build")
    triggers { githubPush() }
    scm { github("applied-continuous-delivery-livelessons/simple-maven-boot") }
    wrappers { colorizeOutput() }
    steps { shell("./mvnw clean install") }
    publishers {
        archiveJunit("**/target/surefire-reports/TEST-*.xml")
        archiveArtifacts("target/*.jar")
        downstreamParameterized {
            trigger("simple-maven-boot-deploy") { triggerWithNoParameters() }
        }
    }
}

factory.job("simple-maven-boot-deploy") {
    deliveryPipelineConfiguration("Deployment")
    scm { github("applied-continuous-delivery-livelessons/simple-maven-boot") }
    steps { shell('echo "Deploying artifact"') }
}

factory.deliveryPipelineView("simple-maven-boot-pipeline-view") {
    pipelines {
        component("Deployment", "simple-maven-boot-build")
    }
    allowPipelineStart()
    showChangeLog()
    allowRebuild()
    showDescription()
    showPromotions()
    showTotalBuildTime()
}