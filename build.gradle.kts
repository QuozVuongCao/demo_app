repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jcenter.bintray.com/") }
    maven {
        url = uri("http://packages.ros.org/ros/ubuntu")
        isAllowInsecureProtocol = true // Cho phép HTTP
    }
}
