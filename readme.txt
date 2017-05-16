1* Firefox version: 42.0
2* add in  "c:\Program Files (x86)\Java\jre1.8.0_131\lib\security\java.policy" ->
		grant {

		permission java.security.AllPermission;

		};
3* sys var: JAVA_TOOL_OPTIONS=-javaagent:c:/prj/spy-java-agent-mol.git/target/spy-java-agent-mol.jar
4* java from control panel: -Djavaplugin.trace=true -Xdebug -Xrunjdwp:transport=dt_shmem,address=debug,server=y,suspend=y
5* or better both (3+4) in control panel: -javaagent:c:/prj/spy-java-agent-mol.git/target/spy-java-agent-mol.jar -Djavaplugin.trace=true -Xdebug -Xrunjdwp:transport=dt_shmem,address=debug,server=y,suspend=n
6* export fiddler cert: Tools -> Fiddler Options... -> HTTPS -> Export Root Certificate to Desktop
7* make java type key store and import the certificate: <JDK_Home>\bin\keytool.exe -import -file C:\Users\<Username>\Desktop\FiddlerRoot.cer -keystore FiddlerKeystore -alias Fiddler
8* add for Fiddler (with fiddler certificate export made before): -DproxySet=true -Dhttp.proxyHost=127.0.0.1 -Dhttps.proxyHost=127.0.0.1 -Dhttp.proxyPort=8888 -Dhttps.proxyPort=8888 -Djavax.net.ssl.trustStore=C:\Users\mihai\FiddlerKeystore -Djavax.net.ssl.trustStorePassword=changeit
9* !!!! important: add: d:/mol_conversation/ folder !!!