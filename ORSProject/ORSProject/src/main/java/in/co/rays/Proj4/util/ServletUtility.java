package in.co.rays.Proj4.util;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.Proj4.bean.BaseBean;
import in.co.rays.Proj4.controller.BaseCtl;
import in.co.rays.Proj4.controller.ORSView;
import in.co.rays.Proj4.exception.ApplicationException;

/**
 * Utility class for common servlet operations such as forwarding, redirecting,
 * managing request attributes (error/success messages, beans, lists), and exception handling.
 * <p>
 * Provides static methods to:
 * <ul>
 *   <li>forward or redirect to views</li>
 *   <li>set and retrieve messages or beans from request scope</li>
 *   <li>handle pagination parameters</li>
 *   <li>redirect to a centralized error view on exception</li>
 * </ul>
 * </p>
 * <p>
 * The {@link #forward(String, HttpServletRequest, HttpServletResponse)} method
 * wraps {@link RequestDispatcher#forward(ServletRequest, ServletResponse)},
 * which is a server-side transfer: the browser URL remains unchanged and
 * the original request attributes stay intact :contentReference[oaicite:1]{index=1}.
 * In contrast, {@link #redirect(String, HttpServletRequest, HttpServletResponse)}
 * uses {@link HttpServletResponse#sendRedirect(String)}, which triggers a
 * new client-side request and clears request-scope attributes :contentReference[oaicite:2]{index=2}.
 * </p>
 *
 * @author Sanjana Gangrade
 * @version 1.0
 * @since 1.0
 */

public class ServletUtility {

    /**
     * Forwards the request to another resource within the same application.
     *
     * @param page     the JSP or servlet path to forward to
     * @param request  the current HTTP request
     * @param response the HTTP response
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	 /**
     * Redirects the client to a different URL, causing the browser to issue a new request.
     *
     * @param page     the URL to redirect to (relative or absolute)
     * @param request  the current HTTP request
     * @param response the HTTP response
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(page);
	}

	/**
	 * @param property
	 * @param request
	 * @return
	 */
	
	public static String getErrorMessage(String property, HttpServletRequest request) {

		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * @param property
	 * @param request
	 * @return
	 */
	
	public static String getMessage(String property, HttpServletRequest request) {
		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * @param msg
	 * @param request
	 */
	public static void setErrorMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_ERROR, msg);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getErrorMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * @param msg
	 * @param request
	 */
	public static void setSuccessMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getSuccessMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * @param bean
	 * @param request
	 */
	public static void setBean(BaseBean bean, HttpServletRequest request) {
		request.setAttribute("bean", bean);
	}

	/**
	 * @param request
	 * @return
	 */
	public static BaseBean getBean(HttpServletRequest request) {
		return (BaseBean) request.getAttribute("bean");
	}

	/**
	 * @param property
	 * @param request
	 * @return
	 */
	public static String getParameter(String property, HttpServletRequest request) {
		String val = (String) request.getParameter(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * @param list
	 * @param request
	 */
	public static void setList(List list, HttpServletRequest request) {
		request.setAttribute("list", list);
	}

	/**
	 * @param request
	 * @return
	 */
	public static List getList(HttpServletRequest request) {
		return (List) request.getAttribute("list");
	}

	/**
	 * @param pageNo
	 * @param request
	 */
	public static void setPageNo(int pageNo, HttpServletRequest request) {
		request.setAttribute("pageNo", pageNo);
	}

	/**
	 * @param request
	 * @return
	 */
	public static int getPageNo(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageNo");
	}

	/**
	 * @param pageSize
	 * @param request
	 */
	public static void setPageSize(int pageSize, HttpServletRequest request) {
		request.setAttribute("pageSize", pageSize);
	}

	/**
	 * @param request
	 * @return
	 */
	public static int getPageSize(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageSize");
	}

	/**
	 * @param e
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("exception", e);
		response.sendRedirect(ORSView.ERROR_CTL);
	}
	}
