package by.epam.bohnat.provider.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filters requests and responses by setting the character encoding to the value
 * defined in web.xml.
 * 
 * @author Denis Bohnat
 * @version 1.0
 */
public class CharsetFilter implements Filter {

	/**
	 * The name of the parameter whose value is the required encoding
	 */
	private static final String ENCODING_PARAM = "character-encoding";

	/**
	 * Required encoding
	 */
	private String encoding;

	@Override
	public void destroy() {

	}

	/**
	 * Initializing the filter: extracting the required encoding using the
	 * {@code FilterConfig}.
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter(ENCODING_PARAM);
	}

	/**
	 * Set the required encoding in the body of the HTTP request and the HTTP
	 * response. The control is passed along the filter chain.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

}
