1* Firefox version: 42.0
2* add in  "c:\Program Files (x86)\Java\jre1.8.0_131\lib\security\java.policy" ->
		grant {

		permission java.security.AllPermission;

		};
3* sys var: JAVA_TOOL_OPTIONS=-javaagent:c:/prj/spy-java-agent-mol.git/target/spy-java-agent-mol.jar
4* java from control panel: -Djavaplugin.trace=true -Xdebug -Xrunjdwp:transport=dt_shmem,address=debug,server=y,suspend=y
5* or better both (3+4) in control panel: -javaagent:c:/prj/spy-java-agent-mol.git/target/spy-java-agent-mol.jar -Djavaplugin.trace=true -Xdebug -Xrunjdwp:transport=dt_shmem,address=debug,server=y,suspend=n