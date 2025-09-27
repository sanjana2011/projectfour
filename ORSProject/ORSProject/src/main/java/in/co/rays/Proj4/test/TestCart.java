
package in.co.rays.Proj4.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj4.bean.CartBean;
import in.co.rays.Proj4.exception.ApplicationException;
import in.co.rays.Proj4.model.CartModel;

/**
 * Test class for CartModel. Demonstrates add, update, delete, findByPK, search,
 * and list operations.
 */

public class TestCart {

	public static void main(String[] args) throws ApplicationException {
		// SearchTest();
		TestFindByPk();
	}

	private static void TestFindByPk() throws ApplicationException {

		CartModel model = new CartModel();
		CartBean bean = model.findByPK(2);
		System.out.println(bean.getId());

	}

	private static void SearchTest() throws Exception {
		CartBean bean = new CartBean();
		CartModel model = new CartModel();

		List list = model.list();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CartBean) it.next();
			System.out.print(bean.getId());

		}
	}
}