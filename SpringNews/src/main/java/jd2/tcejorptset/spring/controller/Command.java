package jd2.tcejorptset.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Deprecated
public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
}
