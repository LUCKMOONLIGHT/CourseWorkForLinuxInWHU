package test;

import javax.swing.Icon;

public class ViewS {

	private int id;//景点唯一编号
	private String viewName;//景点名
	private int viewScores;//景点评分
	private Icon viewImage;//景点图片
	private String title;//景点标题
	private String detail;//景点详情
	private double price;//景点价格
	private String reasonOfRecommendation;//推荐理由
	private String viewTime;//景点时节
	private String viewKind;//景点类型
	
	public ViewS(){
		
	}
	
	
	/**
	 * 
	 * @param id 景点唯一编号
	 * @param viewName 景点名
	 * @param viewScores 景点评分
	 * @param viewImage 景点图片
	 * @param title 景点标题
	 * @param detail 景点详情
	 * @param price 景点价格
	 * @param reasonOfRecommendation 推荐理由
	 * @param viewTime 景点时节
	 * @param viewKind 景点类型
	 */
	public ViewS(int id, String viewName, int viewScores, Icon viewImage, String title, String detail, double price,
			String reasonOfRecommendation, String viewTime, String viewKind) {
		super();
		this.id = id;
		this.viewName = viewName;
		this.viewScores = viewScores;
		this.viewImage = viewImage;
		this.title = title;
		this.detail = detail;
		this.price = price;
		this.reasonOfRecommendation = reasonOfRecommendation;
		this.viewTime = viewTime;
		this.viewKind = viewKind;
	}



	public int getViewScores() {
		return viewScores;
	}
	public void setViewScores(int viewScores) {
		this.viewScores = viewScores;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public Icon getViewImage() {
		return viewImage;
	}
	public void setViewImage(Icon viewImage) {
		this.viewImage = viewImage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getReasonOfRecommendation() {
		return reasonOfRecommendation;
	}
	public void setReasonOfRecommendation(String reasonOfRecommendation) {
		this.reasonOfRecommendation = reasonOfRecommendation;
	}
	public String getViewTime() {
		return viewTime;
	}
	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}
	public String getViewKind() {
		return viewKind;
	}
	public void setViewKind(String viewKind) {
		this.viewKind = viewKind;
	}
	
	
	
	
	
}
