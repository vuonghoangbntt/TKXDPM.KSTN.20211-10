package entity.payment;

/**
 * This {@code CreditCard} class represent credit card entity
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class CreditCard {
	private String cardCode;
	private String owner;
	private int cvvCode;
	private String dateExpired;

	/**
	 * phuong thuc khoi tao doi tuong credit card
	 * @param cardCode ma cua card
	 * @param owner chu so huu cua card
	 * @param cvvCode ma cvv
	 * @param dateExpired ngay het han cua card
	 */
	public CreditCard(String cardCode, String owner, int cvvCode, String dateExpired) {
		super();
		this.cardCode = cardCode;
		this.owner = owner;
		this.cvvCode = cvvCode;
		this.dateExpired = dateExpired;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getCvvCode() {
		return cvvCode;
	}

	public void setCvvCode(int cvvCode) {
		this.cvvCode = cvvCode;
	}

	public String getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
	}
}
