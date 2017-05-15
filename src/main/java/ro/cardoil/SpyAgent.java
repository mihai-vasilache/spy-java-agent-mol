/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.cardoil;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author mihai
 */
public class SpyAgent implements ClassFileTransformer {

	private JarFile jarFile;
	private static PrintWriter printFile;

	public SpyAgent(String jarLocation) {
		try {
			jarFile = new JarFile(jarLocation);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void premain(String agentArguments, Instrumentation instrumentation) {
		try {
			agentArguments = "c:\\prj\\magyar-olaj-applet\\target\\magyar-olaj-applet-1.1.jar";
			printFile = new PrintWriter(new FileOutputStream("d:\\_applet"), true);
			printFile.println("INIT");
			printFile.flush();
			System.out.println("*******************************************");
			System.out.println("Java agent loaded");
			System.out.println("Jar location: " + agentArguments);
			System.out.println("*******************************************");
//			System.out.println("Load class: " + hu.polygon.jform.client.Session.class.getName());
			instrumentation.addTransformer(new SpyAgent(agentArguments));
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(printFile);
			printFile.flush();
		}
	}

	@Override
	public byte[] transform(ClassLoader loader, String className, Class redefiningClass, ProtectionDomain domain, byte[] bytes) throws IllegalClassFormatException {
		try {
//			printFile.println("transform: " + className);
//			printFile.flush();
//			System.out.println("Transform class: " + className);
			
			// !!!!!!!!!!!
			//if(1==1){return null;};
			
			if (!className.startsWith("hu/polygon/jform/client") && !className.startsWith("a/a/a")) {
				return null;
			}
			
			if(!className.equals("hu/polygon/jform/client/Session")) {
				return null;
			}

			byte classByte[];
			System.out.println("Reload class: " + className);
			
			printFile.println("ACCEPT: " + className);
			printFile.flush();

			JarEntry entry = jarFile.getJarEntry(className + ".class");
			InputStream is = jarFile.getInputStream(entry);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			int nextValue = is.read();
			while (-1 != nextValue) {
				byteStream.write(nextValue);
				nextValue = is.read();
			}
			classByte = byteStream.toByteArray();
			return classByte;
		} catch (Exception e) {
			throw new RuntimeException("class transform exception", e);
		}
	}

}
