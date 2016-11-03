package pl.jsolve.templ4docx.executor;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.extractor.VariableFinder;
import pl.jsolve.templ4docx.insert.Insert;
import pl.jsolve.templ4docx.variable.Variables;

/**
 * This class uses variableFinder to generate list of Insert, and then begins the process of filling a template.
 * @author Lukasz Stypka
 */
public class DocumentExecutor {

    private VariableFinder variableFinder;
    private Variables variables;

    public DocumentExecutor(Variables variables) {
        this.variableFinder = new VariableFinder(variables);
        this.variables = variables;
    }

    /**
     * This method replaces variables in docx template. Note, that before executing this method you should invoke
     * DocumentCleaner.clean method
     * @param docx Docx object with opened .docx file
     */
    public void execute(Docx docx) {
        List<Insert> inserts = variableFinder.find(docx.getXWPFDocument(), variables);
        
        for (XWPFHeader header : docx.getXWPFDocument().getHeaderList()) {
            inserts.addAll(variableFinder.find(header, variables));
        }
        
        for (XWPFFooter footer : docx.getXWPFDocument().getFooterList()) {
            inserts.addAll(variableFinder.find(footer, variables));
        }
        
        variableFinder.replace(inserts);
    }

}
