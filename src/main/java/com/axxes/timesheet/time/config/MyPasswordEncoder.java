package com.axxes.timesheet.time.config;

public class MyPasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
	@Override
	public String encode(CharSequence charSequence) {
		return charSequence.toString();
	}

	@Override
	public boolean matches(CharSequence charSequence, String s) {
		return true;
	}
}
