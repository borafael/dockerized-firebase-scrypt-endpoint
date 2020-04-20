package com.frubana.core.services.fscrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class FirebaseScryptService {

	public String hash(String raw, String key, String salt, String separator, int rounds, int memCost) throws IOException {
		return runFScryptCommand(raw, key, salt, separator, rounds, memCost);
	}

	private String runFScryptCommand(String raw, String key, String salt, String separator, int rounds, int memCost)
			throws IOException {

		String command = String.format("/usr/local/bin/scrypt \"%s\" \"%s\" \"%s\" %d %d -P <<< \"%s\"",
				key, salt, separator, rounds, memCost, raw);

		String[] cmd = { "bash", "-c", command };
		Process process = new ProcessBuilder(cmd).start();

		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String line;
		String hash = null;
		while ((line = br.readLine()) != null) {
			hash = line;
		}

		return hash;
	}
}
