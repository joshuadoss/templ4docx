package pl.jsolve.templ4docx.core;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Test;

import pl.jsolve.templ4docx.variable.BulletListVariable;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variable;
import pl.jsolve.templ4docx.variable.Variables;

/**
 * This test is more of a framework for execution and less of a... you know... test.
 *
 * @author Joshua Doss
 * @version 1.0
 *
 */
public class DocxTest {
	private static String prefix = "#{";
	private static String suffix = "}";
	
	@Test
	public void fillTemplateWithTextVariablesAndSaveTest() {
		InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("template.docx");
		Docx      docx = new Docx(in);
		Variables vars = new Variables();
		
		docx.setVariablePattern(new VariablePattern(prefix, suffix));
		/*
		ArrayList<Variable> list = new ArrayList<Variable>();
		list.add(new TextVariable(prefix+"list placeholder"+suffix, "This"));
		list.add(new TextVariable(prefix+"list placeholder"+suffix, "is a"));
		list.add(new TextVariable(prefix+"list placeholder"+suffix, "list"));
		*/
		
		vars.addTextVariable(new TextVariable(prefix+"placeholder"+suffix, "Header 1"));
		vars.addTextVariable(new TextVariable(prefix+"placeholder2"+suffix, "Bold Text"));
		//vars.addBulletListVariable(new BulletListVariable(prefix+"list placeholder"+suffix, list));
		
		vars.addTextVariable(new TextVariable(prefix+"header placeholder"+suffix, "HEADER!"));
		vars.addTextVariable(new TextVariable(prefix+"footer placeholder"+suffix, "FOOTER!"));
		
		docx.fillTemplate(vars);
		docx.save("/Users/joshua/output.docx");
		
		assertTrue(true);
	}
}
