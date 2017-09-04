package by.epam.bohnat.provider.tag;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Custom tag that displays drop-down list to select the number of items
 * displayed on one page.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class FormatElementsPerPage extends TagSupport {

	private static final String ELEMENTS_PER_PAGE_ATTR = "elementsPerPage";

	private static final String SELECT_OPEN_TAG = "<select name=\"elementsPerPage\" onChange=\"this.form.submit();\">";

	private static final String OPTION_OPEN_TAG = "<option value=\"";

	private static final String SELECTED_ATTR = "\" selected>";

	private static final String NO_SELECTED_ATTR = "\">";

	private static final String OPTION_END_TAG = "</option>";

	private static final String SELECT_END_TAG = "</select>";

	private static final String DELIMETER = ",";

	private String variants;

	public String getVariants() {
		return variants;
	}

	public void setVariants(String variants) {
		this.variants = variants;
	}

	@Override
	public int doStartTag() throws JspException {
		int[] varArray = parseVariants();
		int elementsPerPageReq = Integer.parseInt(pageContext.getRequest().getParameter(ELEMENTS_PER_PAGE_ATTR));

		String selectString = constructSelectString(elementsPerPageReq, varArray);
		try {
			pageContext.getOut().write(selectString.toString());
		} catch (IOException e) {
			throw new JspException("Unable to write string processed by tag", e);
		}
		return SKIP_BODY;
	}

	private String constructSelectString(int elementsPerPage, int[] varArray) {
		StringBuilder selectString = new StringBuilder(SELECT_OPEN_TAG);
		for (int variant : varArray) {
			String optionString = null;
			if (variant == elementsPerPage) {
				optionString = OPTION_OPEN_TAG + variant + SELECTED_ATTR + variant + OPTION_END_TAG;
			} else {
				optionString = OPTION_OPEN_TAG + variant + NO_SELECTED_ATTR + variant + OPTION_END_TAG;
			}
			selectString.append(optionString);
		}
		selectString.append(SELECT_END_TAG);
		return selectString.toString();
	}

	private int[] parseVariants() throws JspException {
		StringTokenizer tokenizer = new StringTokenizer(variants, DELIMETER);
		int[] varArray = new int[tokenizer.countTokens()];
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			try {
				varArray[i] = Integer.parseInt(tokenizer.nextToken());
				i++;
			} catch (NumberFormatException e) {
				throw new JspException("Invalid elementsPerPage string format in tag", e);
			}
		}
		return varArray;
	}

}
