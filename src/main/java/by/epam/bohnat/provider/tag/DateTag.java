package by.epam.bohnat.provider.tag;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Custom tag that displays the current date.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class DateTag extends TagSupport {

	private LocalDate date;

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int doStartTag() throws JspException {
		if (date == null) {
			return SKIP_BODY;
		}

		try {
			Locale locale = pageContext.getRequest().getLocale();
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
			pageContext.getOut().write(df.format(Date.valueOf(date)));
		} catch (IOException e) {
			throw new JspException(e.getMessage(), e);
		}
		return SKIP_BODY;
	}

}
