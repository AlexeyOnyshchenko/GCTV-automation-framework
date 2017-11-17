Install environment to be able to execute tests and build report:

This project is Java based project that use maven build tool.
So first of all we need to install java. Java version 7 will be enough.

1. Install Java

More instructions could be found here: 

http://www.java.com/en/download/help/download_options.xml

We need JRE and JDK to be installed. It can be downloaded here: http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
You need to accept license and download jdk-7uXX-your_OS file and install it. Be sure you have selected correct arch version (ex. x64 for windows).


Since java is installed on your machine, you need to add and modify some system and user variables. 

Create JAVA_HOME variable C:\Program Files\Java\jdk1.7.0_67 for example - if you use windows. 

For other systems (Mac): http://www.mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/

For other systems (Linux): http://www.cyberciti.biz/faq/linux-unix-set-java_home-path-variable/

For windows add java to path: https://www.itechtics.com/wp-content/uploads/2013/07/clip_image001.jpg

Check Java is installed:

"java -version" in terminal (cmd)

2. Install Maven build tool
Maven is a tool for software project management.

Based on a model of the project (POM = project object model), Maven can manage a project’s build, reporting and documentation from a central piece of information

Download url:  http://maven.apache.org/download.cgi
    1.Unzip the distribution archive, i.e. apache-maven-3.x.x-bin.zip to the directory you wish to install Maven 3.x.x. 
    
    These instructions assume you chose C:\Program Files\Apache Software Foundation. The subdirectory apache-maven-3.x.x will be created     from the archive.
    
    2.Add MAVEN_HOME variable with value “C:\Program Files\Apache Software Foundation. The subdirectory apache-maven-3.x.x”
    
    3.Add value to the path “%MAVEN_HOME%\bin”
        in case of MAC OS (in your .bash_profile after downloading maven): 
        (for example)
        export M2_HOME=/Users/user/Tools/apache-maven-3.2.5
        export M2=$M2_HOME/bin
        export PATH=$M2:$PATH
    
    4.Go to cmd(terminal) and check mvn -version


For Web Execution: Install Chrome and Firefox browsers
    http://www.google.com/chrome/
    https://www.mozilla.org/en-US/firefox/new/

Now you are done and be able to execute web tests from project root with following:

to run tests:
mvn clean test -Dprovider=local -DsuiteXmlFile=suite-chrome.xml

to generate report after tests:
mvn site

to open report:
*/target/graph-test-report/index.html
