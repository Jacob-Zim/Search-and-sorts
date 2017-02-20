
public class WordNum implements Comparable<WordNum> {
	public String word;
	public int num;
	public WordNum(String word, int num) {
		this.word = word;
		this.num = num;
	}
	@Override
	public int compareTo(WordNum other) {
		// TODO Auto-generated method stub
		return this.num - other.num;
	}

}
