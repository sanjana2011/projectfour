
package in.co.rays.Proj4.bean;

import java.util.Date;

public class CartBean extends BaseBean {

	private String customerName;
	private String product;
	private Date transactionDate;
	private int quantity;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getkey() {
		return id +"";
	}

	@Override
	public String getValue() {
		return product;
	}

	

}