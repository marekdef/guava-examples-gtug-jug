package org.gtug.lodz.guava.strings;

import org.junit.Test;

import com.google.common.base.Splitter;

public class SplitterTest {
	@Test
	public void testSplit(){
		
		Iterable<String> split = Splitter.on(";").on(",").split("JUG; GTUG; , Łódź;; Guava");
	
	}
}
