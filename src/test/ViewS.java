package test;

import javax.swing.Icon;

public class ViewS {

	private int id;//����Ψһ���
	private String viewName;//������
	private int viewScores;//��������
	private Icon viewImage;//����ͼƬ
	private String title;//�������
	private String detail;//��������
	private double price;//����۸�
	private String reasonOfRecommendation;//�Ƽ�����
	private String viewTime;//����ʱ��
	private String viewKind;//��������
	
	public ViewS(){
		
	}
	
	
	/**
	 * 
	 * @param id ����Ψһ���
	 * @param viewName ������
	 * @param viewScores ��������
	 * @param viewImage ����ͼƬ
	 * @param title �������
	 * @param detail ��������
	 * @param price ����۸�
	 * @param reasonOfRecommendation �Ƽ�����
	 * @param viewTime ����ʱ��
	 * @param viewKind ��������
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
