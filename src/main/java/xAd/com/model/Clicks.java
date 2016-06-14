package xAd.com.model;

public class Clicks {
	public String unix_timestamp="";
	public String transaction_id="";
	public int clicks=0;
	

	public String getUnix_timestamp() {
		return unix_timestamp;
	}
	public void setUnix_timestamp(String unix_timestamp) {
		this.unix_timestamp = unix_timestamp;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
}
