package in.co.rays.Proj4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.Proj4.bean.BaseBean;
import in.co.rays.Proj4.bean.CartBean;
import in.co.rays.Proj4.exception.ApplicationException;
import in.co.rays.Proj4.model.CartModel;
import in.co.rays.Proj4.util.DataUtility;
import in.co.rays.Proj4.util.PropertyReader;
import in.co.rays.Proj4.util.ServletUtility;

//TODO: Auto-generated Javadoc
/**
 * Cart List functionality Controller. Performs operation for list, search and
 * delete operations of Cart
 * 
 * @author Sanjana Gangrade
 */
@WebServlet(name = "CartListCtl", urlPatterns = { "/ctl/CartListCtl" })
public class CartListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(CartListCtl.class);
	@Override
	protected void preload(HttpServletRequest request) {
	    CartModel model = new CartModel();
	    try {
	        List<CartBean> clist = model.list();
	        HashMap<String, String> productMap = new HashMap<>();

	        for (CartBean bean : clist) {
	            if (bean.getProduct() != null && !bean.getProduct().isEmpty()) {
	                productMap.put(bean.getProduct(), bean.getProduct());
	            }
	        }

	        request.setAttribute("productList", productMap); // ✅ Final list to be used in JSP

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/*
	 * @Override protected void preload(HttpServletRequest request) { CartModel
	 * model = new CartModel(); try { List<CartBean> clist = model.list();
	 * request.setAttribute("clist", clist);
	 * 
	 * 
	 * HashMap<String, String> cType = new HashMap<>(); for(CartBean bean : clist) {
	 * if(bean.getProduct() != null) { } cType.put(bean.getProduct(),
	 * bean.getProduct()); }
	 * 
	 * } } catch (Exception e) {
	 * 
	 * } }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#populateBean(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		CartBean bean = new CartBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));

		bean.setProduct(DataUtility.getString(request.getParameter("product")));
		System.out.println("product : "+request.getParameter("product"));

		bean.setTransactionDate(DataUtility.getDate(request.getParameter("transactionDate")));

		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		return bean;
	}

	/**
	 * Contains Display logics.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("CartListCtl doGet Start");
		preload(request);
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		CartBean bean = (CartBean) populateBean(request);
		CartModel model = new CartModel();

		try {
			List<CartBean> list = model.search(bean, pageNo, pageSize);
			List<CartBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);

			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Contains Submit logics.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("CartListCtl doPost Start");

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("pageSize")) : pageSize;

		CartBean bean = (CartBean) populateBean(request);
		CartModel model = new CartModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");

		try {
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.CART_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					CartBean deletebean = new CartBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Data is deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.CART_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.CART_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);

			if (!OP_DELETE.equalsIgnoreCase(op)) {
				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			e.printStackTrace();
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.CART_LIST_VIEW;
	}

}