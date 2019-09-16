public class QuizCard{

	private String question = null;
	private String answer = null;

	public QuizCard(String q, String a){
		this.question = q;
		this.answer = a;
	}

	public String getQuestion(){
		return this.question;
	}

	public String getAnswer(){
		return this.answer;
	}
}