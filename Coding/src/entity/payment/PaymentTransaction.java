package entity.payment;

public class PaymentTransaction {
	private final String errorCode;
	private final CreditCard card;
	private final String transactionId;
	private final String transactionContent;
	private final int amount;
	private final String createdAt;
	
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
