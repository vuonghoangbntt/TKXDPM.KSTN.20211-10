package entity.payment;

/**
 * This {@code PaymentTransaction} class represent Payment transaction entity
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class PaymentTransaction {
	private final String errorCode;
	private final CreditCard card;
	private final String transactionId;
	private final String transactionContent;
	private final int amount;
	private final String createdAt;

	/**
	 * phuong thuc khoi tao payment transaction
	 * @param errorCode ma loi cua giao dich
	 * @param card card giao dich
	 * @param transactionId id giao dich
	 * @param transactionContent noi dung giao dich
	 * @param amount tong so tien giao dich
	 * @param createdAt ngay tao giao dich
	 */
	public PaymentTransaction(String errorCode, CreditCard card, String transactionId, String transactionContent,
			int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
