package com.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BusinessLogic {
	void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException;
	void putPaths(String pathimg, String path) throws IOException; 
}
