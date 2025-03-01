package com.skh;

 
public class Activity {
    private String activity;
    private String type;
    private String link;
    private String key;
    private Integer participants;
    private Double price;
    private Double accessibility;
    
    public Activity() {
    	
    }
    
	public Activity(String activity, String type, String link, String key, Integer participants, Double price,
			Double accessibility) {
		super();
		this.activity = activity;
		this.type = type;
		this.link = link;
		this.key = key;
		this.participants = participants;
		this.price = price;
		this.accessibility = accessibility;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getParticipants() {
		return participants;
	}

	public void setParticipants(Integer participants) {
		this.participants = participants;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(Double accessibility) {
		this.accessibility = accessibility;
	}

	@Override
	public String toString() {
		return "Activity [activity=" + activity + ", type=" + type + ", link=" + link + ", key=" + key
				+ ", participants=" + participants + ", price=" + price + ", accessibility=" + accessibility + "]";
	}
	
	
    
    
}
