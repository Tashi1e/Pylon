package jd2.tcejorptset.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AttributesContainer {
	
	private Map <String, Object> modelAttributesContainer = new HashMap<String, Object>();
	
	public Map <String, Object> getContainer(){
		return modelAttributesContainer;
	}
}
