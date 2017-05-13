* Firefox version: 42.0
* add in  "c:\Program Files (x86)\Java\jre1.8.0_131\lib\security\java.policy" ->
		grant {

		permission java.security.AllPermission;

		};
* sys var: JAVA_TOOL_OPTIONS=-javaagent:c:/prj/spy-java-agent-mol.git/target/spy-java-agent-mol.jar
* java from control panel: -Djavaplugin.trace=true -Xdebug -Xrunjdwp:transport=dt_shmem,address=debug,server=y,suspend=y
