package pojo;

import java.util.List;

public class subJsonClass {
	
	private List<webAutomation> webAutomation; // -> As it has 3 elements in it. so we are changesd to List<>
	private List<api> api;
	private List<mobile> mobile;
	
	
	public List<pojo.webAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<pojo.webAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<api> getApi() {
		return api;
	}
	public void setApi(List<pojo.api> api) {
		this.api = api;
	}
	public List<mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<pojo.mobile> mobile) {
		this.mobile = mobile;
	}
	
	
}
