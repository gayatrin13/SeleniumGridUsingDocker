package com.java.commandRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandRunner {

	public static void main(String[] args) {
		cmdExec("docker compose -f docker-compose-grid.yml up", "Registered a node");
	}

	public static void cmdExec(String command, String text) {
//	String command[] = { "C:", "docker compose -f docker-compose-grid.yml up" };
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c","cd \"d:"+command);
		Process p;
		try {
			p = pb.start();
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (line.contains("")) {
					Thread.sleep(2000);
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
